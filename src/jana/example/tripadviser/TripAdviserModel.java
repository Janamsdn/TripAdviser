package jana.example.tripadviser;

import android.content.Context;

// this model class will single ton approach
public class TripAdviserModel  {

	private static Context mContext;
	private TripAdviserData tripAdviserData;

	private static TripAdviserModel mTripAdviserData;

	public static TripAdviserModel getAdviserInstance(Context context)
	{
		mContext = context;
		if(mTripAdviserData == null)
		{
			mTripAdviserData = new TripAdviserModel();
		}
		return mTripAdviserData;

	}
	// get tripAdviserData
	public TripAdviserData getTripAdviserData() {
		return tripAdviserData;
	}
	// set setTripAdviserData
	public void setTripAdviserData(TripAdviserData tripAdviserData) {
		this.tripAdviserData = tripAdviserData;
	}








}