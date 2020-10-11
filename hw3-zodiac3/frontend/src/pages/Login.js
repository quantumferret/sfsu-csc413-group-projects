import React from 'react';
import axios from 'axios';
import {Redirect} from 'react-router-dom';
import Jumbotron from 'react-bootstrap/Jumbotron';

const Login = ({ appUser, setAppUser, isAdmin, setManager }) => {
    const [username, setUsername] = React.useState('');
    const [password, setPassword] = React.useState('');
    const [adminStatus, setAdminStatus] = React.useState(undefined);
    const [error, setError] = React.useState('');

    const handleAuth = () => {
        const body = {
            username: username,
            password: password,
            adminStatus: adminStatus
        };
        axios.post('/api/login', body)
            .then((res) => {
                console.log(res.data); // dto coming from spark
                if (res.data.success) {
                    console.log('Worked!');
                    setAdminStatus(res.data.adminStatus);
                    if (res.data.adminStatus === true) {
                        setManager();
                        setAppUser(username); // triggers page change

                    }
                    // if (res.data.adminStatus === false) {
                    //     setAdminStatusFalse();
                    //     setAppUser(username); // triggers page change

                    // }
                    else {
                        setAppUser(username);
                    }
                    // console.log(isAdmin);
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
                <h1>Login</h1>
                <div>
                    <input
                        placeholder="username"
                        value={username}
                        onChange={e => setUsername(e.target.value)}
                    />
                </div>
                <div>
                    <input
                        placeholder="password"
                        type="password"
                        value={password}
                        onChange={e => setPassword(e.target.value)}
                    />
                </div>
                <div>
                    <button disabled={!username || !password} onClick={handleAuth}> Login</button>
                </div>
                {error && <strong>{error}</strong>}
            </div>
        </Jumbotron>
    );
};

export default Login;
