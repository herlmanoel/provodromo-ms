import React from "react";

const Button = ({titulo, handleClick}) => {
    return (
        <button
            type="submit"
            className="w-full bg-blue-500 text-white px-4 py-2 rounded-md mb-4 font-medium hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2"
            onClick={handleClick}
        >
            {titulo}
        </button>
    )
}
export default Button;