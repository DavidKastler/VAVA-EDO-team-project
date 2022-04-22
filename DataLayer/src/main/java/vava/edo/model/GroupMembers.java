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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "gm_id")
    private Integer gmId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_Id", nullable = false)
    private Group group;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id", nullable = false)
    private User member;


    /**
     * Debugging method
     * @return string with method variables
     */
    @Override
    public String toString() {
        return "GroupMembers{" +
                "group_id=" + group +
                ", member_id=" + member +
                '}';
    }
}
