import './style/App.css';
import Login from "./pages/Login.jsx";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Home from "./pages/Home.jsx";
import UserForm from "./pages/Usuario/UserForm.jsx";
import UserList from "./pages/Usuario/UserList.jsx";
import ExamList from "./pages/Prova/ExamList.jsx";
import ExamForm from "./pages/Prova/ExamForm.jsx";
import UserTypeList from "./pages/UserType/UserTypeList.jsx";
import UserTypeForm from "./pages/UserType/UserTypeForm.jsx";
import TurmaList from "./pages/Turma/TurmaList.jsx";
import TurmaForm from "./pages/Turma/TurmaForm.jsx";

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Login/>}/>
                <Route path="/dashboard" element={<Home/>}/>
                <Route path="/turma/list" element={<TurmaList/>}/>
                <Route path="/turma/form" element={<TurmaForm/>}/>
                <Route path="/user/list" element={<UserList/>}/>
                <Route path="/user/form" element={<UserForm/>}/>
                <Route path="/exam/list" element={<ExamList/>}/>
                <Route path="/exam/form" element={<ExamForm/>}/>
                <Route path="/type/list" element={<UserTypeList/>}/>
                <Route path="/type/form" element={<UserTypeForm/>}/>
            </Routes>
        </BrowserRouter>
    );
}

export default App;
