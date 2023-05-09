import React, {useEffect, useState} from 'react';
import {useNavigate} from 'react-router-dom';
import AuthService, {getCurrentUserToken} from '../api/auth/auth.service';
import TableRow from "@mui/material/TableRow";
import TableCell from "@mui/material/TableCell";
import TableHead from "@mui/material/TableHead";
import TableBody from "@mui/material/TableBody";
import Table from "@mui/material/Table";
import UserDelete from "../delete/UserDelete";

const AdminOptions = () => {
    const navigate = useNavigate();
    const [currentUser, setCurrentUser] = useState(undefined);
    const [userData, setUserData] = useState([]);

    useEffect(() => {
        const user = AuthService.getCurrentUser();
        if (user) {
            setCurrentUser(user);
        }

        fetch('http://localhost:8080/api/test/users', {
            method: 'GET',
            redirect: 'follow',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + getCurrentUserToken(),
            },
        })
            .then((res) => res.json())
            .then((result) => setUserData(result))
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
                            <TableCell>Rollen</TableCell>
                            <TableCell/>
                        </TableRow>
                    </TableHead>
                    {userData.map((user) => (
                    <TableBody>
                        <TableRow key={user.id}>
                            <TableCell align={"center"}> {user.username}</TableCell>
                            <TableCell align="left">{user.email}</TableCell>
                            <TableCell>{user.roles.map((role) => role.name.replace("ROLE_", "")).join(", ")}</TableCell>
                            <TableCell><UserDelete id={user.id}/></TableCell>
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
