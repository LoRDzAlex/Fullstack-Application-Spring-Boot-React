import * as React from "react";
import {useEffect, useState} from "react";
import {Collapse, IconButton} from "@mui/material";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import KeyboardArrowDownIcon from "@mui/icons-material/KeyboardArrowDown";
import FormUpdateCompanyDialog from "../update/FormUpdateCompanyDialog";
import KeyboardArrowUpIcon from "@mui/icons-material/KeyboardArrowUp";
import AuthService from "../api/auth/auth.service";
import FormUpdateContactDialog from "../update/FormUpdateContactDialog";

export const CompanyContactList = ({ id, companyName, website, canton, contactName, gender, tel, email, contactId }) => {
    const [currentUser, setCurrentUser] = useState(undefined);
    const [showAdminOptions, setShowAdminOptions] = useState(false);
    const [expanded, setExpanded] = useState(false);
    const [showCompanyOptions, setShowCompanyOptions] = useState(false);
    useEffect(() => {
        const user = AuthService.getCurrentUser();
        if (user) {
            setCurrentUser(user);
            setShowAdminOptions(user.roles.includes("ROLE_ADMIN"));
            setShowCompanyOptions(user.roles.includes("ROLE_COMPANY"));
        }
    }, []);

    const handleExpandClick = () => {
        setExpanded(!expanded);
    };

    return (
        <TableRow>
            <TableCell colSpan={9}>
                <IconButton
                    aria-label="expand row"
                    size="big"
                    onClick={handleExpandClick}
                    style={{ width: "100%" , borderRadius: "0px"}}
                >
                    {expanded ? <KeyboardArrowUpIcon /> : <KeyboardArrowDownIcon />}
                </IconButton>
                <Collapse in={expanded} timeout="auto" unmountOnExit>
                    <Box sx={{ margin: 1 }}>
                        <Typography variant="h6" gutterBottom component="div">
                            Allgemeine Informationen
                        </Typography>
                        <Table size="small" aria-label="company table">
                            <TableHead sx={{ bgcolor: "#eaeaea" }}>
                                <TableRow>
                                    <TableCell>CompanyName</TableCell>
                                    <TableCell>Website</TableCell>
                                    <TableCell>Canton</TableCell>
                                    {showAdminOptions && <TableCell></TableCell>}
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                <TableRow>
                                    <TableCell>
                                        {companyName}
                                    </TableCell>
                                    <TableCell>{website}</TableCell>
                                    <TableCell>{canton}</TableCell>
                                    {showAdminOptions && (
                                        <TableCell>
                                            <FormUpdateCompanyDialog
                                                id={id}
                                                companyName={companyName}
                                                website={website}
                                                canton={canton}
                                            />
                                        </TableCell>
                                    )}
                                    {showCompanyOptions && (
                                        <TableCell>
                                            <FormUpdateCompanyDialog
                                                id={id}
                                                companyName={companyName}
                                                website={website}
                                                canton={canton}
                                            />
                                        </TableCell>
                                    )}
                                </TableRow>
                            </TableBody>
                        </Table>
                        <br/>
                        <Typography variant="h6" gutterBottom component="div">
                            Wenn kontaktier ich?
                        </Typography>
                        <Table size="small" aria-label="contact table">
                            <TableHead sx={{ bgcolor: "#eaeaea" }}>
                                <TableRow>
                                    <TableCell >Name</TableCell>
                                    <TableCell >Geschlecht</TableCell>
                                    <TableCell >Email</TableCell>
                                    <TableCell >Telefonnummer</TableCell>
                                    <TableCell/>
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                <TableRow>
                                    <TableCell>
                                        {contactName}
                                    </TableCell>
                                    <TableCell >{gender}</TableCell>
                                    <TableCell >{email}</TableCell>
                                    <TableCell >{tel}</TableCell>
                                    {showAdminOptions && (
                                        <TableCell>
                                            <FormUpdateContactDialog id={id} gender={gender} contactName={contactName} tel={tel} email={email}/>
                                        </TableCell>
                                    )}
                                    {showCompanyOptions && (
                                        <TableCell>
                                            <FormUpdateContactDialog id={id} gender={gender} contactName={contactName} tel={tel} email={email}/>
                                        </TableCell>
                                    )}
                                </TableRow>
                            </TableBody>
                        </Table>
                    </Box>
                </Collapse>
            </TableCell>
        </TableRow>

    );

};

export default CompanyContactList;