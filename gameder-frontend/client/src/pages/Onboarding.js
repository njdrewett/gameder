
import Nav from "../components/Nav"
import { useState } from 'react'
import defaultProfileData from '../images/placeholder.png'
import { useCookies } from 'react-cookie'
import { useNavigate } from "react-router-dom";

const Onboarding = () => {
    
    let navigate = useNavigate()

    const [error, setError] = useState(null);
    const [cookies, setCookie, removeCookie] = useCookies(null)
    const [formData, setFormData] = useState({
        id: cookies.userId,
        displayName: '',
        dateOfBirth: '',
        telephoneNumber:'',
        introductionText:''
    })

    const [profileData, setProfileData] = useState({
        profileData:''
    })
 
    const [profileFile, setProfileFile] = useState({
        profileFile:null
    })

    
    const handleSubmit = async (e) => {
        e.preventDefault()
        console.log("Submitted")

        const updateLink = "http://localhost:8080/api/gamer/update"
    
        try {

            let submitFormData = new FormData();
            const json = JSON.stringify({
                id: formData.id ,
                displayName: formData.displayName,
                dateOfBirth: formData.dateOfBirth,
                telephoneNumber: formData.telephoneNumber,
                introductionText: formData.introductionText
            })
            const blob = new Blob([json], {
                type: 'application/json'
              });
              
              submitFormData.append('updateGamerRequest',blob)
              submitFormData.append('profileImage',profileFile )

        // Attempt to signup
        fetch(updateLink, {
            method: "POST",
            headers: {
                "Authorization": "Bearer " + cookies.jwToken
            },
            body: submitFormData
        })
        .then(res => res.json())
        .then(res => {
            console.log(res)
            if(res.success) {
                console.log("signup success")
                navigate('/dashboard')
            } else {
                setError(res.errorMessage)
            }
        })
        .catch(console.log);
    } catch (error) {
        console.log(error)
    }

    }
    
    const handleChange = (e) => {
        console.log('e',e)
        const value = e.target.value
        const name = e.target.name

        console.log("handleChange: name=" + name, "value:"+ value);

 
        setFormData((prevState) => ({
            ...prevState,
            [name]: value
        }))
    }

    return (
        <>
            <Nav minimal={true}
                setShowAuthModal={() => { }}
                showAuthModal={false}
                setIsSignUp={false}
            />
            <div className="onboarding-panel">
                <h2>CREATE ACCOUNT</h2>
                <form onSubmit={handleSubmit}>
                    <section >
                        <label htmlFor="displayName">First Name:</label>
                        <input id="displayName"
                            type="text"
                            name="displayName"
                            placeholder="Display Name"
                            required={true}
                            value={formData.displayName}
                            onChange={handleChange}
                        />

                        <label htmlFor="dateOfBirth">Date of Birth</label>
                        <input id="dateOfBirth"
                            type="date"
                            name="dateOfBirth"
                            placeholder="Date Of Birth"
                            required={true}
                            value={formData.dateOfBirth}
                            onChange={handleChange}
                        />
                        <label htmlFor="telephoneNumber">Telephone Number:</label>
                        <input id="telephoneNumber"
                            type="tel"
                            name="telephoneNumber"
                            placeholder="Telephone Number"
                            required={false}
                            value={formData.telephoneNumber}
                            onChange={handleChange}
                        />
                        <label htmlFor="introductionText">Introduction Text:</label>
                        <input id="introductionText"
                            type="text"
                            name="introductionText"
                            placeholder="Introduction Text"
                            required={false}
                            value={formData.introductionText}
                            onChange={handleChange}
                        />

                        <input type ="submit" />
                    </section>
                    <section>
                        <label htmlFor="profileImage">Profile Image:</label>
                        <input id="profileImage"
                            type="file"
                            name="profileImage"
                            placeholder="Profile Image"
                            required={false}
                            value={formData.profileImageFile}
                            onChange={(e) => {
                                setProfileData(URL.createObjectURL(e.target.files[0]) )
                                setProfileFile(e.target.files[0]) 
                            }}
                        />
                        
                        <div className="profile-container">
                             <img src={  profileData.profileData != '' ? profileData : defaultProfileData } alt="Profile Image Preview"/>
                        </div>

                    </section>

                </form >

            </div>
        </>

    )
}

export default Onboarding