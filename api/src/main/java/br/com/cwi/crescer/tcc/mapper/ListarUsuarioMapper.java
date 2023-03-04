package br.com.cwi.crescer.tcc.mapper;

import br.com.cwi.crescer.tcc.controller.response.ListarUsuarioResponse;
import br.com.cwi.crescer.tcc.security.domain.Usuario;

public class ListarUsuarioMapper {

    public static ListarUsuarioResponse toResponse(Usuario usuario) {
        return  ListarUsuarioResponse.builder()
                .id(usuario.getId())
                .email(usuario.getEmail())
                .imagemPerfil(usuario.getImagemPerfil())
                .nome(usuario.getNome())
                .build();
    }
}
