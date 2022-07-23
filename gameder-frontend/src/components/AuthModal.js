import { useState } from "react";

const AuthModal = ({setShowAuthModal, isSignUp}) => {

    const [ email, setEmail ] = useState(null);
    const [ password, setPassword ] = useState(null);
    const [ confirmedPassword, setConfirmedPassword ] = useState(null);
    const [ error , setError ] = useState(null);


    console.log("email: " + email + ", password: " + password + ", confirm password: " + password);

    const handleAuthModalClick = () => {
        setShowAuthModal(false)
    }

    const handleSubmit = (e) => {
        e.preventDefault();
        try {
            if(isSignUp && password !== confirmedPassword) {
                setError("Passwords need to match");
            }
            console.log("Post request for signup or login");
        }catch(error) {
            console.log(error)
        }

    }

    return (
        <div className="auth-modal">
            <div className="close-icon" onClick={handleAuthModalClick}>X</div>
            <h2>{isSignUp? 'Sign Up' : 'Log In'}</h2>
            <form onSubmit={handleSubmit}>
                <input 
                    type="email" 
                    id="email" 
                    name="email" 
                    placeHolder="email" 
                    required={true}
                    onChange={(e) => setEmail(e.target.value)} />
                <input 
                    type="password" 
                    id="password" 
                    name="password" 
                    placeHolder="password" 
                    required={true}
                    onChange={(e) => setPassword(e.target.value)} />
                
               {isSignUp && <input 
                    type="password" 
                    id="password-confirm" 
                    name="password-confirm" 
                    placeHolder="password confirm" 
                    required={true}
                    onChange={(e) => setConfirmedPassword(e.target.value)} />}
                <input class="auth-modal-submit" type="submit" />
                <p>{error}</p>

           </form>
   </div>
    )
}

export default AuthModal 