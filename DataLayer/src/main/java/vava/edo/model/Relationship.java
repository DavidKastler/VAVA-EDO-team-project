package vava.edo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vava.edo.model.enums.RelationshipStatus;
import vava.edo.schema.RelationshipEditStatus;

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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "first_user_id", nullable = false)
    private User firstUserId;
    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "second_user_id", nullable = false)
    private User secondUserId;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private RelationshipStatus status;
    @Column(name = "since")
    private Long since;


    public Relationship(User firstUserId, User secondUserId, RelationshipStatus status) {
        this.firstUserId = firstUserId;
        this.secondUserId = secondUserId;
        this.status = status;
        this.since = System.currentTimeMillis() / 1000L;
    }


    /**
     * Static casting method from RelationshipCreate object
     *
     * @param newRequest RelationshipCreate object that you want to cast
     * @return cast Relationships object
     */
    public static Relationship from(RelationshipEditStatus newRequest) {
        Relationship request = new Relationship();
        request.setFirstUserId(newRequest.getFirstUserId());
        request.setSecondUserId(newRequest.getSecondUserId());
        request.setStatus(newRequest.getStatus());
        request.setSince(System.currentTimeMillis() / 1000L);
        return request;
    }


    /**
     * Debugging method
     *
     * @return string with method variables
     */
    @Override
    public String toString() {
        return "Relationships{" +
                ", firstUserId=" + firstUserId +
                ", secondUserId=" + secondUserId +
                ", status=" + status +
                ", since=" + since +
                '}';
    }
}
