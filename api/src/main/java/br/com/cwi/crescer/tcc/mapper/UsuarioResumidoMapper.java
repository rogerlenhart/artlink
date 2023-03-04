package br.com.cwi.crescer.tcc.mapper;

import br.com.cwi.crescer.tcc.controller.response.UsuarioResumidoResponse;
import br.com.cwi.crescer.tcc.security.domain.Usuario;

public class UsuarioResumidoMapper {

    public static UsuarioResumidoResponse toResponse(Usuario usuario) {
        return UsuarioResumidoResponse.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .imagemPerfil(usuario.getImagemPerfil())
                .apelido(usuario.getApelido())
                .build();
    }
}
