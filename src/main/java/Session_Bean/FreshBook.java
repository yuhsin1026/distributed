package Session_Bean;

import Entity_Bean.BookEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless(name = "RefreshEJB",mappedName = "RefreshEJB")
public class FreshBook {
    @PersistenceContext(unitName = "final")
    private EntityManager em;
    public void freshbook() {
        try {
            TypedQuery<BookEntity> query = em.createQuery("SELECT b FROM BookEntity b WHERE b.owned is Not NULL", BookEntity.class);
            List<BookEntity> booklist = query.getResultList();
            for (BookEntity cur_book : booklist) {

               // System.out.println("The name of the book is"+cur_book.getBookName());
              //  System.out.println("The day left is"+cur_book.getDayLeft());
                if (cur_book.getDayLeft() > 0) {
                    em.remove(cur_book);
                    cur_book.setDayLeft(cur_book.getDayLeft() - 1);



                    em.persist(cur_book);


                }
            }
           // em.flush();
        } catch (Exception e) {
         //   System.out.println("null book is borrowed");
        }
    }

    public void freshfreq() {
        try {
            TypedQuery<BookEntity> query = em.createQuery("SELECT b FROM BookEntity b WHERE b.monthlyFreq>0", BookEntity.class);
            List<BookEntity> booklist = query.getResultList();
            for (BookEntity cur_book : booklist) {
                em.remove(cur_book);
              //  System.out.println("The name of the book is"+cur_book.getBookName());
               // System.out.println("The borrow frequence is"+cur_book.getMonthlyFreq());
                cur_book.setMonthlyFreq(0);
                em.persist(cur_book);



            }
            // em.flush();
        } catch (Exception e) {
         //   System.out.println("null book is borrowed this month");
        }
    }
}
