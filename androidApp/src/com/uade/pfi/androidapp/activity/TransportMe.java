package com.uade.pfi.androidapp.activity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.uade.pfi.androidapp.R;


public class TransportMe extends Activity {
	private String BASE_URL = "http://186.136.91.211:8080";
	//private String BASE_URL = "http://chiwi.homelinux.com:8080";
	Context context = this;
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button startButton = (Button) findViewById(R.id.startButton);
		startButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				TransportMe.this.startService(new Intent("TRANSPORT_ME_SERVICE"));
			}
		});
		Button stopButton = (Button) findViewById(R.id.stopButton);
		stopButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				TransportMe.this.stopService(new Intent("TRANSPORT_ME_SERVICE"));
			}
		});
		
		Button mapButton = (Button) findViewById(R.id.mapButton);
		mapButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				Intent i = new Intent(getApplicationContext(), ShowMapView.class);
				startActivity(i);
				
			}
		});
		
/*		Button getButton = (Button) findViewById(R.id.getButton);
		getButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
				acceptableMediaTypes.add(MediaType.APPLICATION_JSON);

				HttpHeaders requestHeaders = new HttpHeaders();
				requestHeaders.setAccept(acceptableMediaTypes);

				HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
			    String url = BASE_URL + "/web/location/get.json";
			    RestTemplate restTemplate =  new RestTemplate();
			    ResponseEntity<Integer> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Integer.class);
			    Integer result = responseEntity.getBody();
			    Toast.makeText(getApplicationContext(),result+"", Toast.LENGTH_SHORT).show();
			}
		});*/
	}

}
