package Entity_Bean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "admin", schema = "library")
public class AdminEntity {
    private String adminId;
    private String adminPwd;

    @Id
    @Column(name = "admin_id", nullable = false, length = 15)
    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    @Basic
    @Column(name = "admin_pwd", nullable = false, length = 15)
    public String getAdminPwd() {
        return adminPwd;
    }

    public void setAdminPwd(String adminPwd) {
        this.adminPwd = adminPwd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminEntity that = (AdminEntity) o;
        return Objects.equals(adminId, that.adminId) &&
                Objects.equals(adminPwd, that.adminPwd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adminId, adminPwd);
    }
}
