package Entity_Bean;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Author", schema = "library")
public class AuthorEntity {
    @Id
    @Column(name = "Author_ID", nullable = false)
    private int authorId;

    @Basic
    @Column(name = "last_name", nullable = false, length = 15)
    private String lastName;

    @Basic
    @Column(name = "first_name", nullable = false, length = 15)
    private String firstName;

    @ManyToMany(mappedBy = "createdByArthor")
    private List<BookEntity> appearsOnBooks;
    public List<BookEntity> getAppearsOnBooks() { return appearsOnBooks; }
    public void setAppearsOnBooks(List<BookEntity> appearsOnBooks) { this.appearsOnBooks = appearsOnBooks; }

    //getter and setter
    public int getAuthorId() {
        return authorId;
    }
    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorEntity that = (AuthorEntity) o;
        return Objects.equals(authorId, that.authorId) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(firstName, that.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId, lastName, firstName);
    }
}
