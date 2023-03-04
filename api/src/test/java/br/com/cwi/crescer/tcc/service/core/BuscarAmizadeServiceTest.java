package br.com.cwi.crescer.tcc.service.core;

import br.com.cwi.crescer.tcc.domain.Amizade;
import br.com.cwi.crescer.tcc.factories.AmizadeFactory;
import br.com.cwi.crescer.tcc.repository.AmizadeRepository;
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
class BuscarAmizadeServiceTest {

    @InjectMocks
    private BuscarAmizadeService tested;

    @Mock
    private AmizadeRepository amizadeRepository;

    @Test
    @DisplayName("Deve buscar amizade por id")
    void deveRetornarAmizade() {
        Amizade amizade = AmizadeFactory.getAceito();
        Long amizadeId = amizade.getId();

        when(amizadeRepository.findById(amizadeId)).thenReturn(Optional.of(amizade));

        Amizade retorno = tested.porId(amizadeId);

        verify(amizadeRepository).findById(amizadeId);
        assertEquals(amizade, retorno);
    }

    @Test
    @DisplayName("Deve retornar erro quando não encontrar amizade")
    void deveRetornarErro() {

        ResponseStatusException exception =
                assertThrows(ResponseStatusException.class, () -> tested.porId(1L));

        assertEquals("Amizade não encontrada.", exception.getReason());
    }
}
