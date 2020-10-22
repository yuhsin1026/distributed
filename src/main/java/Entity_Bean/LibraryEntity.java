package Entity_Bean;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
@XmlRootElement
@Entity
@Table(name = "library", schema = "library")
public class LibraryEntity {
    @Id @GeneratedValue
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "library_name", nullable = true, length = 15)
    private String libraryName;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name= "rank_table",joinColumns = @JoinColumn(name = "library_id"))
    @MapKeyColumn(name ="book_name")
    @Column(name = "total_month")
    private Map<String,Integer> ranks= new HashMap<>();
    @Basic
    @Column(name = "address", nullable = true, length = 50)
    private String address;

    public LibraryEntity(){}

    //getter and setter
    public Map<String, Integer> getRanks() {
        return ranks;
    }

    public void setRanks(Map<String, Integer> ranks) {
        this.ranks = ranks;
    }

    public void addRank(String bookname, Integer freq) {
        this.ranks.put(bookname,freq);
    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "library_name", nullable = true, length = 15)
    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LibraryEntity that = (LibraryEntity) o;
        return id == that.id &&
                Objects.equals(libraryName, that.libraryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, libraryName);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
