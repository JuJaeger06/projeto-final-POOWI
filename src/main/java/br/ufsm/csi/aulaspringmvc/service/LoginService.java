package br.ufsm.csi.aulaspringmvc.service;

import br.ufsm.csi.aulaspringmvc.dao.UsuarioDAO;
import br.ufsm.csi.aulaspringmvc.model.Usuario;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final UsuarioDAO usuarioDAO;
    public LoginService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public Usuario autenticar(String email, String senha) {
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        Usuario usuario = usuarioDAO.buscarUser(email);


        if (usuario != null && usuario.getSenha().equals(senha)) {
            return usuario; // Retorna o usuário se a autenticação for bem-sucedida
        } else {
            return null; // Retorna null se a autenticação falhar
        }
    }
}