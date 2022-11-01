import { useState } from "react";
import { useCookies } from 'react-cookie'
import axios from 'axios'

const ChatInput = ({ gamer, clickedGame, retrieveMessages }) => {
    const [chatText, setChatText] = useState("")
    const [cookies] = useCookies(null)
    const [setError] = useState(null)

    const token = cookies.jwToken

    let axiosConfig = {
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + token
        }
    }
    const addMessage = async () => {

        const gamerId = gamer?.id
        console.log("Gamer ID ", gamerId)
        const messagePayload = {
            fromGamerId: gamerId,
            toGamerId: "40288086840027860184002c96bf0000",
            messageText: chatText
        }

        const json = JSON.stringify(messagePayload)

        await axios.post("http://localhost:8080/api/message", json, axiosConfig).then(() => {
            retrieveMessages()
            setChatText("")
        }).catch(setError)

    }

    return (
        <div className="chat-input-panel">
            <textarea value={chatText} onChange={(event) => setChatText(event.target.value)} />
            <button className="chat-submit-button" onClick={addMessage}>Submit</button>
        </div>
    )
}

export default ChatInput; 