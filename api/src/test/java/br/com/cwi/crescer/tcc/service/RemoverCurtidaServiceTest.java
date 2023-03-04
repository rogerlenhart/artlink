package br.com.cwi.crescer.tcc.service;

import br.com.cwi.crescer.tcc.domain.Curtida;
import br.com.cwi.crescer.tcc.domain.Publicacao;
import br.com.cwi.crescer.tcc.factories.CurtidaFactory;
import br.com.cwi.crescer.tcc.factories.UsuarioFactory;
import br.com.cwi.crescer.tcc.repository.CurtidaRepository;
import br.com.cwi.crescer.tcc.security.domain.Usuario;
import br.com.cwi.crescer.tcc.security.service.UsuarioAutenticadoService;
import br.com.cwi.crescer.tcc.service.core.BuscarPublicacaoService;
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
public class RemoverCurtidaServiceTest {

    @InjectMocks
    private RemoverCurtidaService tested;

    @Mock
    private BuscarPublicacaoService buscarPublicacaoService;

    @Mock
    private CurtidaRepository curtidaRepository;

    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Test
    @DisplayName("Deve remover uma curtida da publicação")
    void deveRemoverCurtidaPublicacao() {

        Curtida curtida = CurtidaFactory.get();

        Publicacao publicacao = curtida.getPublicacao();
        Long publicacaoId = publicacao.getId();

        Usuario usuario = curtida.getUsuario();

        when(usuarioAutenticadoService.get()).thenReturn(usuario);
        when(buscarPublicacaoService.porId(publicacaoId)).thenReturn(publicacao);
        when(curtidaRepository.findByPublicacaoAndUsuario(publicacao, usuario)).thenReturn(Optional.of(curtida));

        tested.remover(publicacaoId);

        verify(curtidaRepository).delete(curtida);
    }

    @Test
    @DisplayName("Não deve remover curtida se não houver uma curtida cadastrada para o usuário")
    void deveRetornarErroSeNaoHouverCurtidaCadastradaParaUsuario() {

        Curtida curtida = CurtidaFactory.get();

        Publicacao publicacao = curtida.getPublicacao();
        Long publicacaoId = publicacao.getId();

        Usuario usuario = UsuarioFactory.get();

        when(usuarioAutenticadoService.get()).thenReturn(usuario);
        when(buscarPublicacaoService.porId(publicacaoId)).thenReturn(publicacao);
        when(curtidaRepository.findByPublicacaoAndUsuario(publicacao, usuario)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> {
            tested.remover(publicacaoId);
        });

        verify(curtidaRepository, never()).delete(any());
    }
}
