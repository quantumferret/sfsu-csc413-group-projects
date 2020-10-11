import React from 'react';
import axios from 'axios';
import {Redirect} from 'react-router-dom';

const StoreManagement = ({ appUser, itemDtoList, fetchItems}) => {

    // pass in default value into useState
    const [item, setItem] = React.useState(''); // create a state variable + setter
    const [price, setPrice] = React.useState('');
    //*************************************************** */
   // Moving this to App.js to be able to pass the item state to the Store component
   //
    //const [itemDtoList, setItems] = React.useState([]); // if map of undefined 
   //***************************************************** */

    // const [deleteprice, setdeletePrice] = React.useState('');
    // const [deleteitem, setdeleteItem] = React.useState(''); // create a state variable + setter

    // Moving this to App.js
    // const fetchItems = () => {
    //     // utility to get all items
    //     axios.get('/api/listitems')
    //         .then((res) => {
    //             console.log(res);
    //             setItems(() => {
    //                 var myObject = JSON.parse(res.data);
    //                 console.log(res.data);
    //                 return myObject.itemDtoList;
    //             });
    //         })
    //         .catch(console.log);
    // };

    const submitItem = () => { // arrow/lambda function
        console.log(item, price);
        const body = {
            item: item,
            price: price
        };
        axios.post('/api/additem', body)
            .then(() => setItem(''))
            .then(() => setPrice(''))
            .then(() => fetchItems()) // fetch after submit
            .catch(console.log);
    };

    //or deleting Item
    const deletingItem = (item, price) => { // arrow/lambda function
        console.log(item, price);
        const body = {
            item: item,
            price: price
        };
        axios.post('/api/deleteitem', body)
            .then(() => setItem(''))
            .then(() => setPrice(''))
            .then(() => fetchItems())
            .catch(console.log);
    };


    if (!appUser) {
        return <Redirect to="/login" />;
    };

    // jsx
    return (
        <div>
            <h1>Store Management</h1>
            <div>
                <div align="Center">
                    <tr> <h3> <td className="A" align="center">Item Name</td><td className="A" align="center">Price  </td></h3></tr>
                    <input value={item} placeholder="Enter name of the item" onChange={e => setItem(e.target.value)} />
                    <input type="Number" placeholder="Enter price of the item" value={price} onChange={e => setPrice(e.target.value)} />
                </div>
                <div>
                    <button onClick={submitItem}>Add Item</button>
                </div>
                <div>
                    <button onClick={fetchItems}>Fetch Items</button>
                    <div>
                        <div className="items-list">
                            <caption> Items in the Store: </caption>
                        </div>
                        <table>
                            <tr>
                                <td><th>Item Name</th></td>
                                <td><th>Price</th> </td><th></th>
                            </tr>

                            {itemDtoList.map((item) => {
                                return (
                                    <tr>
                                        <td> {item.name}</td>
                                        <td> {item.price}</td>
                                        <button type="button" onClick={() => { deletingItem(item.name, item.price) }} class="deletebtn"> X </button>
                                    </tr>
                                )  // return ends
                            })}

                        </table>

                    </div>
                </div>
            </div>
        </div>
    );
}

export default StoreManagement;