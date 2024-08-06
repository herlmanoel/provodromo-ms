import React, { useState, useCallback, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import AuthService from "../service/AuthService.jsx";
import Button from "../components/Button.jsx"; // Para navegação programática

function Login() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const [loginSuccess, setLoginSuccess] = useState(false);
    const navigate = useNavigate(); // Usado para navegação programática

    const handleSubmit = useCallback(async (e) => {
        e.preventDefault();
        try {
            await AuthService.login(email, password);
            setLoginSuccess(true); // Atualiza o estado para indicar sucesso
        } catch (err) {
            setError('Falha ao fazer login. Verifique suas credenciais.');
        }
    }, [email, password]);

    useEffect(() => {
        if (loginSuccess) {
            navigate('/dashboard'); // Redireciona após login bem-sucedido
        }
    }, [loginSuccess, navigate]);

    return (
        <div className="flex flex-col min-h-screen items-center justify-center">
            <div className="p-8 border border-gray-700 rounded-lg shadow-lg w-96">
                <h2 className="text-2xl font-semibold text-center text-black mb-6">Login</h2>
                {error && (
                    <div className="mb-4 p-2 bg-red-200 text-red-800 border border-red-400 rounded">
                        {error}
                    </div>
                )}
                <form onSubmit={handleSubmit}>
                    <div className="mb-4">
                        <label className="block text-black mb-2" htmlFor="email">Email</label>
                        <input
                            type="email"
                            id="email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            className="w-full px-3 py-2 border-2 border-black rounded-md focus:outline-none focus:ring-2 focus:ring-navy-500"
                            placeholder="Enter your email"
                            required
                        />
                    </div>
                    <div className="mb-6">
                        <label className="block text-black mb-2" htmlFor="password">Password</label>
                        <input
                            type="password"
                            id="password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            className="w-full px-3 py-2 border-2 border-black rounded-md focus:outline-none focus:ring-2 focus:ring-navy-500"
                            placeholder="Enter your password"
                            required
                        />
                    </div>
                    <Button titulo={"Logar"} />
                </form>
            </div>
        </div>
    );
}

export default Login;
