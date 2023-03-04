package br.com.cwi.crescer.tcc.service;

import br.com.cwi.crescer.tcc.domain.Amizade;
import br.com.cwi.crescer.tcc.factories.AmizadeFactory;
import br.com.cwi.crescer.tcc.factories.UsuarioFactory;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CancelarAmizadeServiceTest {

    @InjectMocks
    private CancelarAmizadeService tested;

    @Mock
    private AmizadeRepository amizadeRepository;

    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Mock
    private BuscarAmizadeService buscarAmizadeService;

    @Test
    @DisplayName("Deve cancelar solicitação de amizade pendente")
    void deveAceitarSolicitacaoAmizadePendente() {

        Amizade amizade = AmizadeFactory.getPendente();

        Usuario usuario = amizade.getUsuario();
        Long usuarioId = usuario.getId();

        Usuario amigo = amizade.getAmigo();
        Long amigoId = amigo.getId();

        when(usuarioAutenticadoService.get()).thenReturn(amigo);
        when(amizadeRepository.findByUsuarioIdAndAmigoId(amigoId, usuarioId)).thenReturn(Optional.of(amizade));

        tested.cancelar(usuarioId);

        verify(amizadeRepository).delete(amizade);
    }

    @Test
    @DisplayName("Deve cancelar solicitação de amizade aceita")
    void deveAceitarSolicitacaoAmizadeAceita() {

        Amizade amizade = AmizadeFactory.getAceito();

        Usuario usuario = amizade.getUsuario();
        Long usuarioId = usuario.getId();

        Usuario amigo = amizade.getAmigo();
        Long amigoId = amigo.getId();

        when(usuarioAutenticadoService.get()).thenReturn(amigo);
        when(amizadeRepository.findByUsuarioIdAndAmigoId(amigoId, usuarioId)).thenReturn(Optional.of(amizade));

        tested.cancelar(usuarioId);

        verify(amizadeRepository).delete(amizade);
    }

    @Test
    @DisplayName("Não deve cancelar amizade quando ela não existir")
    void deveRetornarErroSeAmizadeNaoExiste() {

        Amizade amizade = AmizadeFactory.getPendente();

        Usuario amigo = amizade.getAmigo();
        Long amigoId = amigo.getId();

        Usuario usuario = UsuarioFactory.get();
        Long usuarioId = usuario.getId();

        when(usuarioAutenticadoService.get()).thenReturn(usuario);
        when(amizadeRepository.findByUsuarioIdAndAmigoId(usuarioId, amigoId)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> {
            tested.cancelar(amigoId);
        });

        verify(amizadeRepository, never()).delete(any());
    }
}