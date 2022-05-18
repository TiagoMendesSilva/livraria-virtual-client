import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class MainPost {
    public static void main(String[] args) {

        Livro livro = new Livro(
                10L,"Livro F","ISBN-1111", "Gênero D",19.99,"Autor 2");

        /*
        Livro livroResposta = ClientBuilder.newClient()
                .target("http://localhost:8080/livraria-virtual")
                .path("livro")
                .request()
                .post(Entity.entity(livro,MediaType.APPLICATION_XML)
                        ,Livro.class);

         */

        Response response = ClientBuilder.newClient()
                .target("http://localhost:8080/livraria-virtual")
                .path("livro")
                .request()
                .post(Entity.xml(livro));

        if(response.getStatus() == Response.Status.CREATED.getStatusCode()){

            ItemBusca item = ClientBuilder.newClient()
                    .target(response.getLocation())
                    .request()
                    .get(ItemBusca.class);

            System.out.println(item.getLivro().getTitulo());
        }else if(response.getStatus() == Response.Status.CONFLICT.getStatusCode()){
            System.out.println("Verifique se os dados estão corretos.");
        }
    }
}
