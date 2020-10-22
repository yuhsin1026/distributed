package Entity_Bean;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Objects;
@XmlRootElement
@Entity
@Table(name = "Book", schema = "library")
public class BookEntity {
    @Id
    @Column(name = "Book_ID", nullable = false)
    private int bookId;

    @Basic
    @Column(name = "book_name", nullable = true, length = 25)
    private String bookName;

    @Basic
    @Column(name = "day_left", nullable = false)
    private int dayLeft;

    @Basic
    @Column(name = "monthly_freq", nullable = false)
    private int monthlyFreq;

    @Basic
    @Column(name = "total", nullable = false)
    private int total;

    @ManyToMany
    @JoinTable(name = "JND_Book_Author",
        joinColumns = @JoinColumn(name = "book_id"),
        inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<AuthorEntity>createdByArthor;
    public List<AuthorEntity> getCreatedByArthor() { return createdByArthor; }
    public void setCreatedByArthor(List<AuthorEntity> createdByArthor) { this.createdByArthor = createdByArthor; }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="library_id")
    private LibraryEntity location;
    public LibraryEntity getLocation() { return location; }
    public void setLocation(LibraryEntity location) { this.location = location; }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private UserTbEntity owned;
    public UserTbEntity getOwned() { return owned; }
    public void setOwned(UserTbEntity owned) { this.owned = owned; }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    private UserTbEntity ordered;
    public UserTbEntity getOrdered() { return ordered; }
    public void setOrdered(UserTbEntity ordered) { this.ordered = ordered; }

    public int getBookId() {
        return bookId;
    }
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getDayLeft() {
        return dayLeft;
    }
    public void setDayLeft(int dayLeft) {
        this.dayLeft = dayLeft;
    }

    public int getMonthlyFreq() {
        return monthlyFreq;
    }
    public void setMonthlyFreq(int monthlyFreq) {
        this.monthlyFreq = monthlyFreq;
    }

    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEntity that = (BookEntity) o;
        return dayLeft == that.dayLeft &&
                monthlyFreq == that.monthlyFreq &&
                total == that.total &&
                Objects.equals(bookId, that.bookId) &&
                Objects.equals(bookName, that.bookName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, bookName, dayLeft, monthlyFreq, total);
    }
}
