import "./App.css"
import {BrowserRouter, Route, Routes} from "react-router-dom";
import JobList from './components/list/JobList'
import Layout from "./components/Layout";
import FormUpdateDialog from "./components/update/FormUpdateDialog";
import SignIn from "./components/signin/SignIn";
import SignUp from "./components/signup/SignUp";
import AccountDetails from "./components/dashboard/account";
import Firmenliste from "./components/list/Firmenliste";
import AdminOptions from "./components/dashboard/adminoptions";
import AccountSettings from "./components/dashboard/accountsettings";

export default function App() {
  return (
      <div className="App">
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<Layout />}>
              <Route path="/joblist" element={<JobList/>} />
                <Route path={"formdialog"} element={<FormUpdateDialog/>}/>
                <Route path="/signin" element={<SignIn/>}/>
                <Route path="/signup" element={<SignUp/>}/>
                <Route path="*" element={<h1>Not Found</h1>} />
                <Route path="/companylist" element={<Firmenliste/>} />
                <Route path="/adminmenu" element={<AdminOptions/>} />
                <Route path="/dashboard" element={<AccountDetails/>} />
                <Route path="/usersettings" element={<AccountSettings/>} />
            </Route>
          </Routes>
        </BrowserRouter>
      </div>
  );
}
