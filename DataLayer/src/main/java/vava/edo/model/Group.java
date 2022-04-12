package vava.edo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vava.edo.schema.GroupCreate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Class representing user in groups table
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
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_creator_id", nullable = false)
    private User groupCreatorId;


    public static Group from(GroupCreate groupCreate) {
        Group group = new Group();
        group.setGroupName(groupCreate.getGroupName());
        return group;
    }


    /**
     * Debugging method
     * @return  string with method variables
     */
    @Override
    public String toString() {
        return "Group{" +
                "grId=" + grId +
                ", group_name='" + groupName + '\'' +
                ", group_creator_id=" + groupCreatorId +
                '}';
    }

}











