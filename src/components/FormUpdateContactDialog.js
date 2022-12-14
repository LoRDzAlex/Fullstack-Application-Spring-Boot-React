import * as React from 'react';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import EditIcon from "@mui/icons-material/Edit";
import {IconButton} from "@mui/material";
import {useState} from "react";


export const FormUpdateCompanyDialog = ({id, gender, contactName, tel, email}) =>{
    const [open, setOpen] = useState(false);
    const [error, setError] = useState(null);
    const [gr, setGr] = useState('');
    const [cN, setCn] = useState('');
    const [tl, setTl] = useState('');
    const [em, setEm] = useState('');
    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };


    function handleUpdate(id, gender, contactName, tel, email){
        fetch(`http://localhost:8080/contact?id=${id}&gender=${gender}&contactName=${contactName}&tel=${tel}&email=${email}`, {
            method: 'PUT',
            redirect: 'follow',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({"id": id, "gender": gender,"contactName": contactName,"tel" :tel, "email" : email})
        }).then(res => {
            return console.log(res)
        }).then(
            (error) => {setError(error)}
        )
        return console.log("Success")
    }

    if (error) {
        return <div>Error: {error.message}</div>;
    }
    return (
        <div>
            <IconButton onClick={() => handleClickOpen()}>
                <EditIcon/>
            </IconButton>

            <Dialog open={open} onClose={handleClose} >
                <DialogTitle>Update</DialogTitle>
                <DialogContent>
                    <TextField
                        autoFocus
                        margin="dense"
                        defaultValue={gender}
                        id="gender"
                        label="Gender"
                        onChange={(e) => setGr(e.target.value)}
                        type="text"
                        fullWidth
                        variant="standard"
                    />
                    <TextField
                        autoFocus
                        margin="dense"
                        defaultValue={contactName}
                        id="contactName"
                        label="ContactName"
                        onChange={(e) => setCn(e.target.value)}
                        type="text"
                        fullWidth
                        variant="standard"
                    />
                    <TextField
                        autoFocus
                        margin="dense"
                        defaultValue={tel}
                        id="tel"
                        label="Tel."
                        onChange={(e) => setTl(e.target.value)}
                        type="text"
                        fullWidth
                        variant="standard"
                    />
                    <TextField
                        autoFocus
                        margin="dense"
                        defaultValue={email}
                        id="email"
                        label="Email"
                        onChange={(e) => setEm(e.target.value)}
                        type="email"
                        fullWidth
                        variant="standard"
                    />
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose}>Cancel</Button>
                    <Button onClick={() => {handleUpdate(id, gr, cN, tl, em); handleClose(); window.location.reload();}}>Update</Button>
                </DialogActions>
            </Dialog>
        </div>
    );
}
export default FormUpdateCompanyDialog