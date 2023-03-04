package br.com.cwi.crescer.tcc.controller;

import br.com.cwi.crescer.tcc.controller.request.AdicionarComentarioRequest;
import br.com.cwi.crescer.tcc.controller.request.IncluirPublicacaoRequest;
import br.com.cwi.crescer.tcc.controller.response.ListarPublicacaoResponse;
import br.com.cwi.crescer.tcc.controller.response.PublicacaoDetalhadaResponse;
import br.com.cwi.crescer.tcc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/publicacoes")
public class PublicacaoController {

    @Autowired
    private ListarPublicacaoService listarPublicacaoService;

    @Autowired
    DetalharPublicacaoService detalharPublicacaoService;

    @Autowired
    private IncluirPublicacaoService incluirPublicacaoService;

    @Autowired
    private CurtirPublicacaoService curtirPublicacaoService;

    @Autowired
    private RemoverCurtidaService removerCurtidaService;

    @Autowired
    private AdicionarComentarioService adicionarComentarioService;

    @GetMapping
    public Page<ListarPublicacaoResponse> listar(Pageable pageable) {
        return listarPublicacaoService.listar(pageable);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void incluir(@Valid @RequestBody IncluirPublicacaoRequest request) { incluirPublicacaoService.incluir(request); }

    @PostMapping("/{id}/curtidas")
    @ResponseStatus(CREATED)
    public void curtir(@PathVariable Long id) {  curtirPublicacaoService.curtir(id); }

    @DeleteMapping("/{id}/curtidas")
    @ResponseStatus(NO_CONTENT)
    public void removerCurtida(@PathVariable Long id) {  removerCurtidaService.remover(id); }

    @GetMapping("/{id}")
    public PublicacaoDetalhadaResponse detalhar(@PathVariable Long id) { return detalharPublicacaoService.detalhar(id); }

    @PostMapping("/{id}/comentarios")
    @ResponseStatus(CREATED)
    public void adicionarComentario(@PathVariable Long id, @Valid @RequestBody AdicionarComentarioRequest request) {
        adicionarComentarioService.adicionar(id, request);
    }
}
