import { useEffect, useState } from 'react'
import { useCookies } from 'react-cookie'
import axios from 'axios'
const MatchesDisplay = ( {matchedGames, setClickedGame} ) => {
    const [gamers, setGamers] = useState(null)
    const [error, setError] = useState(null)
    const [cookies, setCookie, removeCookie] = useCookies(null)

    const retrieveGamers = async() => {

        console.log("retrieveGamers Called" )
        if(matchedGames !== null) {
        const userId = cookies.userId

        const token = cookies.jwToken
        const headers = {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + token
          }
        console.log("retrieveGamers for " + userId)

        const getGamersLink = "http://localhost:8080/api/gamer/find"
            await axios({
                method: "post",
                url: getGamersLink,
                headers:  headers,
                data: JSON.stringify({excludeId: userId})
            } ).then((response) => {
                setGamers(response.data)
                setError(null)
            }).catch(setError)
        }
    }

    console.log("matches display " + matchedGames)

    useEffect(() => {
      //  retrieveGamers();
    }, [matchedGames])
    
    return (        
        <div className="matches-display-panel">Game
            {matchedGames?.map((match, index) => (
                <div key={{index}} className="match-card" onClick={() => setClickedGame(match)}>
                    <div class="image-container">
                        <img src={match?.url} alt={match?.name} />
                    </div>
                    <h3>{match?.name}</h3>
                </div>
                ))}
        </div>
    )
}

export default MatchesDisplay; 