/**
 * 
 */
package com.uade.pfi.androidapp.overlays;

import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

/**
 * @author fedec
 *
 */
public class WhereIAmOverlay extends ItemizedOverlay<OverlayItem> {
	private OverlayItem item;
	

	public WhereIAmOverlay(Drawable defaultMarker,OverlayItem item) {
		super(boundCenterBottom(defaultMarker));
		this.item = item;
		populate();
	}


	@Override
	protected OverlayItem createItem(int i) {
		return item;
	}

	@Override
	public int size() {
		return 1;
	}

}
