/**
 * 
 */
package com.uade.pfi.emulator;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.divbyzero.gpx.GPX;
import net.divbyzero.gpx.Track;
import net.divbyzero.gpx.TrackSegment;
import net.divbyzero.gpx.Waypoint;
import net.divbyzero.gpx.parser.JDOM;
import net.divbyzero.gpx.parser.Parser;
import net.divbyzero.gpx.parser.ParsingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.style.ToStringCreator;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.uade.pfi.api.dto.SessionCheckInDTO;
import com.uade.pfi.api.dto.SessionUpdateDto;
import com.uade.pfi.api.dto.TransportListDTO;

/**
 * @author chiwi
 *
 */
public class DefaultEmulator implements TransportsEmulator{
	private Log logger = LogFactory.getLog(DefaultEmulator.class);
	private GPX gpx;
	private boolean shouldStop = false;
	private long speedRatio = 1L; 
	private String sessionId;
	private RestTemplate rest;
	private String baseUrl="";
	
	public DefaultEmulator(String baseUrl,String gpxFile) throws ParsingException {
		Parser parser = new JDOM();
		this.baseUrl = baseUrl;
		gpx = parser.parse(new File(gpxFile));
		startTransportSession();
	}

	private void startTransportSession() {
		initializeRestTemplate();
		logger.info("buscando la lista de transportes");
		TransportListDTO transportListDTO = rest.getForObject(baseUrl + "/transports/getAll.json", TransportListDTO.class);
		int size = transportListDTO.getList().length;
		Random r = new Random();
		int transportIndex = r.nextInt(size-1);
		String transportId = transportListDTO.getList()[transportIndex].getId();
		logger.info("Se eligió un transporte al azar: " + transportId);
		SessionCheckInDTO checkIn = new SessionCheckInDTO();
		checkIn.setTransportId(transportId);
		logger.info("Checking in...");
		sessionId = rest.postForObject(baseUrl+"/checkIn.json", checkIn, String.class);
		logger.info("done, sessionId:" + sessionId);
	}

	private void initializeRestTemplate() {
		logger.info("inicializando restTemplate");
		rest = new RestTemplate();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(new StringHttpMessageConverter());
		messageConverters.add(new MappingJacksonHttpMessageConverter());
		rest.setMessageConverters(messageConverters);
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(new MediaType("application","json"));
	}

	public void startEmulation() {
		logger.info("starting emulation.");
		ArrayList<Track> tracks = gpx.getTracks();
		for (Track track : tracks) {
			ArrayList<TrackSegment> segments = track.getSegments();
			for (TrackSegment trackSegment : segments) {
				ArrayList<Waypoint> waypoints = trackSegment.getWaypoints();
				Iterator<Waypoint> waypointsIterator = waypoints.iterator();
				while(waypointsIterator.hasNext()){
					if(shouldStop)
						return;
					Waypoint currentWaypoint = waypointsIterator.next();
					postLocation(currentWaypoint);
					if(waypointsIterator.hasNext()){
						Waypoint nextWaypoint = waypointsIterator.next();
						long interval = nextWaypoint.getTime().getTime() - currentWaypoint.getTime().getTime();
						long intervalWithFixedSpeed = interval / speedRatio;
						try {
							logger.info("sleeping " + intervalWithFixedSpeed + " ms, (speedRatio: " + speedRatio+")" );
							Thread.sleep(intervalWithFixedSpeed);
						} catch (InterruptedException e) {
							logger.error("error sleeping",e);
						}
					}
				}
			}
		}
		logger.info("finished emulation.");
	}

	private void postLocation(Waypoint currentWaypoint) {
		SessionUpdateDto update = new SessionUpdateDto();
		update.setSessionId(sessionId);
		update.setLatitude(currentWaypoint.getCoordinate().getLatitude());
		update.setLongitude(currentWaypoint.getCoordinate().getLongitude());
		logger.info("Updating session: " + new ToStringCreator(update).append("sessionId",update.getSessionId())
				.append("latitude",update.getLatitude())
				.append("longitude", update.getLongitude()).toString());
		rest.postForObject(baseUrl+"/postLocation.json", update, Boolean.class);
	}

	public void stopEmulation() {
		this.shouldStop = true;
	}

	public void setSpeed(long ratio) {
		this.speedRatio = ratio;
	}
}
