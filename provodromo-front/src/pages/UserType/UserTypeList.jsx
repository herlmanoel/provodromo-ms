import React, {useCallback, useEffect, useState} from 'react';
import Cartao from "../../components/Cartao.jsx";
import { FaRegEdit, FaRegTrashAlt } from "react-icons/fa";
import UserService from "../../service/UserService.jsx";

const UserTypeList = () => {
    const [userTypes, setUserTypes] = useState([]);
    const [error, setError] = useState(null);

    const fetchUserTypes = useCallback(() => {
        UserService.getUserTypes()
            .then(response => setUserTypes(response))
            .catch(err => setError(err));
    }, []);

    useEffect(() => {
        fetchUserTypes();
    }, [fetchUserTypes]);

    if (error) {
        return <div className="text-red-600 p-4">Erro: {error.message}</div>;
    }

    return (
        <div className="text-gray-700 p-16 flex justify-center">
            <div className="p-8 w-5/6 md:w-2/3 lg:w-4/5 xl:w-4/5">
                <div className="flex items-center justify-between gap-8 p-4 md:p-6">
                    <h1 className="text-2xl font-bold">Tipos de Usuários</h1>
                    <a
                        href="/type/form"
                        className="inline-flex items-center justify-center whitespace-nowrap rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 border border-gray-300 bg-white hover:bg-gray-100 hover:text-gray-900 h-10 px-4 py-2"
                    >
                        Cadastrar
                    </a>
                </div>

                <div className=" grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4 p-4 md:p-6">
                    {userTypes.map((userType) => (
                        <Cartao>
                            <div className="grid gap-1.5">
                                <h3 className="text-lg font-bold tracking-tight">{userType.nome}</h3>
                            </div>
                            <div className="flex-grow"></div>
                            <div className="flex justify-end space-x-2">
                                <a
                                    href={`/tipoUsuario/editar/${userType.id}`}
                                    className="inline-flex items-center justify-center whitespace-nowrap text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 border border-gray-300 bg-white hover:bg-gray-100 hover:text-gray-900 h-9 rounded-md px-3"
                                >
                                    <FaRegEdit />
                                    <span className="sr-only">Edit</span>
                                </a>
                                <a
                                    href={`/tipoUsuario/excluir/${userType.id}`}
                                    className="inline-flex items-center justify-center whitespace-nowrap text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 border border-gray-300 bg-white hover:bg-gray-100 hover:text-gray-900 h-9 rounded-md px-3"
                                >
                                    <FaRegTrashAlt />
                                    <span className="sr-only">Delete</span>
                                </a>
                            </div>
                        </Cartao>
                    ))}
                </div>
            </div>
        </div>
    );
};

export default UserTypeList;
