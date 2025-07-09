package br.ufsm.csi.aulaspringmvc.service;


import br.ufsm.csi.aulaspringmvc.dao.FilmeSerieDAO;
import br.ufsm.csi.aulaspringmvc.model.FilmeSerie;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FilmeSerieService {
    private static FilmeSerieDAO filmeSerieDAO = new FilmeSerieDAO();

    public ArrayList<FilmeSerie> getFilmesSeries(int idUser) {
        return filmeSerieDAO.getFilmesSeries(idUser);
    }

    public String cadastrarFilmeSerie(FilmeSerie filmeSerie) {
        return filmeSerieDAO.inserir(filmeSerie);
    }

    public String excluir(int idPrograma) {
        if (filmeSerieDAO.excluir(idPrograma)) {
            return "Sucesso ao excluir programa";
        } else {
            return "Erro ao excluir programa";
        }
    }

    public String atualizarFilmeSerie(FilmeSerie fs) {
        if (filmeSerieDAO.alterar(fs)) {
            return "Sucesso ao alterar programa";
        } else {
            return "Erro ao alterar programa";
        }
    }

    // Se quiser permitir busca individual:
    public FilmeSerie buscarEditar(int idUser, int idPrograma) {
        return filmeSerieDAO.buscarEditar(idUser, idPrograma);
    }
}
