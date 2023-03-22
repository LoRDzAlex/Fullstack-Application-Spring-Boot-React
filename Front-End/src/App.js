import "./App.css"
import { createRoot } from 'react-dom/client';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import JobList from './components/JobList'
import Layout from "./components/Layout";
import FormUpdateDialog from "./components/FormUpdateDialog";

export default function App() {
  return (
      <div className="App">
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<Layout />}>
              <Route path="/joblist" element={<JobList/>} />
                <Route path={"formdialog"} element={<FormUpdateDialog/>}/>
            </Route>
          </Routes>
        </BrowserRouter>
      </div>
  );
}
const container = document.getElementById('root');
const root = createRoot(container);
root.render(<App />);
