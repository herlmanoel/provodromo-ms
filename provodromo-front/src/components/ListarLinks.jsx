// ListaProvasFinalizadas.jsx
import React from 'react';
import Cartao from "./Cartao.jsx";

const ListarLinks = ({ links }) => {
    return (
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4 p-4 md:p-6">
            {links.map((link, index) => (
                <Cartao key={index}>
                    <div className="text-lg text-gray-700 ">
                        {link.text}
                    </div>
                    <div className="text-sm text-gray-700 ">Data de Início: {link.startDate}</div>
                    <div className="text-sm text-gray-700 ">Data de Término: {link.endDate}</div>
                    <div className="text-sm text-gray-500 ">Turma: {link.class}</div>
                </Cartao>
            ))}
        </div>
    );
};

export default ListarLinks;
