/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lanag
 */
public class json {

    public String jsonResponse(String action, boolean logged, String message, String jsontxt) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"action\": \"").append(action).append("\",")
                .append("\"logged\": \"").append(logged).append("\",")
                .append("\"message\": \"").append(message).append("\"")
                .append(jsontxt).append("}");

        return sb.toString();

    }
}
