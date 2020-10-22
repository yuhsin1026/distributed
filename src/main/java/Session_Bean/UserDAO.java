package Session_Bean;

import Entity_Bean.BookEntity;
import Entity_Bean.UserTbEntity;
import Entity_Bean.UserType;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

import static Entity_Bean.UserType.*;

@Stateless(name = "UserDAOEJB",mappedName = "UserDAOEJB")
public class UserDAO{
    @PersistenceContext(unitName = "final")
    private EntityManager em;

    public UserDAO() {
    }

    public List<UserTbEntity> finduserbyname(String Lastname, String Firstname){
        TypedQuery<UserTbEntity> query = this.em.createQuery("SELECT p FROM UserTbEntity p where p.lastName like :last_name AND p.firstName like :first_name",UserTbEntity.class);
        query.setParameter("last_name",Lastname);
        query.setParameter("first_name",Firstname);
        query.setMaxResults(50);
        return query.getResultList();
    }
    public UserTbEntity login(String userid,String password){

        try {
            TypedQuery<UserTbEntity> query = this.em.createQuery("SELECT u FROM UserTbEntity u where (u.userId like :userid OR u.email like :userid) AND u.userPassword like :password", UserTbEntity.class);
            query.setParameter("userid", userid);
            query.setParameter("password", password);
            return query.getSingleResult();
        }catch (Exception e){
            return null;
        }

    }
    public String regist(UserTbEntity newuser){
        try {
            TypedQuery<UserTbEntity> query = this.em.createQuery("SELECT u FROM UserTbEntity u where u.userId like :userid OR u.email like :email",UserTbEntity.class);


            query.setParameter("userid",newuser.getUserId());
            query.setParameter("email",newuser.getEmail());
            query.getSingleResult();
            return "exist";
        }catch (Exception e){
            if(newuser.getUserId()==null)
                return "invalid";
            else{
                /*if(newuser.getUserId().startsWith("r0"))
                    newuser.setTitle(STUDENT);
                else if(newuser.getUserId().startsWith("z0"))
                    newuser.setTitle(TEACHER);
                else
                    newuser.setTitle(GENERAL_USER);*/
                this.em.persist(newuser);
                return "success";
            }
        }

    }
    public List<UserTbEntity> finduserbyid(String userid) {
        try {
            TypedQuery<UserTbEntity> query = this.em.createQuery("SELECT u FROM UserTbEntity u where u.userId like :userid", UserTbEntity.class);
            query.setParameter("userid", userid);
            query.setMaxResults(1);
            query.setFirstResult(0);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    public UserTbEntity user_return(UserTbEntity user, BookEntity book)
    {
        user = this.em.find(UserTbEntity.class,user.getUserId());
        user.removebook(book);
        this.em.refresh(user);
        return user;
    }
    public UserTbEntity manageUser(UserTbEntity user){
        return this.em.find(UserTbEntity.class,user.getUserId());
    }

}
