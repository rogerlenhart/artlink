package br.com.cwi.crescer.tcc.mapper;

import br.com.cwi.crescer.tcc.controller.response.UsuarioDetalhadoResponse;
import br.com.cwi.crescer.tcc.security.domain.Usuario;

public class UsuarioDetalhadoMapper {

    public static UsuarioDetalhadoResponse toResponse(Usuario usuario) {
        return UsuarioDetalhadoResponse.builder()
                .id(usuario.getId())
                .email(usuario.getEmail())
                .apelido(usuario.getApelido())
                .nome(usuario.getNome())
                .dataNascimento(usuario.getDataNascimento())
                .imagemPerfil(usuario.getImagemPerfil())
                .build();
    }
}
