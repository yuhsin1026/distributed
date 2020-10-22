package Session_Bean;

import Entity_Bean.AuthorEntity;
import Entity_Bean.BookEntity;
import org.springframework.util.StringUtils;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

@Stateless(name = "FindBooksEJB",mappedName = "FindBooksEJB")
public class FindBooksBean {
    @PersistenceContext(unitName = "final")
    private EntityManager em;
    public FindBooksBean() {
    }
     List<AuthorEntity> findarthors(String AuthorLast,String AuthorFirst) {
         TypedQuery<AuthorEntity> query;
         try {
            if (StringUtils.isEmpty(AuthorFirst) && StringUtils.isEmpty(AuthorLast))
                return Collections.emptyList();
            else if (StringUtils.isEmpty(AuthorFirst))
                query= this.em.createQuery("SELECT a FROM AuthorEntity a where a.lastName like :last_name ",AuthorEntity.class);
            else if (StringUtils.isEmpty(AuthorLast))
                query= this.em.createQuery("SELECT a FROM AuthorEntity a where a.firstName like :first_name ",AuthorEntity.class);
            else
                query= this.em.createQuery("SELECT a FROM AuthorEntity a where a.lastName like :last_name " +
                        " AND a.firstName like :first_name", AuthorEntity.class);
            if (!StringUtils.isEmpty(AuthorLast))
                query.setParameter("last_name",AuthorLast);
            if (!StringUtils.isEmpty(AuthorFirst))
                query.setParameter("first_name",AuthorFirst);
            return query.getResultList();

        }catch (Exception e){
            return null;
        }
    }

    public BookEntity clearstatus(int bookid){
        BookEntity book = em.find(BookEntity.class,bookid);
        book.setDayLeft(0);
        book.setOwned(null);
        em.flush();
        return book;
    }
    //order: book, author, library
    public List<Object[]> searchform(String bookname,String AuthorLast,String AuthorFirst, String library) {
        TypedQuery<Object[]> query;
        String pre = "SELECT DISTINCT b.bookName, b.location.libraryName, count(b.bookName) , SUM(CASE WHEN b.owned IS NULL THEN 0 ELSE 1 END)";
        String post = "GROUP BY b.location, b.bookName ";
        //String post =" ";
        //convert author name to author entities
        List<AuthorEntity> authors = findarthors(AuthorLast, AuthorFirst);
        //author is not blank but cannot find in database
        if ((!(StringUtils.isEmpty(AuthorFirst) && StringUtils.isEmpty(AuthorLast))) && authors.isEmpty())
            return null;
        try {
            //8 cases
            if (StringUtils.isEmpty(bookname) && authors.isEmpty() && StringUtils.isEmpty(library))
                return Collections.emptyList();
            else if (StringUtils.isEmpty(bookname) && authors.isEmpty())
                query = this.em.createQuery(pre + " FROM BookEntity b where b.location.libraryName like :library_name " + post, Object[].class);
            else if (StringUtils.isEmpty(bookname) && StringUtils.isEmpty(library))
                query = this.em.createQuery(pre + " FROM BookEntity b join b.createdByArthor a where a IN :authors " + post, Object[].class);
            else if (authors.isEmpty() && StringUtils.isEmpty(library))
                query = this.em.createQuery(pre + " FROM BookEntity b where b.bookName like :book_name " + post, Object[].class);
            else if (StringUtils.isEmpty(bookname))
                query = this.em.createQuery(pre + " FROM BookEntity b join b.createdByArthor a where " +
                        " b.location.libraryName like :library_name AND a IN :authors " + post, Object[].class);
            else if (authors.isEmpty())
                query = this.em.createQuery(pre + " FROM BookEntity b where b.bookName like :book_name " +
                        " AND b.location.libraryName like :library_name " + post, Object[].class);
            else if (StringUtils.isEmpty(library))
                query = this.em.createQuery(pre + " FROM BookEntity b join b.createdByArthor a where b.bookName like :book_name " +
                        " AND a IN :authors " + post, Object[].class);
            else
                query = this.em.createQuery(pre + " FROM BookEntity b join b.createdByArthor a where b.bookName like :book_name " +
                        " AND b.location.libraryName like :library_name AND a IN :authors " + post, Object[].class);
            if (!StringUtils.isEmpty(bookname))
                query.setParameter("book_name", bookname);
            if (!StringUtils.isEmpty(library))
                query.setParameter("library_name", library);
            if (!authors.isEmpty())
                query.setParameter("authors", authors);
            //query.setMaxResults(50);
            List<Object[]> results = query.getResultList();
            return results;
        } catch (Exception e) {
            return null;
        }
    }
    public List<BookEntity> findbooks(String bookname,String AuthorLast,String AuthorFirst, String library)
    {
        TypedQuery<BookEntity> query;
        String pre="SELECT b";
        //convert author name to author entities
        List<AuthorEntity> authors = findarthors(AuthorLast,AuthorFirst);
        //author is not blank but cannot find in database
        if((!(StringUtils.isEmpty(AuthorFirst) && StringUtils.isEmpty(AuthorLast)))&&authors.isEmpty())
            return null;
        try{
            //8 cases
            if (StringUtils.isEmpty(bookname) && authors.isEmpty() && StringUtils.isEmpty(library))
                return Collections.emptyList();
            else if (StringUtils.isEmpty(bookname) && authors.isEmpty())
                query = this.em.createQuery(pre+" FROM BookEntity b where b.location.libraryName like :library_name ",BookEntity.class);
            else if (StringUtils.isEmpty(bookname) && StringUtils.isEmpty(library))
                query = this.em.createQuery(pre+" FROM BookEntity b join b.createdByArthor a where a IN :authors ",BookEntity.class);
            else if (authors.isEmpty() && StringUtils.isEmpty(library))
                query = this.em.createQuery(pre+" FROM BookEntity b where b.bookName like :book_name ",BookEntity.class);
            else if (StringUtils.isEmpty(bookname))
                query = this.em.createQuery(pre+" FROM BookEntity b join b.createdByArthor a where " +
                        " b.location.libraryName like :library_name AND a IN :authors ",BookEntity.class);
            else if (authors.isEmpty())
                query = this.em.createQuery(pre+" FROM BookEntity b where b.bookName like :book_name " +
                        " AND b.location.libraryName like :library_name ",BookEntity.class);
            else if (StringUtils.isEmpty(library))
                query = this.em.createQuery(pre+" FROM BookEntity b join b.createdByArthor a where b.bookName like :book_name " +
                        " AND a IN :authors ",BookEntity.class);
            else
                query = this.em.createQuery(pre+" FROM BookEntity b join b.createdByArthor a where b.bookName like :book_name " +
                        " AND b.location.libraryName like :library_name AND a IN :authors ",BookEntity.class);
            if (!StringUtils.isEmpty(bookname))
                query.setParameter("book_name",bookname);
            if (!StringUtils.isEmpty(library))
                query.setParameter("library_name",library);
            if(!authors.isEmpty())
                query.setParameter("authors",authors);
            //query.setMaxResults(50);
            List<BookEntity> results = query.getResultList();
            return results;
        }catch (Exception e) {
            return null;
        }
    }
}
