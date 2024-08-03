import React from "react";
import Tabs from "../components/Tabs.jsx";
import ListarLinks from "../components/ListarLinks.jsx";

//Requisição GET | provas, dividir entre Abertar e finalizadas
const linksData = [
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

const propsTabs = [
    {
        titulo: "Recentes",
        corpo: <ListarLinks links={linksData} />
    },
    {
        titulo: "Finalizadas",
        corpo: <ListarLinks links={linksData} />
    },
]

function Home() {
    return (
        <div className={"p-16"}>
            <Tabs tabs={propsTabs}/>
        </div>
    );
}

export default Home
;