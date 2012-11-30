/**
 * 
 */
package com.uade.pfi.androidapp.activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.uade.pfi.androidapp.server.ServerFacade;
import com.uade.pfi.androidapp.service.TransportMeService;
import com.uade.pfi.api.dto.TransportDTO;
import com.uade.pfi.api.dto.TransportListDTO;
import com.uade.pfi.api.dto.TransportTypeListDTO;
import com.uadepfi.android.R;
/**
 * @author chiwi
 *
 */
public class CheckInActivity extends Activity{
	
	private ServerFacade server;
	private Map<String, List<TransportDTO>> transportMap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_checkin);
	}

	@Override
	protected void onStart() {
		super.onStart();
		server=ServerFacade.getInstace(this.getBaseContext());
		configureView();
	}
	
	private void configureView() {
		configureTransportTypeSpinner();
		configureLineSpinner();
		configureBranchSpinner();
		configureHeadingSpinner();
		disableCheckinButton();
	}

	private void configureLineSpinner() {
		getLineSpinner().setEnabled(false);
	}

	private void configureBranchSpinner() {
		getBranchSpinner().setEnabled(false);
	}

	private void configureHeadingSpinner() {
		getHeadingSpinner().setEnabled(false);
	}
	
	private void configureTransportTypeSpinner() {
		SpinnerAdapter arrayAdapter = getAdapterFor(aTransportTypeArray());
		OnItemSelectedListener transportTypeListener = createTransportTypeListener();
		enableSpinner(getTransportTypeSpinner(), arrayAdapter, transportTypeListener);
	}

	private OnItemSelectedListener createTransportTypeListener() {
		return new OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
		    	if(parentView.getSelectedItemPosition()!=0) {
		    		String transportType = (String)((TextView)selectedItemView).getText();
		    		enableLineSpinnerWithData(transportType);
		    	} else {
					onNothingSelected(parentView);
				}
		    }

		    @Override
		    public void onNothingSelected(AdapterView<?> parentView) {
		    	if(parentView.getSelectedItemPosition() == 0) {
		    		disableLineSpinner();
		    		disableBranchSpinner();
		    		disableHeadingSpinner();
		    		disableCheckinButton();
		    	}
		    }
		};
	}

	protected void disableCheckinButton() {
		((Button) findViewById(R.id.checkInOKButton)).setEnabled(false);
	}

	protected void enableCheckinButton() {
		((Button) findViewById(R.id.checkInOKButton)).setEnabled(true);
	}
	
	protected void enableLineSpinnerWithData(String transportType) {
		createTransportMap(transportType);
		SpinnerAdapter arrayAdapter = getAdapterFor(getLineListFrom());
		OnItemSelectedListener selectionListener = createLineSelectionListener();
		enableSpinner(getLineSpinner(), arrayAdapter, selectionListener);
	}

	private String[] getLineListFrom() {
		//TODO: VER COMO DEVOLVER LA LISTA ORDENADA, AL SER TEXTO ORDENA PARA EL CULO
		Set<String> keySet = this.transportMap.keySet();
		String[] lines = new String[keySet.size() + 1];
		int i = 0;
		lines[i++] = "seleccione linea";
		for (String line : keySet) {
			lines[i++] = line; 
		}
		return lines;
	}

	private void enableSpinner(Spinner spinner, SpinnerAdapter adapter, OnItemSelectedListener listener) {
		spinner.setEnabled(true);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(listener);
	}

	private OnItemSelectedListener createLineSelectionListener() {
		return new OnItemSelectedListener() {

			@Override
		    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
				if(parentView.getSelectedItemPosition()!=0) {
					//Fire the other spinner population
		    		String selectedLine = (String)((TextView)selectedItemView).getText();
		    		enableBranchSpinnerWithData(selectedLine);
				} else {
					onNothingSelected(parentView);
				}
		    }

		    @Override
		    public void onNothingSelected(AdapterView<?> parentView) {
		    	if(parentView.getSelectedItemPosition() == 0) {
		    		disableBranchSpinner();
		    		disableHeadingSpinner();
		    	}
		    }
		};
	}

	private void disableHeadingSpinner() {
		getHeadingSpinner().setSelection(0);
		getHeadingSpinner().setEnabled(false);
	}

	private void disableBranchSpinner() {
		getBranchSpinner().setSelection(0);
		getBranchSpinner().setEnabled(false);
	}

	private void disableLineSpinner() {
		getLineSpinner().setSelection(0);
		getLineSpinner().setEnabled(false);
	}
	
	protected void enableBranchSpinnerWithData(String aSelectedLine) {
		SpinnerAdapter arrayAdapter = getAdapterFor(aBranchLineArrayFor(aSelectedLine));
		OnItemSelectedListener selectionListener = createBranchSelectionListener(aSelectedLine);
		enableSpinner(getBranchSpinner(), arrayAdapter, selectionListener);
	}

	private OnItemSelectedListener createBranchSelectionListener(final String aSelectedLine) {
		return new OnItemSelectedListener() {

			@Override
		    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
				if(parentView.getSelectedItemPosition()!=0) {
					//Fire the other spinner population
		    		String selectedBranch = (String)((TextView)selectedItemView).getText();
		    		enableHeadingSpinnerWithData(aSelectedLine, selectedBranch);
				} else {
					onNothingSelected(parentView);
				}
		    }

		    @Override
		    public void onNothingSelected(AdapterView<?> parentView) {
		    	if(parentView.getSelectedItemPosition() == 0) {
		    		disableHeadingSpinner();
		    	}
		    }
		};
	}

	protected void enableHeadingSpinnerWithData(String aSelectedLine, String aSelectedBranch) {
		SpinnerAdapter arrayAdapter = getAdapterFor(aHeadingArrayFor(aSelectedLine, aSelectedBranch));
		OnItemSelectedListener listener = createHeadingListener();
		enableSpinner(getHeadingSpinner(), arrayAdapter, listener);
	}

	private OnItemSelectedListener createHeadingListener() {
		return new OnItemSelectedListener() {

			@Override
		    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
				if(parentView.getSelectedItemPosition()!=0) {
					enableCheckinButton();
				} else {
					onNothingSelected(parentView);
				}
		    }

		    @Override
		    public void onNothingSelected(AdapterView<?> parentView) {
		    	if(parentView.getSelectedItemPosition() == 0) {
		    		disableCheckinButton();
		    	}
		    }
		};
	}

	private SpinnerAdapter getAdapterFor(String[] transportTypes) {
		return new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, transportTypes);
	}

	private String[] aTransportTypeArray() {
		TransportTypeListDTO transportTypeList = this.server.getTransportTypeList(null);
		String[] transportTypes = new String[transportTypeList.getTransportTypes().size() + 1];
		int i = 0;
		transportTypes[i++] = "seleccione tipo de transporte";
		for (String type : transportTypeList.getTransportTypes()) {
			transportTypes[i++] = type;
		}
		return transportTypes;
	}
	
	private void createTransportMap(String transportType) {
		TransportListDTO transportListBy = this.server.getTransportListBy(transportType);
		this.transportMap = new HashMap<String, List<TransportDTO>>();
		for (TransportDTO transport : transportListBy.getList()) {
			String key = transport.getName();
			if (this.transportMap.containsKey(key)) {
				this.transportMap.get(key).add(transport);
			} else {
				List<TransportDTO> tranportList = new ArrayList<TransportDTO>();
				tranportList.add(transport);
				this.transportMap.put(key, tranportList);
			}
		}
	}

	private String[] aBranchLineArrayFor(String selectedLine) {
		List<TransportDTO> lineTransportList = this.transportMap.get(selectedLine);
		String[] branches = new String[lineTransportList.size() + 1];
		int i=0;
		branches[i++] = "seleccione ramal";
		for (TransportDTO transportDTO : lineTransportList) {
			branches[i++] = transportDTO.getBranch();
		}
		return branches;
	}
	
	private String[] aHeadingArrayFor(String aSelectedLine, String aSelectedBranch) {
		List<TransportDTO> lineTransportList = this.transportMap.get(aSelectedLine);
		String[] heading = new String[3];//IDA O VUELTA
		int i = 0;
		heading[i++] = "seleccione destino";
		for (TransportDTO transportDTO : lineTransportList) {
			if (transportDTO.getBranch().equals(aSelectedBranch)){
				heading[i++]= transportDTO.getHeading();
			}
		}
		return heading;
	}
	
	public void checkIn(View v){
		Spinner transportTypeSpinner = getTransportTypeSpinner();
		Spinner lineSpinner = getLineSpinner();
		Spinner branchSpinner = getBranchSpinner();
		Spinner headingSpinner = getHeadingSpinner();
		
		String transportType = (String) transportTypeSpinner.getSelectedItem();
		String line = (String) lineSpinner.getSelectedItem();
		String branch = (String) branchSpinner.getSelectedItem();
		String heading = (String) headingSpinner.getSelectedItem();
		
		Intent intent = new Intent(this,TransportMeService.class);
		
		intent.putExtra("transportType", transportType);
		intent.putExtra("line", line);
		intent.putExtra("branch", branch);
		intent.putExtra("heading", heading);
		
		CheckInActivity.this.startService(intent);
		CheckInActivity.this.finish();
	}

	private Spinner getHeadingSpinner() {
		return (Spinner) findViewById(R.id.headingType_spinner);
	}

	private Spinner getBranchSpinner() {
		return (Spinner) findViewById(R.id.branch_spinner);
	}

	private Spinner getLineSpinner() {
		return (Spinner) findViewById(R.id.line_spinner);
	}

	private Spinner getTransportTypeSpinner() {
		return (Spinner) findViewById(R.id.transportType_spinner);
	}

}
