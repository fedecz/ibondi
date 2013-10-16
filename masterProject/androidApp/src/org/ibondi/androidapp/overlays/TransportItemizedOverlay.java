package org.ibondi.androidapp.overlays;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class TransportItemizedOverlay extends ItemizedOverlay<OverlayItem> {
	
	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
//	private Context mContext;

	public TransportItemizedOverlay(Drawable defaultMarker, Context context) {
		super(boundCenterBottom(defaultMarker));
//		this.mContext = context;
	}
	
//	@Override
//	protected boolean onTap(int index) {
//	  OverlayItem item = mOverlays.get(index);
//	  if(item==null) return false;
//	  AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
//	  dialog.setTitle(item.getTitle());
//	  dialog.setMessage(item.getSnippet());
//	  dialog.show();
//	  return true;
//	}

	@Override
	protected OverlayItem createItem(int i) {
	  return mOverlays.get(i);
	}

	@Override
	public int size() {
		return mOverlays.size();
	}
	
	public void addOverlay(OverlayItem overlay) {
	    mOverlays.add(overlay);
	    populate();
	}

}
