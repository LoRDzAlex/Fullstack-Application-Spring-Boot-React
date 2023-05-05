import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import AuthService, { getCurrentUserToken } from '../api/auth/auth.service';
import TableRow from "@mui/material/TableRow";
import TableCell from "@mui/material/TableCell";
import TableHead from "@mui/material/TableHead";
import TableBody from "@mui/material/TableBody";
import Table from "@mui/material/Table";

const AdminOptions = () => {
    const navigate = useNavigate();
    const [userData, setUserData] = useState([]);
    const [currentUser, setCurrentUser] = useState(undefined);

    useEffect(() => {
        const token = getCurrentUserToken();
        const user = AuthService.getCurrentUser();
        if (user) {
            setCurrentUser(user);
        }
        fetch('http://localhost:8080/api/test/users', {
            method: 'GET',
            headers: {
                Authorization: `Bearer ${token}`,
            },
        })
            .then((res) => res.json())
            .then((data) => setUserData(data))
            .catch((error) => console.log(error));
    }, []);

    return (
        <>
            {currentUser && currentUser.roles.includes('ROLE_ADMIN') ? (
                <Table>
                <TableHead>
                        <TableRow>
                            <TableCell align="center">Username</TableCell>
                            <TableCell align="left">Email</TableCell>
                            <TableCell></TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {userData.map((user) => (
                        <TableRow key={user.id}>
                            <TableCell component="th" scope="row" align="left"> {userData.username}</TableCell>
                            <TableCell align="right">{userData.email}</TableCell>
                        </TableRow>
                        ))}
                    </TableBody>
                </Table>
            ) : (
                <p>Not authorized</p>
            )}
        </>
    );
};

export default AdminOptions;
