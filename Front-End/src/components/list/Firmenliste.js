import {useEffect, useState} from "react";
import * as React from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import FormUpdateCompanyDialog from "../update/FormUpdateCompanyDialog";
import AuthService, {getCurrentUserToken} from "../api/auth/auth.service";

export const Firmenliste = () => {
    const [currentUser, setCurrentUser] = useState(undefined);
    const [showAdminOptions, setShowAdminOptions] = useState(false);
    const [companyList, setCompanyList] = useState([]);

    useEffect(() => {
        const user = AuthService.getCurrentUser();
        if (user) {
            setCurrentUser(user);
            setShowAdminOptions(user.roles.includes('ROLE_ADMIN'));
        }

        fetch("http://localhost:8080/company", {
            method: 'GET',
            redirect: 'follow',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + getCurrentUserToken(),
            },
        })
            .then(res => res.json())
            .catch((error) => console.log(error));
    }, []);

    const [expanded, setExpanded] = useState(false);
    return (
        <React.Fragment>
                            <Table size="big" aria-label="purchases">
                                <TableHead>
                                    <TableRow>
                                        <TableCell align="center">Firmen Name</TableCell>
                                        <TableCell>Website</TableCell>
                                        <TableCell >Kanton</TableCell>
                                        <TableCell></TableCell>
                                    </TableRow>
                                </TableHead>
                                {companyList.map((list) => (
                                <TableBody >
                                    <TableRow key={list.id}>
                                        <TableCell component="th" scope="row">
                                            {list.companyName}
                                        </TableCell>
                                        <TableCell align="right">{list.website}</TableCell>
                                        <TableCell align="right">{list.canton}</TableCell>
                                        {showAdminOptions && (
                                            <TableCell>
                                                <FormUpdateCompanyDialog id={list.id} companyName={list.companyName} website={list.website} canton={list.canton}/>
                                            </TableCell>
                                        )}
                                    </TableRow>
                                </TableBody>
                                    ))}
                            </Table>
        </React.Fragment>
    );

}
export default Firmenliste;