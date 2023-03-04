package br.com.cwi.crescer.tcc.service.core;

import br.com.cwi.crescer.tcc.factories.UsuarioFactory;
import br.com.cwi.crescer.tcc.security.domain.Usuario;
import br.com.cwi.crescer.tcc.security.repository.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuscarUsuarioServiceTest {

    @InjectMocks
    private BuscarUsuarioService tested;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("Deve buscar usuario por id")
    void deveRetornarUsuario() {
        Usuario usuario = UsuarioFactory.get();
        Long usuarioId = usuario.getId();

        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));

        Usuario retorno = tested.porId(usuarioId);

        verify(usuarioRepository).findById(usuarioId);
        assertEquals(usuario, retorno);
    }

    @Test
    @DisplayName("Deve retornar erro quando não encontrar usuario")
    void deveRetornarErro() {

        ResponseStatusException exception =
                assertThrows(ResponseStatusException.class, () -> tested.porId(1L));

        assertEquals("Usuário não encontrado.", exception.getReason());
    }
}
