
import blackLogo from '../images/logo-black.png'
import colourLogo from '../images/logo-colour.png'



const Nav = ({ minimal, setShowAuthModal, showAuthModal , setIsSignUp}) => {
    const handleLoginClick = () => {
        setShowAuthModal(true)
        setIsSignUp(false)
    }
    
const authToken = false;

    return (
        <nav>
            <div className="logo-container">
                <img className="logo" src={minimal ? blackLogo : colourLogo} />
            </div>

            {!authToken && !minimal && 
                <button 
                    className="nav-button" 
                    onClick={handleLoginClick}
                    disabled={showAuthModal}>Log In</button>
                    
                }
        </nav>
    )
}

export default Nav; 