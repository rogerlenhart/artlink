package br.com.cwi.crescer.tcc.service;

import br.com.cwi.crescer.tcc.domain.Curtida;
import br.com.cwi.crescer.tcc.domain.Publicacao;
import br.com.cwi.crescer.tcc.factories.PublicacaoFactory;
import br.com.cwi.crescer.tcc.repository.CurtidaRepository;
import br.com.cwi.crescer.tcc.security.domain.Usuario;
import br.com.cwi.crescer.tcc.security.service.UsuarioAutenticadoService;
import br.com.cwi.crescer.tcc.service.core.BuscarPublicacaoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CurtirPublicacaoServiceTest {

    @InjectMocks
    private CurtirPublicacaoService tested;

    @Mock
    private BuscarPublicacaoService buscarPublicacaoService;

    @Mock
    private CurtidaRepository curtidaRepository;

    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Captor
    private ArgumentCaptor<Curtida> curtidaCaptor;

    @Test
    @DisplayName("Deve incluir uma curtida para a publicação")
    void deveCurtirPublicacao() {

        Publicacao publicacao = PublicacaoFactory.getPublica();
        Long publicacaoId = publicacao.getId();
        Usuario usuario = publicacao.getAutor();

        when(usuarioAutenticadoService.get()).thenReturn(usuario);
        when(buscarPublicacaoService.porId(publicacaoId)).thenReturn(publicacao);
        when(curtidaRepository.existsByPublicacaoAndUsuario(publicacao, usuario)).thenReturn(false);

        tested.curtir(publicacaoId);

        verify(curtidaRepository).save(curtidaCaptor.capture());

        Curtida curtida = curtidaCaptor.getValue();
        assertEquals(usuario, curtida.getUsuario());
        assertEquals(publicacao, curtida.getPublicacao());
    }

    @Test
    @DisplayName("Não deve curtir publicação se já houver uma curtida cadastrada para o usuário")
    void deveRetornarErroSePublicacaoPossuirCurtidaParaUsuario() {

        Publicacao publicacao = PublicacaoFactory.getPublica();
        Long publicacaoId = publicacao.getId();
        Usuario usuario = publicacao.getAutor();

        when(usuarioAutenticadoService.get()).thenReturn(usuario);
        when(buscarPublicacaoService.porId(publicacaoId)).thenReturn(publicacao);
        when(curtidaRepository.existsByPublicacaoAndUsuario(publicacao, usuario)).thenReturn(true);

        assertThrows(ResponseStatusException.class, () -> {
            tested.curtir(publicacaoId);
        });

        verify(curtidaRepository, never()).save(any());
    }
}
