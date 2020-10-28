package handler;

import com.google.gson.GsonBuilder;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.google.gson.Gson;
import dao.DataAccessException;
import models.request.ComponentRequest;
import models.request.LoginRequest;
import models.request.UserBuildsRequest;
import models.result.ComponentResult;
import models.result.LoginResult;
import models.result.UserBuildsResult;
import service.UserServices;
import services.BuildService;

import java.io.*;
import java.net.HttpURLConnection;

public class UserBuildsRequestHandler implements HttpHandler {
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
            if (httpE.getRequestMethod().toUpperCase().equals("GET")) {
                System.out.println("getting user builds");
                Headers reqHeaders = httpE.getRequestHeaders();

                InputStream reqBody = httpE.getRequestBody();
                InputStreamReader isr = new InputStreamReader(reqBody);
                BufferedReader br = new BufferedReader(isr);
                //System.out.println("br " + br.);

                Gson gson = new Gson();
                UserBuildsRequest buildsReq = gson.fromJson(br, UserBuildsRequest.class);
                reqBody.close();
                //TODO implement build services

                BuildService bServ = new BuildService();
//                try {
                    UserBuildsResult compRes = bServ.getUserBuilds(buildsReq);
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
//                } catch (Exception e) {
//                    httpE.getResponseBody().close();
//                }

                System.out.println("done getting cpus");
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