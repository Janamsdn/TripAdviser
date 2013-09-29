package jana.example.tripadviser;

import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;

// this class will define structure and get/set properties
public class TripAdviserData  {

	private String[] mTripID;
	private String[] mDetails;
	private String[] mSeat;
	private String[] mDestination;
	private String[] mDepart;
	private String[] mTransport;
	private String[] mTransportNumber;
	public List<HashMap<String, String>> fillMaps; 

	// TripID
	public String[] getmTripID() {
		return mTripID;
	}

	public void setmTripID(String[] mTripID) {
		this.mTripID = mTripID;
	}
	//Details
	public String[] getmDetails() {
		return mDetails;
	}

	public void setmDetails(String[] mDetails) {
		this.mDetails = mDetails;
	}
	// Seat
	public String[] getmSeat() {
		return mSeat;
	}

	public void setmSeat(String[] mSeat) {
		this.mSeat = mSeat;
	}
	// Destination
	public String[] getmDestination() {
		return mDestination;
	}

	public void setmDestination(String[] mDestination) {
		this.mDestination = mDestination;
	}
	//Departure
	public String[] getmDepart() {
		return mDepart;
	}

	public void setmDepart(String[] mDepart) {
		this.mDepart = mDepart;
	}
	// Transport
	public String[] getmTransport() {
		return mTransport;
	}

	public void setmTransport(String[] mTransport) {
		this.mTransport = mTransport;
	}
	// Transport Number
	public String[] getmTransportNumber() {
		return mTransportNumber;
	}

	public void setmTransportNumber(String[] mTransportNumber) {
		this.mTransportNumber = mTransportNumber;
	}
	// List map
	public List<HashMap<String, String>> getFillMaps() {
		return fillMaps;
	}

	public void setFillMaps(List<HashMap<String, String>> fillMaps) {
		this.fillMaps = fillMaps;
	}
	// load values from string xml file
	public void fillValues(Activity activity ) {
		// get array values keep into dictionary
		this.setmTripID(activity.getResources().getStringArray(R.array.TripID));
		this.setmDetails(activity.getResources().getStringArray(R.array.Details));
		this.setmSeat(activity.getResources().getStringArray(R.array.Seat));
		this.setmDestination(activity.getResources().getStringArray(R.array.Destination));
		this.setmDepart(activity.getResources().getStringArray(R.array.Depart));
		this.setmTransport(activity.getResources().getStringArray(R.array.Transport));
		this.setmTransportNumber(activity.getResources().getStringArray(R.array.TransportNumber));
	}
	/**
	 * Shows alert messages
	 */
	public void AlertMessage(String message,final Activity activity) {
		try {
			AlertDialog.Builder builder = new AlertDialog.Builder(activity);
			builder.setMessage(message);
			builder.setTitle(UIConstants.TransportLink);

			builder.setPositiveButton(UIConstants.okey,new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					//Toast.makeText(activity.getBaseContext(), UIConstants.Clicked, Toast.LENGTH_LONG).show();
					dialog.cancel();

				}
			});	
			AlertDialog alert = builder.create();			
			alert.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}    
}