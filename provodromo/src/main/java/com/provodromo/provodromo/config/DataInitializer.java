package com.provodromo.provodromo.config;

import com.provodromo.provodromo.model.Alternativa;
import com.provodromo.provodromo.model.Prova;
import com.provodromo.provodromo.model.Questao;
import com.provodromo.provodromo.model.ProvaFinalizada;
import com.provodromo.provodromo.model.RespostaAlternativa;
import com.provodromo.provodromo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;

@Component
public class DataInitializer implements CommandLineRunner {
     @Autowired
     private ProvaRepository provaRepository;
     @Autowired
     private QuestaoRepository questaoRepository;
     @Autowired
     private AlternativaRepository alternativaRepository;
     @Autowired
     private ProvaFinalizadaRepository provaFinalizadaRepository;
     @Autowired
     private RespostaAlternativaRepository respostaAlternativaRepository;

     @Transactional
     @Override
     public void run(String... args) throws Exception {
        if (provaRepository.count() > 0) {
            return;
        }

        // Questão 1
        Questao questao1 = new Questao();
        questao1.setTexto("Qual a capital do Brasil?");
        questaoRepository.save(questao1);

        // Alternativa 1
        Alternativa alternativa1 = new Alternativa();
        alternativa1.setQuestao(questao1);
        alternativa1.setCorreta(true);
        alternativa1.setTexto("Brasília");
        alternativaRepository.save(alternativa1);

        // Alternativa 2
        Alternativa alternativa2 = new Alternativa();
        alternativa2.setQuestao(questao1);
        alternativa2.setCorreta(false);
        alternativa2.setTexto("Rio de Janeiro");
        alternativaRepository.save(alternativa2);

        // Alternativa 3
        Alternativa alternativa3 = new Alternativa();
        alternativa3.setQuestao(questao1);
        alternativa3.setCorreta(false);
        alternativa3.setTexto("São Paulo");
        alternativaRepository.save(alternativa3);

        // Questão 2
        Questao questao2 = new Questao();
        questao2.setTexto("Qual a capital da Argentina?");
        questaoRepository.save(questao2);

        // Alternativa 4
        Alternativa alternativa4 = new Alternativa();
        alternativa4.setQuestao(questao2);
        alternativa4.setCorreta(false);
        alternativa4.setTexto("Brasília");
        alternativaRepository.save(alternativa4);

        // Alternativa 5
        Alternativa alternativa5 = new Alternativa();
        alternativa5.setQuestao(questao2);
        alternativa5.setCorreta(false);
        alternativa5.setTexto("Rio de Janeiro");
        alternativaRepository.save(alternativa5);

        // Alternativa 6
        Alternativa alternativa6 = new Alternativa();
        alternativa6.setQuestao(questao2);
        alternativa6.setCorreta(true);
        alternativa6.setTexto("Buenos Aires");
        alternativaRepository.save(alternativa6);

        // Prova 1
        Prova prova1 = new Prova();
        prova1.setTitulo("Prova 1");
        prova1.setNota(10L);
        prova1.getQuestoes().add(questao1);
        prova1.getQuestoes().add(questao2);
        prova1.setTurmaId(1L);
        provaRepository.save(prova1);

        // Questão 3
        Questao questao3 = new Questao();
        questao3.setTexto("Qual a capital do Japão?");
        questaoRepository.save(questao3);

        // Alternativa 7
        Alternativa alternativa7 = new Alternativa();
        alternativa7.setQuestao(questao3);
        alternativa7.setCorreta(false);
        alternativa7.setTexto("Pequim");
        alternativaRepository.save(alternativa7);

        // Alternativa 8
        Alternativa alternativa8 = new Alternativa();
        alternativa8.setQuestao(questao3);
        alternativa8.setCorreta(false);
        alternativa8.setTexto("Xangai");
        alternativaRepository.save(alternativa8);

        // Alternativa 9
        Alternativa alternativa9 = new Alternativa();
        alternativa9.setQuestao(questao3);
        alternativa9.setCorreta(true);
        alternativa9.setTexto("Tóquio");
        alternativaRepository.save(alternativa9);

        // Questão 4
        Questao questao4 = new Questao();
        questao4.setTexto("Qual a capital da França?");
        questaoRepository.save(questao4);

        // Alternativa 10
        Alternativa alternativa10 = new Alternativa();
        alternativa10.setQuestao(questao4);
        alternativa10.setCorreta(false);
        alternativa10.setTexto("Londres");
        alternativaRepository.save(alternativa10);

        // Alternativa 11
        Alternativa alternativa11 = new Alternativa();
        alternativa11.setQuestao(questao4);
        alternativa11.setCorreta(false);
        alternativa11.setTexto("Madri");
        alternativaRepository.save(alternativa11);

        // Alternativa 12
        Alternativa alternativa12 = new Alternativa();
        alternativa12.setQuestao(questao4);
        alternativa12.setCorreta(true);
        alternativa12.setTexto("Paris");
        alternativaRepository.save(alternativa12);

        // Prova 2
        Prova prova2 = new Prova();
        prova2.setTitulo("Prova 2");
        prova2.setNota(10L);
        prova2.getQuestoes().add(questao3);
        prova2.getQuestoes().add(questao4);
        prova2.setTurmaId(2L);
        provaRepository.save(prova2);

        // Prova finalizada 1
        ProvaFinalizada provaFinalizada1 = new ProvaFinalizada();
        provaFinalizada1.setNota(10L);
        provaFinalizada1.setProva(prova1);
        provaFinalizada1.setUsuarioId(1L);
        provaFinalizadaRepository.save(provaFinalizada1);

        // Resposta 1
        RespostaAlternativa resposta1 = new RespostaAlternativa();
        resposta1.setAlternativa(alternativa1);
        resposta1.setProvaFinalizada(provaFinalizada1);
        respostaAlternativaRepository.save(resposta1);

        // Resposta 2
        RespostaAlternativa resposta2 = new RespostaAlternativa();
        resposta2.setAlternativa(alternativa6);
        resposta2.setProvaFinalizada(provaFinalizada1);
        respostaAlternativaRepository.save(resposta2);

        // Prova finalizada 2
        ProvaFinalizada provaFinalizada2 = new ProvaFinalizada();
        provaFinalizada2.setNota(10L);
        provaFinalizada2.setProva(prova2);
        provaFinalizada2.setUsuarioId(1L);
        provaFinalizadaRepository.save(provaFinalizada2);

        // Resposta 3
        RespostaAlternativa resposta3 = new RespostaAlternativa();
        resposta3.setAlternativa(alternativa9);
        resposta3.setProvaFinalizada(provaFinalizada2);
        respostaAlternativaRepository.save(resposta3);

        // Resposta 4
        RespostaAlternativa resposta4 = new RespostaAlternativa();
        resposta4.setAlternativa(alternativa12);
        resposta4.setProvaFinalizada(provaFinalizada2);
        respostaAlternativaRepository.save(resposta4);

        // Prova finalizada 3
        ProvaFinalizada provaFinalizada3 = new ProvaFinalizada();
        provaFinalizada3.setNota(5L);
        provaFinalizada3.setProva(prova1);
        provaFinalizada3.setUsuarioId(1L);
        provaFinalizadaRepository.save(provaFinalizada3);

        // Resposta 5
        RespostaAlternativa resposta5 = new RespostaAlternativa();
        resposta5.setAlternativa(alternativa1);
        resposta5.setProvaFinalizada(provaFinalizada3);
        respostaAlternativaRepository.save(resposta5);

        // Resposta 6
        RespostaAlternativa resposta6 = new RespostaAlternativa();
        resposta6.setAlternativa(alternativa5);
        resposta6.setProvaFinalizada(provaFinalizada3);
        respostaAlternativaRepository.save(resposta6);

        // Prova finalizada 4
        ProvaFinalizada provaFinalizada4 = new ProvaFinalizada();
        provaFinalizada4.setNota(5L);
        provaFinalizada4.setProva(prova2);
        provaFinalizada4.setUsuarioId(1L);
        provaFinalizadaRepository.save(provaFinalizada4);

        // Resposta 7
        RespostaAlternativa resposta7 = new RespostaAlternativa();
        resposta7.setAlternativa(alternativa7);
        resposta7.setProvaFinalizada(provaFinalizada4);
        respostaAlternativaRepository.save(resposta7);

        // Resposta 8
        RespostaAlternativa resposta8 = new RespostaAlternativa();
        resposta8.setAlternativa(alternativa11);
        resposta8.setProvaFinalizada(provaFinalizada4);
        respostaAlternativaRepository.save(resposta8);
     }
}
