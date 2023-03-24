import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { getCurrentUserToken } from '../api/auth/auth.service';

const AccountDetails = () => {
    const navigate = useNavigate();
    const [userData, setUserData] = useState(null);

    useEffect(() => {
        const token = getCurrentUserToken();

        fetch('http://localhost:8080/api/test/account', {
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
        <div>
            {userData ? (
                <div>
                    <h1>Account Details</h1>
                    <p>Username: {userData.username}</p>
                    <p>Email: {userData.email}</p>
                </div>
            ) : (
                <p>Loading...</p>
            )}
        </div>
    );
}

export default AccountDetails;
