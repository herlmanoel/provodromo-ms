import axios from 'axios';

// Adicione as credenciais do cliente
const CLIENT_ID = 'myappname123';
const CLIENT_SECRET = 'myappname123';
const AUTH_HEADER = `Basic ${btoa(`${CLIENT_ID}:${CLIENT_SECRET}`)}`;


const API = axios.create({
   baseURL: 'http://localhost:8765/provodromo', // Defina o URL base da sua API ou do seu API Gateway
   headers: {
      'Content-Type': 'application/json',
   }
});

const ProvodromoService = {
   // Finalizar uma prova
   finalizarProva: async (provaFinalizada) => {
      try {
         const response = await API.post('/prova-finalizada', provaFinalizada);
         return response.data;
      } catch (error) {
         console.error('Erro ao finalizar prova:', error);
         throw error;
      }
   },

   // Recuperar prova finalizada por usuário e prova
   retrieveProvaFinalizadaByUsuarioIdAndProvaId: async (usuarioId, provaId) => {
      try {
         const response = await API.get('/prova-finalizada/por-usuario-e-prova', {
            params: { usuarioId, provaId }
         });
         return response.data;
      } catch (error) {
         console.error('Erro ao recuperar prova finalizada por usuário e prova:', error);
         throw error;
      }
   },

   // Recuperar provas finalizadas por prova
   retrieveByProvaId: async (provaId) => {
      try {
         const response = await API.get('/prova-finalizada/por-prova', {
            params: { provaId }
         });
         return response.data;
      } catch (error) {
         console.error('Erro ao recuperar provas finalizadas por prova:', error);
         throw error;
      }
   },

   // Recuperar provas finalizadas por usuário
   retrieveByUsuarioId: async (usuarioId) => {
      try {
         const response = await API.get('/prova-finalizada/por-usuario', {
            params: { usuarioId }
         });
         return response.data;
      } catch (error) {
         console.error('Erro ao recuperar provas finalizadas por usuário:', error);
         throw error;
      }
   },

   // Recuperar provas finalizadas por usuário e turma
   retrieveByUsuarioIdAndTurmaId: async (usuarioId, turmaId) => {
      try {
         const response = await API.get('/prova-finalizada/por-usuario-e-turma', {
            params: { usuarioId, turmaId }
         });
         return response.data;
      } catch (error) {
         console.error('Erro ao recuperar provas finalizadas por usuário e turma:', error);
         throw error;
      }
   },
   // Listar todas as questões
   listarQuestoes: async () => {
      try {
         const response = await API.get('/api/questao/listar');
         return response.data;
      } catch (error) {
         console.error('Erro ao listar questões:', error);
         throw error;
      }
   },

   // Criar uma nova questão
   criarQuestao: async (questaoRequestDTO) => {
      try {
         const response = await API.post('/api/questao/salvar', questaoRequestDTO);
         return response.data;
      } catch (error) {
         console.error('Erro ao criar questão:', error);
         throw error;
      }
   },

   // Buscar uma questão por ID
   buscarQuestao: async (id) => {
      try {
         const response = await API.get(`/api/questao/buscar/${id}`);
         return response.data;
      } catch (error) {
         console.error('Erro ao buscar questão:', error);
         throw error;
      }
   },

   // Atualizar uma questão por ID
   atualizarQuestao: async (id, questaoRequestDTO) => {
      try {
         const response = await API.put(`/api/questao/atualizar/${id}`, questaoRequestDTO);
         return response.data;
      } catch (error) {
         console.error('Erro ao atualizar questão:', error);
         throw error;
      }
   },

   // Excluir uma questão por ID
   excluirQuestao: async (id) => {
      try {
         await API.delete(`/api/questao/excluir/${id}`);
      } catch (error) {
         console.error('Erro ao excluir questão:', error);
         throw error;
      }
   },
};

export default ProvodromoService;
