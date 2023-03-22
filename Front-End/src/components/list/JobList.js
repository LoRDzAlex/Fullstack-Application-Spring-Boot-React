import {useEffect, useState} from "react";
import * as React from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import FormUpdateDialog from "../update/FormUpdateDialog";
import CompanyList from "./CompanyList";
import styles from "../css/table.css"
import JobDelete from "../delete/JobDelete";


export const JobList = () => {
    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [lists, setList] = useState([]);
    // Note: the empty deps array [] means
    // this useEffect will run once
    // similar to componentDidMount()
    useEffect(() => {
        fetch("http://localhost:8080/job", {
            method: 'GET',
            redirect: 'follow',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
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

    if (error) {
        return <div>Error: {error.message}</div>;
    } else if (!isLoaded) {
        return <div>Loading...</div>;
    } else {
        return (

            <TableContainer component={Paper} className={styles.TableContainer}>

                <Table aria-label="collapsible table" className={styles.Table}>

                    <TableHead>
                        <TableRow>
                            <TableCell>JobListing</TableCell>
                            <TableCell align="right">Job Name</TableCell>
                            <TableCell align="right">Address</TableCell>
                            <TableCell align="right">Zip</TableCell>
                            <TableCell align="right">Status</TableCell>
                            <TableCell align="right">Changed</TableCell>
                            <TableCell align="right">Created</TableCell>
                            <TableCell align="right"></TableCell>
                        </TableRow>
                    </TableHead>
                    {lists.map((list) => (
                    <TableBody>

                            <TableRow
                                key={list.id}
                                sx={{ '&:last-child td, &:last-child th': { border: 0 }  }}
                            >
                                <TableCell component="th" scope="row" >

                                </TableCell>
                                <TableCell align="right">{list.jobName || "nicht vorhanden"}</TableCell>
                                <TableCell align="right">{list.address || "nicht vorhanden"}</TableCell>
                                <TableCell align="right">{list.zip || "nicht vorhanden"}</TableCell>
                                <TableCell align="right">{list.status || "nicht vorhanden"}</TableCell>
                                <TableCell align="right">{list.changed || "nicht vorhanden"}</TableCell>
                                <TableCell align="right">{list.created || "nicht vorhanden"}</TableCell>
                                <TableCell>
                                    <FormUpdateDialog id={list.id} jobName={list.jobName} address={list.address} zip={list.zip} status={list.status}/>
                                    <JobDelete id={list.id}/>
                                </TableCell>
                            </TableRow>
                        <CompanyList id={list.company.id} companyName={list.company.companyName} website={list.company.website} canton={list.company.canton} contactId={list.contact.id} contactName={list.contact.contactName} gender={list.contact.gender} email={list.contact.email} tel={list.contact.tel}/>
                    </TableBody>))}
                    </Table>
            </TableContainer>
        );
    }
}
export default JobList