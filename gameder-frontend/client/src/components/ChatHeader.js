import { useEffect, useState } from 'react'
import defaultProfileData from '../images/placeholder.png'
import { useCookies } from 'react-cookie'

const ChatHeader = ({gamer}) => {
    const [error, setError] = useState(null);
    const [cookies, setCookie, removeCookie] = useCookies(null)
    const [profileData, setProfileData] = useState({
        profileData:''
    })
 
    useEffect(() => {
        const retrieveGamerProfile = () => {
            console.log("Gamer profile: " , gamer)
            if(gamer !== null) {
    
                console.log("Getting profile link URL")
            const profileLink = "http://localhost:8080/api/gamer/profileImage/"+ gamer?.id
            fetch(profileLink, {              
                method: "GET",
                headers: {
                    "Authorization": "Bearer " + cookies.jwToken
                }
            }).then((response) => {
                response.arrayBuffer().then(function(buffer) {
                    const url = window.URL.createObjectURL(new Blob([buffer]));

                    setProfileData(url )
                })
            }).catch(setError)
        }
        }
    
        retrieveGamerProfile()
    }, [gamer])


    const logout = () => {
        removeCookie("userId")
        removeCookie("emailAddress")
        removeCookie("jwToken")
        window.location.reload()
    }
        return (
            <div className="chat-container-header">
                <div className="profile">
                    <div className="image-container">
                        <img src={  profileData.profileData != '' ? profileData : defaultProfileData } alt="Profile Image Preview"/>
                    </div>
                    <h2>{gamer?.displayName}</h2>    
                </div>
                <i className="log-out-icon" onClick={logout}>Logout</i>
            </div>
    )
}

export default ChatHeader; 