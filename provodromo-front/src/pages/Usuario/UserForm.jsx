import React, {useCallback, useEffect, useState} from 'react';
import Button from "../../components/Button.jsx";
import {useNavigate} from "react-router-dom";
import UserService from "../../service/UserService.jsx";

// Requisição - GET | Tipo usuário
// Requisição - POST | Salvar Usuário
const UserForm = () => {
    const [nome, setNome] = useState('');
    const [email, setEmail] = useState('');
    const [senha, setSenha] = useState('');
    const [tipoUsuario, setTipoUsuario] = useState('');
    const [tiposUsuario, setTiposUsuario] = useState([]); // Armazena os tipos de usuário carregados
    const [error, setError] = useState('');
    const [registerSuccess, setRegisterSuccess] = useState(false);
    const navigate = useNavigate();

    const carregarDados = useCallback(async () => {
        try {
            const response = await UserService.getUserTypes();
            setTiposUsuario(response);
        } catch (error) {
            console.error('Erro ao carregar tipos de usuário:', error);
            setError('Falha ao carregar tipos de usuário.');
        }
    }, []);

    useEffect(() => {
        carregarDados();
    }, [carregarDados]);

    const handleSubmit = useCallback(async (e) => {
        e.preventDefault();
        try {
            const response = await UserService.postUsers({
                nome,
                email,
                senha,
                tipoUsuario
            });

            console.log('Registro bem-sucedido:', response.data);
            setRegisterSuccess(true);
        } catch (error) {
            console.error('Erro ao registrar:', error);
            setError('Falha ao registrar. Verifique suas informações.');
        }
    }, [nome, email, senha, tipoUsuario]);

    useEffect(() => {
        if (registerSuccess) {
            navigate('/login'); // Redireciona para a página de login após o registro bem-sucedido
        }
    }, [registerSuccess, navigate]);

    return (
        <div className="flex flex-col min-h-screen items-center justify-center">
            <div className="p-8 border border-gray-700 rounded-lg shadow-lg w-96">
                <h2 className="text-2xl font-semibold text-center text-black mb-6">Registre-se</h2>
                {error && (
                    <div className="mb-4 p-2 bg-red-200 text-red-800 border border-red-400 rounded">
                        {error}
                    </div>
                )}
                <form onSubmit={handleSubmit}>
                    <div className="mb-4">
                        <label className="block text-black mb-2" htmlFor="nome">Nome</label>
                        <input
                            type="text"
                            id="nome"
                            value={nome}
                            onChange={(e) => setNome(e.target.value)}
                            className="w-full px-3 py-2 border-2 border-black rounded-md focus:outline-none focus:ring-2 focus:ring-navy-500"
                            placeholder="Enter your name"
                            required
                        />
                    </div>
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
                        <label className="block text-black mb-2" htmlFor="senha">Senha</label>
                        <input
                            type="password"
                            id="senha"
                            value={senha}
                            onChange={(e) => setSenha(e.target.value)}
                            className="w-full px-3 py-2 border-2 border-black rounded-md focus:outline-none focus:ring-2 focus:ring-navy-500"
                            placeholder="Enter your password"
                            required
                        />
                    </div>
                    <div className="mb-4">
                        <label className="block text-black mb-2" htmlFor="tipoUsuario">Tipo de Usuário</label>
                        <select
                            id="tipoUsuario"
                            value={tipoUsuario}
                            onChange={(e) => setTipoUsuario(e.target.value)}
                            className="w-full px-3 py-2 border-2 border-black rounded-md focus:outline-none focus:ring-2 focus:ring-navy-500"
                            required
                        >
                            <option value="">Selecione um tipo de usuário</option>
                            {tiposUsuario.map(tipo => (
                                <option key={tipo.id} value={tipo.id}>
                                    {tipo.nome}
                                </option>
                            ))}
                        </select>
                    </div>
                    <Button titulo={"Registrar"} />
                </form>
            </div>
        </div>
    )
};

export default UserForm;
