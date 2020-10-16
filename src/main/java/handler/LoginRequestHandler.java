package handler;

import com.google.gson.GsonBuilder;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.google.gson.Gson;
import dao.DataAccessException;
import models.request.LoginRequest;
import models.result.LoginResult;
import service.UserServices;

import java.io.*;
import java.net.HttpURLConnection;

public class LoginRequestHandler implements HttpHandler {
    public void handle(HttpExchange httpE) throws IOException
    {
        try {
            if (httpE.getRequestMethod().toUpperCase().equals("POST")) {
                Headers reqHeaders = httpE.getRequestHeaders();

                InputStream reqBody = httpE.getRequestBody();
                System.out.println("reqbody " + reqBody.toString());
                InputStreamReader isr = new InputStreamReader(reqBody);
                System.out.println("isr " + isr.toString());
                BufferedReader br = new BufferedReader(isr);
                //System.out.println("br " + br.);

                Gson gson = new Gson();
                LoginRequest logReq = gson.fromJson(br, LoginRequest.class);
                reqBody.close();
                UserServices uServ = new UserServices();
                try {
                    LoginResult logRes = uServ.login(logReq);
                    OutputStream respBody = httpE.getResponseBody();
                    Gson ogson = new GsonBuilder().setPrettyPrinting().create();
                    String output = ogson.toJson(logRes);
                    OutputStreamWriter sw = new OutputStreamWriter(respBody);
                    BufferedWriter bw = new BufferedWriter(sw);
                    httpE.sendResponseHeaders(200,0);
                    System.out.println(output);
                    bw.write(output);
                    bw.flush();
                    //respBody.close();
                    httpE.getResponseBody().close();
                } catch (DataAccessException e) {
                    httpE.getResponseBody().close();
                }
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