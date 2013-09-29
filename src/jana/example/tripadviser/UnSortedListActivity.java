package jana.example.tripadviser;


import java.util.ArrayList;
import java.util.HashMap;
import android.app.Activity;
import android.app.AlertDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;

import android.os.Bundle;


import android.view.View;
import android.view.View.OnClickListener;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;


public class UnSortedListActivity extends Activity implements OnClickListener   {

	private TripAdviserData tripData = new TripAdviserData();
	private Button msortTripdetails;
	protected ListAdapter adapter;
	private ListView lv;
	private Context mContext;
	// to values
	private int[] to = new int[] { R.id.tripName, R.id.tripTitle,  };


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// load lay out
		setContentView(R.layout.unsorted_list); 
		//loadValues from resource file- strings.xml
		tripData.fillValues(this);
		mContext = this;
		// fill data into map collection
		fillData();
		// get values
		getViewById();
		// set listeners
		setOnClickListener();
		// set adapter
		SimpleAdapter adapter = new SimpleAdapter(this, tripData.fillMaps, 
				R.layout.trip_list_item, new String[] {UIConstants.BYTRIP, UIConstants.ROOT}, to);
		lv.setAdapter(adapter);
		// setOnItemClickListener
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
				displayAlertDetails(myItemInt);

			}

			private void displayAlertDetails(int myItemInt) {
				// get values from selected name
				String buildedMessage = displayListItems(myItemInt);
				tripData.AlertMessage(buildedMessage,(Activity) mContext);
			}


		});
	}

	private String displayListItems(int myItemInt) {
		String transportName=tripData.fillMaps.get(myItemInt).get(UIConstants.BYTRIP).toString();
		String transportRoot=tripData.fillMaps.get(myItemInt).get(UIConstants.ROOT).toString();

		String buildedMessage =UIConstants.TRANSPORT + transportName 
				+ UIConstants.TWOLINES +UIConstants.WAY+ transportRoot
				+ UIConstants.NEWLINE + UIConstants.SEAT+tripData.getmSeat()[myItemInt]
						+ UIConstants.TWOLINES + UIConstants.DETAILS+tripData.getmDetails()[myItemInt];
		return buildedMessage;
	}        
	// load values
	private void fillData() {
		// get root and trip from people Array List
		tripData.fillMaps  = new ArrayList<HashMap<String, String>>();
		for(int i = 0; i < tripData.getmTripID().length; i++){
			HashMap<String, String> map = new HashMap<String, String>();
			map.put(UIConstants.BYTRIP,tripData.getmTransport()[i]+" - " +tripData.getmTransportNumber()[i]);
			map.put(UIConstants.ROOT, tripData.getmDepart()[i]+" > "+tripData.getmDestination()[i]);
			tripData.fillMaps.add(map);

		}
		// set data to single ton class
		tripData.setFillMaps(tripData.fillMaps);
		TripAdviserModel tripAdviserDataModel =TripAdviserModel.getAdviserInstance(this);
		tripAdviserDataModel.setTripAdviserData(tripData);
	}

	/**
	 * This method fetch the id's for all the resources.
	 * 
	 * @throws Exception
	 *             when getViewById returns null
	 */
	private void getViewById() throws NotFoundException {
		msortTripdetails = (Button) findViewById(R.id.btn_trip);
		lv = (ListView) findViewById(R.id.listLV);
	}
	private void setOnClickListener() {
		msortTripdetails.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_trip:		
			// call SortedListActivity intent
			Intent intent = new Intent(this,SortedListActivity.class);
			this.startActivity(intent);
			break;
		default:
		}


	}




}





