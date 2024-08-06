import axios from 'axios';

// Adicione as credenciais do cliente
const CLIENT_ID = 'myappname123';
const CLIENT_SECRET = 'myappname123';
const AUTH_HEADER = `Basic ${btoa(`${CLIENT_ID}:${CLIENT_SECRET}`)}`;


const API = axios.create({
    baseURL: 'http://localhost:8765',  // Defina o URL base da sua API ou do seu API Gateway
    headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
        'Authorization': AUTH_HEADER, // Adicione seu cabeçalho de autenticação se necessário

    }
});


// Interceptor para adicionar o token JWT à autorização
API.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem('accessToken'); // Supondo que você armazene o token JWT em localStorage
        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

const AuthService = {
    // Método para login
    login: async (username, password) => {
        try {
            const response = await API.post('/oauth/oauth/token', new URLSearchParams({
                username: username,
                password: password,
                grant_type: 'password'
            }), {
                headers: {
                    'Authorization': AUTH_HEADER,
                }
            });
            localStorage.setItem('accessToken', response.data.access_token); // Ajuste conforme o nome do campo retornado
            return response.data;
        } catch (error) {
            console.error('Erro ao fazer login:', error);
            throw error;
        }
    },
};

export default AuthService;
