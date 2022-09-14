import { useState } from "react";
import { useNavigate } from "react-router-dom";
import bcrypt, { hashSync } from 'bcryptjs'
import { useCookies } from 'react-cookie'

const AuthModal = ({ setShowAuthModal, isSignUp }) => {

    const [email, setEmail] = useState(null);
    const [password, setPassword] = useState(null);
    const [confirmedPassword, setConfirmedPassword] = useState(null)
    const [error, setError] = useState(null);
    const [cookie, setCookie, removeCookie] = useCookies(null)
    const [userId, setUserId] = useState(null)
    const [jwToken, setJwToken] = useState(null)


    let navigate = useNavigate()

    const handleAuthModalClick = () => {
        setShowAuthModal(false)
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
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

            console.log("email: " + email + ", password: " + password + ", confirm password: " + password);

            const emailLower = email.toLowerCase()
            const passwordHashed = hashSync(password,10)
            const signup = "http://localhost:8080/api/auth/signup";

            fetch(signup, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({emailAddress: emailLower , password: passwordHashed})
            })
                .then(res => res.json())
                .then(exists => {
                    console.log(exists);
                    var success = !exists;
                    console.log(success);
                    if (success) {

    //                    createNewGamer(emailLower, passwordHashed);

                        navigate('/onboarding');
                    } else {
                        console.log("User email already exists");
                        setError("User email already exists");
                    }
                })
                .catch(console.log);

            console.log("Post request for signup or login");
        }

        function performLogin() {
            if (password !== confirmedPassword) {
                setError("Passwords need to match");
            }

            console.log("email: " + email + ", password: " + password + ", confirm password: " + password);

            const emailLower = email.toLowerCase()
            const passwordHashed = hashSync(password,10)
            const gamersEmailExists = "http://localhost:8080/api/gamer/emailExists?emailAddress=" + emailLower;

            fetch(gamersEmailExists, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json"
                }
            })
                .then(res => res.json())
                .then(exists => {
                    console.log(exists);
                    var success = exists;
                    console.log(success);
                    if (success) {

                        let created = loginGamer(emailLower,passwordHashed);

                        if(created) {
                            navigate('/dashboard');
                        } else {
                            console.log("Error creating gamer")
                        }
                    } else {
                        console.log("User email not found");
                        setError("User email not found");
                    }
                })
                .catch(console.log);

            console.log("Post request for signup or login");
        }

    }

    const createNewGamer = async (emailAddress, passwordHashed) => {
        console.log("Creating user with email: " + emailAddress + " &, password: " + password);

        //should always store a hashed password. 
        const gamersEmailExists = "http://localhost:8080/api/gamer"
        let success = false
        fetch(gamersEmailExists, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ emailAddress: email, password: passwordHashed })
        })
            .then(res => res.json())
            .then(data => {
                console.log(data)
                setCookie('email', emailAddress)
                setCookie('userId', data.id)
                success = true
                console.log(data)
            })
            .catch(console.log)
        return success
    }

    const loginGamer = async (emailAddress,passwordHashed) => {
        console.log("Creating user with email: " + emailAddress + " &, password: " + password);

        //should always store a hashed password. 
        const gamersEmailExists = "http://localhost:8080/api/gamer"
        let success = false;
        fetch(gamersEmailExists, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ emailAddress: email, password: passwordHashed })
        })
            .then(res => res.json())
            .then(data => {
                console.log(data)
                success = data
                setCookie('email', emailAddress)
                setCookie('userId', data.id)
                console.log(data)
            })
            .catch(console.log)

        return success
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