import "../App.css"
import React from "react";
import {Link } from "react-router-dom";
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import FormatListBulletedIcon from '@mui/icons-material/FormatListBulleted';
import FormCreateDialog from "./FormCreateDialog";
import {IconButton} from "@mui/material";

export default function Appbar() {  
  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static">
        <Toolbar>
          <IconButton color="inherit">
            <FormatListBulletedIcon/>
            <Link to="/joblist"></Link>
          </IconButton>
              <FormCreateDialog/>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }} align={"right"}>
            JobApplication
          </Typography>
        </Toolbar>
      </AppBar>
    </Box>
  );
}
