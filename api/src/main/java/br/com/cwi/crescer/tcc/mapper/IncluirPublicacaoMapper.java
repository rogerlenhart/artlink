package br.com.cwi.crescer.tcc.mapper;

import br.com.cwi.crescer.tcc.controller.request.IncluirPublicacaoRequest;
import br.com.cwi.crescer.tcc.domain.Publicacao;

public class IncluirPublicacaoMapper {

    public static Publicacao toEntity(IncluirPublicacaoRequest request) {
        return Publicacao.builder()
                .titulo(request.getTitulo())
                .descricao(request.getDescricao())
                .imagem(request.getImagem())
                .privado(request.getPrivado())
                .build();
    }
}
