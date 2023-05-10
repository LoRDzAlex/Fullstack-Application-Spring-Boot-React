import * as React from "react";
import {useEffect, useState} from "react";
import {Collapse, IconButton} from '@mui/material';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import KeyboardArrowDownIcon from "@mui/icons-material/KeyboardArrowDown";
import FormUpdateCompanyDialog from "../update/FormUpdateCompanyDialog";
import KeyboardArrowUpIcon from '@mui/icons-material/KeyboardArrowUp';
import ContactList from "./ContactList";
import AuthService from "../api/auth/auth.service";

/**
 * Hier werden die Firmen in einer Liste angezeigt die per Knopfdruck ausgebreitet werden kann
 * @param id - Die ID der Firma
 * @param companyName - Der Name der Firma
 * @param website - Die Webseite der Firma
 * @param canton - Der Kanton, in dem die Firma ansässig ist
 * @param contactName - Der Name des Ansprechpartners der Firma
 * @param gender - Das Geschlecht des Ansprechpartners der Firma
 * @param tel - Die Telefonnummer des Ansprechpartners der Firma
 * @param email - Die E-Mail-Adresse des Ansprechpartners der Firma
 * @param contactId - Die ID des Ansprechpartners der Firma
 * @returns {JSX.Element} - Eine Tabelle der vorhandenen Firmen
 * @constructor
 */
export const CompanyList = ({id, companyName, website, canton, contactName, gender, tel, email, contactId}) => {
    const [currentUser, setCurrentUser] = useState(undefined);
    const [showAdminOptions, setShowAdminOptions] = useState(false);

    useEffect(() => {
        const user = AuthService.getCurrentUser();
        if (user) {
            setCurrentUser(user);
            setShowAdminOptions(user.roles.includes('ROLE_ADMIN'));
        }
    }, []);

    const [expanded, setExpanded] = useState(false);
        return (
            <React.Fragment>
                <button style={{ border: "0px", display: "block"}}>
                    <IconButton
                        aria-label="expand row"
                        size="small"
                        onClick={() => setExpanded(!expanded)}
                        style={{width: "1000%"}}
                    >
                        {expanded ? <KeyboardArrowUpIcon /> : <KeyboardArrowDownIcon />}
                    </IconButton>
                </button>
            <TableRow sx={{ '& > *': { borderBottom: 'unset' } }}>
                <TableCell style={{ paddingBottom: 0, paddingTop: 0 }} colSpan={6}>
                    <Collapse in={expanded} timeout="auto" unmountOnExit>
                        <Box sx={{ margin: 1 }}>
                            <Typography variant="h6" gutterBottom component="div" >
                                Company
                            </Typography>
                            <Table size="small" aria-label="purchases">
                                <TableHead>
                                <TableRow>
                                        <TableCell>CompanyName</TableCell>
                                        <TableCell align="right">Website</TableCell>
                                        <TableCell align="right">Canton</TableCell>
                                        <TableCell></TableCell>
                                </TableRow>
                                </TableHead>
                                <TableBody >
                                        <TableRow key={id}>
                                            <TableCell component="th" scope="row">
                                                {companyName}
                                            </TableCell>
                                            <TableCell align="right">{website}</TableCell>
                                            <TableCell align="right">{canton}</TableCell>
                                            {showAdminOptions && (
                                            <TableCell>
                                                <FormUpdateCompanyDialog id={id} companyName={companyName} website={website} canton={canton}/>
                                            </TableCell>
                                            )}
                                        </TableRow>
                                </TableBody>
                            </Table>
                        </Box>
                    </Collapse>
                </TableCell>
            </TableRow>
                <ContactList contactName={contactName} id={contactId} expanded={expanded} email={email} gender={gender} tel={tel}/>
            </React.Fragment>
        );

}
export default CompanyList