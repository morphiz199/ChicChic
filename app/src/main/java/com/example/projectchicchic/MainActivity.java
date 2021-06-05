package com.example.projectchicchic;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    ArrayList<LatLng>arrayList=new ArrayList<LatLng>();
    LatLng Madamnailsandspa = new LatLng(13.777113277643103, 100.73928376740636);
    LatLng NailishStudio = new LatLng(13.75146385164286, 100.73949616244529);
    LatLng MadamNailSpa = new LatLng(13.754352131260212, 100.72045140728824);
    LatLng CandyNailArt = new LatLng(13.762054035986324, 100.72215056759964);
    LatLng STARNailSpa = new LatLng(13.762397865107141, 100.72915960388421);
    LatLng FernNails = new LatLng(13.764598359513002, 100.72455771137416);
    LatLng NUNUINAIL = new LatLng(13.767073890973412, 100.72540729152986);
    LatLng AlexNiceNail = new LatLng(13.768380410901358, 100.73213313442918);
    LatLng KingsNailDesigns = new LatLng(13.774844138920535, 100.73397389143321);
    LatLng summerynailstudio = new LatLng(13.7299375368563, 100.76739918007975);

    ArrayList<String>title=new ArrayList<String>();

    Spinner Location_search;
    Button search_click;
    Button jel_nearby, paint_nearby, nail_nearby;
    LatLng latLng;

    double currentLat = 0,currentLong = 0;

    boolean isPermissionGranted;
    GoogleMap mGoogleMap;
    FloatingActionButton fab;
    private FusedLocationProviderClient mLocationClient;
    private int GPS_REQUEST_CODE = 9001;
    FusedLocationProviderClient fusedLocationProviderClient;
    SupportMapFragment supportMapFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        fab = findViewById(R.id.fab);
        Location_search = findViewById(R.id.Location_search);
        search_click = findViewById(R.id.search_click);
        jel_nearby = findViewById(R.id.jel_nearby);
        paint_nearby = findViewById(R.id.paint_nearby);
        nail_nearby = findViewById(R.id.nail_nearby);
        checkMyPermission();
        initMap();

        arrayList.add(Madamnailsandspa);
        arrayList.add(NailishStudio);
        arrayList.add(MadamNailSpa);
        arrayList.add(CandyNailArt);
        arrayList.add(STARNailSpa);
        arrayList.add(FernNails);
        arrayList.add(NUNUINAIL);
        arrayList.add(AlexNiceNail);
        arrayList.add(KingsNailDesigns);
        arrayList.add(summerynailstudio);

        title.add("Madam nail sand spa");
        title.add("Nailish Studio");
        title.add("Madam Nail Spa");
        title.add("Candy NailArt");
        title.add("STAR Nail Spa");
        title.add("Fern Nails");
        title.add("NUNUINAIL");
        title.add("Alex Nice Nail");
        title.add("King's Nail Designs");
        title.add("summery nail studio");


        String[] placeTypeList = {"salon", "nail salon", "paint nail"};
        String[] placeNameList = {"salon", "nail salon", "paint nail"};

        Location_search.setAdapter(new ArrayAdapter<>(MainActivity.this
                , android.R.layout.simple_spinner_dropdown_item, placeNameList));
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(MainActivity.this
                , Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
            getCurrentLocation();
        }else {
            ActivityCompat.requestPermissions(MainActivity.this
                    ,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
        }
        search_click.setOnClickListener(v -> {
            int i = Location_search.getSelectedItemPosition();

            String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json​" + "?location=" + currentLat + "," + currentLong
                    + "&radius=5000" + "&type="
                    + placeTypeList[i] + "&sensor=true" + "&key="
                    + getResources().getString(R.string.google_maps_key);


            new PlaceTask().execute(url);

        });


        mLocationClient = new FusedLocationProviderClient(this);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCurrLoc();
            }
        });


    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null)
                {
                    currentLat = location.getLatitude();
                    currentLong = location.getLongitude();
                }
                supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(@NonNull GoogleMap googleMap) {
                        mGoogleMap = googleMap;
                        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                new LatLng(currentLat,currentLong),10
                        ));
                    }
                });

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 44) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            }
        }
    }

    public void findJel(View v){
//        StringBuilder stringBuilder = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
//        stringBuilder.append("location="+latLng.latitude+","+latLng.longitude);
//        stringBuilder.append("&radius="+1000);
//        stringBuilder.append("&keyword="+"salon");
//        stringBuilder.append("&key="+getResources().getString(R.string.google_api_key));
//
//        String url = stringBuilder.toString();
//        Object dataTransfer[] = new Object[2];
//        dataTransfer[0] = mGoogleMap;
//        dataTransfer[1]= url;
//
//        GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData(this);
//        getNearbyPlacesData.execute(dataTransfer);



    }

    private void initMap() {
        if (isPermissionGranted){
            if (isGPSenable()){
                supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
                supportMapFragment.getMapAsync(this);

            }
            }


    }

    private boolean isGPSenable(){
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        boolean providerEnable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (providerEnable){
            return true;
        }else {
            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setTitle("GPS Permission")
                    .setMessage("GPS is required for this app to work. Please enable GPS")
                    .setPositiveButton("Yes",((dialog, which) -> {
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivityForResult(intent, GPS_REQUEST_CODE);
                    }))
                    .setCancelable(false)
                    .show();
        }
        return false;
    }
    @SuppressLint("MissingPermission")
    private void getCurrLoc() {
        mLocationClient.getLastLocation().addOnCompleteListener(task -> {

            if (task.isSuccessful()){
                Location location = task.getResult();
                gotoLocation(location.getLatitude(), location.getLongitude());
             }
        });

    }

    private void gotoLocation(double latitude, double longitude) {
        latLng = new LatLng(latitude,longitude);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng,18);
        mGoogleMap.moveCamera(cameraUpdate);
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

    }


    private void checkMyPermission(){
        Dexter.withContext(this).withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        Toast.makeText(MainActivity.this,"Permission Granted", Toast.LENGTH_SHORT).show();
                        isPermissionGranted = true;
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        Intent intent = new Intent();
                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(),"");
                        intent.setData(uri);
                        startActivity(intent);

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();

                    }
                }).check();
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mGoogleMap = googleMap;

        for (int i=0;i<arrayList.size();i++){

            for (int j=0;j<title.size();j++){
                mGoogleMap.addMarker(new MarkerOptions().position(arrayList.get(i)).title(String.valueOf(title.get(i))));

            }
            mGoogleMap.setMyLocationEnabled(true);
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(arrayList.get(i)));
        }






    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(@NonNull int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GPS_REQUEST_CODE){
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

            boolean providerEnable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            if (providerEnable){
                Toast.makeText(this,"GPS is enable",Toast.LENGTH_SHORT).show();

            }else {
                Toast.makeText(this,"GPS is not enable",Toast.LENGTH_SHORT).show();

            }
        }

    }

    private class PlaceTask extends AsyncTask<String,Integer,String> {
        String data = null;

        @Override
        protected String doInBackground(String... strings) {
            try {
                data = downloadUrl(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }

        @Override
        protected void onPostExecute(String s) {
            new ParserTask().execute(s);
        }
    }

    private String downloadUrl(String string) throws IOException {
        URL url = new URL(string);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();

        InputStream stream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        StringBuilder builder = new StringBuilder();
        String line ="";

        while ((line = reader.readLine())!=null){
            builder.append(line);
        }
        String data = builder.toString();

        reader.close();
        return data;
    }

    private class ParserTask extends AsyncTask<String,Integer, List<HashMap<String,String>>>{
        @Override
        protected List<HashMap<String, String>> doInBackground(String... strings) {
            JsonParaser jsonParaser = new JsonParaser();
            List<HashMap<String,String>> mapList = null;
            JSONObject object = null;

            try {
                object = new JSONObject(strings[0]);
                mapList = jsonParaser.parseResult(object);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<HashMap<String, String>> hashMaps) {
            mGoogleMap.clear();
            for (int i=0;i<hashMaps.size();i++){
                HashMap<String,String> hashMapList = hashMaps.get(i);

                double lat = Double.parseDouble(hashMapList.get("lat"));
                double lng = Double.parseDouble(hashMapList.get("lng"));

                String name = hashMapList.get("name");

                LatLng latLng = new LatLng(lat,lng);

                MarkerOptions options = new MarkerOptions();

                options.position(latLng);
                options.title(name);

                mGoogleMap.addMarker(options);
            }
        }
    }
}