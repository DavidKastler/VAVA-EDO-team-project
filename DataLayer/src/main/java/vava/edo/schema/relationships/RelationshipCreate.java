package vava.edo.schema.relationships;


import com.sun.istack.NotNull;
import lombok.Data;

/**
 * Data transfer object for creating relationship
 * Used to create row in database
 */
@Data
public class RelationshipCreate {
    @NotNull
    private Integer senderId;
    @NotNull
    private String receiverName;
}
