package vava.edo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private User userId;
    @Column(name = "fb_message", nullable = false)
    private String feedbackMessage;


    /**
     * Debugging method
     *
     * @return string with method variables
     */
    @Override
    public String toString() {
        return "Feedback{" +
                "feedbackId=" + feedbackId +
                ", userId=" + userId +
                ", feedbackMessage='" + feedbackMessage + '\'' +
                '}';
    }
}
