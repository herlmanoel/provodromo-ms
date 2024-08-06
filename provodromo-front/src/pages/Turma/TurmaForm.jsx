import React, { useCallback, useEffect, useState } from 'react';
import { useNavigate } from "react-router-dom";
import InstitucionalService from "../../service/InstitucionalService.jsx";
import Button from "../../components/Button.jsx";

function TurmaForm() {
    const [nome, setNome] = useState('');
    const [professores, setProfessores] = useState([]);
    const [professoresSelecionados, setProfessoresSelecionados] = useState([]);
    const [error, setError] = useState('');
    const [registerSuccess, setRegisterSuccess] = useState(false);
    const navigate = useNavigate();

    const carregarProfessores = useCallback(async () => {
        try {
            const response = await InstitucionalService.listarProfessores(); // Usar o serviço para listar professores
            setProfessores(response);
        } catch (error) {
            console.error('Erro ao carregar professores:', error);
            setError('Falha ao carregar professores.');
        }
    }, []);

    useEffect(() => {
        carregarProfessores();
    }, [carregarProfessores]);

    const handleSubmit = useCallback(async (e) => {
        e.preventDefault();
        try {
            await InstitucionalService.postTurma({
                nome,
                professores: professoresSelecionados
            });

            console.log('Registro bem-sucedido');
            setRegisterSuccess(true);
        } catch (error) {
            console.error('Erro ao registrar turma:', error);
            setError('Falha ao registrar turma. Verifique suas informações.');
        }
    }, [nome, professoresSelecionados]);

    useEffect(() => {
        if (registerSuccess) {
            navigate('/turma/listar'); // Redireciona para a página de listagem após o registro bem-sucedido
        }
    }, [registerSuccess, navigate]);

    return (
        <div className="flex flex-col min-h-screen items-center justify-center">
            <div className="p-8 border border-gray-700 rounded-lg shadow-lg w-96">
                <h2 className="text-2xl font-semibold text-center text-black mb-6">Registrar Turma</h2>
                {error && (
                    <div className="mb-4 p-2 bg-red-200 text-red-800 border border-red-400 rounded">
                        {error}
                    </div>
                )}
                <form onSubmit={handleSubmit}>
                    <div className="mb-4">
                        <label className="block text-black mb-2" htmlFor="nome">Nome da Turma</label>
                        <input
                            type="text"
                            id="nome"
                            value={nome}
                            onChange={(e) => setNome(e.target.value)}
                            className="w-full px-3 py-2 border-2 border-black rounded-md focus:outline-none focus:ring-2 focus:ring-navy-500"
                            placeholder="Digite o nome da turma"
                            required
                        />
                    </div>
                    <div className="mb-4">
                        <label className="block text-black mb-2" htmlFor="professores">Professores</label>
                        <select
                            id="professores"
                            value={professoresSelecionados}
                            onChange={(e) => setProfessoresSelecionados(Array.from(e.target.selectedOptions, option => option.value))}
                            className="w-full px-3 py-2 border-2 border-black rounded-md focus:outline-none focus:ring-2 focus:ring-navy-500"
                            required
                        >
                            <option value="">Selecione os professores</option>
                            {professores.map(professor => (
                                <option key={professor.id} value={professor.id}>
                                    {professor.nome}
                                </option>
                            ))}
                        </select>
                    </div>
                    <Button titulo={"Registrar"} />
                </form>
            </div>
        </div>
    );
};

export default TurmaForm;
