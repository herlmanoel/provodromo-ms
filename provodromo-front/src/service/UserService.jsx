import axios from 'axios';

const API = axios.create({
   baseURL: '',  // Defina o URL base da API
   headers: {
      'Content-Type': 'application/json',
      // Adicione outros headers necessários, como tokens de autenticação
      // 'Authorization': `Bearer ${token}`
   }
});

const UserService = {
   getUserTypes: async (id) => {
      try {
         const response = await API.get(`/user/types`);
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
