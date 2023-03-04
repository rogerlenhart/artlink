package br.com.cwi.crescer.tcc.controller.response;

import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioDetalhadoResponse {

    private Long id;

    private String nome;

    private String email;

    private String apelido;

    private LocalDate dataNascimento;

    private String imagemPerfil;
}
