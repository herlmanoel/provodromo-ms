import React from 'react';

const Navbar = () => {
    return (
        <nav className="bg-white border-b border-gray-700 shadow-lg">
            <div className="max-w-7xl mx-auto px-4 py-3 flex justify-between items-center">
                <div > <a className="text-2xl font-bold text-black" href="/"> Provodromo </a></div>
                <div className="space-x-4">
                    <a href="/" className="text-black hover:text-navy-500">Home</a>
                    <a href="/user/list" className="text-black hover:text-navy-500">Usuários</a>
                    <a href="/exam/list" className="text-black hover:text-navy-500">Provas</a>
                    <a href="/type/list" className="text-black hover:text-navy-500">Tipo Usuário</a>
                </div>
            </div>
        </nav>
    );
};

export default Navbar;
