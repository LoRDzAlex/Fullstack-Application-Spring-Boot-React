import {Collapse} from '@mui/material';
import * as React from 'react';
import {useEffect, useState} from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import FormUpdateContactDialog from "../update/FormUpdateContactDialog"
import AuthService from "../api/auth/auth.service";

/**
 * Hier wird eine Tabelle mit Firmenkontakte geladen
 * @param id - ID des Kontakts
 * @param gender - Geschlecht des Kontakts
 * @param contactName - Name des Kontakts
 * @param tel - Telefonnummer des Kontakts
 * @param email - E-Mail-Adresse des Kontakts
 * @param expanded - Boolean-Wert, der angibt, ob die Zeile mit der Tabelle erweitert ist oder nicht
 * @returns {JSX.Element} - Eine Tabelle mit vorhandenen Kontakten
 * @constructor
 */
export const ContactList = ({id, gender, contactName, tel, email, expanded}) => {

    const [currentUser, setCurrentUser] = useState(undefined);
    const [showAdminOptions, setShowAdminOptions] = useState(false);

    useEffect(() => {
        const user = AuthService.getCurrentUser();
        if (user) {
            setCurrentUser(user);
            setShowAdminOptions(user.roles.includes('ROLE_ADMIN'));
        }
    }, []);

    return (
        <React.Fragment>
            <TableRow sx={{ '& > *': { borderBottom: 'unset' } }}>
                <TableCell style={{ paddingBottom: 0, paddingTop: 0 }} colSpan={6}>
                    <Collapse in={expanded} timeout="auto" unmountOnExit>
                        <Box sx={{ margin: 1 }}>
                            <Typography variant="h6" gutterBottom component="div" >
                                Contact
                            </Typography>
                            <Table size="small" aria-label="contactlist">
                                <TableHead>
                                    <TableRow>
                                        <TableCell>Gender</TableCell>
                                        <TableCell align="right">ContactName</TableCell>
                                        <TableCell align="right">Tel.</TableCell>
                                        <TableCell align="right">Email</TableCell>
                                        <TableCell></TableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody >
                                    <TableRow key={id}>
                                        <TableCell component="th" scope="row">
                                            {gender}
                                        </TableCell>
                                        <TableCell align="right">{contactName}</TableCell>
                                        <TableCell align="right">{tel}</TableCell>
                                        <TableCell align="right">{email}</TableCell>
                                        {showAdminOptions && (
                                        <TableCell>
                                            <FormUpdateContactDialog id={id} gender={gender} contactName={contactName} tel={tel} email={email}/>
                                        </TableCell>
                                        )}
                                    </TableRow>
                                </TableBody>
                            </Table>
                        </Box>
                    </Collapse>
                </TableCell>
            </TableRow>
        </React.Fragment>
    );

}
export default ContactList