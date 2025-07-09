package br.ufsm.csi.aulaspringmvc.controller;

import br.ufsm.csi.aulaspringmvc.model.FilmeSerie;
import br.ufsm.csi.aulaspringmvc.model.Usuario;
import br.ufsm.csi.aulaspringmvc.service.FilmeSerieService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/filmes")
public class FilmeSerieController {

    private final FilmeSerieService filmeSerieService;

    @Autowired
    public FilmeSerieController(FilmeSerieService filmeSerieService) {
        this.filmeSerieService = filmeSerieService;
    }

    @GetMapping("/cadastrar")
    public String showCadastrarFilmeSerieForm(Model model) {
        model.addAttribute("filmeSerie", new FilmeSerie());
        return "/pages/cadastros/cadastrar_filmeSerie";
    }

    @PostMapping("/cadastrar")
    public String cadastrarFilmeSerie(@ModelAttribute("filmeSerie") FilmeSerie filmeSerie, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        Usuario usuario = (Usuario) request.getSession().getAttribute("userLogado");
        if (usuario != null) {
            filmeSerie.setIdUser(usuario.getIdUser());
            String resultado = filmeSerieService.cadastrarFilmeSerie(filmeSerie);
            redirectAttributes.addFlashAttribute("mensagem", resultado);
        } else {
            redirectAttributes.addFlashAttribute("erro", "Usuário não logado. Por favor, faça login.");
            return "redirect:/";
        }
        return "redirect:/home";
    }

    @GetMapping("/editar/{idPrograma}")
    public String showEditarFilmeSerieForm(@PathVariable int idPrograma, Model model, HttpServletRequest request) {
        Usuario usuario = (Usuario) request.getSession().getAttribute("userLogado");
        if (usuario != null) {
            FilmeSerie filmeSerie = filmeSerieService.buscarEditar(usuario.getIdUser(), idPrograma);
            model.addAttribute("filmeSerie", filmeSerie);
            return "/pages/cadastros/cadastrar_filme_serie";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/atualizar")
    public String atualizarFilmeSerie(@ModelAttribute("filmeSerie") FilmeSerie filmeSerie, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        Usuario usuario = (Usuario) request.getSession().getAttribute("userLogado");
        if (usuario != null) {
            filmeSerie.setIdUser(usuario.getIdUser());
            String resultado = filmeSerieService.atualizarFilmeSerie(filmeSerie);
            redirectAttributes.addFlashAttribute("mensagem", resultado);
        } else {
            redirectAttributes.addFlashAttribute("erro", "Usuário não logado. Por favor, faça login.");
            return "redirect:/";
        }
        return "redirect:/home";
    }

    @PostMapping("/excluir/{id}")
    public String excluirFilmeSerie(@PathVariable int id, RedirectAttributes redirectAttributes) {
        String resultado = filmeSerieService.excluir(id);
        redirectAttributes.addFlashAttribute("mensagem", resultado);
        return "redirect:/home";
    }
}
