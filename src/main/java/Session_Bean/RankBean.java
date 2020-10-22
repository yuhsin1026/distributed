package Session_Bean;

import Entity_Bean.LibraryEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless(name = "RankEJB",mappedName = "RankEJB")
public class RankBean {
    @PersistenceContext(unitName = "final")
    private EntityManager em;

    public RankBean() {}

    public List<Object[]> rank_a_library(LibraryEntity lib){

        lib=this.em.find(LibraryEntity.class,lib.getId());
        lib.getRanks().clear();
        TypedQuery<Object[]> query;
        List<Object[]> ranklist;
        query = this.em.createQuery("SELECT DISTINCT b.bookName, sum(b.monthlyFreq),sum(b.total) FROM BookEntity b where b.location.libraryName like :library_name GROUP BY b.location, b.bookName order by sum(b.total) desc ",Object[].class);
        query.setMaxResults(50);
        query.setParameter("library_name",lib.getLibraryName());
        ranklist=query.getResultList();
        for (Object[] rank: ranklist)
        {
            lib.addRank((String) rank[0], ((Long)rank[1]).intValue());
            em.flush();
        }
        return ranklist;
    }

    public List<Object[]> getTotalrank(){
        TypedQuery<Object[]> query;
        query = this.em.createQuery("SELECT DISTINCT b.bookName, sum(b.monthlyFreq), sum(b.total) FROM BookEntity b GROUP BY b.bookName order by sum(b.total) desc ",Object[].class);
        query.setMaxResults(50);
        return query.getResultList();

    }

    public List<LibraryEntity> getLibraries(){
        TypedQuery<LibraryEntity> query;
        query = this.em.createQuery("SELECT b FROM LibraryEntity b",LibraryEntity.class);
        return query.getResultList();
    }
    public LibraryEntity getLibrarybyname(String libname){
        TypedQuery<LibraryEntity> query;
        try{
            query = this.em.createQuery("SELECT b FROM LibraryEntity b where b.libraryName like :name",LibraryEntity.class);
            query.setParameter("name",libname);
            return query.getSingleResult();
        }catch (Exception e){
            return null;
        }
    }
}
