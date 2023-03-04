package br.com.cwi.crescer.tcc.service.core;

import br.com.cwi.crescer.tcc.domain.Amizade;
import br.com.cwi.crescer.tcc.factories.AmizadeFactory;
import br.com.cwi.crescer.tcc.factories.UsuarioFactory;
import br.com.cwi.crescer.tcc.repository.AmizadeRepository;
import br.com.cwi.crescer.tcc.security.domain.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ValidaPodeSolicitarAmizadeServiceTest {

    @InjectMocks
    private ValidaPodeSolicitarAmizadeService tested;

    @Mock
    private AmizadeRepository amizadeRepository;

    @Test
    @DisplayName("Deve fazer nada se ainda não existir uma solicitação de amizade")
    void deveFazerNadaSeNaoExistirSolicitacaoAmizade() {

        Usuario usuario = UsuarioFactory.get();
        Long usuarioId = usuario.getId();

        Usuario amigo = UsuarioFactory.get();
        Long amigoId = amigo.getId();

        when(amizadeRepository.findByUsuarioIdAndAmigoId(usuarioId, amigoId)).thenReturn(Optional.empty());

        tested.validar(usuarioId, amigoId);

        verify(amizadeRepository).findByUsuarioIdAndAmigoId(usuarioId, amigoId);
    }

    @Test
    @DisplayName("Deve retornar erro quando o amigo informado for o próprio usuário")
    void deveRetornarErroQuandoAmigoForProprioUsuario() {

        Usuario usuario = UsuarioFactory.get();
        Long usuarioId = usuario.getId();

        ResponseStatusException exception =
                assertThrows(ResponseStatusException.class, () -> tested.validar(usuarioId, usuarioId));

        verify(amizadeRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve retornar erro quando já existir uma solicitação de amizade pendente")
    void deveRetornarErroQuandoExistirSolicitacaoPendente() {

        Amizade amizade = AmizadeFactory.getPendente();

        Long usuarioId = amizade.getUsuario().getId();

        Long amigoId = amizade.getAmigo().getId();

        when(amizadeRepository.findByUsuarioIdAndAmigoId(usuarioId, amigoId)).thenReturn(Optional.of(amizade));

        ResponseStatusException exception =
                assertThrows(ResponseStatusException.class, () -> tested.validar(usuarioId, amigoId));

        verify(amizadeRepository).findByUsuarioIdAndAmigoId(usuarioId, amigoId);
    }

    @Test
    @DisplayName("Deve retornar erro quando usuários já forem amigos")
    void deveRetornarErroQuandoForemAmigos() {

        Amizade amizade = AmizadeFactory.getAceito();

        Long usuarioId = amizade.getUsuario().getId();

        Long amigoId = amizade.getAmigo().getId();

        when(amizadeRepository.findByUsuarioIdAndAmigoId(usuarioId, amigoId)).thenReturn(Optional.of(amizade));

        ResponseStatusException exception =
                assertThrows(ResponseStatusException.class, () -> tested.validar(usuarioId, amigoId));

        verify(amizadeRepository).findByUsuarioIdAndAmigoId(usuarioId, amigoId);
    }
}
