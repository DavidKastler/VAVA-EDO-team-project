package vava.edo.Handlers;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import vava.edo.Exepctions.HttpStatusExceptions.UnexpectedHttpStatusException;
import vava.edo.models.Report;

import java.util.ArrayList;

public class ReportHandler {

    //DONE
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

    public static void acceptReport(Integer userId, Integer reportId) {

        try {
            HttpResponse<JsonNode> acceptReportJson = Unirest.put("http://localhost:8080/reports/accept/{report_id}?token={token}")
                    .header("Content-type", "application/json")
                    .routeParam("token", String.valueOf(userId))
                    .routeParam("report_id", String.valueOf(reportId))
                    .asJson();

            if (acceptReportJson.getStatus() != 200) throw new UnexpectedHttpStatusException(acceptReportJson.getStatus(), 200, acceptReportJson.getStatusText());

        } catch (UnirestException e) {
            System.out.println(e.getMessage());
        } catch (UnexpectedHttpStatusException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void rejectReport(Integer userId, Integer reportId) {
        try {
            HttpResponse<JsonNode> rejectReportJson = Unirest.put("http://localhost:8080/reports/reject/{report_id}?token={token}")
                    .header("Content-type", "application/json")
                    .routeParam("token", String.valueOf(userId))
                    .routeParam("report_id", String.valueOf(reportId))
                    .asJson();

            if (rejectReportJson.getStatus() != 200) throw new UnexpectedHttpStatusException(rejectReportJson.getStatus(), 200, rejectReportJson.getStatusText());

        } catch (UnirestException e) {
            System.out.println(e.getMessage());
        } catch (UnexpectedHttpStatusException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<Report> getAllReports(Integer userId) {
        ArrayList<Report> reports = new ArrayList<>();

        try {
            HttpResponse<JsonNode> reportsJson = Unirest.get("http://localhost:8080/reports/all?token={token}")
                    .routeParam("token", String.valueOf(userId))
                    .asJson();
            for (Object group : reportsJson.getBody().getArray()) {
                reports.add(new Gson().fromJson(group.toString(), Report.class));
            }

            if (reportsJson.getStatus() != 200) throw new UnexpectedHttpStatusException(reportsJson.getStatus(), 200, reportsJson.getStatusText());

        } catch (UnirestException e) {
            System.out.println("Connection to localhost:8080 failed ! (PLease start backend server)");
        } catch (UnexpectedHttpStatusException e) {
            System.out.println(e.getMessage());
        }

        return reports;
    }

    public static ArrayList<Report> getPendingReports(Integer userId) {
        ArrayList<Report> reports = new ArrayList<>();

        try {
            HttpResponse<JsonNode> reportsJson = Unirest.get("http://localhost:8080/reports/pending?token={token}")
                    .routeParam("token", String.valueOf(userId))
                    .asJson();
            for (Object group : reportsJson.getBody().getArray()) {
                reports.add(new Gson().fromJson(group.toString(), Report.class));
            }

            if (reportsJson.getStatus() != 200) throw new UnexpectedHttpStatusException(reportsJson.getStatus(), 200, reportsJson.getStatusText());

        } catch (UnirestException e) {
            System.out.println("Connection to localhost:8080 failed ! (PLease start backend server)");
        } catch (UnexpectedHttpStatusException e) {
            System.out.println(e.getMessage());
        }

        return reports;
    }
}
