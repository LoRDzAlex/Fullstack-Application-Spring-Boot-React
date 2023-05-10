import React from "react";
import {Outlet} from "react-router-dom";
import Appbar from "./Appbar";
/*
    Hier wird das Layout erstellt.
 */
const Layout = () => {
  return (
    <>
      <Appbar />
      <Outlet />
    </>
  );
};

export default Layout;