package com.uade.pfi.androidapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.uade.pfi.androidapp.R;


public class TransportMeMainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button startButton = (Button) findViewById(R.id.startButton);
		startButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				TransportMeMainActivity.this.startService(new Intent("TRANSPORT_ME_SERVICE"));
			}
		});
		Button stopButton = (Button) findViewById(R.id.stopButton);
		stopButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				TransportMeMainActivity.this.stopService(new Intent("TRANSPORT_ME_SERVICE"));
			}
		});
		
		Button mapButton = (Button) findViewById(R.id.mapButton);
		mapButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				TransportMeMainActivity.this.startActivity(new Intent(getBaseContext(),TransportMapActivity.class));
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
