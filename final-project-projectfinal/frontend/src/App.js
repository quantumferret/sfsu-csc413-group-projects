import React from 'react';
import './App.css';
import Chat from './pages/Chat';
import axios from 'axios';
import Signup from "./pages/Signup";
import Login from "./pages/Login";
import { Switch, Route, Link } from 'react-router-dom';

const App = ({}) => {

  const [appUser, setAppUser] = React.useState(null);
  const [messages, setMessages] = React.useState([]); //list of messages
  
  //Fetches all of the messages from the backend when the App component first mounts
  //The messages are added to the state variable 'messages' and passed in as prop to the Chat
  React.useEffect(() => {
    
    console.log("Fetching messages...");
    axios.get('/api/fetchmessages')
      .then((res) => {
        console.log(res.data);
        setMessages(res.data);
      })
      .catch(() => console.log("Error fetching messages."));

  }, []);

  return (

    <div className='App'>
      { !appUser &&
        <nav>
        <Link to="/Login">Login </Link>
        <Link to ="/Signup">Signup </Link>
      </nav>

      }
      
      <Switch>
        <Route path ="/login">
          <Login appUser={appUser} setAppUser={setAppUser}/>
        </Route>
        <Route path ="/signup">
          <Signup appUser={appUser} setAppUser={setAppUser}/>
        </Route>
        <Route path ="/chat">
          {appUser && <Chat appUser={appUser} messages={messages} setMessages={setMessages}/>}
        </Route>
      </Switch>
    </div>

  );
};

export default App;
