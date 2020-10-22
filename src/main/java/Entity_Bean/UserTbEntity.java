package Entity_Bean;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user_tb", schema = "library")
public class UserTbEntity {
    @Id
    @Column(name = "user_id", nullable = false, length = 15)
    private String userId;

    @Basic
    @Column(name = "email", nullable = true, length = 50)
    private String email;

    @Basic
    @Column(name = "first_name", nullable = true, length = 15)
    private String firstName;

    @Basic
    @Column(name = "last_name", nullable = true, length = 15)
    private String lastName;

    @Basic
    @Column(name = "user_password", nullable = false, length = 15)
    private String userPassword;

    @Basic
    @Column(name = "phone_num", nullable = true, length = 15)
    private String phoneNum;

    @Column(name = "title", nullable = true)
    @Enumerated
    private UserType title;

    @OneToMany(
            mappedBy = "owned",
            cascade = CascadeType.ALL
    )
    private List<BookEntity> borrowbooks;

    @OneToMany(
            mappedBy = "ordered",
            cascade = CascadeType.ALL
    )
    private List<BookEntity> orderbooks;


    //getter and setter
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserPassword() { return userPassword; }
    public void setUserPassword(String userPassword) { this.userPassword = userPassword; }

    public String getPhoneNum() { return phoneNum; }
    public void setPhoneNum(String phoneNum) { this.phoneNum = phoneNum; }

    public UserType getTitle() { return title;}
    public void setTitle(UserType title) {  this.title = title;}

    public List<BookEntity> getOrderbooks() {
        return orderbooks;
    }
    public void setOrderbooks(List<BookEntity> orderbooks) {
        this.orderbooks = orderbooks;
    }

    public List<BookEntity> getBorrowbooks() {
        return borrowbooks;
    }
    public void setBorrowbooks(List<BookEntity> borrowbooks) {
        this.borrowbooks = borrowbooks;
    }
    public void removebook(BookEntity book)
    {
        if(this.borrowbooks.contains(book))
        this.borrowbooks.remove(book);
    }
    public void addbook(BookEntity book)
    {
        if(!this.borrowbooks.contains(book))
            this.borrowbooks.add(book);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTbEntity that = (UserTbEntity) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(email, that.email) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(userPassword, that.userPassword) &&
                Objects.equals(phoneNum, that.phoneNum) &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, email, firstName, lastName, userPassword, phoneNum, title);
    }
}
