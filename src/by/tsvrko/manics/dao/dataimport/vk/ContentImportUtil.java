package by.tsvrko.manics.dao.dataimport.vk;

import by.tsvrko.manics.dao.dataimport.vk.implementations.ChatImportVKImpl;
import by.tsvrko.manics.exceptions.VKApiException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by tsvrko on 1/4/2017.
 */
public final class ContentImportUtil {

    private static Logger log = Logger.getLogger(ChatImportVKImpl.class.getName());

    public static String readContent(URIBuilder uriBuilder) {

        HttpResponse response = ContentImportUtil.connectResponse(uriBuilder);
        Integer status = response.getStatusLine().getStatusCode();

        String text = "";

        if (status == 200) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                text = reader.readLine();
            } catch (IOException e) {
                log.debug("ioexception in reading json");
            }
        } else {
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
            log.debug("request on this uri can't be executed");
        }

        return response;
    }

}

