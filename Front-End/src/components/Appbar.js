import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import Menu from '@mui/material/Menu';
import MenuIcon from '@mui/icons-material/Menu';
import Container from '@mui/material/Container';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import Tooltip from '@mui/material/Tooltip';
import MenuItem from '@mui/material/MenuItem';
import AdbIcon from '@mui/icons-material/Adb';
import {useNavigate} from "react-router-dom";
import {useEffect, useState} from "react";
import AuthService from "./api/auth/auth.service";

function ResponsiveAppBar() {
    const [anchorElNav, setAnchorElNav] = useState(null);
    const [anchorElUser, setAnchorElUser] = useState(null);
    const navigate = useNavigate();
    const [currentUser, setCurrentUser] = useState(undefined);
    const [showCompanyBoard, setShowCompanyBoard] = useState(false);
    const [showAdminBoard, setShowAdminBoard] = useState(false);

    const handleOpenNavMenu = (event) => {
        setAnchorElNav(event.currentTarget);
    };
    const handleOpenUserMenu = (event) => {
        setAnchorElUser(event.currentTarget);
    };

    const handleCloseNavMenu = () => {
        setAnchorElNav(null);
    };

    const handleCloseUserMenu = () => {
        setAnchorElUser(null);
    };

    useEffect(() => {
        const user = AuthService.getCurrentUser();
        if (user) {
            setCurrentUser(user);
            setShowCompanyBoard(user.roles.includes('ROLE_COMPANY'));
            setShowAdminBoard(user.roles.includes('ROLE_ADMIN'));
        }
    }, []);

    const logOut = () => {
        AuthService.logout();
        setShowCompanyBoard(false);
        setShowAdminBoard(false);
        setCurrentUser(undefined);
    };
    return (
        <AppBar position="static">
            <Container maxWidth="xl">
                <Toolbar disableGutters>
                    <AdbIcon sx={{ display: { xs: 'none', md: 'flex' }, mr: 1 }} />
                    <Typography
                        variant="h6"
                        noWrap
                        component="a"
                        href="/"
                        sx={{
                            mr: 2,
                            display: { xs: 'none', md: 'flex' },
                            fontFamily: 'monospace',
                            fontWeight: 700,
                            letterSpacing: '.3rem',
                            color: 'inherit',
                            textDecoration: 'none',
                        }}
                    >
                        PrakTec
                    </Typography>

                    <Box sx={{ flexGrow: 1, display: { xs: 'flex', md: 'none' } }}>
                        <IconButton
                            size="large"
                            aria-label="account of current user"
                            aria-controls="menu-appbar"
                            aria-haspopup="true"
                            onClick={handleOpenNavMenu}
                            color="inherit"
                        >
                            <MenuIcon />
                        </IconButton>
                        <Menu
                            id="menu-appbar"
                            anchorEl={anchorElNav}
                            anchorOrigin={{
                                vertical: 'bottom',
                                horizontal: 'left',
                            }}
                            keepMounted
                            transformOrigin={{
                                vertical: 'top',
                                horizontal: 'left',
                            }}
                            open={Boolean(anchorElNav)}
                            onClose={handleCloseNavMenu}
                            sx={{
                                display: { xs: 'block', md: 'none' },
                            }}
                        >
                            <MenuItem onClick={() => {handleCloseNavMenu(); navigate("/")}}>
                                <Typography textAlign="center">Home</Typography>
                            </MenuItem>
                            <MenuItem onClick={() => {handleCloseNavMenu(); navigate("/joblist")}}>
                                <Typography textAlign="center">Praktikumsliste</Typography>
                            </MenuItem>
                            <MenuItem onClick={() => {handleCloseNavMenu(); navigate("/companylist")}}>
                                <Typography textAlign="center">Firmenliste</Typography>
                            </MenuItem>
                        </Menu>
                    </Box>
                    <AdbIcon sx={{ display: { xs: 'flex', md: 'none' }, mr: 1 }} />
                    <Typography
                        variant="h5"
                        noWrap
                        component="a"
                        href=""
                        sx={{
                            mr: 2,
                            display: { xs: 'flex', md: 'none' },
                            flexGrow: 1,
                            fontFamily: 'monospace',
                            fontWeight: 700,
                            letterSpacing: '.3rem',
                            color: 'inherit',
                            textDecoration: 'none',
                        }}
                    >
                        LOGO
                    </Typography>
                    <Box sx={{ flexGrow: 1, display: { xs: 'none', md: 'flex' } }}>
                            <Button
                                onClick={() => {handleCloseNavMenu(); navigate("/")}}
                                sx={{ my: 2, color: 'white', display: 'block' }}
                            >
                                Home
                            </Button>
                        <Button
                            onClick={() => {handleCloseNavMenu(); navigate("/joblist")}}
                            sx={{ my: 2, color: 'white', display: 'block' }}
                        >
                            Praktikumsliste
                        </Button>
                        <Button
                            onClick={() => {handleCloseNavMenu(); navigate("/companylist")}}
                            sx={{ my: 2, color: 'white', display: 'block' }}
                        >
                            Firmenliste
                        </Button>
                    </Box>

                    <Box sx={{ flexGrow: 0 }}>
                        <Tooltip title="Open settings">
                            <IconButton onClick={handleOpenUserMenu} sx={{ p: 0 }}>
                                <Avatar alt="Remy Sharp" src="" />
                            </IconButton>
                        </Tooltip>
                        <Menu
                            sx={{ mt: '45px' }}
                            id="menu-appbar"
                            anchorEl={anchorElUser}
                            anchorOrigin={{
                                vertical: 'top',
                                horizontal: 'right',
                            }}
                            keepMounted
                            transformOrigin={{
                                vertical: 'top',
                                horizontal: 'right',
                            }}
                            open={Boolean(anchorElUser)}
                            onClose={handleCloseUserMenu}
                        >
                            {showCompanyBoard && (
                                <MenuItem onClick={() => {handleCloseNavMenu(); navigate("/companymenu")}}>
                                    <Typography textAlign="center">Company Menu</Typography>
                                </MenuItem>
                            )
                            }
                            {showAdminBoard && (
                                <>
                                <MenuItem onClick={() => {handleCloseNavMenu(); navigate("/adminmenu")}}>
                                    <Typography textAlign="center">Admin Menu</Typography>
                                </MenuItem>
                                </>
                            )}
                            {currentUser && (
                                <MenuItem onClick={() => {handleCloseNavMenu(); navigate("/usersettings")}}>
                                    <Typography textAlign="center">Settings</Typography>
                                </MenuItem>
                            )}
                            {currentUser ? (
                                <>
                                    <MenuItem onClick={() => {handleCloseNavMenu(); navigate("/dashboard")}}>
                                        <Typography textAlign="center">Profile</Typography>
                                    </MenuItem>
                                    <MenuItem onClick={() => {handleCloseNavMenu(); logOut(); navigate("/signin")}}>
                                        <Typography textAlign="center">LogOut</Typography>
                                    </MenuItem>
                                </>
                            ) : (
                                <>
                                    <MenuItem onClick={() => {handleCloseNavMenu(); navigate("/signin")}}>
                                        <Typography textAlign="center">Login</Typography>
                                    </MenuItem>
                                    <MenuItem onClick={() => {handleCloseNavMenu(); navigate("/signup")}}>
                                        <Typography textAlign="center">Sign Up</Typography>
                                    </MenuItem>
                                </>
                            )}
                        </Menu>
                    </Box>
                </Toolbar>
            </Container>
        </AppBar>
    );
}
export default ResponsiveAppBar;