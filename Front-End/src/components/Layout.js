import React from "react";
import {Outlet} from "react-router-dom";
import Appbar from "./Appbar";
import JobList from "./JobList";
import GlobalNavigation from "./GlobalNavigation";

const Layout = () => {
  return (
    <>
      <GlobalNavigation /> // Appbar used to be here
        <JobList/>
      <Outlet />
    </>
  );
};

export default Layout;