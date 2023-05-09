import * as React from 'react';
import {useEffect, useState} from 'react';
import TextField from '@mui/material/TextField';
import {getCurrentUserToken} from "../api/auth/auth.service";
import Button from "@mui/material/Button";
import Paper from "@mui/material/Paper";
import UserDelete from "../delete/UserDelete";
import Typography from "@mui/material/Typography";


export const AccountSettings = () =>{
    const [un, setUn] = useState('');
    const [em, setEm] = useState('');
    const [userData, setUserData] = useState(null);

    useEffect(() => {

        fetch('http://localhost:8080/api/test/account', {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + getCurrentUserToken(),
            },
        })
            .then((res) => res.json())
            .then((data) => setUserData(data))
            .then((data) => console.log(data))
            .catch((error) => console.log(error));

    }, []);

    function handleUpdate(id, username, email){
        fetch(`http://localhost:8080/api/test/
        user?id=${id}&username?=${username}&email?=${email}`, {
            method: 'PUT',
            redirect: 'follow',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + getCurrentUserToken(),
            },
        }).then(res => {
            return console.log(res)
        }).then(
            (error) => {console.log(error)}
        )
        return console.log("Success")
    }


    return (
        <>
            {userData ? (
            <Paper>
                    <TextField
                        autoFocus
                        margin="dense"
                        defaultValue={userData.username || ''}
                        id="name"
                        label="Username"
                        onChange={(e) => setUn(e.target.value)}
                        type="text"
                        fullWidth
                        variant="standard"
                    />
                    <TextField
                        autoFocus
                        margin="dense"
                        defaultValue={userData.email || ''}
                        id="email"
                        label="Email"
                        onChange={(e) => setEm(e.target.value)}
                        type="email"
                        fullWidth
                        variant="standard"
                    />
            <Button onClick={() => {handleUpdate(userData.id, un, em)}} color="primary">Ändern</Button>
                <Typography variant="h6" gutterBottom component="div">
                    Willst du deinen Account löschen?
                    <UserDelete id={userData.id}/>
                </Typography>
            </Paper>
            ) : (
                <p>Loading...</p>
            )}
        </>

    );
}
export default AccountSettings