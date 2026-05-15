package br.edu.utfpr.agenciadenoticias.controle;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.utfpr.agenciadenoticias.modelo.Autor;
import br.edu.utfpr.agenciadenoticias.modelo.Noticia;
import br.edu.utfpr.agenciadenoticias.persistencia.AutorDAO;
import br.edu.utfpr.agenciadenoticias.persistencia.NoticiaDAO;

@Controller
public class AutorController {

    @Autowired
    private AutorDAO autorDao;

    @Autowired
    private NoticiaDAO noticiaDao;

    @GetMapping("/")
    public String exibirPaginaInicial(Model model) {

        List<Noticia> noticias =
                noticiaDao.listarNoticias();

        model.addAttribute(
                "noticias",
                noticias);

        return "paginainicial";
    }

    @GetMapping("/cadastrarAutor")
    public String exibirPaginaCadastroAutor() {

        return "autor";
    }

    @PostMapping("/cadastrarAutor")
    public String receberFormularioCadastro(
            Autor autor) {

        autorDao.cadastrar(autor);

        return "redirect:/listarAutores";
    }

    @GetMapping("/listarAutores")
    public String exibirPaginaListagem(
            Model model) {

        List<Autor> autores =
                autorDao.listarAutores();

        model.addAttribute(
                "autores",
                autores);

        return "listagem-autores";
    }

    @GetMapping("/removerAutor")
    public String removerAutor(
            @RequestParam String email) {

        autorDao.remover(email);

        return "redirect:/listarAutores";
    }

    @GetMapping("/editarAutor")
    public String exibirPaginaEditarAutor(
            @RequestParam String email,
            Model model) {

        Autor autor =
                autorDao.buscar(email);

        if (autor == null) {

            return "redirect:/listarAutores";
        }

        model.addAttribute(
                "autor",
                autor);

        return "editar-autor";
    }

    @PostMapping("/atualizarAutor")
    public String atualizarAutor(
            Autor autor) {

        autorDao.atualizar(autor);

        return "redirect:/listarAutores";
    }

    @GetMapping("/cadastrarNoticia")
    public String exibirPaginaCadastrarNoticia(
            Model model) {

        List<Autor> autores =
                autorDao.listarAutores();

        model.addAttribute(
                "autores",
                autores);

        return "nova-noticia";
    }

    @PostMapping("/cadastrarNoticia")
    public String receberFormularioCadastroNoticia(
            Noticia noticia) {

        noticia.setId(
                UUID.randomUUID().toString());

        noticiaDao.cadastrar(noticia);

        return "redirect:/";
    }

    @GetMapping("/removerNoticia")
    public String removerDocumento(
            @RequestParam String idDocumento) {

        noticiaDao.remover(idDocumento);

        return "redirect:/";
    }

    @GetMapping("/detalheNoticia")
    public String exibirDetalheNoticia(
            @RequestParam String idDocumento,
            Model model) {

        Noticia noticia =
                noticiaDao.buscarPorId(idDocumento);

        if (noticia == null) {

            return "redirect:/";
        }

        model.addAttribute(
                "noticia",
                noticia);

        return "detalhe-noticia";
    }

    @GetMapping("/editarNoticia")
    public String exibirPaginaEditarNoticia(
            @RequestParam String idDocumento,
            Model model) {

        Noticia noticia =
                noticiaDao.buscarPorId(idDocumento);

        if (noticia == null) {

            return "redirect:/";
        }

        model.addAttribute(
                "noticia",
                noticia);

        return "editar";
    }

    @PostMapping("/atualizarNoticia")
    public String atualizarNoticia(
            Noticia noticia) {

        noticiaDao.atualizar(noticia);

        return "redirect:/";
    }
}