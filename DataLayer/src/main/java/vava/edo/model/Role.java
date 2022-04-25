package vava.edo.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Class representing role in roles table
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "r_id", nullable = false)
    private Integer rId;
    @Column(name = "role_name", nullable = false)
    private String roleName;
    @Column(name = "todo_access_rights", nullable = false)
    private boolean todoAccessRights;
    @Column(name = "team_leader_rights", nullable = false)
    private boolean teamLeaderRights;
    @Column(name = "manager_rights", nullable = false)
    private boolean managerRights;
    @Column(name = "admin_rights", nullable = false)
    private boolean adminRights;

    /**
     * Debugging method
     *
     * @return string with method variables
     */
    @Override
    public String toString() {
        return "Role{" +
                "rId=" + rId +
                ", roleName='" + roleName + '\'' +
                ", todoAccessRights=" + todoAccessRights +
                ", teamLeaderRights=" + teamLeaderRights +
                ", basicRights=" + managerRights +
                ", adminRights=" + adminRights +
                '}';
    }
}
