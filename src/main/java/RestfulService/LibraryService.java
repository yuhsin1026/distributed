package RestfulService;
import Entity_Bean.BookEntity;
import Entity_Bean.LibraryEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;
@Stateless
@Path("/library")
public class LibraryService {
  @PersistenceContext(unitName = "final")
  private EntityManager em;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<LibraryEntity> getLibrariesinfo() {
    return this.em.createQuery("select lib from LibraryEntity lib",LibraryEntity.class).getResultList();
  }

  @GET
  @Path("/{libName}")
  @Produces(MediaType.APPLICATION_JSON)
  public List<LibraryEntity> getOneLibraryinfo(@PathParam("libName") String libName) {

    try {
      TypedQuery<LibraryEntity> query=this.em.createQuery("select lib from LibraryEntity lib where lib.libraryName like :lib_name",LibraryEntity.class);
      query.setParameter("lib_name",libName);
      return query.getResultList();
    }catch (Exception e){
      return null;
    }
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response donate(BookEntity book) {
    int max=this.em.createQuery("select max(b.bookId) from BookEntity b", Integer.class).getSingleResult()+1;
    book.setBookId(max);
    book.setOwned(null);
    book.setDayLeft(0);
    book.setMonthlyFreq(0);
    book.setTotal(0);
    this.em.persist(book);
    return Response.ok(book,MediaType.APPLICATION_JSON).build();
  }
}
