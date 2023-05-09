import React, {useEffect, useState} from 'react';
import {useNavigate} from 'react-router-dom';
import AuthService from '../api/auth/auth.service';
import TableRow from "@mui/material/TableRow";
import TableCell from "@mui/material/TableCell";
import TableHead from "@mui/material/TableHead";
import TableBody from "@mui/material/TableBody";
import Table from "@mui/material/Table";
import CompanyListingDelete from "../delete/CompanyListingDelete";

const AdminOptions = () => {
    const navigate = useNavigate();
    const [currentUser, setCurrentUser] = useState(undefined);
    const [companyData, setCompanyData] = useState([]);

    useEffect(() => {
        const user = AuthService.getCurrentUser();
        if (user) {
            setCurrentUser(user);
        }

        fetch('http://localhost:8080/company', {
            method: 'GET',
            redirect: 'follow',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
        })
            .then((res) => res.json())
            .then((result) => setCompanyData(result))
            .catch((error) => console.log(error));
    }, []);


    return (
        <>
            {currentUser && currentUser.roles.includes('ROLE_COMPANY') ? (
                <Table>
                    <TableHead sx={{ bgcolor: "#eaeaea" }}>
                        <TableRow>
                            <TableCell align="center">Firmen Namen</TableCell>
                            <TableCell/>
                        </TableRow>
                    </TableHead>
                    {companyData.map((company) => (
                        <TableBody>
                            <TableRow key={company.id}>
                                <TableCell align={"center"}> {company.companyName}</TableCell>
                                <TableCell align={"center"}><CompanyListingDelete name={company.companyName}/></TableCell>
                            </TableRow>
                        </TableBody>
                    ))}
                </Table>
            ) : (
                <p>Not authorized</p>
            )}
        </>
    );
};

export default AdminOptions;
