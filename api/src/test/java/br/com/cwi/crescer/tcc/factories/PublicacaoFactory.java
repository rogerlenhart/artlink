package br.com.cwi.crescer.tcc.factories;

import br.com.cwi.crescer.tcc.domain.Publicacao;

public class PublicacaoFactory {

    public static Publicacao.PublicacaoBuilder getBuilder() {
        return Publicacao.builder()
                .id(SimpleFactory.getRandomLong())
                .autor(UsuarioFactory.get())
                .imagem("https://picsum.photos/300")
                .descricao("Descrição teste")
                .titulo("Título teste");
    }

    public static Publicacao getPublica() {
        return getBuilder().privado(false).build();
    }

    public static Publicacao getPrivada() {
        return getBuilder().privado(true).build();
    }
}
