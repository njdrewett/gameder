import { useState } from 'react'
import TinderCard from "react-tinder-card"
import Nav from '../components/Nav'
import ChatContainer from '../components/ChatContainer'

// stub for testing
import apexLegends from '../images/game-covers/apexlegends.jpg'
import fallguys from '../images/game-covers/fallguys.jpg'
import playerunknownbattlegrounds from '../images/game-covers/playerunknownbattlegrounds.jpg'
import callofduty from '../images/game-covers/callofduty.jpg'
import eldonring from '../images/game-covers/eldonring.jpg'
import amongus from '../images/game-covers/amongus.jpg'



const Dashboard = () => {
    const db = [
        {
            name: 'APEX Legends',
            url: `${apexLegends}`
        },
        {
            name: 'Fall Guys',
            url: `${fallguys}`
        },
        {
            name: 'Player Unknown Battlegrounds',
            url: `${playerunknownbattlegrounds}`
        },
        {
            name: 'Call Of Duty',
            url: `${callofduty}`
        },
        {
            name: 'Eldon Ring',
            url: `${eldonring}`
        },
        {
            name: 'Among Us',
            url: `${amongus}`
        }

    ]

    const characters = db
    const [lastDirection, setLastDirection] = useState()

    const swiped = (direction, nameToDelete) => {
        console.log('removing: ' + nameToDelete)
        setLastDirection(direction)
    }

    const outOfFrame = (name) => {
        console.log(name + ' left the screen!')
    }


    return (
        <div>
            <Nav minimal={true}
                setShowAuthModal={() => { }}
                showAuthModal={false}
                setIsSignUp={false}
            />
            <div className="dashboard-panel">

                <ChatContainer />
                <div className="swipe-container">
                    <div className="card-container">
                        {characters.map((character) =>
                            <TinderCard
                                className='swipe'
                                key={character.name}
                                onSwipe={(dir) => swiped(dir, character.name)}
                                onCardLeftScreen={() => outOfFrame(character.name)}>
                                <div style={{ backgroundImage: 'url(' + character.url + ')' }} className='card'>
                                    <h3>{character.name}</h3>
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
    )
}

export default Dashboard 