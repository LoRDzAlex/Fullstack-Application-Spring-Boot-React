import DeleteOutlineIcon from "@mui/icons-material/DeleteOutline";
import {IconButton} from "@mui/material";
import * as React from "react";


export const ContactDelete = ({id}) => {
    function handleContactDelete(id) {
        fetch(`http://localhost:8080/contact?id=${id}`, {
            method: 'DELETE',
            redirect: 'follow',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
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