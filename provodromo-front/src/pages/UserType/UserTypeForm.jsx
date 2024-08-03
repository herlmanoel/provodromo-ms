import React, {useState} from 'react';
import UserService from "../../service/UserService.jsx";
import {useNavigate} from "react-router-dom";

const UserTypeForm = () => {
    const [name, setName] = useState('');
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            const newUserType = { nome: name };
            await UserService.postType(newUserType);
            navigate('/type/list');
        } catch (err) {
            setError(err);
        }
    };

    if (error) {
        return <div className="text-red-600 p-4">Erro: {error.message}</div>;
    }
    return (
        <div className="p-16 mx-auto">
            <div className="p-8 border border-gray-700 rounded-lg shadow-lg flex items-center justify-between gap-8 md:p-6">
                <div className="mx-auto max-w-sm space-y-6">
                    <div className="text-center space-y-2">
                        <h1 className="text-3xl font-bold text-black">Formulário de Tipo de Usuário</h1>
                        <p className="text-gray-500">Entre com as informações do tipo de usuário.</p>
                    </div>
                    <form action="/tipoUsuario/salvar" method="post" className="space-y-4">
                        <input type="hidden" name="id" />

                        <div className="space-y-2">
                            <label htmlFor="name" className="text-sm font-medium text-gray-700">Nome</label>
                            <input
                                type="text"
                                id="name"
                                placeholder="Enter your name"
                                required
                                className="block w-full px-3 py-2 border rounded-md border-gray-300 focus:ring-2 focus:ring-blue-500 focus:outline-none"
                                name="Tipo"
                                onChange={() => setName}
                            />
                        </div>

                        <button
                            type="submit"
                            className="inline-flex w-full items-center justify-center px-4 py-2 text-sm font-medium text-white bg-blue-600 rounded-md hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500"
                            onClick={handleSubmit}
                        >
                            Submeter
                        </button>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default UserTypeForm;
