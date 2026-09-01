import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "./pages/Login";
import Dashbhoard from "./pages/Dashboard";
import Companies from "./pages/Companies";
import Drives from "./pages/Drives";
import Applications from "./pages/Applications";
import Interviews from "./pages/Interviews";
import Layout from "./components/Layout";
import Auth from "./pages/Auth";


function App(){
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Auth/>}/>
        <Route element={<Layout/>}>
          <Route path="/dashboard" element={<Dashbhoard/>}/>
          <Route path="/companies" element={<Companies/>} />
          <Route path="/drives" element={<Drives/>} />
          <Route path="/applications" element={<Applications />} />
          <Route path="/interviews" element={<Interviews />} />
        </Route>
      </Routes>
    </BrowserRouter>
  )
}

export default App;