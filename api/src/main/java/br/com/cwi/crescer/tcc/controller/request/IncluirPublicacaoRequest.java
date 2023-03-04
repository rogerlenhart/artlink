package br.com.cwi.crescer.tcc.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class IncluirPublicacaoRequest {

    @NotBlank
    private String titulo;

    @NotBlank
    private String descricao;

    @NotBlank
    private String imagem;

    @NotNull
    private Boolean privado;
}
