import React from 'react';

const Cartao = ({ children, key}) => {
    return (
        <div key={key} className="border border-gray-700 rounded-lg hover:ring-2 ring-blue-500">
            <div className="p-4 grid gap-3">
                {children}
            </div>
        </div>
    );
};

export default Cartao;
