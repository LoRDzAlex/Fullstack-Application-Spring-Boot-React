import {Collapse} from '@mui/material';
import * as React from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import FormUpdateContactDialog from "./FormUpdateContactDialog"

export const ContactList = ({id, gender, contactName, tel, email, expanded}) => {

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
                                        <TableCell>
                                            <FormUpdateContactDialog id={id} gender={gender} contactName={contactName} tel={tel} email={email}/>
                                        </TableCell>
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