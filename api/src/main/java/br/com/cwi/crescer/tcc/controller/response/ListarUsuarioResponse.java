package br.com.cwi.crescer.tcc.controller.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ListarUsuarioResponse {

    private Long id;

    private String nome;

    private String email;

    private String imagemPerfil;
}
