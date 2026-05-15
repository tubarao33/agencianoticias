package br.edu.utfpr.agenciadenoticias.persistencia;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.stereotype.Component;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import br.edu.utfpr.agenciadenoticias.modelo.Noticia;

@Component
public class MongoNoticiaDAO implements NoticiaDAO {

    private MongoCollection<Document> colecao;

    public MongoNoticiaDAO() {

        MongoClient client = MongoClients.create("mongodb://mongo:27017");

        MongoDatabase database = client.getDatabase("teste");

        colecao = database.getCollection("noticias");
    }

    @Override
    public void cadastrar(Noticia noticia) {

        Document documento = new Document();

        documento.append("id", noticia.getId());

        documento.append("nomeAutor", noticia.getNomeAutor());

        documento.append("emailAutor", noticia.getEmailAutor());

        documento.append("titulo", noticia.getTitulo());

        documento.append("assunto", noticia.getAssunto());

        documento.append("conteudo", noticia.getConteudo());

        colecao.insertOne(documento);
    }

    @Override
    public List<Noticia> listarNoticias() {

        List<Noticia> noticias = new ArrayList<>();

        FindIterable<Document> documentos = colecao.find();

        for (Document doc : documentos) {

            Noticia noticia = new Noticia();

            noticia.setId(doc.getString("id"));

            noticia.setNomeAutor(doc.getString("nomeAutor"));

            noticia.setEmailAutor(doc.getString("emailAutor"));

            noticia.setTitulo(doc.getString("titulo"));

            noticia.setAssunto(doc.getString("assunto"));

            noticia.setConteudo(doc.getString("conteudo"));

            noticias.add(noticia);
        }

        return noticias;
    }

    @Override
    public Noticia buscarPorId(String id) {

        FindIterable<Document> documentos =
                colecao.find(new Document("id", id));

        Document doc = documentos.first();

        if (doc == null) {

            return null;
        }

        Noticia noticia = new Noticia();

        noticia.setId(doc.getString("id"));

        noticia.setNomeAutor(doc.getString("nomeAutor"));

        noticia.setEmailAutor(doc.getString("emailAutor"));

        noticia.setTitulo(doc.getString("titulo"));

        noticia.setAssunto(doc.getString("assunto"));

        noticia.setConteudo(doc.getString("conteudo"));

        return noticia;
    }

    @Override
    public void remover(String id) {

        colecao.deleteOne(new Document("id", id));
    }

    @Override
    public void atualizar(Noticia noticia) {

        Document filtro = new Document("id", noticia.getId());

        Document novosDados = new Document();

        novosDados.append("nomeAutor", noticia.getNomeAutor());

        novosDados.append("emailAutor", noticia.getEmailAutor());

        novosDados.append("titulo", noticia.getTitulo());

        novosDados.append("assunto", noticia.getAssunto());

        novosDados.append("conteudo", noticia.getConteudo());

        Document update = new Document("$set", novosDados);

        colecao.updateOne(filtro, update);
    }
}