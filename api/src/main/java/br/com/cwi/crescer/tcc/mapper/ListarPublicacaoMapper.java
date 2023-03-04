package br.com.cwi.crescer.tcc.mapper;

import br.com.cwi.crescer.tcc.controller.response.ListarPublicacaoResponse;
import br.com.cwi.crescer.tcc.domain.Publicacao;

public class ListarPublicacaoMapper {

    public static ListarPublicacaoResponse toResponse(Publicacao publicacao) {
        return ListarPublicacaoResponse.builder()
                .id(publicacao.getId())
                .titulo(publicacao.getTitulo())
                .imagem(publicacao.getImagem())
                .descricao(publicacao.getDescricao())
                .dataCriacao(publicacao.getDataCriacao())
                .autor(UsuarioResumidoMapper.toResponse(publicacao.getAutor()))
                .build();
    }
}
