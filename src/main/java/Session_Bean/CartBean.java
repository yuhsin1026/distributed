package Session_Bean;

import Entity_Bean.BookEntity;
import Entity_Bean.Pair;
import Entity_Bean.UserTbEntity;
import Entity_Bean.UserType;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.ejb.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.beans.JavaBean;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import static Entity_Bean.UserType.STUDENT;
import static Entity_Bean.UserType.TEACHER;

@Stateful(name = "CartEJB",mappedName = "CartEJB")

@StatefulTimeout(unit = TimeUnit.MINUTES, value = 10)
public class CartBean {
    @PersistenceContext(unitName = "final")
    private EntityManager em;

    private List<Pair<String,String>> books = new ArrayList<>();

    public CartBean() {
    }

    public List<Pair<String, String>> getBooks() {
        return books;
    }

    public void addBook(Pair<String,String> book,String id){
        int borrowed =update_user(id).get(0).getBorrowbooks().size();
        if ((borrowed+books.size())<5)
            if(!books.contains(book)) //no same book in the cart
                books.add(book);
    }

    public void removeBook(Pair<String,String> book){
        if(books.contains(book))
            books.remove(book);

    }
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public UserTbEntity complete(UserTbEntity user){
        for(Pair<String, String> book : books){

            BookEntity cur_book= findfirst(book.first(),book.second()).get(0);
            //em.getTransaction().begin();
            cur_book.setOwned(user);
            /*if(user.getTitle()== STUDENT)
                cur_book.setDayLeft(14);
            else if(user.getTitle()==TEACHER)
                cur_book.setDayLeft(20);
            else*/
                cur_book.setDayLeft(14);
            cur_book.setMonthlyFreq(cur_book.getMonthlyFreq()+1);
            cur_book.setTotal(cur_book.getTotal()+1);
            user = em.find(UserTbEntity.class,user.getUserId());
            user.addbook(cur_book);
        }
        books.clear();
        return user;
    }
    @Remove
    public void remove() {
        books = null;
    }

    public List<BookEntity> findfirst(String bookname, String library)
    {
        try{
            TypedQuery<BookEntity> query = this.em.createQuery("SELECT b FROM BookEntity b where b.bookName like :book_name " +
                    " AND b.location.libraryName like :library_name AND b.owned is NULL",BookEntity.class);
            if (!StringUtils.isEmpty(bookname))
                query.setParameter("book_name",bookname);
            if (!StringUtils.isEmpty(library))
                query.setParameter("library_name",library);
            query.setMaxResults(1);
            query.setFirstResult(0);
            return query.getResultList();

        }catch (Exception e) {
            return null;
        }
    }
    public List<UserTbEntity> update_user(String userid){

        try {
            TypedQuery<UserTbEntity> query = this.em.createQuery("SELECT u FROM UserTbEntity u where u.userId like :userid", UserTbEntity.class);
            query.setParameter("userid", userid);
            return query.getResultList();
        }catch (Exception e){
            return null;
        }

    }
}
