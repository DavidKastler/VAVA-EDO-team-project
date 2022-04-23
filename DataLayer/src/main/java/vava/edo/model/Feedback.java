package vava.edo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import vava.edo.model.enums.ReadStatus;

import javax.persistence.*;

/**
 * Class representing one feedback in feedback table
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "fb_id", nullable = false)
    private Integer feedbackId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "u_id", nullable = false)
    private User user;
    @Column(name = "fb_message", nullable = false)
    private String feedbackMessage;
    @Enumerated(EnumType.STRING)
    @Type(type = "vava.edo.model.enums.EnumTypePostgreSql")
    @Column(name = "read", nullable = false)
    private ReadStatus status = ReadStatus.not_seen;

    public Feedback(User user, String feedbackMessage) {
        this.user = user;
        this.feedbackMessage = feedbackMessage;
    }

    /**
     * Debugging method
     *
     * @return string with method variables
     */
    @Override
    public String toString() {
        return "Feedback{" +
                "feedbackId=" + feedbackId +
                ", userId=" + user +
                ", feedbackMessage='" + feedbackMessage + '\'' +
                '}';
    }
}
