import React, {useState} from 'react';
import Questionnaire from "../../components/Questionnaire.jsx";
import Button from "../../components/Button.jsx";

const ExamForm = () => {
    const [turma, setTurma] = useState('');
    const handleTurmaChange = (event) => {
        setTurma(event.target.value);
    };

    return (
        <div className="p-16">
            <div className="flex flex-col items-center justify-between gap-8 md:p-6">
                <button
                    className="bg-green-500 text-white px-4 py-2 rounded-md mt-4 block w-full max-w-[35%]  hover:bg-green-600"
                >
                    Salvar Prova
                </button>

                <div className="p-8 border border-gray-700 rounded-lg shadow-lg">
                    <div className="text-center space-y-2">
                        <h1 className="text-3xl font-bold text-black">Formulário de Provas</h1>
                        <p className="text-gray-500">Entre com as informações da prova que deseja cadastrar.</p>
                    </div>
                    <form className="space-y-4" action="/prova/salvar" method="post">
                        <input type="hidden" name="id"/>

                        <div className="space-y-2 text-gray-700">
                            <label htmlFor="titulo" className="text-sm font-medium ">Título</label>
                            <input
                                type="text"
                                id="titulo"
                                placeholder="Insira o título da prova"
                                required
                                className="bg-transparent  block w-full px-3 py-2 border rounded-md border-gray-300 focus:ring-2 focus:ring-blue-500 focus:outline-none"
                                name="titulo"
                            />
                        </div>

                        <div className="grid grid-cols- gap-2 sm:grid-cols-2 text-gray-700">
                            <div>
                                <label htmlFor="turma" className="text-sm font-medium">Turma</label>

                                <select
                                    id="turma"
                                    name="turma"
                                    value={turma}
                                    onChange={handleTurmaChange}
                                    className="block w-full bg-white border border-gray-300 rounded-md py-2 px-3 focus:outline-none focus:border-blue-500"
                                >
                                    <option value="">Selecione uma turma</option>
                                    {/* Example: Replace this part with actual data mapping */}
                                    <option value="1">Turma A</option>
                                    <option value="2">Turma B</option>
                                </select>
                            </div>
                            <div>
                                <label htmlFor="nota" className="text-sm font-medium">Selecione uma nota de 1 a
                                    10</label>
                                <input
                                    type="number"
                                    id="nota"
                                    name="Nota"
                                    min="1"
                                    max="10"
                                    required
                                    className="bg-transparent block w-full px-3 py-2 border rounded-md border-gray-300 focus:ring-2 focus:ring-blue-500 focus:outline-none"
                                />
                            </div>
                        </div>
                    </form>


                </div>
                <Questionnaire/>

            </div>
        </div>
    );
};

export default ExamForm;
