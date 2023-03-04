package br.com.cwi.crescer.tcc.service;

import br.com.cwi.crescer.tcc.domain.Amizade;
import br.com.cwi.crescer.tcc.repository.AmizadeRepository;
import br.com.cwi.crescer.tcc.security.domain.Usuario;
import br.com.cwi.crescer.tcc.security.service.UsuarioAutenticadoService;
import br.com.cwi.crescer.tcc.service.core.BuscarUsuarioService;
import br.com.cwi.crescer.tcc.service.core.ValidaPodeSolicitarAmizadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolicitarAmizadeService {

    @Autowired
    private AmizadeRepository amizadeRepository;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;

    @Autowired
    private ValidaPodeSolicitarAmizadeService validaPodeSolicitarAmizadeService;

    public void solicitar(Long usuarioId) {
        Usuario usuario = usuarioAutenticadoService.get();

        validaPodeSolicitarAmizadeService.validar(usuario.getId(), usuarioId);

        Usuario amigo = buscarUsuarioService.porId(usuarioId);

        Amizade amizade = new Amizade();
        amizade.setUsuario(usuario);
        amizade.setAmigo(amigo);
        amizade.setAceito(false);

        amizadeRepository.save(amizade);
    }
}
