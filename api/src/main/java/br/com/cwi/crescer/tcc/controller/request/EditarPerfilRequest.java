package br.com.cwi.crescer.tcc.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class EditarPerfilRequest {

    @NotBlank
    private String apelido;

    @NotBlank
    private String nome;

    @NotBlank
    private String imagemPerfil;
}
