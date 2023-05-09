import * as React from 'react';
import {useState} from 'react';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import EditIcon from "@mui/icons-material/Edit";
import {IconButton} from "@mui/material";
import {getCurrentUserToken} from "../api/auth/auth.service";


export const FormUpdateCompanyDialog = ({id, companyName, website, canton}) =>{
    const [open, setOpen] = useState(false);
    const [error, setError] = useState(null);
    const [cn, setCn] = useState('');
    const [wb, setWb] = useState('');
    const [ct, setCt] = useState('');
    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };


    function handleUpdate(id, companyName, website, canton){
        fetch(`http://localhost:8080/company?id=${id}&companyName=${companyName}&website=${website}&canton=${canton}`, {
            method: 'PATCH',
            redirect: 'follow',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + getCurrentUserToken(),
            },
            body: JSON.stringify({"id": id, "companyName": companyName,"website": website,"canton" :canton})
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
        <>
            <IconButton onClick={() => handleClickOpen()}>
                <EditIcon/>
            </IconButton>

            <Dialog open={open} onClose={handleClose} >
                <DialogTitle>Update</DialogTitle>
                <DialogContent>
                    <TextField
                        autoFocus
                        margin="dense"
                        defaultValue={companyName}
                        id="name"
                        label="CompanyName"
                        onChange={(e) => setCn(e.target.value)}
                        type="text"
                        fullWidth
                        variant="standard"
                    />
                    <TextField
                        autoFocus
                        margin="dense"
                        defaultValue={website}
                        id="website"
                        label="Website"
                        onChange={(e) => setWb(e.target.value)}
                        type="text"
                        fullWidth
                        variant="standard"
                    />
                    <TextField
                        autoFocus
                        margin="dense"
                        defaultValue={canton}
                        id="canton"
                        label="Canton"
                        onChange={(e) => setCt(e.target.value)}
                        type="text"
                        fullWidth
                        variant="standard"
                    />
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose}>Cancel</Button>
                    <Button onClick={() => {handleUpdate(id, cn, wb, ct); handleClose(); window.location.reload();}}>Update</Button>
                </DialogActions>
            </Dialog>
        </>
    );
}
export default FormUpdateCompanyDialog