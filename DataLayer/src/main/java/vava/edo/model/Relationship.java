package vava.edo.model;

import lombok.*;
import vava.edo.schema.RelationshipEditStatus;

import javax.persistence.*;
import java.sql.Date;

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
    @Column(name = "r_id", nullable = false)
    private Integer rId;
    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "first_user_id", nullable = false)
    private User firstUserId;
    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "second_user_id", nullable = false)
    private User secondUserId;

    @Column(name = "status")
    private String status;
    @Column(name = "since")
    private Date since;


    public Relationship(@NonNull User firstUserId, @NonNull User secondUserId, String status) {
        this.firstUserId = firstUserId;
        this.secondUserId = secondUserId;
        this.status = status;
        this.since = new java.sql.Date(System.currentTimeMillis());
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
        request.setSince(new java.sql.Date(System.currentTimeMillis()));
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
                "rId=" + rId +
                ", firstUserId=" + firstUserId +
                ", secondUserId=" + secondUserId +
                ", status=" + status +
                ", since=" + since +
                '}';
    }
}
