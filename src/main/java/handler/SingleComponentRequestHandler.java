package handler;

import com.google.gson.GsonBuilder;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.google.gson.Gson;
import dao.DataAccessException;
import models.request.ComponentRequest;
import models.request.GetSingleComponentRequest;
import models.result.ComponentResult;
import models.result.GetSingleComponentResult;
import service.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.util.List;

public class SingleComponentRequestHandler implements HttpHandler {
    public void handle(HttpExchange httpE) throws IOException
    {
        try {
            if (httpE.getRequestMethod().toUpperCase().equals("OPTIONS")) {
                httpE.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                httpE.getResponseHeaders().add("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
                httpE.getResponseHeaders().add("Access-Control-Allow-Headers", "*");

                System.out.println("about to send headers for options");
                httpE.sendResponseHeaders(200,0);
                System.out.println("headers sent");
            }
            else if (httpE.getRequestMethod().toUpperCase().equals("GET")) {
                System.out.println("getting components");
                Headers reqHeaders = httpE.getRequestHeaders();
                String URI = httpE.getRequestURI().toString();

                String[] URIList = httpE.getRequestURI().toString().split("/");
                for(int i =0; i < URIList.length; i++) {
                    System.out.println(i + ": " +URIList[i]);
                }
                /*InputStream reqBody = httpE.getRequestBody();
                InputStreamReader isr = new InputStreamReader(reqBody);
                BufferedReader br = new BufferedReader(isr);
                String s = null;


                Gson gson = new Gson();*/
                GetSingleComponentRequest compReq = new GetSingleComponentRequest(URIList[2]);

                System.out.println("componentID: " + compReq.getComponentId());
                //reqBody.close();

                ComponentServices cServ = new ComponentServices();
                //try {
                GetSingleComponentResult compRes = cServ.getComponent(compReq);
                OutputStream respBody = httpE.getResponseBody();
                Gson ogson = new GsonBuilder().setPrettyPrinting().create();
                String output = ogson.toJson(compRes);
                OutputStreamWriter sw = new OutputStreamWriter(respBody);
                BufferedWriter bw = new BufferedWriter(sw);
                httpE.sendResponseHeaders(200,0);
                System.out.println(output);
                bw.write(output);
                bw.flush();
                    //respBody.close();
                httpE.getResponseBody().close();
                /*} catch (DataAccessException e) {
                    System.out.println(e.getMessage());
                    httpE.getResponseBody().close();
                }*/

            } else {
                httpE.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            }
        } catch (IOException e) {
            httpE.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            httpE.getResponseBody().close();

            e.printStackTrace();
        }
    }
}