package vava.edo.schema.relationships;


import com.sun.istack.NotNull;
import lombok.Data;

/**
 * Class used to update relations in re
 */
@Data
public class RelationshipCreate {
    @NotNull
    private Integer senderId;
    @NotNull
    private String receiverName;
}
