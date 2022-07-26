import { useState } from "react";

const ChatInput = () => {
    const [chatText, setChatText] = useState(null)

    return (
        <div className="chat-input-panel">
            <textarea value={chatText} onChange={(event) => setChatText(event.target.value)}/>
            <button className="chat-submit-button">Submit</button>
        </div>
        
    )
}

export default ChatInput; 