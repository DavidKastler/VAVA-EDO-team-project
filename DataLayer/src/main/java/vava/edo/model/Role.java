package vava.edo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "roles")
@EntityListeners(AuditingEntityListener.class)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "r_id", nullable = false)
    private int rId;
    @Column(name = "role_name", nullable = false)
    private String roleName;
    @Column(name = "todo_access_rights", nullable = false)
    private boolean todoAccessRights;
    @Column(name = "team_leader_rights", nullable = false)
    private boolean teamLeaderRights;
    @Column(name = "admin_rights", nullable = false)
    private boolean adminRights;
}
