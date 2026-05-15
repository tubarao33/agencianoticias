package br.edu.utfpr.agenciadenoticias.persistencia;

import java.util.List;

import br.edu.utfpr.agenciadenoticias.modelo.Autor;

public interface AutorDAO {

    public void cadastrar(Autor autor);

    public Autor buscar(String id);

    public void remover(String id);

    public void atualizar(Autor autor);

    public List<Autor> listarAutores();
}