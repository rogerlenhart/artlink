package br.com.cwi.crescer.tcc.factories;

import br.com.cwi.crescer.tcc.security.domain.Usuario;

import java.time.LocalDate;

public class UsuarioFactory {

    public static Usuario get() {
        Usuario usuario = new Usuario();
        usuario.setId(SimpleFactory.getRandomLong());
        usuario.setNome("Usuário de teste");
        usuario.setEmail("teste@cwi.com.br");
        usuario.setImagemPerfil("https://teste/imagem_perfil.jpg");
        usuario.setApelido("usuarioTeste");
        usuario.setDataNascimento(LocalDate.of(2000, 1, 10));
        return usuario;
    }
}
