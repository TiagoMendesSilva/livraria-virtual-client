import org.glassfish.jersey.SslConfigurator;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

public class Main {

    public static void main(String[] args) {

        SslConfigurator configurator = SslConfigurator
                .newInstance()
                .trustStoreFile("library_server.keystore")
                .trustStorePassword("livraria");

        SSLContext context = configurator.createSSLContext();

        Client client = ClientBuilder.newBuilder().sslContext(context).build();

        HttpAuthenticationFeature auth =
                HttpAuthenticationFeature.basic("admin","password");

        client.register(auth);

        Livros livros = client
                .target("https://localhost:8443/livraria-virtual")
                .path("livro")
                .request(MediaType.APPLICATION_XML)
                .get(Livros.class);

        for(Livro livro : livros.getLivros()){
            System.out.println(livro.getTitulo());
        }


    }
}
