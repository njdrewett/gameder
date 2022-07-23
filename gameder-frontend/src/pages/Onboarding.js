
import Nav from "../components/Nav"
import { useState } from 'react'
import defaultProfileData from '../images/placeholder.png'


const Onboarding = () => {

    const [formData, setFormData] = useState({
        displayName: '',
        dateOfBirth: '',
        emailAddress:'',
        telephoneNumber:'',
        introductionText:''        
    })

    const [profileData, setProfileData] = useState({
        profileData:''
    })

    const handleSubmit = () => {
        console.log("Submitted")
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
                        <label htmlFor="emailAddress">Email Address:</label>
                        <input id="emailAddress"
                            type="email"
                            name="emailAddress"
                            placeholder="Email Address"
                            required={true}
                            value={formData.emailAddress}
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
                            value={formData.profileImage}
                            onChange={(e) => setProfileData(URL.createObjectURL(e.target.files[0]))}
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