import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { hashSync } from 'bcryptjs'
import { useCookies } from 'react-cookie'

const AuthModal = ({ setShowAuthModal, isSignUp }) => {

    const [email, setEmail] = useState(null);
    const [password, setPassword] = useState(null);
    const [confirmedPassword, setConfirmedPassword] = useState(null)
    const [error, setError] = useState(null);
    const [cookies, setCookie, removeCookie] = useCookies(null)
    const [userId, setUserId] = useState(null)
    const [jwToken, setJwToken] = useState(null)

 
    let navigate = useNavigate()

    const handleAuthModalClick = () => {
        setShowAuthModal(false)
    }

    const handleSubmit = async (e) => {
        e.preventDefault()
        try {
            if (isSignUp) {
                performSignup()
            } else {
                performLogin()
            }
        } catch (error) {
            console.log(error)
        }


        function performSignup() {
            if (password !== confirmedPassword) {
                setError("Passwords need to match");
            }

            const emailLower = email.toLowerCase()
            const passwordHashed = hashSync(password,10)
            const signup = "http://localhost:8080/api/auth/signup";
    
            // Attempt to signup
            fetch(signup, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({emailAddress: emailLower , password: passwordHashed})
            })
                .then(res => res.json())
                .then(res => {
                    if(res.signupSuccessful) {
                        setUserId(res.userId)
                        setJwToken(res.jwToken)
                        setCookie("userId",res.userId)
                        setCookie("emailAddress",emailLower)
                        setCookie("jwToken", res.jwToken)
                        navigate('/onboarding')
                    } else {
                        setError(res.errorMessage)
                    }
                })
                .catch(console.log);
        }

        function performLogin() {

            const emailLower = email.toLowerCase()
 
            const login = "http://localhost:8080/api/auth/login";
 
            // Attempt to signup
            fetch(login, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({emailAddress: emailLower , password: password})
            })
                .then(res => res.json())
                .then(res => {
                    if(res.loginSuccessful) {
                        console.log("login success")
                        setUserId(res.userId)
                        setJwToken(res.jwToken)
                        setCookie("userId",res.userId)
                        setCookie("emailAddress",emailLower)
                        setCookie("jwToken", res.jwToken)
                        navigate('/dashboard')
                    } else {
                        setError(res.errorMessage)
                    }
                     
                })
                .catch(console.log);
            console.log("Post request for login");
        }

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
                <p></p>
            </form>
        </div>
    )
}

export default AuthModal 