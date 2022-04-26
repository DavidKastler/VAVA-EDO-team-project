package vava.edo.models;

import java.io.Serializable;

public class Role implements Serializable {

    private Integer rid;
    private String roleName;
    private boolean todoAccessRights;
    private boolean teamLeaderRights;
    private boolean managerRights;
    private boolean adminRights;


    public int getrId() {
        return this.rid;
    }

    public void setrId(int rId) {
        this.rid = rId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public boolean isManagerRights() {
        return managerRights;
    }

    public void setManagerRights(boolean managerRights) {
        this.managerRights = managerRights;
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


    @Override
    public String toString() {
        return "Role{" +
                "rId=" + rid +
                ", roleName='" + roleName + '\'' +
                ", basicRights=" + managerRights +
                ", todoAccessRights=" + todoAccessRights +
                ", teamLeaderRights=" + teamLeaderRights +
                ", adminRights=" + adminRights +
                '}';
    }
}
