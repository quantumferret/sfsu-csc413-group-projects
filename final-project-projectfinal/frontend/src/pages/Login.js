
import React from "react";
import "./Login.css";
import axios from 'axios';
import {Redirect} from 'react-router-dom';

// export default function Login() {
//   const [username, setUsername] = useState("");
//   const [password, setPassword] = useState("");

const Login = ({appUser, setAppUser}) => {
    /*Implementation of State Variables and Functions */
    const [username, setUsername] = React.useState('');
    const [password, setPassword] = React.useState('');
    const [error, setError] = React.useState('');

//   function handleSubmit(event) {
//     event.preventDefault();
//   }

    /*Authentication implementation*/
    const handleSubmit = () => {
        const body = {
            username: username,
            password: password,
        };

        axios.post('/api/login', body)
        .then((res) => {
            console.log(res.data); // Grabs DTO

            if(res.data.success){
                console.log("User successfully logged in.");
                setAppUser(username);
                
            
            }
            else {
                setError(res.data.error);
            }
        })
        .catch(() => {
            setError("An error occured while attempting to login.");
        });
    }; //---END OF HANDLEAUTH---

    if (appUser) {
      return <Redirect exact to={"/chat"}/>
    }

  return (
    <div className="topText">
      Login
      <div className="loginBox">
        {/* <form onSubmit={handleSubmit}> */}
          <div>
            <label>Username</label>
          </div>
          <div>
            <input
              type="text"
              name="username"
              className="username"
              placeholder="username"
              value={username}
              onChange={e => setUsername(e.target.value)}
            />
          </div>
          <div>
            <label>Password</label>
          </div>
          <div>
            <input
              type="password"
              name="password"
              className="password"
              placeholder="password"
              value={password}
              onChange={e => setPassword(e.target.value)}
            />
          </div>
          <div>
            <center>
              <button type="submit" className="loginButton"
                onClick ={handleSubmit} > Login
              </button>
              <div className="register">
                <label>Don't have account?</label>

                {/* TODO link to signuppage */}
                <div className="registerButton">Register</div>
                {error && <strong>{error}</strong>}
              </div>
            </center>
          </div>
        {/* </form> */}
      </div>
    </div>
  );
}
export default Login;
