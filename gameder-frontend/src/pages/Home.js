import Nav from "../components/Nav"
import { useState } from 'react'
import { useCookies } from 'react-cookie'
import AuthModal from "../components/AuthModal";

const Home = () => {


    const [showAuthModal, setShowAuthModal] = useState(false)
    const [isSignUp, setIsSignUp] = useState(true)
    const [cookies,setCookie,removeCookie] = useCookies()
 
    const authToken = cookies.jwToken
    const BACKEND_URI_API = process.env.BACKEND_URI_API

    console.log("backend : ", BACKEND_URI_API)
 
    const handleHomeClick = () => {
        if(authToken !== undefined) {
            console.log("Removing")
            removeCookie("userId")
            removeCookie("emailAddress")
            removeCookie("jwToken")
            window.location.reload()   
            return      
    
        }
        setShowAuthModal(true)
        setIsSignUp(true)        
    }

    return (
        <div className="overlay"> 
        <Nav
            minimal={false} 
            authToken={authToken} 
            setShowAuthModal={setShowAuthModal}  
            showAuthModal={showAuthModal} 
            setIsSignUp={setIsSignUp}
            />
        <div className="home">
            <h1 className="main-title">Game Right</h1>
            <button className="home-button" onClick={handleHomeClick}>
                { authToken ? 'Signout' : 'Sign Up'}
            </button>
         </div>
         {showAuthModal && (
            <AuthModal setShowAuthModal={setShowAuthModal} 
            isSignUp={isSignUp}/> )}
       </div>
    )
}
export default Home;