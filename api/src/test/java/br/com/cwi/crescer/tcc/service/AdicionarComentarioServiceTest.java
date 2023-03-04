package br.com.cwi.crescer.tcc.service;

import br.com.cwi.crescer.tcc.controller.request.AdicionarComentarioRequest;
import br.com.cwi.crescer.tcc.domain.Comentario;
import br.com.cwi.crescer.tcc.domain.Publicacao;
import br.com.cwi.crescer.tcc.factories.PublicacaoFactory;
import br.com.cwi.crescer.tcc.factories.UsuarioFactory;
import br.com.cwi.crescer.tcc.repository.ComentarioRepository;
import br.com.cwi.crescer.tcc.security.domain.Usuario;
import br.com.cwi.crescer.tcc.security.service.UsuarioAutenticadoService;
import br.com.cwi.crescer.tcc.service.core.BuscarPublicacaoService;
import br.com.cwi.crescer.tcc.service.core.NowService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdicionarComentarioServiceTest {

    @InjectMocks
    private AdicionarComentarioService tested;

    @Mock
    private NowService nowService;

    @Mock
    private ComentarioRepository comentarioRepository;

    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Mock
    private BuscarPublicacaoService buscarPublicacaoService;

    @Captor
    private ArgumentCaptor<Comentario> comentarioCaptor;

    @Test
    @DisplayName("Deve adicionar comentário com autor e data de criação")
    void deveIncluirPublicacao() {

        Usuario usuario = UsuarioFactory.get();
        Long usuarioId = usuario.getId();

        Publicacao publicacao = PublicacaoFactory.getPublica();
        Long publicacaoId = publicacao.getId();

        AdicionarComentarioRequest request = new AdicionarComentarioRequest();
        request.setMensagem("Mensagem teste");

        LocalDateTime agora = LocalDateTime.now();

        when(usuarioAutenticadoService.get()).thenReturn(usuario);
        when(buscarPublicacaoService.porId(publicacaoId)).thenReturn(publicacao);
        when(nowService.getDateTime()).thenReturn(agora);

        tested.adicionar(publicacaoId, request);

        verify(comentarioRepository).save(comentarioCaptor.capture());

        Comentario comentario = comentarioCaptor.getValue();
        assertEquals(publicacaoId, comentario.getPublicacao().getId());
        assertEquals(usuarioId, comentario.getAutor().getId());
        assertEquals(agora, comentario.getDataCriacao());
    }
}