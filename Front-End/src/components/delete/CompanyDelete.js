import DeleteOutlineIcon from "@mui/icons-material/DeleteOutline";
import {IconButton} from "@mui/material";
import * as React from "react";
import {getCurrentUserToken} from "../api/auth/auth.service";

/**
 * Hier kann eine Firma per ID gelöscht werden.
 * @param id - Die ID der Firma
 * @returns {JSX.Element} - Ein IconButton zur löschung
 * @constructor
 */
export const CompanyDelete = (id) => {
    function handleCompanyDelete(id) {
        fetch(`http://localhost:8080/company?id=${id}`, {
            method: 'DELETE',
            redirect: 'follow',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + getCurrentUserToken(),
            },
        }).then(res => {
            return console.log(res.json())
        })
        return console.log("Success")
    }

    return(
    <IconButton onClick={() => {
        handleCompanyDelete(id);
        window.location.reload();
    }}>
        <DeleteOutlineIcon/>
    </IconButton>
    );
}
export default CompanyDelete