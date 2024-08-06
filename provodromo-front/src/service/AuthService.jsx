import axios from 'axios';

// Adicione as credenciais do cliente
const CLIENT_ID = 'myappname123';
const CLIENT_SECRET = 'myappname123';
const AUTH_HEADER = "Basic bXlhcHBuYW1lMTIzOm15YXBwbmFtZTEyMw==";

const API = axios.create({
    baseURL: 'http://localhost:8765',  // URL base do seu API Gateway (Zuul)
    headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
        'Authorization': AUTH_HEADER,
        'Accept': '*/*',
        'Accept-Encoding': 'gzip, deflate, br',
        'Connection': 'keep-alive'
    }
});

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
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            });

            localStorage.setItem('accessToken', response.data.access_token);
            return response.data;
        } catch (error) {
            console.error('Erro ao fazer login:', error);
            throw error;
        }
    },
};

export default AuthService;
