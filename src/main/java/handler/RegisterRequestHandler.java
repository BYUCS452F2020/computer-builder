package handler;

import com.google.gson.GsonBuilder;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.DataAccessException;
import models.User;
import models.request.RegisterRequest;
import models.result.RegisterResult;
import service.UserServices;
import com.google.gson.Gson;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.nio.file.Files;

public class RegisterRequestHandler implements HttpHandler {

    public void handle(HttpExchange httpE) throws IOException
    {
        try {
            System.out.println(httpE.getRequestMethod());
            if (httpE.getRequestMethod().toUpperCase().equals("OPTIONS")) {
                httpE.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                httpE.getResponseHeaders().add("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
                httpE.getResponseHeaders().add("Access-Control-Allow-Headers", "*");

                System.out.println("about to send headers for options");
                httpE.sendResponseHeaders(200,0);
                System.out.println("headers sent");
            }
            else if (httpE.getRequestMethod().toUpperCase().equals("POST")) {
                System.out.println("in the if");
                //httpE.getResponseHeaders().add("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
                //httpE.getResponseHeaders().add("Access-Control-Allow-Headers", "*");
                //httpE.getResponseHeaders().add("Access-Control-Allow-Credentials", "true");
                //httpE.getResponseHeaders().add("Access-Control-Allow-Credentials-Header", "*");

                InputStream reqBody = httpE.getRequestBody();
                InputStreamReader isr = new InputStreamReader(reqBody);
                BufferedReader br = new BufferedReader(isr);

                Gson gson = new Gson();


                RegisterRequest regReq = gson.fromJson(br, RegisterRequest.class);
                System.out.println("user in the handler is " + regReq.getUsername());
                //TODO handle passwords with registration properly (when to hash and when to leave as string)
                //RegisterRequest regReq = new RegisterRequest(user.getUsername(),"password",user.getEmail(),
                //        user.getFirstName(),user.getLastName());
                reqBody.close();

                UserServices uServ = new UserServices();
                try {
                    System.out.println("Trying to insert new user haha");
                    RegisterResult regRes = uServ.register(regReq);
                    OutputStream respBody = httpE.getResponseBody();
                    Gson ogson = new GsonBuilder().setPrettyPrinting().create();
                    String output = ogson.toJson(regRes);
                    OutputStreamWriter sw = new OutputStreamWriter(respBody);
                    BufferedWriter bw = new BufferedWriter(sw);
                    httpE.sendResponseHeaders(200,0);
                    System.out.println(output);
                    bw.write(output);
                    bw.flush();
                    //respBody.close();
                    System.out.println("got to the end");
                    httpE.getResponseBody().close();
                } catch (DataAccessException e) {
                    System.out.println("error: " + e.toString());
                    httpE.getResponseBody().close();
                    return;
                }


            } else {
                System.out.println("bad req");
                httpE.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            }


        } catch (IOException e) {
            System.out.println("error: " + e.getMessage() + e.getStackTrace()[2] + ": " + e.getStackTrace()[2].getLineNumber());
            httpE.sendResponseHeaders(500, 0);
            httpE.getResponseBody().close();

            e.printStackTrace();
        }
    }
}