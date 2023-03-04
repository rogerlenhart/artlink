package br.com.cwi.crescer.tcc.controller;

import br.com.cwi.crescer.tcc.controller.request.EditarPerfilRequest;
import br.com.cwi.crescer.tcc.controller.response.AmizadeResponse;
import br.com.cwi.crescer.tcc.controller.response.ListarUsuarioResponse;
import br.com.cwi.crescer.tcc.controller.response.UsuarioDetalhadoResponse;
import br.com.cwi.crescer.tcc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/perfil")
public class PerfilController {

    @Autowired
    private EditarPerfilService editarPerfilService;

    @Autowired
    private PesquisarUsuarioService pesquisarUsuarioService;

    @Autowired
    private ListarAmigosService listarAmigosService;

    @Autowired
    private SolicitarAmizadeService solicitarAmizadeService;

    @Autowired
    private AceitarAmizadeService aceitarAmizadeService;

    @Autowired
    private CancelarAmizadeService cancelarAmizadeService;

    @Autowired
    private ListarPedidosAmizade listarPedidosAmizade;

    @Autowired
    private DetalharPerfilService detalharPerfilService;

    @Autowired
    private DetalharPerfilAutenticadoService detalharPerfilAutenticadoService;

    @GetMapping
    public UsuarioDetalhadoResponse detalharAutenticado() { return detalharPerfilAutenticadoService.detalhar(); }

    @PutMapping
    public void editar(@Valid @RequestBody EditarPerfilRequest request) {
        editarPerfilService.editar(request);
    }

    @GetMapping("/{id}")
    public UsuarioDetalhadoResponse detalhar(@PathVariable Long id) { return detalharPerfilService.detalhar(id); }

    @GetMapping("/pesquisar")
    public Page<ListarUsuarioResponse> pesquisar(Pageable pageable, @RequestParam String texto) {
        return pesquisarUsuarioService.pesquisar(pageable, texto);
    }

    @GetMapping("/amizades")
    public Page<ListarUsuarioResponse> listarAmigos(Pageable pageable, @RequestParam String texto) {
        return listarAmigosService.listar(pageable, texto);
    }

    @GetMapping("/amizades/pedidos")
    public List<AmizadeResponse> listarPedidosAmizade() {
        return listarPedidosAmizade.listar();
    }

    @PostMapping("/{id}/amizades")
    @ResponseStatus(CREATED)
    public void solicitar(@PathVariable Long id) {
        solicitarAmizadeService.solicitar(id);
    }

    @PutMapping("/{id}/amizades")
    public void aceitar(@PathVariable Long id) {
        aceitarAmizadeService.aceitar(id);
    }

    @DeleteMapping("/{id}/amizades")
    @ResponseStatus(NO_CONTENT)
    public void cancelar(@PathVariable Long id) {
        cancelarAmizadeService.cancelar(id);
    }
}
