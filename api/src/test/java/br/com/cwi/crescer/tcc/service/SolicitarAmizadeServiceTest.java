package br.com.cwi.crescer.tcc.service;

import br.com.cwi.crescer.tcc.domain.Amizade;
import br.com.cwi.crescer.tcc.factories.UsuarioFactory;
import br.com.cwi.crescer.tcc.repository.AmizadeRepository;
import br.com.cwi.crescer.tcc.security.domain.Usuario;
import br.com.cwi.crescer.tcc.security.service.UsuarioAutenticadoService;
import br.com.cwi.crescer.tcc.service.core.BuscarUsuarioService;
import br.com.cwi.crescer.tcc.service.core.ValidaPodeSolicitarAmizadeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SolicitarAmizadeServiceTest {

    @InjectMocks
    private SolicitarAmizadeService tested;

    @Mock
    private AmizadeRepository amizadeRepository;

    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Mock
    private BuscarUsuarioService buscarUsuarioService;

    @Mock
    private ValidaPodeSolicitarAmizadeService validaPodeSolicitarAmizadeService;

    @Captor
    private ArgumentCaptor<Amizade> amizadeCaptor;

    @Test
    @DisplayName("Deve incluir nova solicitacao de amizade")
    void deveIncluirSolicitacaoAmizade() {

        Usuario usuario = UsuarioFactory.get();
        Long usuarioId = usuario.getId();

        Usuario amigo = UsuarioFactory.get();
        Long amigoId = amigo.getId();

        when(usuarioAutenticadoService.get()).thenReturn(usuario);
        when(buscarUsuarioService.porId(amigoId)).thenReturn(amigo);

        tested.solicitar(amigoId);

        verify(amizadeRepository).save(amizadeCaptor.capture());

        Amizade amizade = amizadeCaptor.getValue();
        assertEquals(usuario, amizade.getUsuario());
        assertEquals(amigo, amizade.getAmigo());
        assertFalse(amizade.isAceito());
    }

    @Test
    @DisplayName("Não deve incluir solicitacao de amizade se o amigo informado for o próprio usuário")
    void deveRetornarErroSeAmigoForProprioUsuario() {

        Usuario usuario = UsuarioFactory.get();
        Long usuarioId = usuario.getId();

        when(usuarioAutenticadoService.get()).thenReturn(usuario);

        doThrow(ResponseStatusException.class)
                .when(validaPodeSolicitarAmizadeService).validar(usuarioId, usuarioId);

        assertThrows(ResponseStatusException.class, () -> {
            tested.solicitar(usuarioId);
        });

        verify(amizadeRepository, never()).save(any());
    }
}