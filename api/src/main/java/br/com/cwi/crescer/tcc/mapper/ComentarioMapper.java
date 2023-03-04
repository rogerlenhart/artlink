package br.com.cwi.crescer.tcc.mapper;

import br.com.cwi.crescer.tcc.controller.request.AdicionarComentarioRequest;
import br.com.cwi.crescer.tcc.controller.response.ComentarioResponse;
import br.com.cwi.crescer.tcc.domain.Comentario;

public class ComentarioMapper {

    public static Comentario toEntity(AdicionarComentarioRequest request) {
        return Comentario.builder()
                .mensagem(request.getMensagem())
                .build();
    }

    public static ComentarioResponse toResponse(Comentario comentario) {
        return ComentarioResponse.builder()
                .id(comentario.getId())
                .mensagem(comentario.getMensagem())
                .dataCriacao(comentario.getDataCriacao())
                .autor(UsuarioResumidoMapper.toResponse(comentario.getAutor()))
                .build();
    }
}
