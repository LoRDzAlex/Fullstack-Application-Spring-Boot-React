import * as React from 'react';
import {useState} from 'react';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import {DialogContentText, IconButton} from "@mui/material";
import AddBoxIcon from "@mui/icons-material/AddBox";
import {getCurrentUserToken} from "../api/auth/auth.service";

/**
 * Komponente für das Erstellen eines neuen Jobs.
 *
 * @returns {JSX.Element} JSX-Element mit einem Button, der ein Dialogfenster öffnet
 *          und ein Formular zum Erstellen eines neuen Jobs enthält
 * @constructor
 */

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

    // Öffnet das Dialog-Fenster für das Erstellen eines neuen Jobs.
    const handleClickOpen = () => {
        setOpen(true);
    };
    // Schließt das Dialog-Fenster.
    const handleClose = () => {
        setOpen(false);
    };

    /**
     * Erstellt einen neuen Job mit den eingegebenen Daten.
     *
     * @param {string} jobName - Berufsbezeichnung des Jobs
     * @param {string} address - Adresse des Jobs
     * @param {string} zip - PLZ des Jobs
     * @param {string} status - Status des Jobs
     * @param {string} companyname - Firmenname des Jobs
     * @param {string} website - Website der Firma des Jobs
     * @param {string} canton - Kanton des Jobs
     * @param {string} gender - Geschlecht des Kontakts des Jobs
     * @param {string} fullname - Vor- und Nachname des Kontakts des Jobs
     * @param {string} tel - Telefonnummer des Kontakts des Jobs
     * @param {string} email - E-Mail-Adresse des Kontakts des Jobs
     */
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
        <>
            <IconButton onClick={() => handleClickOpen()}>
                <AddBoxIcon/>
            </IconButton>
            <Dialog open={open} onClose={handleClose}>
                <DialogTitle>Create</DialogTitle>
                <DialogContent>
                    <DialogContentText>Berufsbezeichnung</DialogContentText>
                    <TextField
                        autoFocus
                        error={jobname===""}
                        helperText={jobname === "" ? 'please fill in empty field' : ' '}
                        value={jobname}
                        margin="dense"
                        id="name"
                        onChange={(e) => {setJobname(e.target.value);}}
                        label="Berufsbezeichnung"
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
                        label="Adresse"
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
                        label="PLZ"
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
                    <DialogContentText><br/>Firma</DialogContentText>
                    <TextField
                        error={companyname===""}
                        helperText={companyname === "" ? 'please fill in empty field' : ' '}
                        value={companyname}
                        autoFocus
                        margin="dense"
                        id="companyname"
                        onChange={(e) => setCompanyname((e.target.value))}
                        label="Firmenname"
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
                        label="Kanton"
                        type="text"
                        fullWidth
                        variant="standard"
                        required
                    />
                    <DialogContentText><br/>Kontakt</DialogContentText>
                    <TextField
                        error={gender===""}
                        helperText={gender === "" ? 'please fill in empty field' : ' '}
                        value={gender}
                        autoFocus
                        margin="dense"
                        id="gender"
                        onChange={(e) => setGender((e.target.value))}
                        label="Geschlecht"
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
                        label="Vorname Nachname"
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
                        label="Telefonnummer"
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
        </>
    );
}