package br.com.cwi.crescer.tcc.service;

import br.com.cwi.crescer.tcc.controller.request.IncluirPublicacaoRequest;
import br.com.cwi.crescer.tcc.domain.Publicacao;
import br.com.cwi.crescer.tcc.factories.UsuarioFactory;
import br.com.cwi.crescer.tcc.repository.PublicacaoRepository;
import br.com.cwi.crescer.tcc.security.domain.Usuario;
import br.com.cwi.crescer.tcc.security.service.UsuarioAutenticadoService;
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
class IncluirPublicacaoServiceTest {

    @InjectMocks
    private IncluirPublicacaoService tested;

    @Mock
    private NowService nowService;

    @Mock
    private PublicacaoRepository publicacaoRepository;

    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Captor
    private ArgumentCaptor<Publicacao> publicacaoCaptor;

    @Test
    @DisplayName("Deve incluir nova publicação com autor e data de criação")
    void deveIncluirPublicacao() {

        Usuario usuario = UsuarioFactory.get();
        Long usuarioId = usuario.getId();

        IncluirPublicacaoRequest request = new IncluirPublicacaoRequest();
        request.setDescricao("Descrição teste.");
        request.setTitulo("Título teste");
        request.setImagem("http://teste/imagem.png");
        request.setPrivado(true);

        LocalDateTime agora = LocalDateTime.now();

        when(usuarioAutenticadoService.get()).thenReturn(usuario);
        when(nowService.getDateTime()).thenReturn(agora);

        tested.incluir(request);

        verify(publicacaoRepository).save(publicacaoCaptor.capture());

        Publicacao publicacao = publicacaoCaptor.getValue();
        assertEquals(usuarioId, publicacao.getAutor().getId());
        assertEquals(agora, publicacao.getDataCriacao());
    }
}