package br.edu.utfpr.agenciadenoticias.modelo;

import java.time.LocalDate;
import java.util.UUID;

public class Noticia {

    private String id;
    private String nomeAutor;
    private String emailAutor;
    private LocalDate dataNascimento;
    private String titulo;
    private String assunto;
    private String conteudo;

   
    public Noticia() {
    }

   
    public Noticia(String nomeAutor, String emailAutor, LocalDate dataNascimento,
                   String titulo, String assunto, String conteudo) {
        this.id = UUID.randomUUID().toString();
        this.nomeAutor = nomeAutor;
        this.emailAutor = emailAutor;
        this.dataNascimento = dataNascimento;
        this.titulo = titulo;
        this.assunto = assunto;
        this.conteudo = conteudo;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    public String getEmailAutor() {
        return emailAutor;
    }

    public void setEmailAutor(String emailAutor) {
        this.emailAutor = emailAutor;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
}