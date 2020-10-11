import React from 'react';
import {Redirect} from 'react-router-dom';
import Jumbotron from 'react-bootstrap/Jumbotron';
import axios from 'axios';


const Signup = ({ appUser, setAppUser, setManager }) => {
    const [username, setUsername] = React.useState('');
    const [password, setPassword] = React.useState('');
    const [adminStatus, setAdminStatus] = React.useState(undefined);
    const [error, setError] = React.useState('');

    const handleAuth = () => {
        const body = {
            username: username,
            password: password,
            adminStatus: adminStatus,
        };
        axios.post('/api/signup', body)
            .then((res) => {
                console.log(res.data); // dto coming from spark
                if (res.data.success) {
                    // console.log('Worked!');
                    // if (res.data.adminStatus === true) {
                    //     setAdminStatusTrue();
                    // }
                    // if (res.data.adminStatus === false) {
                    //     setAdminStatusFalse();
                    // }
                    // console.log(isAdmin);
                    if (res.data.adminStatus === true) {
                        setManager(true);
                        setAppUser(username);

                    }
                    setAppUser(username); // triggers page change
                } else {
                    setError(res.data.error);
                }
            })
            .catch(() => {
                setError("Failed to register");
            });
    };

    if (appUser) {
        if (adminStatus === true) {
            return <Redirect exact to={"/storemanagement"}/>
        }
        else {
            return <Redirect exact to={"/store"}/>
        }
    }

    return (
        <Jumbotron>
            <div className={"text-center"}>
                <h1>Sign Up</h1>
                <div>
                    <input
                        placeholder = "username"
                        value = {username}
                        onChange = { e => setUsername(e.target.value) }
                    />
                </div>
                <div>
                    <input
                        placeholder = "password"
                        value={password}
                        onChange={e => setPassword(e.target.value)}
                    />
                </div>
                <div>
                    <input
                        type = "radio"
                        value = {adminStatus}
                        name = "isAdmin"
                        onChange={() => setAdminStatus(false)}
                    />Customer
                    <input
                        type = "radio"
                        value ={adminStatus}
                        name = "isAdmin"
                        onChange={() => setAdminStatus(true)}
                    />Store Manager
                </div>
                <div>
                    <button disabled={!username || !password || adminStatus === undefined}
                        onClick={handleAuth}> Sign Up</button>
                </div>
                {error && <strong>{error}</strong>}
            </div>
        </Jumbotron>
    );
};

export default Signup;