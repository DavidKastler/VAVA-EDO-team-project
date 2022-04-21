package vava.edo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

/**
 * Class representing assignment in assignments table
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "assignments")
public class Assignments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ass_id", nullable = false)
    private Integer assignmentsId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "ass_start", nullable = false)
    private Date assignmentsStart;
    @Column(name = "ass_end", nullable = false)
    private Date assignmentsEnd;
    @Column(name = "completed", nullable = false)
    private Boolean completed;


    /**
     * Debugging method
     *
     * @return string with method variables
     */
    @Override
    public String toString() {
        return "Assignments{" +
                "assignmentsId=" + assignmentsId +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", assignmentsStart=" + assignmentsStart +
                ", assignmentsEnd=" + assignmentsEnd +
                ", completed=" + completed +
                '}';
    }
}
