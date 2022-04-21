package vava.edo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class representing one to-do group in todo_group table
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "todo_group")
public class TodoGroup {
    @Id
    @Column(name = "td_g_id")
    private Integer todoGroupId;
    @Column(name = "u_id")
    private Integer uId;
    @Column(name = "td_group_name")
    private String tdGroupName;
}
