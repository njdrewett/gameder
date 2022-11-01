
const Chat = ({ descendingMessages }) => {

    return (
        <>
            <div className="chat-display">
                {descendingMessages?.map((message, index) => (
                    <div key={index}>
                        <div className="chat-message-header">
                            <p>
                                {message.name}
                            </p>

                        </div>
                        <p>
                            {message.message}
                        </p>

                    </div>
                ))}
            </div>
        </>
    )
}

export default Chat; 