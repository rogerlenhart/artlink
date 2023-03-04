package br.com.cwi.crescer.tcc.mapper;

import br.com.cwi.crescer.tcc.controller.response.AmizadeResponse;
import br.com.cwi.crescer.tcc.domain.Amizade;

public class AmizadeMapper {

    public static AmizadeResponse toResponse(Amizade amizade) {
        return AmizadeResponse.builder()
                .id(amizade.getId())
                .aceito(amizade.isAceito())
                .usuario(UsuarioResumidoMapper.toResponse(amizade.getUsuario()))
                .build();
    }
}
