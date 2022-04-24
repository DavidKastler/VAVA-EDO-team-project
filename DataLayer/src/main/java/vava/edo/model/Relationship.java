package vava.edo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import vava.edo.model.enums.RelationshipStatus;

import javax.persistence.*;

/**
 * Class representing relationships between users in relationships table
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "relationships")
public class Relationship {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rel_id")
    private Integer relationshipId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "first_user_id", nullable = false)
    private User firstUser;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "second_user_id", nullable = false)
    private User secondUser;
    @Enumerated(EnumType.STRING)
    @Type(type = "vava.edo.model.enums.EnumTypePostgreSql")
    @Column(name = "status", nullable = false)
    private RelationshipStatus status = RelationshipStatus.pending;
    @Column(name = "since")
    private Long since;


    public Relationship(User firstUserId, User secondUserId) {
        this.firstUser = firstUserId;
        this.secondUser = secondUserId;
        this.since = System.currentTimeMillis() / 1000L;
    }

    /*
     * Static casting method from RelationshipCreate object
     * @param newRequest RelationshipCreate object that you want to cast
     * @return cast Relationships object
     */
//    public static Relationship from(RelationshipCreate newRequest) {
//        Relationship request = new Relationship();
//        request.setFirstUser(newRequest.getSenderId());
//        request.setSecondUser(newRequest.getReceiverId());
//        request.setSince(System.currentTimeMillis() / 1000L);
//        return request;
//    }

    /**
     * Debugging method
     * @return string with method variables
     */
    @Override
    public String toString() {
        return "Relationships{" +
                ", firstUserId=" + firstUser +
                ", secondUserId=" + secondUser +
                ", status=" + status +
                ", since=" + since +
                '}';
    }
}
