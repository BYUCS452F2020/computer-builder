package handler;

import com.google.gson.GsonBuilder;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.google.gson.Gson;
import dao.DataAccessException;
import models.request.ComponentRequest;
import models.result.ComponentResult;
import org.apache.commons.io.IOUtils;
import service.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;

public class ComponentsRequestHandler implements HttpHandler {
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

                //InputStream reqBody = httpE.getRequestBody();
                //InputStreamReader isr = new InputStreamReader(reqBody);
                //BufferedReader br = new BufferedReader(isr);
                //String s = IOUtils.toString(reqBody, StandardCharsets.UTF_8);
                //System.out.println(s);



                //Gson gson = new Gson();
                ComponentRequest compReq = new ComponentRequest(URIList[2],URIList[3],Integer.parseInt(URIList[4]),Integer.parseInt(URIList[5]), 0);
                if (compReq.getCpuFamily().equals("null")) {
                    compReq.setCpuFamily(null);
                }
                System.out.println("componenttype: " + compReq.getComponentType());
                //reqBody.close();

                ComponentServices cServ = new ComponentServices();
                //try {
                ComponentResult compRes = cServ.queryComponents(compReq);
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

                System.out.println("done getting " + compReq.getComponentType());
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