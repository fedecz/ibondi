/**
 * 
 */
package com.uade.pfi.androidapp.activity;

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

import com.uade.pfi.androidapp.service.TransportMeService;
import com.uadepfi.android.R;
/**
 * @author chiwi
 *
 */
public class CheckInActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_checkin);
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
		SpinnerAdapter arrayAdapter = getAdapterFor(getTransportLineArray());
		OnItemSelectedListener selectionListener = createLineSelectionListener();
		enableSpinner(getLineSpinner(), arrayAdapter, selectionListener);
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
		OnItemSelectedListener selectionListener = createBranchSelectionListener();
		enableSpinner(getBranchSpinner(), arrayAdapter, selectionListener);
	}

	private OnItemSelectedListener createBranchSelectionListener() {
		return new OnItemSelectedListener() {

			@Override
		    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
				if(parentView.getSelectedItemPosition()!=0) {
					//Fire the other spinner population
		    		String selectedBranch = (String)((TextView)selectedItemView).getText();
		    		enableHeadingSpinnerWithData(selectedBranch);
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

	protected void enableHeadingSpinnerWithData(String aSelectedBranch) {
		SpinnerAdapter arrayAdapter = getAdapterFor(aHeadingArrayFor(aSelectedBranch));
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

	private String[] aHeadingArrayFor(String aSelectedBranch) {
		// Ver como ir a buscar el servicio que nos trae esto
		return new String[] {"seleccione sentido","La Boca","Olivos"};
	}

	private String[] aBranchLineArrayFor(String selectedLine) {
		// TODO Ver como devolvemos esto
		return new String[] {"seleccione ramal", "SR","UCA","Boca"};
	}

	private String[] getTransportLineArray() {
		// ver como corno ir a buscar los datos al servicio 
		return new String[]{"seleccione linea", "152","59"};
	}

	private String[] aTransportTypeArray() {
		// TODO Ver como ir a buscar los datos
		return new String[]{"seleccione transporte", "Colectivo", "Tren"};
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
