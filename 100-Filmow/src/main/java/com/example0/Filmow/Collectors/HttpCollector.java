package com.example0.Filmow.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HttpCollector {
    URL url;

    private StringBuilder response = new StringBuilder();
    public void Collect() throws IOException {
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("x-locale", "pl-PL");
        int responseCode = con.getResponseCode();
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        }
    }
    public void writeToConsole() {
        System.out.println(this.response.toString());
    }

    public HttpCollector(URL url) {
        this.url = url;
    }

    public StringBuilder getResponse() {
        return response;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public void setResponse(StringBuilder response) {
        this.response = response;
    }
}
