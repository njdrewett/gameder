import Nav from "../components/Nav"
import { useState } from 'react'
import AuthModal from "../components/AuthModal";

const Home = () => {

    const authToken = false;

    const [showAuthModal, setShowAuthModal] = useState(false)
    const [isSignUp, setIsSignUp] = useState(true)

    const handleHomeClick = () => {
        console.log('home clicked')
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
                {authToken ? 'Signout' : 'Sign Up'}
            </button>
         </div>
         {showAuthModal && (
            <AuthModal setShowAuthModal={setShowAuthModal} 
            isSignUp={isSignUp}/> )}
       </div>
    )
}
export default Home;