package vava.edo.Handlers;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import vava.edo.Exepctions.MenuScreen.FeedbackCreationError;
import vava.edo.models.Feedback;

public class FeedbackHandler {

    /**
     *
     * @param uId
     * @param feedbackMsg
     */
    public static void sendFeedback(int uId, String feedbackMsg) throws FeedbackCreationError {

        if(feedbackMsg.equals("")){
            throw new FeedbackCreationError("User left the textArea empty");
        }

        try {

            HttpResponse<JsonNode> apiResponse = Unirest.post("http://localhost:8080/feedback/create/?token={token}")
                    .routeParam("token", String.valueOf(uId))
                    .body(feedbackMsg)
                    .asJson();

            Feedback feedback = new Gson().fromJson(apiResponse.getBody().toString(), Feedback.class);

            if(feedback.getFeedbackMessage() == null){
                throw new FeedbackCreationError("Database failed to create a feedback");
            }

        } catch (UnirestException e){
            System.out.println("Connection to localhost:8080 failed ! (PLease start backend server)");
        }
    }

}
