import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.divbyzero.gpx.parser.ParsingException;

import org.junit.Test;

import com.uade.pfi.emulator.DefaultEmulator;
import com.uade.pfi.emulator.TransportsEmulator;


public class EmulatorTest {
//	static String baseUrl = "http://ibondi.aws.af.cm";
	static String baseUrl = "http://localhost:8080/web";

	@Test
	public void circuitoBasicoNela_Casa() throws ParsingException{
		TransportsEmulator emu = new DefaultEmulator(baseUrl,"circuitos/circuito1.gpx");
		emu.startEmulation();
	}
	
	public static void main(String[] args) throws InterruptedException{
		
		Thread.sleep(5000);
		
		
		Runnable r1 = new Runnable(){
			public void run() {
				TransportsEmulator emu = null;
				try {
					emu = new DefaultEmulator(baseUrl,"circuitos/circuito1.gpx");
				} catch (ParsingException e) {
					e.printStackTrace();
				}
				emu.startEmulation();		
			}
		};
		Runnable r2 = new Runnable(){
			public void run() {
				TransportsEmulator emu = null;
				try {
					emu = new DefaultEmulator(baseUrl,"circuitos/circuito2.gpx");
				} catch (ParsingException e) {
					e.printStackTrace();
				}
				emu.startEmulation();		
			}
		};
		Runnable r3 = new Runnable(){
			public void run() {
				TransportsEmulator emu = null;
				try {
					emu = new DefaultEmulator(baseUrl,"circuitos/circuito3.gpx");
				} catch (ParsingException e) {
					e.printStackTrace();
				}
				emu.startEmulation();		
			}
		};
		Runnable r4 = new Runnable(){
			public void run() {
				TransportsEmulator emu = null;
				try {
					emu = new DefaultEmulator(baseUrl,"circuitos/circuito4.gpx");
				} catch (ParsingException e) {
					e.printStackTrace();
				}
				emu.startEmulation();		
			}
		};
		Runnable r5 = new Runnable(){
			public void run() {
				TransportsEmulator emu = null;
				try {
					emu = new DefaultEmulator(baseUrl,"circuitos/circuito5.gpx");
				} catch (ParsingException e) {
					e.printStackTrace();
				}
				emu.startEmulation();		
			}
		};
		Runnable r6 = new Runnable(){
			public void run() {
				TransportsEmulator emu = null;
				try {
					emu = new DefaultEmulator(baseUrl,"circuitos/circuito6.gpx");
				} catch (ParsingException e) {
					e.printStackTrace();
				}
				emu.startEmulation();		
			}
		};
		Runnable r7 = new Runnable(){
			public void run() {
				TransportsEmulator emu = null;
				try {
					emu = new DefaultEmulator(baseUrl,"circuitos/circuito7.gpx");
				} catch (ParsingException e) {
					e.printStackTrace();
				}
				emu.startEmulation();		
			}
		};
		Runnable r8 = new Runnable(){
			public void run() {
				TransportsEmulator emu = null;
				try {
					emu = new DefaultEmulator(baseUrl,"circuitos/circuito8.gpx");
				} catch (ParsingException e) {
					e.printStackTrace();
				}
				emu.startEmulation();		
			}
		};
		ExecutorService executorService = Executors.newFixedThreadPool(8);
		executorService.execute(r1);
		executorService.execute(r2);
		executorService.execute(r3);
		executorService.execute(r4);
		executorService.execute(r5);
		executorService.execute(r6);
		executorService.execute(r7);
		executorService.execute(r8);
	}
}
