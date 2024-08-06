import axios from 'axios';

// Adicione as credenciais do cliente
const CLIENT_ID = 'myappname123';
const CLIENT_SECRET = 'myappname123';
const AUTH_HEADER = `Basic ${btoa(`${CLIENT_ID}:${CLIENT_SECRET}`)}`;

const API = axios.create({
   baseURL: 'http://localhost:8765/institucional',
   headers: {
      'Content-Type': 'application/x-www-form-urlencoded',
      'Authorization': AUTH_HEADER, // Adicione seu cabeçalho de autenticação se necessário
   }
});

const InstitucionalService = {
   // Métodos para professores
   listarProfessores: async () => {
      try {
         const response = await API.get('/api/professor');
         return response.data;
      } catch (error) {
         console.error('Erro ao listar professores:', error);
         throw error;
      }
   },

   criarProfessor: async (professor) => {
      try {
         const response = await API.post('/api/professor', professor);
         return response.data;
      } catch (error) {
         console.error('Erro ao criar professor:', error);
         throw error;
      }
   },

   buscarProfessor: async (id) => {
      try {
         const response = await API.get(`/api/professor/${id}`);
         return response.data;
      } catch (error) {
         console.error('Erro ao buscar professor:', error);
         throw error;
      }
   },

   excluirProfessor: async (id) => {
      try {
         await API.delete(`/api/professor/${id}`);
      } catch (error) {
         console.error('Erro ao excluir professor:', error);
         throw error;
      }
   },

   // Métodos para alunos
   listarAlunos: async () => {
      try {
         const response = await API.get('/api/aluno');
         return response.data;
      } catch (error) {
         console.error('Erro ao listar alunos:', error);
         throw error;
      }
   },

   criarAluno: async (aluno) => {
      try {
         const response = await API.post('/api/aluno', aluno);
         return response.data;
      } catch (error) {
         console.error('Erro ao criar aluno:', error);
         throw error;
      }
   },

   buscarAluno: async (id) => {
      try {
         const response = await API.get(`/api/aluno/${id}`);
         return response.data;
      } catch (error) {
         console.error('Erro ao buscar aluno:', error);
         throw error;
      }
   },

   excluirAluno: async (id) => {
      try {
         await API.delete(`/api/aluno/${id}`);
      } catch (error) {
         console.error('Erro ao excluir aluno:', error);
         throw error;
      }
   },

   associarTurma: async (alunoId, turmaId) => {
      try {
         const response = await API.put(`/api/aluno/${alunoId}/materia/${turmaId}`);
         return response.data;
      } catch (error) {
         console.error('Erro ao associar turma ao aluno:', error);
         throw error;
      }
   },

   // Métodos para turmas
   listarTurmas: async () => {
      try {
         const response = await API.get('/api/turma');
         return response.data;
      } catch (error) {
         console.error('Erro ao listar turmas:', error);
         throw error;
      }
   },

   criarTurma: async (turma) => {
      try {
         const response = await API.post('/api/turma', turma);
         return response.data;
      } catch (error) {
         console.error('Erro ao criar turma:', error);
         throw error;
      }
   },

   buscarTurma: async (id) => {
      try {
         const response = await API.get(`/api/turma/${id}`);
         return response.data;
      } catch (error) {
         console.error('Erro ao buscar turma:', error);
         throw error;
      }
   },

   excluirTurma: async (id) => {
      try {
         await API.delete(`/api/turma/${id}`);
      } catch (error) {
         console.error('Erro ao excluir turma:', error);
         throw error;
      }
   },

   // Métodos para notas
   listarNotas: async () => {
      try {
         const response = await API.get('/api/nota');
         return response.data;
      } catch (error) {
         console.error('Erro ao listar notas:', error);
         throw error;
      }
   },

   criarNota: async (nota) => {
      try {
         const response = await API.post('/api/nota', nota);
         return response.data;
      } catch (error) {
         console.error('Erro ao criar nota:', error);
         throw error;
      }
   },

   buscarNota: async (id) => {
      try {
         const response = await API.get(`/api/nota/${id}`);
         return response.data;
      } catch (error) {
         console.error('Erro ao buscar nota:', error);
         throw error;
      }
   },

   excluirNota: async (id) => {
      try {
         await API.delete(`/api/nota/${id}`);
      } catch (error) {
         console.error('Erro ao excluir nota:', error);
         throw error;
      }
   },
};

export default InstitucionalService;
