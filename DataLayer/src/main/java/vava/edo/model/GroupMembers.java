package vava.edo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Class representing group member in group_members table
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "group_members")
public class GroupMembers {
    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_Id", nullable = false)
    private Group groupId;
    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id", nullable = false)
    private User memberId;


    /**
     * Debugging method
     * @return string with method variables
     */
    @Override
    public String toString() {
        return "GroupMembers{" +
                "group_id=" + groupId +
                ", member_id=" + memberId +
                '}';
    }
}
