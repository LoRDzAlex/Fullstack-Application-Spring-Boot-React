import * as React from "react";
import {getCurrentUserToken} from "../api/auth/auth.service";
import Button from "@mui/material/Button";

/**
 * Hier kann per namen alle beiträge einer Firma gelöscht werden.
 * @param name - Der Name der Firma
 * @returns {JSX.Element} - Ein IconButton zur löschung
 * @constructor
 */
export const CompanyListingDelete = (name) => {
    function handleCompanyDelete(name) {
        fetch(`http://localhost:8080/job/delete?companyName=${name}`, {
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
        <Button onClick={() => {
            handleCompanyDelete(name);
            window.location.reload();
        }}>
            Sperren der Firma
        </Button>
    );
}
export default CompanyListingDelete