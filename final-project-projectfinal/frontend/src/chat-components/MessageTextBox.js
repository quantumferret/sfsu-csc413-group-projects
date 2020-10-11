import React, { Component } from 'react';
import axios from 'axios';
import './Messages.css';


//This component allows the user to input a message and send it to database
const MessageTextBox = ({appUser, message, setMessage, ws}) => {

    //Sends a message to the database after hitting enter
    function messageHandler (e) { 
        //e.keyCode === 13 is the enter key
        if (e.keyCode === 13) {
            e.preventDefault();
            
            //Resets the textarea after you press enter
            e.target.value = '';
            setMessage('');
            
            //Sets the current date
            const date = new Date().toUTCString();

            const body = {
                username: appUser, 
                message: message,
                date: date,
                likes: 0,
            };
        
            // Notifies the Websocket handler that the client is sending a new message.
            // When the Websocket handler receives this message, it will broadcast the new message
            // to all of the users on the app. Afterwards, the 'messages' state in the Chat component
            // gets updated with the new message.
            ws.send(JSON.stringify(body));
            
            //Sends a message to the backend server
            axios.post('/api/sendmessage', body)
                .then(res => {
                    console.log(res.data);
                    if (res.data.success) {
                        console.log('Message sent!');
                    } else {
                        console.log('Something went wrong... :(');
                    }
                })
                .catch(() => {
                    console.log('Failed to send message.');
                });
        };  
    };

    return (
        <div> 
            <textarea
                className="input"
                value={message}
                onChange={e => setMessage(e.target.value)}
                onKeyDown={messageHandler}/>
        </div>
    );
};

export default MessageTextBox;