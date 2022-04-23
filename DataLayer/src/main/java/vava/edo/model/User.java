package vava.edo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vava.edo.schema.users.UserEdit;

import javax.persistence.*;

/**
 * Class representing user in users table
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "u_id", nullable = false)
    private Integer uId;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private Role userRole;


    /**
     * Static casting method from UserRegister object
     * @param userEdit   UserRegister object that you want to casy
     * @return          cast User object
     */
    public static User from(UserEdit userEdit) {
        User user = new User();
        user.setUsername(userEdit.getUsername());
        user.setPassword(userEdit.getPassword());
        return user;
    }


    /**
     * Debugging method
     * @return  string with method variables
     */
    @Override
    public String toString() {
        return "User{" +
                "uId=" + uId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userRole=" + userRole +
                '}';
    }
}
