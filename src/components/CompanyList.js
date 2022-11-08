import {useState} from "react";
import {Collapse, IconButton} from '@mui/material';
import * as React from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import KeyboardArrowDownIcon from "@mui/icons-material/KeyboardArrowDown";
import FormUpdateCompanyDialog from "./FormUpdateCompanyDialog";
import KeyboardArrowUpIcon from '@mui/icons-material/KeyboardArrowUp';
import ContactList from "./ContactList";

export const CompanyList = ({id, companyName, website, canton, contactName, gender, tel, email, contactId}) => {

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
                                            <TableCell>
                                                <FormUpdateCompanyDialog id={id} companyName={companyName} website={website} canton={canton}/>
                                            </TableCell>
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