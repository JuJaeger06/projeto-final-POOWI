package br.ufsm.csi.aulaspringmvc.service;
import br.ufsm.csi.aulaspringmvc.dao.UsuarioDAO;
import br.ufsm.csi.aulaspringmvc.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UsuarioService {
    private final UsuarioDAO usuarioDAO;
    public UsuarioService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public Usuario buscarUser(String email) {
        return usuarioDAO.buscarUser(email);
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarioDAO.getUsuarios() ;
    }

    public String cadastrarUsuario(Usuario user) {
        return usuarioDAO.cadastrarUsuario(user);
    }

    public Usuario getUserLogado(int id) {
        Usuario user = usuarioDAO.getUserLogado(id);

        return user;
    }

    public String excluirUser(int id){

        if(usuarioDAO.excluirUser(id)){
            return "Sucesso ao excluir usuario";
        }else{
            return "Erro ao excluir usuario";
        }

    }

}
