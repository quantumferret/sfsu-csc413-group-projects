import React, { Component } from 'react';
import LikeButton from '../chat-components/LikeButton';
import './Messages.css';

const MessageContainer = ({messages, setMessages, ws}) => {
    
    return (

    <div className='MessageContainer'>
        {messages.map((message) => {
            return (
                    <div className='message' key={message.date}>
                        {message.username} : {message.message} 
                        <LikeButton message={message} messages={messages} setMessages={setMessages} ws={ws} />
                        Date: {message.date}
                    </div>
             
            )
        })}
    </div>
    )
    

};

export default MessageContainer;