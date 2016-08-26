package com.weihuagu.cilisou;
import android.util.Log;

public class AdListener extends com.google.android.gms.ads.AdListener{
	private String stringTag;
	public AdListener() {
		super();

	}
	@Override
	public void onAdClosed() {
		super.onAdClosed();
	}

	@Override
	public void onAdFailedToLoad(int i) {
		super.onAdFailedToLoad(i);
		Log.v("admob","load fail");
	}

	@Override
	public void onAdLeftApplication() {
		super.onAdLeftApplication();
	}

	@Override
	public void onAdOpened() {
		super.onAdOpened();
	}

	@Override
	public void onAdLoaded() {
		super.onAdLoaded();
		Log.v("admob","loaded");
	}
}
