package vava.edo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "relationships")
public class Relationships {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "r_id", nullable = false)
    private Integer rId;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "first_user_id", nullable = false)
    private User firstUserId;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "second_user_id", nullable = false)
    private User secondUserId;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Friendship status;
    @Column(name = "since")
    private Date since;


    /**
     * Debugging method
     * @return  string with method variables
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
