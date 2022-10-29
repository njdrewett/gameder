import ChatHeader from "./ChatHeader";
import ChatDisplay from "./ChatDisplay";
import MatchesDisplay from "./MatchesDisplay";
import { useState } from "react";

const ChatContainer = ( { gamer , matchedGames }) => {

    const [clickedGame, setClickedGame] = useState(null)

    console.log("clikced game " , clickedGame)
    return (
            <div className="chat-container">
                <ChatHeader gamer={gamer} />

                <div>
                    <button className="option" onClick={() => setClickedGame(null)}>Matches</button>
                    <button className="option" disabled={!clickedGame} >Chat</button>

                </div>
                {!clickedGame && <MatchesDisplay matchedGames={matchedGames} setClickedGame={setClickedGame} />}

                {clickedGame  && <ChatDisplay />}
            </div>
    )
}

export default ChatContainer; 