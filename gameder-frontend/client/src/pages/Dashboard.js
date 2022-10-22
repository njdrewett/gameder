import { useEffect, useState } from 'react'
import TinderCard from "react-tinder-card"
import Nav from '../components/Nav'
import ChatContainer from '../components/ChatContainer'
import { useCookies } from 'react-cookie'
import axios from 'axios'

// stub for testing
import apexLegends from '../images/game-covers/apexlegends.jpg'
import fallguys from '../images/game-covers/fallguys.jpg'
import playerunknownbattlegrounds from '../images/game-covers/playerunknownbattlegrounds.jpg'
import callofduty from '../images/game-covers/callofduty.jpg'
import eldonring from '../images/game-covers/eldonring.jpg'
import amongus from '../images/game-covers/amongus.jpg'



const Dashboard = () => {

    const [gamer, setGamer] = useState(null)
    const [error, setError] = useState(null)
    const [cookies, setCookie, removeCookie] = useCookies(['user'])
    const [matchedGame, setMatchedGame] = useState(null)


    useEffect(() => {

        const retrieveGamer = async () => {

            const userId = cookies.userId
    
            console.log("retrieveGamer " + userId)
    
            const getGamerByIdLink = "http://localhost:8080/api/gamer/" + userId
            await axios.get(getGamerByIdLink, {              
                headers: {
                    "Authorization": "Bearer " + cookies.jwToken
                }
            }).then((response) => {
                setGamer(response.data)
                setError(null)
            }).catch(setError)
        }
    
        retrieveGamer()
    }, [])


    useEffect(() => {
        console.log("inner matched game " + matchedGame)
    }, [matchedGame])

    const db = [
        {
            id:1,
            name: 'APEX Legends',
            url: `${apexLegends}`
        },
        {
            id:2,
            name: 'Fall Guys',
            url: `${fallguys}`
        },
        {
            id:3,
            name: 'Player Unknown Battlegrounds',
            url: `${playerunknownbattlegrounds}`
        },
        {
            id:4,
            name: 'Call Of Duty',
            url: `${callofduty}`
        },
        {
            id: 5,
            name: 'Eldon Ring',
            url: `${eldonring}`
        },
        {
            id: 6,
            name: 'Among Us',
            url: `${amongus}`
        }
    ]



    const games = db

    const [lastDirection, setLastDirection] = useState()


    const updateGameMatches = (swipedGame) => {
        console.log("updating game match" )
        setMatchedGame(swipedGame)
        console.log('Matched game ' + swipedGame?.name)
    }

    const swiped = (direction, swipedGame) => {
        if(direction === 'right') {
            updateGameMatches(swipedGame)
        }
        setLastDirection(direction)
    }

    const outOfFrame = (name) => {
        console.log(name + ' left the screen!')
    }

    return (
        <>
        {gamer &&
        <div>
            <Nav minimal={true}
                setShowAuthModal={() => { }}
                showAuthModal={false}
                setIsSignUp={false}
            />
            <div className="dashboard-panel">

                <ChatContainer gamer={gamer} matchedGame={matchedGame}/>
                <div className="swipe-container">
                    <div className="card-container">
                        {games.map((game) =>
                            <TinderCard
                                className='swipe'
                                key={game.name}
                                onSwipe={(dir) => swiped(dir, game)}
                                onCardLeftScreen={() => outOfFrame(game.name)}>
                                <div style={{ backgroundImage: 'url(' + game.url + ')' }} className='card'>
                                    <h3>{game.name}</h3>
                                </div>
                            </TinderCard>
                        )}
                        <div className="swipe-info">
                            {lastDirection ? <p>Swiped {lastDirection}</p> : <p />}
                        </div>
                    </div>
                </div>
            </div>
        </div>
}
        </>
    )
}

export default Dashboard 