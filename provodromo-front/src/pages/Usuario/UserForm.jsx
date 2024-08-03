import React, {useState} from 'react';
import Button from "../../components/Button.jsx";

// Requisição - GET | Tipo usuário
// Requisição - POST | Salvar Usuário
const UserForm = () => {
    const [userType, setUserType] = useState('');

    const handleUserTypeChange = (event) => {
        setUserType(event.target.value);
    };


    return (
        <div className="p-16 flex flex-col items-center justify-center">
            <div className="p-8 border border-gray-700 rounded-lg shadow-lg">
                <div className="text-center space-y-2">
                    <h1 className="text-3xl font-bold text-black">Formulário de Usuário</h1>
                    <p className="text-gray-500">Entre com as informações da sua conta.</p>
                </div>
                <form className="space-y-4" action="/usuario/salvar" method="post">
                    <input type="hidden" name="id"/>

                    <div className="space-y-2">
                        <label htmlFor="name" className="text-sm font-medium text-gray-700">Nome</label>
                        <input
                            type="text"
                            id="name"
                            placeholder="Enter your name"
                            required
                            className="block w-full px-3 py-2 border rounded-md border-gray-300 focus:ring-2 focus:ring-blue-500 focus:outline-none"
                            name="nome"
                        />
                    </div>

                    <div className="space-y-2">
                        <label htmlFor="email" className="text-sm font-medium text-gray-700">Email</label>
                        <input
                            type="email"
                            id="email"
                            placeholder="Enter your email"
                            required
                            className="block w-full px-3 py-2 border rounded-md border-gray-300 focus:ring-2 focus:ring-blue-500 focus:outline-none"
                            name="email"
                        />
                    </div>

                    <div className="space-y-2">
                        <label htmlFor="password" className="text-sm font-medium text-gray-700">Senha</label>
                        <input
                            type="password"
                            id="password"
                            required
                            className="block w-full px-3 py-2 border rounded-md border-gray-300 focus:ring-2 focus:ring-blue-500 focus:outline-none"
                            name="senha"
                        />
                    </div>

                    <div className="grid grid-cols-1 gap-2 sm:grid-cols-2">
                        <label htmlFor="user_type" className="text-sm font-medium text-gray-700">Tipo de
                            usuário</label>
                        <div>
                            <select
                                id="user_type"
                                name="user_type"
                                value={userType}
                                onChange={handleUserTypeChange}
                                className="block w-full bg-white border border-gray-300 rounded-md py-2 px-3 focus:outline-none focus:border-blue-500"
                            >
                                <option value="">Selecione um tipo</option>
                                <option value="1">Admin</option>
                                <option value="2">User</option>
                            </select>
                        </div>
                    </div>


                    <Button titulo={"Cadastrar"}/>
                </form>
            </div>
        </div>
    );
};

export default UserForm;
