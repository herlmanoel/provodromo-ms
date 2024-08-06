import React from 'react';
import { FaRegEdit, FaRegTrashAlt } from "react-icons/fa";
import ForwardButton from "../../components/ForwardButton.jsx";
import Cartao from "../../components/Cartao.jsx";

// Exemplo de dados de turma
const turmas = [
    {
        id: 1,
        nome: 'Turma A',
        professor: 'professorA@example.com',
    },
    {
        id: 2,
        nome: 'Turma B',
        professor: 'professorB@example.com',
    },
    // Adicione mais turmas conforme necessário
];

function TurmaList() {
    return (
        <div className="p-16 flex flex-col items-center">
            <div className="p-8 w-5/6 md:w-2/3 lg:w-4/5 xl:w-4/5">
                <div className="flex items-center justify-between gap-8 p-4 md:p-6">
                    <h1 className="text-2xl text-gray-700 font-bold">Turmas</h1>
                    <ForwardButton title={"Registrar"} link={"/turma/form"}/>
                </div>
                <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4 p-4 md:p-6">
                    {turmas.map((turma) => (
                        <Cartao key={turma.id}>
                            <div>
                                <p className="text-lg text-gray-700">{turma.nome}</p>
                                <p className="text-sm text-gray-700">{turma.professor}</p>
                            </div>
                            <div className="flex justify-end space-x-2">
                                <a
                                    href={`/turma/editar/${turma.id}`}
                                    className="inline-flex items-center justify-center whitespace-nowrap text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 border border-input bg-background hover:bg-accent hover:text-accent-foreground h-9 rounded-md px-3"
                                >
                                    <FaRegEdit />
                                    <span className="sr-only">Edit</span>
                                </a>
                                <a
                                    href={`/turma/excluir/${turma.id}`}
                                    className="inline-flex items-center justify-center whitespace-nowrap text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 border border-input bg-background hover:bg-accent hover:text-accent-foreground h-9 rounded-md px-3"
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
}

export default TurmaList;
