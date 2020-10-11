import React from 'react';
import Jumbotron from 'react-bootstrap/Jumbotron';
import Container from 'react-bootstrap/Container';

const Home = (appUser) => {

    return (
        <Jumbotron fluid>
            <Container className={"text-center"}>
                <h1>Welcome!</h1>
                <p>
                    Please <a href={"/signup"}>create an account </a>
                    or <a href={"/login"}>log in</a> to continue to the store.
                </p>
            </Container>
        </Jumbotron>
    );
};

export default Home;