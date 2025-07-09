package br.ufsm.csi.aulaspringmvc.controller;

import br.ufsm.csi.aulaspringmvc.model.Livros;
import br.ufsm.csi.aulaspringmvc.model.Usuario;
import br.ufsm.csi.aulaspringmvc.service.LivroService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/livro")
public class LivroController {

    private final LivroService livroService;

    @Autowired
    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping("")
    public String getLivros(Model model, HttpServletRequest request) {
        Object usuarioLogadoObj = request.getSession().getAttribute("usuarioLogado");
        Usuario usuario = (Usuario) usuarioLogadoObj;
        model.addAttribute("livros", new LivroService().getLivros(usuario.getIdUser()));
        return "/pages/cadastros/cadastrar_livro";
    }

    @PostMapping("/cadastrar")
    public String cadastrarLivro(@ModelAttribute("livros") Livros livro, RedirectAttributes redirectAttributes) {
        String resultado = livroService.cadastrarLivro(livro);
        System.out.println("Resultado do cadastro: " + resultado);
        redirectAttributes.addFlashAttribute("mensagem", resultado);
        return "redirect:/home";
    }

    @GetMapping("/editar/{id_livro}")
    public String showEditaLivroForm(@PathVariable int id_livro, Model model, HttpServletRequest request) {
        Object usuarioLogadoObj = request.getSession().getAttribute("usuarioLogado");
        Usuario usuario = (Usuario) usuarioLogadoObj;
        int id_user = usuario.getIdUser();
        Livros livro = livroService.buscarEditar(id_user, id_livro);
        model.addAttribute("livros", livro);
        return "home";
    }

    @PostMapping("/excluir/{id}")
    public String excluirLivro(@PathVariable int id, RedirectAttributes redirectAttributes) {
        String resultado = livroService.excluir(id);
        redirectAttributes.addFlashAttribute("mensagem", resultado);
        return "redirect:/home";
    }
}