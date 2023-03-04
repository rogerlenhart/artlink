package br.com.cwi.crescer.tcc.service;

import br.com.cwi.crescer.tcc.domain.Amizade;
import br.com.cwi.crescer.tcc.repository.AmizadeRepository;
import br.com.cwi.crescer.tcc.security.domain.Usuario;
import br.com.cwi.crescer.tcc.security.service.UsuarioAutenticadoService;
import br.com.cwi.crescer.tcc.service.core.BuscarAmizadeService;
import br.com.cwi.crescer.tcc.service.core.BuscarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class AceitarAmizadeService {

    @Autowired
    private BuscarAmizadeService buscarAmizadeService;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;

    @Autowired
    private AmizadeRepository amizadeRepository;

    public void aceitar(Long id) {
        Usuario usuario = usuarioAutenticadoService.get();

        Amizade amizade = amizadeRepository.findByUsuarioIdAndAmigoId(usuario.getId(), id)
                .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, "Você não possui um pedido de amizade deste usuário."));

        if(amizade.getAmigo() != usuario) {
            throw new ResponseStatusException(BAD_REQUEST, "Este pedido de amizade não é direcionado a você.");
        }

        if(amizade.isAceito()) {
            throw new ResponseStatusException(BAD_REQUEST, "Este pedido de amizade já foi aceito.");
        }

        amizade.setAceito(true);

        amizadeRepository.save(amizade);
    }
}
