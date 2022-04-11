package vava.edo.models;

import java.io.Serializable;

public class Role implements Serializable {

    private int rId;
    private String roleName;
    private boolean basicRights;
    private boolean todoAccessRights;
    private boolean teamLeaderRights;
    private boolean adminRights;

    @Override
    public String toString() {
        return "Role{" +
                "rId=" + rId +
                ", roleName='" + roleName + '\'' +
                ", basicRights=" + basicRights +
                ", todoAccessRights=" + todoAccessRights +
                ", teamLeaderRights=" + teamLeaderRights +
                ", adminRights=" + adminRights +
                '}';
    }
}
