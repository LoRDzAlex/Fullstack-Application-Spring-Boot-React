import * as React from 'react';
import {useState} from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import {createTheme, ThemeProvider} from '@mui/material/styles';
import {useNavigate} from "react-router-dom";

/**
 * Diese Funktion erstellt das Registrieren.
 * @param props - Eigenschaften des Registrierens
 * @returns {JSX.Element} - Registrieren
 * @constructor
 */
function Copyright(props) {
    return (
        <Typography variant="body2" color="text.secondary" align="center" {...props}>
            {'Copyright © '}
            <Link color="inherit" href="https://github.com/LoRDzAlex/Fullstack-Application-Spring-Boot-React">
                PrakTec
            </Link>{' '}
            {new Date().getFullYear()}
            {'.'}
        </Typography>
    );
}

const theme = createTheme();

export default function SignUp() {

    const [username, setUserName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState(null);
    const navigate = useNavigate();
    // Hier wird die Eingabe validiert
    function validateAll(username, email, password) {
        const errors = {};

        if (!username.trim()) {
            errors.username = "Please enter a username";
        } else if (username.length < 3) {
            errors.username = "Username must be at least 3 characters long";
        } else if (!/^[a-zA-Z0-9]+$/.test(username)) {
            errors.username = "Username must contain only letters and numbers";
        } else if (username.length > 20) {
            errors.username = "Username must be less than 20 characters long";
        }

        if (!email.trim()) {
            errors.email = "Please enter an email address";
        } else if (!/\S+@\S+\.\S+/.test(email)) {
            errors.email = "Please enter a valid email address";
        }

        if (!password.trim()) {
            errors.password = "Please enter a password";
        } else if (password.length < 8) {
            errors.password = "Password must be at least 8 characters long";
        } else if (!/^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/.test(password)) {
            errors.password = "Password must contain at least one uppercase letter, one lowercase letter, one number and one special character";
        }

        return errors;
    }
    //Hier wird der Login abgeschickt
    const handleSubmit = (event) =>{
        event.preventDefault();
        const errors = validateAll(username, email, password);
        if(Object.keys(errors).length === 0){
        fetch(`http://localhost:8080/api/auth/signup`, {
            method: 'POST',
            redirect: 'follow',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({"username": username,"password":password, "email" : email})
        }).then(res => {
            if (res.ok) {
                console.log("Success")
                navigate("/signin")
            } else if (res.status === 409) {
                alert("Username or email already exists"); // Setze die Fehlermeldung für den Fall, dass Benutzername oder E-Mail bereits vorhanden sind
            } else {
                console.log("Error")
            }
        }).catch(err => setError(err.message));
        } else {
            alert(Object.values(errors).join('\n'))
        }
    }



    return (
        <ThemeProvider theme={theme}>
            <Container component="main" maxWidth="xs">
                <CssBaseline />
                <Box
                    sx={{
                        marginTop: 8,
                        display: 'flex',
                        flexDirection: 'column',
                        alignItems: 'center',
                    }}
                >
                    <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
                        <LockOutlinedIcon />
                    </Avatar>
                    <Typography component="h1" variant="h5">
                        Sign up
                    </Typography>
                    <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 3 }}>
                        <Grid container spacing={2}>
                            <Grid item xs={12}>
                                <TextField
                                    autoComplete="username"
                                    name="username"
                                    required
                                    fullWidth
                                    id="username"
                                    onChange={(e) => {setUserName(e.target.value);}}
                                    label="Username"
                                    autoFocus
                                />
                            </Grid>
                            <Grid item xs={12}>
                                <TextField
                                    required
                                    fullWidth
                                    id="email"
                                    onChange={(e) => {setEmail(e.target.value);}}
                                    label="Email Address"
                                    name="email"
                                    autoComplete="email"
                                />
                            </Grid>
                            <Grid item xs={12}>
                                <TextField
                                    required
                                    fullWidth
                                    name="password"
                                    onChange={(e) => {setPassword(e.target.value);}}
                                    label="Password"
                                    type="password"
                                    id="password"
                                    autoComplete="new-password"
                                />
                            </Grid>
                        </Grid>
                        <Button
                            type="submit"
                            fullWidth
                            variant="contained"
                            sx={{ mt: 3, mb: 2 }}
                        >
                            Sign Up
                        </Button>
                        <Grid container justifyContent="flex-end">
                            <Grid item>
                                <Link href="/signin" variant="body2">
                                    Already have an account? Sign in
                                </Link>
                            </Grid>
                        </Grid>
                    </Box>
                </Box>
                <Copyright sx={{ mt: 5 }} />
            </Container>
        </ThemeProvider>
    );
}