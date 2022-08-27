import { useState} from "react";
import { useNavigate } from "react-router-dom";
import bcrypt from 'bcryptjs'

const AuthModal = ({ setShowAuthModal, isSignUp }) => {

    const [email, setEmail] = useState(null);
    const [password, setPassword] = useState(null);
    const [confirmedPassword, setConfirmedPassword] = useState(null);
    const [error, setError] = useState(null);

    let navigate = useNavigate()

    const handleAuthModalClick = () => {
        setShowAuthModal(false)
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            if (isSignUp && password !== confirmedPassword) {
                setError("Passwords need to match");
            }

            console.log("email: " + email + ", password: " + password + ", confirm password: " + password);


            const emailLower = email.toLowerCase()
            const gamersEmailExists = "http://localhost:8080/api/gamer/emailExists?emailAddress="+emailLower

            fetch(gamersEmailExists, {
                method: "GET",
                headers: {
                   "Content-Type": "application/json"
                }})
            .then(res => res.json())
            .then(exists => {      
                console.log(exists)  
                var success = !exists
                console.log(success)
                if (success) {
                    
                    createNewGamer()

                    navigate('/onboarding')
                }
            })
            .catch(console.log)

            console.log("Post request for signup or login");
        } catch (error) {
            console.log(error)
        }

    }

    const createNewGamer = async (e) => {
        console.log("Creating user with email: " + email + " &, password: " + password );
        const passwordHashed = bcrypt.hashSync(password)
        const gamersEmailExists = "http://localhost:8080/api/gamer"

        fetch(gamersEmailExists, {
            method: "POST",
            headers: {
               "Content-Type": "application/json"
            },
            body: JSON.stringify({emailAddress:email,password:password})})
        .then(res => res.json())
        .then(data => {      
            console.log(data)  
        })
        .catch(console.log)

    }

    return (
        <div className="auth-modal">
            <div className="close-icon" onClick={handleAuthModalClick}>X</div>
            <h2>{isSignUp ? 'Sign Up' : 'Log In'}</h2>
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