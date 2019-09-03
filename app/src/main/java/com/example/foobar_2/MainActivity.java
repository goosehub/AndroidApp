package com.example.foobar_2;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import javax.net.ssl.HttpsURLConnection;

import android.os.Bundle;
import android.view.View;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private Button saveSpotButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saveSpotButton = findViewById(R.id.saveSpotButton);
        saveSpotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSpot();
            }
        });
    }

    public boolean saveSpot() {
        Log.d("debug", "save spot");

//        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
//        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//        double longitude = location.getLongitude();
//        double latitude = location.getLatitude();

        String data = "{\"lat\":0.852792909818,\"lng\":-0.78900363502305,\"world_id\":1,\"room_name\":\"android\",\"world_key\":\"1\"}: ";

        try {
            Log.d("debug", "start try");
//            URL url = new URL("https://bigworld.io/room/create");
            URL url = new URL("https://www.google.com/");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            Log.d("debug", "before out");
            Log.d("debug", urlConnection.getOutputStream().toString());
            OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
            Log.d("debug", "your out of here");

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
            writer.write(data);
            writer.flush();
            writer.close();
            out.close();

            Log.d("debug", "before connect");
            urlConnection.connect();
        } catch (Exception e) {
            Log.d("debug", "save spot failed");
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }
}
