import ChatHeader from "./ChatHeader";
import ChatDisplay from "./ChatDisplay";
import MatchesDisplay from "./MatchesDisplay";

const ChatContainer = ( { gamer , matchedGame }) => {
    return (
            <div className="chat-container">
                <ChatHeader gamer={gamer} />

                <div>
                    <button className="option">Matches</button>
                    <button className="option">Chat</button>

                </div>
                <MatchesDisplay matchedGame={matchedGame}/>

                <ChatDisplay />
            </div>
    )
}

export default ChatContainer; 