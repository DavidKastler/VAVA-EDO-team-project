package vava.edo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import vava.edo.model.dto.UserDto;

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
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
     * Static casting method from UserDto object
     * @param userDto   userDto object that you want to casy
     * @return          caster User object
     */
    public static User from(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
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
