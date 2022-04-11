package vava.edo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.sql.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "gr_id", nullable = false)
    private int groupId;
    @Column(name = "group_name", nullable = false)
    private String groupName;
    @Column(name = "group_creator_id", nullable = false)
    private int groupCreatorId;
    @ElementCollection
    @CollectionTable(name = "group_members", joinColumns = @JoinColumn(name = "group_id"))
    @Column(name = "member_id", nullable = false)
    private List<Integer> memberIds;

    /**
     * Debugging method
     * @return  string with method variables
     */
    @Override
    public String toString() {
        return "Group{" +
                "groupId=" + groupId +
                ", groupName=" + groupName +
                ", groupCreatorId=" + groupCreatorId +
                '}';
    }
}