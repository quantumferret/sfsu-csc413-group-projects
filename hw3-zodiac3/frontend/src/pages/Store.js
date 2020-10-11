import React from 'react';
import axios from 'axios';
import {Redirect} from 'react-router-dom';

const Store = ({ appUser, itemDtoList }) => {

    const [currentUser, setCurrentUser] = React.useState('');
    const [cart, setCart] = React.useState([]);
    const [selectedItem, setSelectedItem] = React.useState('');
    const [transactionList, setTransactionList] = React.useState([]);


    React.useEffect(() => {

        //Sets the selected item to the first item on the list when the component first mounts
        if (selectedItem === '') {
          const item = itemDtoList[0];   
          setSelectedItem(item && item.name);
      
        }
     
    })

    const listTransactions = () => {
        axios.get('/api/listtransactions')
            .then((res) => {
                setTransactionList(() => {
                    let myList = JSON.parse(res.data);
                    const arrList = [];
                    myList.transactionList.forEach((transaction) => {
                        arrList.push(transaction);
                    })

                    return arrList;
                });
            });
    };

    const itemSelector = (e) => {

       // console.log(itemDtoList[0].name);
        const val = e.currentTarget.value;
      //   console.log(val)
        setSelectedItem(val);
       // console.log("----------")
       // console.log(selectedItem);
    };

    const handleAddtoCart = () => {

        setCart(() => {
            const newCart = cart.slice();
            newCart.push(selectedItem);

            return newCart;
        })
    }

    const handleCheckout = () => {


        cart.forEach((item, i) => {
            console.log(`Transaction ${i}: ${appUser} + ${item}`);
            const transaction = {
                user: appUser,
                item: item,
            };

            axios.post("/api/addtransaction", transaction)
                .then((res) => {
                    if (res.data.success) {
                        console.log("Transaction added!");
                    } else {
                        console.log("Failed to add transaction");
                    }
                })
                .catch(() => "Error");
        });

        setCart([]);

    };

    if (!appUser) {
        return <Redirect to="/login" />;
    }

    return (
        <div className='App'>

            <header>
                <h1>Store</h1>
            </header>
            <div className="items">
                <select onChange={itemSelector}>
                
                {itemDtoList.map((item, i) => (
                         
                         <option key={i} value={item.name}>{i} : {item.name}</option>
                            
                    ))}

                
                </select>
                <button onClick={handleAddtoCart}>Add to cart</button>
                <br></br>
            </div>
            <br></br>
            <div className="cart">
                <h1>Cart</h1>
                <div>
                    {cart.map((cartItem, i) => (
                        <div key={i}>
                            {cartItem}
                        </div>
                    ))}
                </div>
                <button onClick={handleCheckout}>Checkout</button>
            </div>

            <div>
                <button onClick={listTransactions}>List Transaction</button>

                {transactionList.map((transaction, i) => {
                        return (
                            <div key={i}> User: {transaction.user} Item: {transaction.item}</div>
                        )
                })}
            </div>
        </div>
    )
};

export default Store;