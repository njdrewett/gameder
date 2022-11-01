import Chat from "./Chat";
import ChatInput from "./ChatInput";
import { useEffect, useState } from 'react'
import { useCookies } from 'react-cookie'
import axios from 'axios'

const ChatDisplay = ({gamer, clickedGame}) => {

    const [setError] = useState(null)
    const [cookies] = useCookies(null)
    const [messages, setMessages] = useState(null)

    const retrieveMessages = async() => {

        console.log("RetreiveMessages Called" )
        if(clickedGame !== null) {
        const userId = cookies.userId

        const token = cookies.jwToken
        const headers = {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + token
          }

        const getGamersMessagesLink = "http://localhost:8080/api/message/findByGamer/" + userId
            await axios({
                method: "get",
                url: getGamersMessagesLink,
                headers:  headers,
            } ).then((response) => {                
                setMessages(response.data)
                setError(null)
            }).catch(setError)
        }
    }

    const formattedMessages = []

    messages?.forEach(message => {
        const formattedMessage = {}
        formattedMessage['name'] = message?.id
        formattedMessage['message'] = message.messageText
        formattedMessage['timestamp'] = message.creationDate
        formattedMessages.push(formattedMessage)
    });

    const descendingMessages = formattedMessages?.sort((a,b) => a.timestamp.localeCompare(b.timestamp))

    useEffect(() => {
        retrieveMessages()
    }, [clickedGame])

    return (
        <div className="chat-display-panel">
            <Chat descendingMessages={descendingMessages}/>
            <ChatInput gamer={gamer} clickedGame={clickedGame} retrieveMessages={retrieveMessages} />
        </div>
    )
}

export default ChatDisplay; 