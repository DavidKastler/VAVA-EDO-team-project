package vava.edo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vava.edo.schema.UserRegister;

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
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role userRole;


    /**
     * Static casting method from UserRegister object
     * @param userRegister   UserRegister object that you want to casy
     * @return          cast User object
     */
    public static User from(UserRegister userRegister) {
        User user = new User();
        user.setUsername(userRegister.getUsername());
        user.setPassword(userRegister.getPassword());
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
