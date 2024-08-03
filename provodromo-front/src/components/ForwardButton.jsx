import React from "react";

const ForwardButton = ({title, link}) => {
    return (
        <a
            href={link}
            className="inline-flex items-center justify-center whitespace-nowrap rounded-md border-gray-700 text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 border border-input bg-background hover:bg-accent hover:text-accent-foreground h-10 px-4 py-2"
        >
            {title}
        </a>
    )
}
export default ForwardButton;