import React, { Component } from 'react';
import axios from 'axios';

const ws = new WebSocket('ws://localhost:4000/ws');

const LikeButton = ({message}) => {

    const [likes, setLikes] = React.useState(message.likes);

    function updateLikes(){
        
        setLikes(likes+1);
        
        const body = {
            username: message.username, 
            message: message.message,
            date: message.date,
            likes: message.likes,
        };

      
        message.likes += 1;

        axios.post("/api/updateLikes", body)
            .then(() => console.log("Successfuly updated the likes"))
            .catch(() => console.log("Failed to update the likes."));
        
    };

    return (
        <>
        <button className="like-button" onClick={updateLikes}>Like </button>
        Likes: {likes}
        </>
    )


};

export default LikeButton;