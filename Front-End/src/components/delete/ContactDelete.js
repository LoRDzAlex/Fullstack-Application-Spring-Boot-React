import DeleteOutlineIcon from "@mui/icons-material/DeleteOutline";
import {IconButton} from "@mui/material";
import * as React from "react";
import {getCurrentUserToken} from "../api/auth/auth.service";

/**
 * Hier kann per ID ein FirmenKontakt gelöscht werden
 * @param id - Die ID des Firmenkontakts
 * @returns {JSX.Element} - Ein IconButton zur löschung
 * @constructor
 */
export const ContactDelete = ({id}) => {
    function handleContactDelete(id) {
        fetch(`http://localhost:8080/contact?id=${id}`, {
            method: 'DELETE',
            redirect: 'follow',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + getCurrentUserToken(),
            },
        }).then(res => {
            return res.json()
        })
        return console.log("Success")
    }

    return (
        <IconButton onClick={() => {
            handleContactDelete(id);
            window.location.reload();
        }}>
            <DeleteOutlineIcon/>
        </IconButton>);
}