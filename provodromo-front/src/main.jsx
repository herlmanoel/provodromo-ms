import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.jsx'
import './style/index.css'
import Navbar from "./components/NavBar.jsx";

ReactDOM.createRoot(document.getElementById('root')).render(
    <React.StrictMode>
        <div className="flex flex-col min-h-screen">
            <Navbar/>
            <App/>
        </div>
    </React.StrictMode>
)
