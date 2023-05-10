import * as React from "react";
import {useEffect, useState} from "react";
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import FormUpdateDialog from "../update/FormUpdateDialog";
import styles from "../css/table.css"
import JobDelete from "../delete/JobDelete";
import AuthService from "../api/auth/auth.service";
import CompanyContactList from "./CompanyContactList";
import Typography from "@mui/material/Typography";
import FormCreateDialog from "../create/FormCreateDialog";

/**
 *  Hier werden die Job Einträge geholt und in die Tabellen geladen
 * @returns {JSX.Element} - Eine Tabelle mit den Praktika
 * @constructor
 */
export const JobList = () => {
    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [lists, setList] = useState([]);
    const [currentUser, setCurrentUser] = useState(undefined);
    const [showAdminOptions, setShowAdminOptions] = useState(false);
    // Note: the empty deps array [] means
    // this useEffect will run once
    // similar to componentDidMount()
    useEffect(() => {
        const user = AuthService.getCurrentUser();
        if (user) {
            setCurrentUser(user);
            setShowAdminOptions(user.roles.includes('ROLE_ADMIN'));
        }

        fetch("http://localhost:8080/job", {
            method: 'GET',
            redirect: 'follow',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
        })
            .then(res => res.json())
            .then(
                (result) => {
                    setIsLoaded(true);
                    setList(result);
                },
                (error) => {
                    setIsLoaded(true);
                    setError(error);
                }
            )
    }, [])

    /**
     * Prüft, ob ein Fehler aufgetreten ist und gibt eine Fehlermeldung aus, wenn dies der Fall ist.
     * Zeigt ansonsten eine Ladeanzeige, bis die Daten geladen sind.
     * Wenn die Daten vorhanden sind, werden sie in einer Tabelle dargestellt.
     */
   if (error) {
        return <div>Error: {error.message}</div>;
    } else if (!isLoaded) {
        return <div>Loading...</div>;
    } else {
        return (

            <TableContainer component={Paper} className={styles.TableContainer}>
                <Typography variant="h6" gutterBottom component="div" align="left">
                    Praktikumsliste
                    {showAdminOptions &&(
                    <FormCreateDialog/>
                    )}
                </Typography>
                <Table aria-label="collapsible table" className={styles.Table}>

                    <TableHead sx={{ bgcolor: "#eaeaea" }}>
                        <TableRow>
                            <TableCell>Berufsbezeichnung</TableCell>
                            <TableCell>Adresse</TableCell>
                            <TableCell>PLZ</TableCell>
                            <TableCell>Status</TableCell>
                            <TableCell>Geändert</TableCell>
                            <TableCell>Erstellt</TableCell>
                            <TableCell></TableCell>
                        </TableRow>
                    </TableHead>
                    {lists.map((list) => (
                    <TableBody>
                            <TableRow
                                key={list.id}
                                sx={{ '&:last-child td, &:last-child th': { border: 0 }  }}
                            >
                                <TableCell>{list.jobName || "nicht vorhanden"}</TableCell>
                                <TableCell>{list.address || "nicht vorhanden"}</TableCell>
                                <TableCell>{list.zip || "nicht vorhanden"}</TableCell>
                                <TableCell>{list.status || "nicht vorhanden"}</TableCell>
                                <TableCell>{list.changed || "nicht vorhanden"}</TableCell>
                                <TableCell>{list.created || "nicht vorhanden"}</TableCell>
                                {showAdminOptions && (
                                <TableCell>
                                    <FormUpdateDialog id={list.id} jobName={list.jobName} address={list.address} zip={list.zip} status={list.status}/>
                                    <JobDelete id={list.id}/>
                                </TableCell>
                                )}
                            </TableRow>
                        <CompanyContactList id={list.company.id} companyName={list.company.companyName} website={list.company.website} canton={list.company.canton} contactId={list.contact.id} contactName={list.contact.contactName} gender={list.contact.gender} email={list.contact.email} tel={list.contact.tel}/>
                    </TableBody>))}
                    </Table>
            </TableContainer>
        );
    }
}
export default JobList