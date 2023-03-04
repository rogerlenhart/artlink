package br.com.cwi.crescer.tcc.mapper;

import br.com.cwi.crescer.tcc.controller.response.ComentarioResponse;
import br.com.cwi.crescer.tcc.controller.response.PublicacaoDetalhadaResponse;
import br.com.cwi.crescer.tcc.domain.Publicacao;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class PublicacaoDetalhadaMapper {

    public static PublicacaoDetalhadaResponse toResponse(Publicacao publicacao, boolean foiCurtido) {
        List<ComentarioResponse> comentarios = publicacao.getComentarios().stream()
                .sorted((c1, c2) -> c2.getDataCriacao().compareTo(c1.getDataCriacao()))
                .map(ComentarioMapper::toResponse)
                .collect(toList());

        return PublicacaoDetalhadaResponse.builder()
                .id(publicacao.getId())
                .titulo(publicacao.getTitulo())
                .descricao(publicacao.getDescricao())
                .imagem(publicacao.getImagem())
                .dataCriacao(publicacao.getDataCriacao())
                .privado(publicacao.isPrivado())
                .autor(UsuarioResumidoMapper.toResponse(publicacao.getAutor()))
                .numeroComentarios(publicacao.getNumeroComentarios())
                .comentarios(comentarios)
                .curtidas(publicacao.getNumeroCurtidas())
                .foiCurtido(foiCurtido)
                .build();
    }
}
