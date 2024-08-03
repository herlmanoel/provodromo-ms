import React from 'react';
import Button from "../components/Button.jsx";

function Login() {
    return (
        <div className="flex flex-col min-h-screen items-center justify-center">
            <div className="p-8 border border-gray-700 rounded-lg shadow-lg w-96">
                <h2 className="text-2xl font-semibold text-center text-black mb-6">Login</h2>
                <form>
                    <div className="mb-4">
                        <label className="block text-black mb-2" htmlFor="email">Email</label>
                        <input
                            type="email"
                            id="email"
                            className="w-full px-3 py-2 border-2 border-black rounded-md focus:outline-none focus:ring-2 focus:ring-navy-500"
                            placeholder="Enter your email"
                        />
                    </div>
                    <div className="mb-6">
                        <label className="block text-black mb-2" htmlFor="password">Password</label>
                        <input
                            type="password"
                            id="password"
                            className="w-full px-3 py-2 border-2 border-black rounded-md focus:outline-none focus:ring-2 focus:ring-navy-500"
                            placeholder="Enter your password"
                        />
                    </div>
                    <Button titulo={"Logar"} />
                </form>
            </div>
        </div>
    );
}

export default Login;
