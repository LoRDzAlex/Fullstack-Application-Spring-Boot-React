import React from "react";
import {Outlet} from "react-router-dom";
import Appbar from "./Appbar";
import JobList from "./JobList";

const Layout = () => {
  return (
    <>
      <Appbar />
        <JobList/>
      <Outlet />
    </>
  );
};

export default Layout;