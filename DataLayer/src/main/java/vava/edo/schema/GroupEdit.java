package vava.edo.schema;

import lombok.Data;

/**
 * Data transfer object for Group class
 * Used in editGroupName
 */
@Data
public class GroupEdit {
    private String groupName;


    /**
     * Debugging method
     *
     * @return string with method variables
     */
    @Override
    public String toString() {
        return "GroupEdit{" +
                "groupName='" + groupName + '\'' +
                '}';
    }
}
