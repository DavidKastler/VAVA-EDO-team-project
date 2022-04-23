package vava.edo.Handlers;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import vava.edo.Exepctions.HttpStatusExceptions.UnexpectedHttpStatusException;

public class ReportHandler {

    public static void createReport(Integer userId, Integer violatorId, String message) {
        JSONObject newReport = new JSONObject();
        newReport.put("reporterId", userId);
        newReport.put("violatorId", violatorId);
        newReport.put("reportMessage", message);

        try {
            HttpResponse<JsonNode> reportJson = Unirest.post("http://localhost:8080/reports/create?token={token}")
                    .header("Content-type", "application/json")
                    .routeParam("token", String.valueOf(userId))
                    .body(newReport)
                    .asJson();

            if (reportJson.getStatus() != 201) throw new UnexpectedHttpStatusException(reportJson.getStatus(), 201, reportJson.getStatusText());

        } catch (UnirestException e) {
            System.out.println(e.getMessage());
        } catch (UnexpectedHttpStatusException e) {
            System.out.println(e.getMessage());
        }
    }
}
