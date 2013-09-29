package jana.example.tripadviser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;



public class SortedListActivity extends Activity implements OnClickListener   {

	private List<HashMap<String, String>> tripMapData;
	private TextView msortetails;
	private Button msortTripdetails;
	private TripAdviserData tripData;
	private TripAdviserModel mtripAdviserModel;
	protected ListAdapter adapter;
	private Context mContext;
	private ListView lv;
	// to values
	private int[] to = new int[] { R.id.tripName, R.id.tripTitle,  };


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// load layout
		setContentView(R.layout.sort_list); 
		mContext = this;
		// fillTempData
		fillTempData();
		// Write algorithm approach -for sorting BigOnotaion
		sortBigOnotaion();
		// get values 
		getViewById();
		// set events
		setOnClickListener();

		// sorted your list 		
		SimpleAdapter adapter = new SimpleAdapter(this, tripData.getFillMaps(), 
				R.layout.trip_list_item, new String[] {UIConstants.BYTRIP, UIConstants.ROOT}, to);
		lv.setAdapter(adapter);
		// setOnItemClickListener
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
				// buildedMessage
				String buildedMessage = displayListItems(myItemInt);
				// show alerts
				tripData.AlertMessage(buildedMessage,(Activity) mContext);

			}


		});

	}
	// buildedMessage
	private String displayListItems(int myItemInt) {
		String transportName=tripData.fillMaps.get(myItemInt).get(UIConstants.BYTRIP).toString();
		String transportRoot=tripData.fillMaps.get(myItemInt).get(UIConstants.ROOT).toString();

		String buildedMessage =UIConstants.TRANSPORT + transportName 
				+ UIConstants.TWOLINES +UIConstants.WAY+ transportRoot
				+ UIConstants.NEWLINE + UIConstants.SEAT+tripData.getmSeat()[myItemInt]
						+ UIConstants.TWOLINES + UIConstants.DETAILS+tripData.getmDetails()[myItemInt];

		return buildedMessage;
	}  

	private void fillTempData() {
		// intilizatin
		mtripAdviserModel = TripAdviserModel.getAdviserInstance(this);
		tripData=mtripAdviserModel.getTripAdviserData();
		tripMapData  = new ArrayList<HashMap<String, String>>();
		for(int i = 0; i < tripData.getmTripID().length; i++){
			HashMap<String, String> map = new HashMap<String, String>();
			map.put(UIConstants.BYTRIP,tripData.getmTransport()[i]+" - " +tripData.getmTransportNumber()[i]);
			map.put(UIConstants.ROOT, tripData.getmDepart()[i]+" > "+tripData.getmDestination()[i]);
			tripMapData.add(map);

		}
	}


	//In other words, sorting list of N elements using Merge Sort equals to using Merge Sort 
	//on both valves (2*M(n/2)) plus the number of operations to put those valves back together (N).

	private void sortBigOnotaion(){

		// loop through new /next card
		for(int i = 0;i<tripData.getmTripID().length ;i++) {
			UIConstants.nextCard[i] = getCardPosition(UIConstants.liveCard[i]);
		}
		// get total trips
		int tripTotal = tripData.getmTripID().length;
		// Declare position
		int position = tripTotal-1;
		int target = -1;
		//Update newPostition
		for(int i = 0;i<tripTotal;i++) {
			if(UIConstants.nextCard[i]==target) {
				UIConstants.newposition[position] = i;
			}
		}
		for(int i = 0;i<tripTotal-1;i++) {
			target =UIConstants.newposition[position];
			position--;

			for(int j = 0;j<tripTotal;j++) {
				if(UIConstants.nextCard[j]==target) {
					UIConstants.newposition[position] = j;
				}
			}
		}

		// copy index values into list after getting newOrder index
		ArrayList<Integer> arrlist=getIndex(UIConstants.newposition);
		// after completed sorting  then fill/set your map collation/ with update positions 
		for(int k = 0;k<tripData.getFillMaps().size();k++) {  
			int index=arrlist.get(k);
			tripData.getFillMaps().set(k, tripMapData.get(index));
		}

	}
	// this will return array position in list
	private ArrayList<Integer> getIndex(int[] newOrder) {
		ArrayList<Integer> arrlist = new ArrayList<Integer>(newOrder.length);
		for(int k = 0;k<newOrder.length;k++) { 
			arrlist.add(newOrder[k]);
		}
		return arrlist;
	}


	/**
	 *  find Card position 
	 * pass input card
	 */
	private int getCardPosition(int i) {
		String destination = tripData.getmDestination()[i];
		int nextCard = -1;
		for(int j = 0;j<tripData.getmTripID().length;j++) {
			if(destination.equals(tripData.getmDepart()[j]) ) {
				nextCard = j;
				break;
			}
		}
		return nextCard;
	}



	/**
	 * This method fetch the id's for all the resources.
	 * 
	 * @throws Exception
	 *             when getViewById returns null
	 */
	private void getViewById() throws NotFoundException {
		msortetails = (TextView) findViewById(R.id.text_trip);
		msortTripdetails = (Button) findViewById(R.id.btn_back);
		// Get a handle to the list view
		lv = (ListView) findViewById(R.id.listSLV);
	}
	private void setOnClickListener() {
		msortTripdetails.setOnClickListener(this);
	}

	/**
	 * back button click event handle. Will open back screen. 
	 * @param v
	 */

	@Override
	public void onClick(View v) {
		Intent unSortedIntent = new Intent(this, UnSortedListActivity.class);
		unSortedIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(unSortedIntent);
	}





}





