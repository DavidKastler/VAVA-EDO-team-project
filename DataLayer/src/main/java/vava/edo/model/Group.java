package vava.edo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vava.edo.schema.groups.GroupCreate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Class representing group in groups table
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "groups")
public class Group implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "gr_id", nullable = false)
    private Integer grId;
    @Column(name = "group_name", nullable = false)
    private String groupName;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_creator_id", nullable = false)
    private User groupCreator;


    @JsonIgnore
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<GroupMember> groupMembers;
    @JsonIgnore
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Chat> chats;



    /**
     * Static casting method from GroupCreate object
     *
     * @param groupCreate GroupCreate object that you want to cast
     * @return cast Group object
     */
    public static Group from(GroupCreate groupCreate) {
        Group group = new Group();
        group.setGroupName(groupCreate.getGroupName());
        return group;
    }


    /**
     * Debugging method
     *
     * @return string with method variables
     */
    @Override
    public String toString() {
        return "Group{" +
                "grId=" + grId +
                ", group_name='" + groupName + '\'' +
                ", group_creator_id=" + groupCreator +
                '}';
    }
}
