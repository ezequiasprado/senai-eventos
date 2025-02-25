package com.evento.specs;

import com.evento.dtos.UsuarioDTO;
import com.evento.exceptions.BussinesException;
import com.evento.models.Usuario;
import com.evento.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class UsuarioSpec {
    private static final String MSG_CPF = "Usuário já cadastrado com cpf: %s.";
    private static final String MSG_EMAIL = "Usuário já cadastrado com email: %s.";
    private static final String MSG_ID = "Id não pode ser nulo.";

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void verificarSeExisteUsuarioComEmailDuplicado(Usuario usuario) {
        if (nonNull(usuario)) {
            throw new BussinesException(
                    String.format(MSG_EMAIL, usuario.getEmail()));
        }
    }

    public void verificarSeExisteUsuarioComCpfDuplicado(Usuario usuario) {
        if (nonNull(usuario)) {
            throw new BussinesException(
                    String.format(MSG_CPF, usuario.getCpf()));
        }
    }

    public void verificarCampoIdNulo(Long id) {
        if (isNull(id)) throw new BussinesException(MSG_ID);
    }

    public void verificarEmailEmUso(Usuario usuario, UsuarioDTO usuarioDTO) {
        boolean alterouEmail = !(usuario.getEmail().equals(usuarioDTO.getEmail()));

        if (alterouEmail) {
            boolean encontrouUsuarioComEmailInformado =
                    nonNull(usuarioRepository
                    .findByEmail(usuarioDTO.getEmail()));

            if(encontrouUsuarioComEmailInformado)
                throw new BussinesException(String.format(MSG_EMAIL,
                        usuarioDTO.getEmail()));
        }
    }
}
