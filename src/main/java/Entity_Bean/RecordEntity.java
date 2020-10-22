package Entity_Bean;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "record", schema = "library")
public class RecordEntity {
    @Id
    //  @GeneratedValue
    @Column(name = "record_id", nullable = false)
    private int indexid;

    @Basic
    @Column(name = "time", nullable = true)
    private Timestamp time;

    @Basic
    @Column(name = "book_id", nullable = false)
    private int bookId;

    @Basic
    @Column(name = "user_id", nullable = false, length = 9)
    private String userId;




    public int getIndex() {
        return indexid;
    }
    public void setIndex(int index) {
        this.indexid = index;
    }


    public Timestamp getTime() {
        return time;
    }
    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getBookId() {
        return bookId;
    }
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecordEntity that = (RecordEntity) o;
        return indexid == that.indexid &&
                bookId == that.bookId &&
                Objects.equals(time, that.time) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(indexid, time, bookId, userId);
    }
}
