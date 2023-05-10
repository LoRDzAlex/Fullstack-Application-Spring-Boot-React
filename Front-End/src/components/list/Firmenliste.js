import * as React from "react";
import {useEffect, useState} from "react";
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import FormUpdateCompanyDialog from "../update/FormUpdateCompanyDialog";
import AuthService from "../api/auth/auth.service";

/**
 * Hier wird eine Tabellen-Liste mit den vorhandenen Firmen geladen
 * Je nach Rollen werden verschiedene zusatz Optionen angezeigt
 * @returns {JSX.Element} - Eine Tabelle mit den Firmen
 * @constructor
 */
export const Firmenliste = () => {
    const [currentUser, setCurrentUser] = useState(undefined);
    const [showAdminOptions, setShowAdminOptions] = useState(false);
    const [companyList, setCompanyList] = useState([]);
    const [showCompanyOptions, setShowCompanyOptions] = useState(false);

    useEffect(() => {
        const user = AuthService.getCurrentUser();
        if (user) {
            setCurrentUser(user);
            setShowAdminOptions(user.roles.includes('ROLE_ADMIN'));
            setShowCompanyOptions(user.roles.includes('ROLE_COMPANY'));
        }

        fetch("http://localhost:8080/company", {
            method: 'GET',
            redirect: 'follow',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
        })
            .then(res => res.json())
            .then((result) => { setCompanyList(result); })
            .catch((error) => console.log(error));
    }, []);

    return (
        <React.Fragment>
            <Table size="big" aria-label="purchases">
                <TableHead sx={{ bgcolor: "#eaeaea" }}>
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
                            <TableCell align={"center"}>
                                {list.companyName}
                            </TableCell>
                            <TableCell>{list.website}</TableCell>
                            <TableCell >{list.canton}</TableCell>
                            {showAdminOptions &&(
                                <TableCell>
                                    <FormUpdateCompanyDialog id={list.id} companyName={list.companyName} website={list.website} canton={list.canton}/>
                                </TableCell>
                            )}
                            {showCompanyOptions && (
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