package com.example.projectchicchic.NearbyPlaces;

import android.os.AsyncTask;

import com.example.projectchicchic.MainActivity;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetNearbyPlacesData extends AsyncTask<Object, String, String> {

    String googlePlacesData;
    GoogleMap mMap;
    String url;
    InputStream is;
    BufferedReader bufferedReader;
    StringBuilder stringBuilder;
    String data;

    public GetNearbyPlacesData(MainActivity mainActivity) {
    }

    @Override
    protected String doInBackground(Object... params) {
        mMap = (GoogleMap) params[0];
        url = (String) params[1];
        try {
            URL myurl = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)myurl.openConnection();
            httpURLConnection.connect();
            is = httpURLConnection.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(is));

            String line ="";
            stringBuilder = new StringBuilder();

            while ((line = bufferedReader.readLine())!=null){
                stringBuilder.append(line);


            }
            data = stringBuilder.toString();


//            DownloadUrl downloadUrl = new DownloadUrl();
//            googlePlacesData = downloadUrl.readUrl(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    protected void onPostExecute(String result) {

        try {
            JSONObject parentObject = new JSONObject(result);
            JSONArray resultsArray = parentObject.getJSONArray("result");
            for (int i = 0; i <resultsArray.length(); i++){
                JSONObject jsonObject = resultsArray.getJSONObject(i);
                JSONObject locationObj = jsonObject.getJSONObject("geometry").getJSONObject("location");

                String latitude = locationObj.getString("lat");
                String longitude = locationObj.getString("lng");

                JSONObject nameObject = resultsArray.getJSONObject(i);

                String name_salon = nameObject.getString("name");
                String vicinity = nameObject.getString("vicinity");

                LatLng latLng = new LatLng(Double.parseDouble(latitude),Double.parseDouble(longitude));
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.title(vicinity);
                markerOptions.position(latLng);

                mMap.addMarker(markerOptions);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        List<HashMap<String, String>> nearbyPlacesList = null;
//        DataParser dataParser = new DataParser();
//        nearbyPlacesList =  dataParser.parse(result);
//        ShowNearbyPlaces(nearbyPlacesList);
    }

//    private void ShowNearbyPlaces(List<HashMap<String, String>> nearbyPlacesList) {
//        for (int i = 0; i < nearbyPlacesList.size(); i++) {
//            MarkerOptions markerOptions = new MarkerOptions();
//            HashMap<String, String> googlePlace = nearbyPlacesList.get(i);
//            double lat = Double.parseDouble(googlePlace.get("lat"));
//            double lng = Double.parseDouble(googlePlace.get("lng"));
//            String placeName = googlePlace.get("place_name");
//            String vicinity = googlePlace.get("vicinity");
//            LatLng latLng = new LatLng(lat, lng);
//            markerOptions.position(latLng);
//            markerOptions.title(placeName + " : " + vicinity);
//            mMap.addMarker(markerOptions);
//            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
//            //move map camera
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//            mMap.animateCamera(CameraUpdateFactory.zoomTo(11));
//        }
//    }
}