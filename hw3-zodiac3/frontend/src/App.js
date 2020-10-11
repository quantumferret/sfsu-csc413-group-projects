import React from 'react';
import './App.css';
import {Switch, Route, Link} from 'react-router-dom';
import axios from 'axios';
import Home from './pages/Home';
import Signup from './pages/Signup';
import Login from './pages/Login';
import Store from './pages/Store';
import StoreManager from './pages/StoreManagement';



const App = () => {
    const [appUser, setAppUser] = React.useState(null);
    const [isAdmin, setAdminStatus] = React.useState(false);
    const [itemDtoList, setItems] = React.useState([]);

    const setUser = (username) => {
        setAppUser(username);
    }

    const setManager = (input) => setAdminStatus(input);

    // const addItem = (stringMessage) => {

    //     setItems((items) => {
    //         const newItems = items.slice();
    //         newItems.push(stringMessage.data);

    //         return newItems;
    //     });
    // };

    React.useEffect(() => {
        
        axios.get('/api/listitems')
            .then((res) => {
                console.log(res);
                setItems(() => {
                    var myObject = JSON.parse(res.data);
                    console.log(res.data);
                    return myObject.itemDtoList;
                });
            })
            .catch(console.log);


    }, []);

    const fetchItems = () => {
        // utility to get all items
        axios.get('/api/listitems')
            .then((res) => {
                console.log(res);
                setItems(() => {
                    var myObject = JSON.parse(res.data);
                    console.log(res.data);
                    return myObject.itemDtoList;
                });
            })
            .catch(console.log);
    };
    return (
        <div>
            <nav style={{ backgroundColor: '#2F4F4F', }}>
                {(<Link to={"/"} className="Link"> Home </Link>)}
                {!appUser && <Link to={"/signup"} className="Link"> Sign Up </Link>}
                {!appUser && <Link to={"/login"} className="Link"> Log In </Link>}
                {/* {appUser===true &&
                    <Link to={"/store"} className="Link"> Store </Link>} */}
                {/* {isAdmin === true &&
                    (<Link to={"/storemanagement"} className="Link"> Store Management </Link>)} */}
                {appUser && <Link to={"/"} className="Link"> Log Out </Link>} 
            </nav>

            <Switch>
                <Route exact path="/">
                    <Home />
                </Route>
                <Route exact path="/login">
                    <Login
                        appUser={appUser}
                        setAppUser={setAppUser}
                        isAdmin={isAdmin}
                        setManager={setManager}
                        // setAdminStatusTrue={setAdminStatusTrue}
                        // setAdminstatusFalse={setAdminstatusFalse}

                    />
                </Route>
                <Route exact path= "/signup">
                    <Signup
                        appUser={appUser}
                        setAppUser={setAppUser}
                        isAdmin={isAdmin}
                        setManager={setManager}
                        // setAdminStatusTrue={setAdminStatusTrue}
                        // setAdminstatusFalse={setAdminstatusFalse}
                    />
                </Route>
                 <Route exact path="/store">
                    <Store
                        appUser={appUser}
                        setAppUser={setAppUser}
                        itemDtoList={itemDtoList}
                    />
                </Route>
                
                <Route exact path="/storemanagement">
                    <StoreManager
                        appUser={appUser}
                        setAppUser={setAppUser}
                        itemDtoList={itemDtoList}
                        fetchItems={fetchItems}
                    />
                </Route>
            </Switch>
        </div>
    );
};

export default App;
