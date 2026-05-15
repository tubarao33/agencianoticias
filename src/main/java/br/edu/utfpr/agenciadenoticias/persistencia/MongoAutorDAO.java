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

import br.edu.utfpr.agenciadenoticias.modelo.Autor;

@Component
public class MongoAutorDAO implements AutorDAO {

    private MongoCollection<Document> colecao;

    public MongoAutorDAO() {

        MongoClient client =
                MongoClients.create(
                        "mongodb://mongo:27017");

        MongoDatabase database =
                client.getDatabase("teste");

        colecao =
                database.getCollection("autor");
    }

    @Override
    public void cadastrar(
            Autor autor) {

        Document documento =
                new Document();

        documento.append(
                "nome",
                autor.getNome());

        documento.append(
                "email",
                autor.getEmail());

        documento.append(
                "dataNascimento",
                autor.getDataNascimento());

        colecao.insertOne(
                documento);
    }

    @Override
    public List<Autor> listarAutores() {

        List<Autor> autores =
                new ArrayList<>();

        FindIterable<Document> documentos =
                colecao.find();

        for (Document doc : documentos) {

            Autor autor =
                    new Autor();

            autor.setNome(
                    doc.getString("nome"));

            autor.setEmail(
                    doc.getString("email"));

            autor.setDataNascimento(
                    doc.getString("dataNascimento"));

            autores.add(
                    autor);
        }

        return autores;
    }

    @Override
    public Autor buscar(
            String email) {

        Document doc =
                colecao.find(
                        new Document(
                                "email",
                                email))
                        .first();

        if (doc == null) {

            return null;
        }

        Autor autor =
                new Autor();

        autor.setNome(
                doc.getString("nome"));

        autor.setEmail(
                doc.getString("email"));

        autor.setDataNascimento(
                doc.getString("dataNascimento"));

        return autor;
    }

    @Override
    public void remover(
            String email) {

        colecao.deleteOne(
                new Document(
                        "email",
                        email));
    }

    @Override
    public void atualizar(
            Autor autor) {

        Document filtro =
                new Document(
                        "email",
                        autor.getEmail());

        Document novosDados =
                new Document();

        novosDados.append(
                "nome",
                autor.getNome());

        novosDados.append(
                "dataNascimento",
                autor.getDataNascimento());

        Document update =
                new Document(
                        "$set",
                        novosDados);

        colecao.updateOne(
                filtro,
                update);
    }
}