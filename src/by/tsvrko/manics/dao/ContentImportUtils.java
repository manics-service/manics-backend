package by.tsvrko.manics.dao;

import by.tsvrko.manics.exceptions.VKApiException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by irats on 1/4/2017.
 */
public abstract class ContentImportUtils {


    public static String readContent(URIBuilder uriBuilder){
        HttpResponse response = ContentImportUtils.connectResponse(uriBuilder);
        Integer status = response.getStatusLine().getStatusCode();

        String text = "";

        if (status == 200) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                text = reader.readLine();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            throw new VKApiException();
        }
        return text;
    }
    private static HttpResponse connectResponse(URIBuilder uriBuilder) {

        URI uri = null;

        try {
            uri = uriBuilder.build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(uri);
        HttpResponse response = null;

        try {
            response = client.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    public static JSONArray parseToJSON(String text) {
        JSONArray jsonArray = null;
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonResp = (JSONObject) parser.parse(text);
            jsonArray = (JSONArray) jsonResp.get("response");

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonArray;

    }

}

