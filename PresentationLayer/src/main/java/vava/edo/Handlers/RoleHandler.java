package vava.edo.Handlers;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import vava.edo.Exepctions.AdminWindow.FailedToAcquireRole;
import vava.edo.models.Role;

public class RoleHandler {

    /**
     * Method which gets the sample role by id and sets the current role to meet the new parameters
     *
     * @param roleId role id of newly acquired role
     * @param role object of current user which is going to be updated
     */
    public static void changeRole(String roleId, Role role) throws FailedToAcquireRole{

        try {
            HttpResponse<JsonNode> apiResponse = Unirest.get("http://localhost:8080/roles/{roleId}")
                    .routeParam("roleId", roleId)
                    .asJson();

            Role returnedRole = new Gson().fromJson(apiResponse.getBody().toString(), Role.class);

            if(returnedRole.getRoleName() != null){

                role.setrId(returnedRole.getrId());
                role.setRoleName(returnedRole.getRoleName());
                role.setBasicRights(returnedRole.isBasicRights());
                role.setTodoAccessRights(returnedRole.isTodoAccessRights());
                role.setTeamLeaderRights(returnedRole.isTeamLeaderRights());
                role.setTeamLeaderRights(returnedRole.isTeamLeaderRights());

            } else {
                throw new FailedToAcquireRole("Failed to acquire sample role from the database");
            }

        } catch (UnirestException e){
            System.out.println("Connection to localhost:8080 failed ! (PLease start backend server)");
        }

    }

}
