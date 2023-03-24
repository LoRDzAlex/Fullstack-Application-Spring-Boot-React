import * as React from 'react';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import {DialogContentText, IconButton} from "@mui/material";
import AddBoxIcon from "@mui/icons-material/AddBox";
import {useState} from "react";
import {getCurrentUserToken} from "../api/auth/auth.service";

export default function FormCreateDialog() {
    const [open, setOpen] = React.useState(false);
    const [error, setError] = useState(null);
    const [jobname, setJobname] = useState('');
    const [address, setAddress] = useState('');
    const [zip, setZip] = useState('');
    const [status, setStatus] = useState('');
    const [companyname, setCompanyname] = useState('');
    const [website, setWebsite] = useState('');
    const [canton, setCanton] = useState('');
    const [gender, setGender] = useState('');
    const [fullname, setFullname] = useState('');
    const [tel, setTel] = useState('');
    const [email, setEmail] = useState('');


    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    function handleCreate(jobName, address, zip, status, companyname, website, canton, gender, fullname, tel, email){
        fetch(`http://localhost:8080/job?jobName=${jobName}&address=${address}&zip=${zip}&status=${status}&companyName=${companyname}&website=${website}&canton=${canton}&gender=${gender}&fullname=${fullname}&tel=${tel}&email=${email}`, {
            method: 'POST',
            redirect: 'follow',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + getCurrentUserToken(),
            },
            body: JSON.stringify({"jobName": jobName,"address": address,"zip" :zip,"status" : status,"companyName": companyname,"website" : website,"canton" : canton,"gender" : gender,"fullname" : fullname,"tel" : tel, "email" : email})
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
                <AddBoxIcon/>
            </IconButton>
            <Dialog open={open} onClose={handleClose}>
                <DialogTitle>Create</DialogTitle>
                <DialogContent>
                    <DialogContentText>Job</DialogContentText>
                    <TextField
                        autoFocus
                        error={jobname===""}
                        helperText={jobname === "" ? 'please fill in empty field' : ' '}
                        value={jobname}
                        margin="dense"
                        id="name"
                        onChange={(e) => {setJobname(e.target.value);}}
                        label="Job Name"
                        type="text"
                        fullWidth
                        variant="standard"
                        required
                    />
                    <TextField
                        error={address===""}
                        helperText={address === "" ? 'please fill in empty field' : ' '}
                        value={address}
                        autoFocus
                        margin="dense"
                        id="address"
                        onChange={(e) => setAddress((e.target.value))}
                        label="Address"
                        type="text"
                        fullWidth
                        variant="standard"
                        required
                    />
                    <TextField
                        error={zip===""}
                        helperText={zip === "" ? 'please fill in empty field' : ' '}
                        value={zip}
                        autoFocus
                        margin="dense"
                        id="zip"
                        onChange={(e) => setZip((e.target.value))}
                        label="Zip"
                        type="number"
                        fullWidth
                        variant="standard"
                        required
                    />
                    <TextField
                        error={status===""}
                        helperText={status === "" ? 'please fill in empty field' : ' '}
                        value={status}
                        autoFocus
                        margin="dense"
                        id="status"
                        onChange={(e) => setStatus((e.target.value))}
                        label="Status"
                        type="text"
                        fullWidth
                        variant="standard"
                        required
                    />
                    <DialogContentText><br/>Company</DialogContentText>
                    <TextField
                        error={companyname===""}
                        helperText={companyname === "" ? 'please fill in empty field' : ' '}
                        value={companyname}
                        autoFocus
                        margin="dense"
                        id="companyname"
                        onChange={(e) => setCompanyname((e.target.value))}
                        label="Company Name"
                        type="text"
                        fullWidth
                        variant="standard"
                        required
                    />
                    <TextField
                        error={website===""}
                        helperText={website === "" ? 'please fill in empty field' : ' '}
                        value={website}
                        autoFocus
                        margin="dense"
                        id="website"
                        onChange={(e) => setWebsite((e.target.value))}
                        label="Website"
                        type="text"
                        fullWidth
                        variant="standard"
                        required
                    />
                    <TextField
                        error={canton===""}
                        helperText={canton === "" ? 'please fill in empty field' : ' '}
                        value={canton}
                        autoFocus
                        margin="dense"
                        id="canton"
                        onChange={(e) => setCanton((e.target.value))}
                        label="Canton"
                        type="text"
                        fullWidth
                        variant="standard"
                        required
                    />
                    <DialogContentText><br/>Contact</DialogContentText>
                    <TextField
                        error={gender===""}
                        helperText={gender === "" ? 'please fill in empty field' : ' '}
                        value={gender}
                        autoFocus
                        margin="dense"
                        id="gender"
                        onChange={(e) => setGender((e.target.value))}
                        label="Gender"
                        type="text"
                        fullWidth
                        variant="standard"
                        required
                    />
                    <TextField
                        error={fullname===""}
                        helperText={fullname === "" ? 'please fill in empty field' : ' '}
                        value={fullname}
                        autoFocus
                        margin="dense"
                        id="fullname"
                        onChange={(e) => setFullname((e.target.value))}
                        label="Fullname"
                        type="text"
                        fullWidth
                        variant="standard"
                        required
                    />
                    <TextField
                        error={tel===""}
                        helperText={tel === "" ? 'please fill in empty field' : ' '}
                        value={tel}
                        autoFocus
                        margin="dense"
                        id="tel"
                        onChange={(e) => setTel((e.target.value))}
                        label="Tel"
                        type="text"
                        fullWidth
                        variant="standard"
                        required
                    />
                    <TextField
                        error={email===""}
                        helperText={email === "" ? 'please fill in empty field' : ' '}
                        value={email}
                        autoFocus
                        margin="dense"
                        id="email"
                        onChange={(e) => setEmail((e.target.value))}
                        label="Email"
                        type="email"
                        fullWidth
                        variant="standard"
                        required
                    />
                </DialogContent>
                <DialogActions>
                    <Button onClick={() => {handleClose(); window.location.reload();}}>Cancel</Button>
                    <Button onClick={() => {handleCreate(jobname, address, zip, status, companyname, website, canton, gender, fullname, tel, email); handleClose(); window.location.reload();}}>Create</Button>
                </DialogActions>
            </Dialog>
        </div>
    );
}