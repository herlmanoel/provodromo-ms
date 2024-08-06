package com.provodromo.usuario.config;

import com.provodromo.usuario.domain.TipoUsuario;
import com.provodromo.usuario.domain.Usuario;
import com.provodromo.usuario.repository.TipoUsuarioRepository;
import com.provodromo.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (tipoUsuarioRepository.findByNome("Coordenador") == null) {
            // TipoUsuario Coordenador
            TipoUsuario tipoUsuarioCoordenador = new TipoUsuario();
            tipoUsuarioCoordenador.setNome("Coordenador");
            tipoUsuarioRepository.save(tipoUsuarioCoordenador);
        }

        if (tipoUsuarioRepository.findByNome("Admin") == null) {
            // TipoUsuario Admin
            TipoUsuario tipoUsuarioAdmin = new TipoUsuario();
            tipoUsuarioAdmin.setNome("Admin");
            tipoUsuarioRepository.save(tipoUsuarioAdmin);
        }

        if (usuarioRepository.count() > 0) {
            return;
        }

        // TipoUsuario Aluno
        TipoUsuario tipoUsuarioAluno = new TipoUsuario();
        tipoUsuarioAluno.setNome("Aluno");
        tipoUsuarioRepository.save(tipoUsuarioAluno);

        // TipoUsuario Professor
        TipoUsuario tipoUsuarioProfessor = new TipoUsuario();
        tipoUsuarioProfessor.setNome("Professor");
        tipoUsuarioRepository.save(tipoUsuarioProfessor);

        // Usuario Aluno
        Usuario usuarioAluno = new Usuario();
        usuarioAluno.setNome("Aluno");
        usuarioAluno.setEmail("aluno@email.com");
        usuarioAluno.setTipoUsuario(tipoUsuarioAluno);
        usuarioAluno.setSenha(passwordEncoder.encode("123"));
        usuarioRepository.save(usuarioAluno);

        // Usuario Professor
        Usuario usuarioProfessor = new Usuario();
        usuarioProfessor.setNome("Professor");
        usuarioProfessor.setEmail("professor@email.com");
        usuarioProfessor.setTipoUsuario(tipoUsuarioProfessor);
        usuarioProfessor.setSenha(passwordEncoder.encode("123"));
        usuarioRepository.save(usuarioProfessor);
    }
}
