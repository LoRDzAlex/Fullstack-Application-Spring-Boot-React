import "./App.css"
import { createRoot } from 'react-dom/client';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import JobList from './components/list/JobList'
import Layout from "./components/Layout";
import FormUpdateDialog from "./components/update/FormUpdateDialog";
import SignIn from "./components/signin/SignIn";
import SignUp from "./components/signup/SignUp";

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
            </Route>
          </Routes>
        </BrowserRouter>
      </div>
  );
}
const container = document.getElementById('root');
const root = createRoot(container);
root.render(<App />);
