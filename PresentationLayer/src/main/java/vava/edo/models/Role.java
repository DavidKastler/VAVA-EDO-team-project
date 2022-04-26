package vava.edo.models;

import java.io.Serializable;

public class Role implements Serializable {

    private int rId;
    private String roleName;
    private boolean basicRights;
    private boolean todoAccessRights;
    private boolean teamLeaderRights;
    private boolean adminRights;


    public int getrId() {
        return rId;
    }

    public void setrId(int rId) {
        this.rId = rId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public boolean isBasicRights() {
        return basicRights;
    }

    public void setBasicRights(boolean basicRights) {
        this.basicRights = basicRights;
    }

    public boolean isTodoAccessRights() {
        return todoAccessRights;
    }

    public void setTodoAccessRights(boolean todoAccessRights) {
        this.todoAccessRights = todoAccessRights;
    }

    public boolean isTeamLeaderRights() {
        return teamLeaderRights;
    }

    public void setTeamLeaderRights(boolean teamLeaderRights) {
        this.teamLeaderRights = teamLeaderRights;
    }

    public boolean isAdminRights() {
        return adminRights;
    }

    public void setAdminRights(boolean adminRights) {
        this.adminRights = adminRights;
    }

    public void normalizeRoleName() {
        if (this.getRoleName().equals("account_manager")) this.roleName = "Account manager";
        if (this.getRoleName().equals("team_leader")) this.roleName = "Team leader";
    }


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
