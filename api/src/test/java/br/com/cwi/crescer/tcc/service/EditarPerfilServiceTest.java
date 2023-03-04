package br.com.cwi.crescer.tcc.service;

import br.com.cwi.crescer.tcc.controller.request.EditarPerfilRequest;
import br.com.cwi.crescer.tcc.factories.UsuarioFactory;
import br.com.cwi.crescer.tcc.security.domain.Usuario;
import br.com.cwi.crescer.tcc.security.repository.UsuarioRepository;
import br.com.cwi.crescer.tcc.security.service.UsuarioAutenticadoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EditarPerfilServiceTest {

    @InjectMocks
    private EditarPerfilService tested;

    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("Deve atualizar perfil com sucesso")
    void deveAtualizarPerfil() {

        EditarPerfilRequest request = new EditarPerfilRequest();
        request.setImagemPerfil("https://nova-imagem.png");
        request.setNome("Novo nome");
        request.setApelido("Novo apelido");

        Usuario usuario = UsuarioFactory.get();

        when(usuarioAutenticadoService.get()).thenReturn(usuario);

        tested.editar(request);

        verify(usuarioRepository).save(usuario);

        assertEquals(request.getImagemPerfil(), usuario.getImagemPerfil());
        assertEquals(request.getNome(), usuario.getNome());
        assertEquals(request.getApelido(), usuario.getApelido());
    }
}
