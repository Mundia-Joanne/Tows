package com.example.tows;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class DownloadUrl {
    public String readUrl(String myurl) throws IOException {
        String data = "";
        InputStream inputStream = null;
        HttpsURLConnection httpsURLConnection = null;

        try {
            URL url = new URL(myurl);
            httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.connect();

            inputStream = httpsURLConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer ab = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) !=null){
                ab.append(line);
            }
            data = ab.toString();
            br.close();
        }

        catch (MalformedURLException e){
            Log.i( "DownloadUrl", "readUrl: " +e.getMessage());

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(inputStream != null)
                inputStream.close();
            if(httpsURLConnection != null)
                httpsURLConnection.disconnect();
        }
        return data;

    }
}


