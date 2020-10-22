package RestfulClient;

import Entity_Bean.BookEntity;
import Entity_Bean.LibraryEntity;
import org.springframework.http.codec.ClientCodecConfigurer;

import javax.jms.Message;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.Produces;
import javax.ws.rs.client.*;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.MessageFormat;
import java.util.List;

public class RestClient {
    private Client client= ClientBuilder.newClient();
    private WebTarget webtarget=client.target("http://localhost:8080/final/webresources/library");
    public RestClient(){
        Client client= ClientBuilder.newClient();
        WebTarget webtarget =client.target("http://localhost:8080/final/webresources/library");

    }
    //get: /
    public List<LibraryEntity> getLibrariesinfo() throws ClientErrorException {
        return  webtarget.request(MediaType.APPLICATION_JSON).get(new GenericType<List<LibraryEntity>>() {});
    }
    //get: /{lib_name}
    public List<LibraryEntity> getalibraryinfo(String name) throws ClientErrorException {
        //webtarget.request(MediaType.APPLICATION_JSON).get().readEntity(BookEntity.class);
        return webtarget.path(name).request(MediaType.APPLICATION_JSON).get(new GenericType<List<LibraryEntity>>() {});
    }
    //post: /
    public Response donate(BookEntity book) throws ClientErrorException {
        return webtarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(book,MediaType.APPLICATION_JSON));
    }

}
