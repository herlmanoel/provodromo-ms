import axios from 'axios';

const API = axios.create({
   baseURL: 'http://localhost:8765/usuario',  // Defina o URL base da sua API ou do seu API Gateway
   headers: {
      'Content-Type': 'application/x-www-form-urlencoded',
   }
});

const UserService = {
   getUserTypes: async (id) => {
      try {
         const response = await API.get(`/api/tipoUsuario`);
         return response.data;
      } catch (error) {
         console.error('Erro ao buscar tipo de usuário:', error);
         throw error;
      }
   },

   getUsers: async () => {
      try {
         const response = await API.get(`/users`);
         return response.data;
      } catch (error) {
         console.error('Erro ao buscar usuário:', error);
         throw error;
      }
   },

   postUsers: async (obj) => await API.post(`/user`, obj),
   postType: async (obj) => {
      try {
         const response = await API.post(`/type`, obj);
         return response.data;
      } catch (error) {
         console.error('Erro ao buscar usuário:', error);
         throw error;
      }
   },
};

export default UserService;
