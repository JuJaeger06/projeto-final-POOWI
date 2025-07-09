package br.ufsm.csi.aulaspringmvc.controller;

import br.ufsm.csi.aulaspringmvc.model.Usuario;
import br.ufsm.csi.aulaspringmvc.service.LoginService;
import br.ufsm.csi.aulaspringmvc.service.FilmeSerieService;
import br.ufsm.csi.aulaspringmvc.service.LivroService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    private final LoginService loginService;
    private final FilmeSerieService filmeSerieService;
    private final LivroService livroService;

    @Autowired
    public LoginController(LoginService loginService, FilmeSerieService filmeSerieService, LivroService livroService) {
        this.loginService = loginService;
        this.filmeSerieService = filmeSerieService;
        this.livroService = livroService;
    }

    @GetMapping("/")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String email, @RequestParam String senha, HttpSession session, RedirectAttributes redirectAttributes) {
        Usuario usuarioAut = loginService.autenticar(email, senha);
        System.out.println("Usuário autenticado: " + usuarioAut);
        if (usuarioAut != null) {
            session.setAttribute("userLogado", usuarioAut);
            session.setMaxInactiveInterval(3600);
            return "home";
        } else {
            redirectAttributes.addFlashAttribute("erro", "Email ou senha incorretos");
            return "redirect:/";
        }
    }

    @GetMapping("/home")
    public String showHomePage(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("userLogado");
        if (usuario != null) {
            model.addAttribute("filmesSeries", filmeSerieService.getFilmesSeries(usuario.getIdUser()));
            model.addAttribute("livros", livroService.getLivros(usuario.getIdUser()));
            return "home";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/logout")
    public String handleLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
