import React from 'react';
import Cartao from "../../components/Cartao.jsx";
import ForwardButton from "../../components/ForwardButton.jsx";
import { FaRegEdit, FaCheckDouble } from "react-icons/fa";

const exams = [
    {
        href: '#',
        text: 'Prova - Unidade 1',
        startDate: '2024-09-01',
        endDate: '2024-09-30',
        class: 'Turma C',
        schedule: '14:00 - 16:00'
    },
    {
        href: '#',
        text: 'Link 3',
        startDate: '2024-09-01',
        endDate: '2024-09-30',
        class: 'Turma C',
        schedule: '14:00 - 16:00'
    },
    {
        href: '#',
        text: 'Link 3',
        startDate: '2024-09-01',
        endDate: '2024-09-30',
        class: 'Turma C',
        schedule: '14:00 - 16:00'
    },
    {
        href: '#',
        text: 'Link 3',
        startDate: '2024-09-01',
        endDate: '2024-09-30',
        class: 'Turma C',
        schedule: '14:00 - 16:00'
    }
];

function ExamList() {
    return (
        <div className="p-16 bg-white flex flex-col items-center">
            <div className="p-8 w-5/6 md:w-2/3 lg:w-4/5 xl:w-4/5">
                <div className="flex items-center justify-between gap-8 p-4 md:p-6">
                    <h1 className="text-2xl text-gray-700 font-bold">Provas - Dashboard Professor</h1>
                    <ForwardButton title={"Criar"} link={"/exam/form"}/>
                </div>
                <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4 p-4 md:p-6">
                    {exams.map((exam) => (
                        <Cartao key={exam.id}>
                            <div className="text-lg text-gray-700 ">
                                {exam.text}
                            </div>
                            <div className="text-sm text-gray-700 ">Data de Início: {exam.startDate}</div>
                            <div className="text-sm text-gray-700 ">Data de Término: {exam.endDate}</div>
                            <div className="text-sm text-gray-500 ">Turma: {exam.class}</div>
                            <div className="flex justify-end space-x-2">
                                <a
                                    href={`/exam/editar/${exam.id}`}
                                    className="inline-flex items-center justify-center whitespace-nowrap text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 border border-input bg-background hover:bg-accent hover:text-accent-foreground h-9 rounded-md px-3"
                                >
                                    <FaRegEdit />
                                    <span className="sr-only text-gray-700">Edit</span>
                                </a>
                                <a
                                    href={`/exam/excluir/${exam.id}`}
                                    className="inline-flex items-center justify-center whitespace-nowrap text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 border border-input bg-background hover:bg-accent hover:text-accent-foreground h-9 rounded-md px-3"
                                >
                                    <FaCheckDouble/>
                                    <span className="sr-only text-gray-700">Delete</span>
                                </a>
                            </div>
                        </Cartao>
                    ))}
                </div>
            </div>
        </div>
    );
}

export default ExamList;
