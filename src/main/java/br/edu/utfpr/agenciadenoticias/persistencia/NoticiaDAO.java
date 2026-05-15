package br.edu.utfpr.agenciadenoticias.persistencia;

import java.util.List;

import br.edu.utfpr.agenciadenoticias.modelo.Noticia;

public interface NoticiaDAO {

    public void cadastrar(Noticia noticia);

    public Noticia buscarPorId(String id);

    public void remover(String id);

    public void atualizar(Noticia noticia);

    public List<Noticia> listarNoticias();
}