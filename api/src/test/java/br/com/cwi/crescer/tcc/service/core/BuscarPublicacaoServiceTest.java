package br.com.cwi.crescer.tcc.service.core;

import br.com.cwi.crescer.tcc.domain.Publicacao;
import br.com.cwi.crescer.tcc.factories.PublicacaoFactory;
import br.com.cwi.crescer.tcc.repository.PublicacaoRepository;
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
class BuscarPublicacaoServiceTest {

    @InjectMocks
    private BuscarPublicacaoService tested;

    @Mock
    private PublicacaoRepository publicacaoRepository;

    @Test
    @DisplayName("Deve buscar publicação por id")
    void deveRetornarPublicacao() {
        Publicacao publicacao = PublicacaoFactory.getPrivada();
        Long publicacaoId = publicacao.getId();

        when(publicacaoRepository.findById(publicacaoId)).thenReturn(Optional.of(publicacao));

        Publicacao retorno = tested.porId(publicacaoId);

        verify(publicacaoRepository).findById(publicacaoId);
        assertEquals(publicacao, retorno);
    }

    @Test
    @DisplayName("Deve retornar erro quando não encontrar publicação")
    void deveRetornarErro() {

        ResponseStatusException exception =
                assertThrows(ResponseStatusException.class, () -> tested.porId(1L));

        assertEquals("Publicação não encontrada.", exception.getReason());
    }
}
