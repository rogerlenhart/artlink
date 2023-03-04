package br.com.cwi.crescer.tcc.service;

import br.com.cwi.crescer.tcc.domain.Amizade;
import br.com.cwi.crescer.tcc.factories.AmizadeFactory;
import br.com.cwi.crescer.tcc.repository.AmizadeRepository;
import br.com.cwi.crescer.tcc.security.domain.Usuario;
import br.com.cwi.crescer.tcc.security.service.UsuarioAutenticadoService;
import br.com.cwi.crescer.tcc.service.core.BuscarAmizadeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AceitarAmizadeServiceTest {

    @InjectMocks
    private AceitarAmizadeService tested;

    @Mock
    private AmizadeRepository amizadeRepository;

    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Mock
    private BuscarAmizadeService buscarAmizadeService;

    @Test
    @DisplayName("Deve aceitar solicitação de amizade pendente")
    void deveAceitarSolicitacaoAmizadePendente() {

        Amizade amizade = AmizadeFactory.getPendente();

        Usuario usuario = amizade.getUsuario();
        Long usuarioId = usuario.getId();

        Usuario amigo = amizade.getAmigo();
        Long amigoId = amigo.getId();

        when(usuarioAutenticadoService.get()).thenReturn(amigo);
        when(amizadeRepository.findByUsuarioIdAndAmigoId(amigoId, usuarioId)).thenReturn(Optional.of(amizade));

        tested.aceitar(usuarioId);

        verify(amizadeRepository).save(amizade);
        assertTrue(amizade.isAceito());
    }

    @Test
    @DisplayName("Não deve aceitar solicitação quando ela não for direcionada ao usuário")
    void deveRetornarErroSeSolicitacaoNaoForDirecionadaAoUsuario() {

        Amizade amizade = AmizadeFactory.getPendente();

        Usuario usuario = amizade.getUsuario();
        Long usuarioId = usuario.getId();

        Usuario amigo = amizade.getAmigo();
        Long amigoId = amigo.getId();

        when(usuarioAutenticadoService.get()).thenReturn(usuario);
        when(amizadeRepository.findByUsuarioIdAndAmigoId(usuarioId, amigoId)).thenReturn(Optional.of(amizade));

        assertThrows(ResponseStatusException.class, () -> {
            tested.aceitar(amigoId);
        });

        verify(amizadeRepository, never()).save(any());
    }

    @Test
    @DisplayName("Não deve aceitar solicitação quando ela já estiver aceita")
    void deveRetornarErroSeSolicitacaoEstiverAceita() {

        Amizade amizade = AmizadeFactory.getAceito();

        Usuario usuario = amizade.getUsuario();
        Long usuarioId = usuario.getId();

        Usuario amigo = amizade.getAmigo();
        Long amigoId = amigo.getId();

        when(usuarioAutenticadoService.get()).thenReturn(amigo);
        when(amizadeRepository.findByUsuarioIdAndAmigoId(amigoId, usuarioId)).thenReturn(Optional.of(amizade));

        assertThrows(ResponseStatusException.class, () -> {
            tested.aceitar(usuarioId);
        });

        verify(amizadeRepository, never()).save(any());
    }
}