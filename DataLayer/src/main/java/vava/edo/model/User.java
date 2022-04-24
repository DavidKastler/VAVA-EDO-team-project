package vava.edo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vava.edo.schema.users.UserEdit;

import javax.persistence.*;
import java.util.List;

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

    @JsonIgnore
    @OneToMany(mappedBy = "groupCreator", cascade = CascadeType.ALL)
    private List<Group> userGroups;
    @JsonIgnore
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<GroupMember> groupMembers;
    @JsonIgnore
    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private List<Chat> chats;
    @JsonIgnore
    @OneToMany(mappedBy = "firstUser", cascade = CascadeType.ALL)
    private List<Relationship> relationshipsFirstUser;
    @JsonIgnore
    @OneToMany(mappedBy = "secondUser", cascade = CascadeType.ALL)
    private List<Relationship> relationshipsSecondUser;
    @JsonIgnore
    @OneToMany(mappedBy = "reporter", cascade = CascadeType.ALL)
    private List<Report> reporterReports;
    @JsonIgnore
    @OneToMany(mappedBy = "violator", cascade = CascadeType.ALL)
    private List<Report> violatorReports;
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Feedback> feedbacks;
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Todo> todos;


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
