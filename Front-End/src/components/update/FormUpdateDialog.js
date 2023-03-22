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


export const FormUpdateDialog = ({id, jobName, address, zip, status}) =>{
    const [open, setOpen] = useState(false);
    const [error, setError] = useState(null);
    const [jn, setJn] = useState('');
    const [ad, setAd] = useState('');
    const [zp, setZp] = useState('');
    const [st, setSt] = useState('');
    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };


    function handleUpdate(id, jobName, address, zip, status){
        fetch(`http://localhost:8080/job?id=${id}&jobName=${jobName}&address=${address}&zip=${zip}&status=${status}`, {
            method: 'PUT',
            redirect: 'follow',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({"id": id, "jobName": jobName,"address": address,"zip" :zip,"status" : status})
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
                        defaultValue={jobName}
                        id="name"
                        label="Job Name"
                        onChange={(e) => setJn(e.target.value)}
                        type="text"
                        fullWidth
                        variant="standard"
                    />
                    <TextField
                        autoFocus
                        margin="dense"
                        defaultValue={address}
                        id="address"
                        label="Address"
                        onChange={(e) => setAd(e.target.value)}
                        type="text"
                        fullWidth
                        variant="standard"
                    />
                    <TextField
                        autoFocus
                        margin="dense"
                        defaultValue={zip}
                        id="zip"
                        label="Zip"
                        onChange={(e) => setZp(e.target.value)}
                        type="number"
                        fullWidth
                        variant="standard"
                    />
                    <TextField
                        autoFocus
                        margin="dense"
                        defaultValue={status}
                        id="status"
                        label="Status"
                        onChange={(e) => setSt(e.target.value)}
                        type="text"
                        fullWidth
                        variant="standard"
                    />
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose}>Cancel</Button>
                    <Button onClick={() => {handleUpdate(id, jn, ad, zp, st); handleClose(); window.location.reload();}}>Update</Button>
                </DialogActions>
            </Dialog>
        </div>
    );
}
export default FormUpdateDialog