import React, {useEffect, useState} from 'react';
import {useNavigate} from 'react-router-dom';
import {getCurrentUserToken} from '../api/auth/auth.service';

/**
 * Die AccountDetails-Komponente zeigt die Account-Informationen des angemeldeten Benutzers an.
 * Die Informationen werden von der Backend-API abgerufen.
 * @returns {JSX.Element} - Eine Tabelle mit User Informationen
 * @constructor
 */
const AccountDetails = () => {
    const navigate = useNavigate();
    const [userData, setUserData] = useState(null);
    /**
     * Diese Methode wird aufgerufen, sobald die Komponente gemountet ist. Sie ruft die Account-Informationen des
     * angemeldeten Benutzers von der Backend-API ab und speichert sie im State der Komponente.
     */
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
