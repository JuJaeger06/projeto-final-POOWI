package br.ufsm.csi.aulaspringmvc.controller;

import br.ufsm.csi.aulaspringmvc.model.Usuario;
import br.ufsm.csi.aulaspringmvc.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService userService;

    @Autowired
    public UsuarioController(UsuarioService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String getUsers(Model model) {
        return "/pages/cadastros/cadastrar_usuario";
    }

    @PostMapping("/cadastrar")
    public String cadastrarUser(@ModelAttribute("user") Usuario user, RedirectAttributes redirectAttributes) {
        System.out.println("AAAAAAAAA" + user.getDtNascimentoValue());
        String resultado = userService.cadastrarUsuario(user);
        System.out.println("BBBBBBBBB" + resultado);
        redirectAttributes.addFlashAttribute("mensagem", resultado);
        return "redirect:/login";
    }

    @GetMapping("/editar/{id}")
    public String showEditarUserForm(@PathVariable int id, Model model) {
        Usuario user = userService.getUserLogado(id);
        model.addAttribute("user", user);
        return "home";
    }

    @PostMapping("/excluir/{id}")
    public String excluirUsuario(@PathVariable int id, RedirectAttributes redirectAttributes) {
        String resultado = userService.excluirUser(id);
        redirectAttributes.addFlashAttribute("mensagem", resultado);
        return "redirect:/home";
    }
}
