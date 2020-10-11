import React, { Component } from 'react';
import MessageTextBox from '../chat-components/MessageTextBox';
import MessageContainer from '../chat-components/MessageContainer';
import { Switch, Route, Link } from 'react-router-dom';
import './Chat.css';

const ws = new WebSocket('ws://localhost:4000/ws');

const Chat = ({appUser, messages, setMessages}) => {

    
    const [message, setMessage] = React.useState('');   //Single message

    // Updates the list of messages when the user sends a new message
    React.useEffect(() => {
        
        ws.onmessage = (stringMessage) => {
          console.log("Websocket received a new message. Updating the list...");
          console.log("Message: " + stringMessage.data);
          setMessages(() => {
            // Copys the old message list
            const newMessages = messages.slice();
            // Pushes the new message onto the list
            newMessages.push(JSON.parse(stringMessage.data));
            // Sets messages to the updated list
            return newMessages;
          });
        };
 
    });


    return (
        <div className="Chat">
        <MessageContainer messages={messages} setMessages={setMessages} ws={ws}/>
        <MessageTextBox appUser={appUser} message={message} setMessage={setMessage} ws={ws} />
        </div>
        
    );
};

export default Chat;