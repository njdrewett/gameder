import { useEffect, useState } from 'react'
import { useCookies } from 'react-cookie'
import axios from 'axios'
const MatchesDisplay = ( {matchedGame} ) => {
    const [gamers, setGamers] = useState(null)
    const [error, setError] = useState(null)
    const [cookies, setCookie, removeCookie] = useCookies(['user'])

    const retrieveGamers = async() => {

        console.log("retrieveGamers Called" )
        if(matchedGame !== null) {
        const userId = cookies.userId

        console.log("retrieveGamers for " + userId)

        const getGamersLink = "http://localhost:8080/api/gamer/all"
            await axios.get(getGamersLink, {              
            headers: {
                "Authorization": "Bearer " + cookies.jwToken
            }
            }).then((response) => {
                setGamers(response.data)
                setError(null)
            }).catch(setError)
        }
    }

    console.log(matchedGame?.name)
    console.log(gamers)

    useEffect(() => {
        retrieveGamers();
    }, [matchedGame])
    
    return (        
        <div className="matches-display-panel">Game
            <h2>{matchedGame?.name}</h2>
        </div>
    )
}

export default MatchesDisplay; 