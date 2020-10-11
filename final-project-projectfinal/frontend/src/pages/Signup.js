import axios from 'axios';
import React, {Fragment} from 'react';
import "./Signup.css"
import {Redirect} from 'react-router-dom';

const Signup = ({appUser, setAppUser}) => {
    /*Implementation of State Variables and Functions */
    const [username, setUsername] = React.useState('');
    const [password, setPassword] = React.useState('');
    const [error, setError] = React.useState('');
    

    /*Authentication implementation*/
    const signupAuth = () => {
        const body = {
            username: username,
            password: password,
        };

        axios.post('/api/signup', body)
        .then((res) => {
            console.log(res.data); // Grabs DTO

            if(res.data.success){
                console.log("User successfully signed up.");
                setAppUser(username);
                
            }
            else {
                setError(res.data.error);
            }
        })
        .catch(() => {
            setError("An error occured while attempting to register.");
        });
    }; //---END OF HANDLEAUTH---

    if (appUser) {
        return <Redirect exact to={"/login"}/>
    }

    /*UI*/
    return(
        <Fragment>
            <h1 id="SignupHeader">Signup</h1>

            <div id = "SignupBox">

                <div id = "username">
                    <input value={username}
                    onChange= {e => setUsername(e.target.value)}
                    placeholder="Username"
                    />
                </div>
                <div id= "password">
                    <input
                    type="password" //Unable to see password
                    value={password}
                    placeholder="Password"
                    onChange= {e => setPassword(e.target.value)}
                    />
                </div>
                
                <div>
                    <button name="button" disabled = {!username || !password}
                    onClick= {signupAuth}>Sign Up!</button>
                </div>
                {error && <strong>{error}</strong>}
            </div> 
        </Fragment>
    );

}; //---END OF SIGNUP---

export default Signup;