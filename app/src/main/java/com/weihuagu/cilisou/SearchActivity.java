package com.weihuagu.cilisou;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import com.google.android.gms.ads.*;

public class SearchActivity extends Activity implements AsyncResponse{
	private AdView mAdView;

	private String keyword=null;
	private List<CiliInfo> ciliList=new ArrayList<CiliInfo>();
	private CiliAdapter mAdapter=null;
	private ListView cililistview=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		this.getIntentData();
		this.initUiResouces();
		this.getCiliList(this.keyword);

		this.showBanner();
	}
	public void getIntentData(){
		Intent intent=getIntent();
	    this.keyword=intent.getStringExtra("searchkey");
	    Toast toast=Toast.makeText(this, "搜索需要一定时间，请耐心等待"+this.keyword, Toast.LENGTH_SHORT); 
		toast.show();    
     }
	
	public void initUiResouces(){
		this.cililistview=(ListView)findViewById(R.id.cililist);
		
	}
	public void getCiliList(String keyword){
		SearchTask mtask = new SearchTask();
		mtask.setOnAsyncResponse(this);
		mtask.execute(keyword);
		
	}
	@Override
	public void onDataReceivedSuccess(List<CiliInfo> cililist) {
		// TODO Auto-generated method stub
		if(cililist!=null){
			for(int i=0;i<cililist.size();i++){
				if(cililist.get(i)!=null){
				this.ciliList.add(cililist.get(i));
				}
					
			}
			//load adapter
			
			this.mAdapter=new CiliAdapter();
			this.mAdapter.setContext(getBaseContext());
			this.mAdapter.addCiliList(this.ciliList);
			this.cililistview.setAdapter(this.mAdapter);
	   }
		
	}
	@Override
	public void onDataReceivedFailed() {
		// TODO Auto-generated method stub
		Toast toast=Toast.makeText(this, "接受数据失败了", Toast.LENGTH_SHORT); 
		toast.show();     
	}

	//ad
	/** Called when leaving the activity */
	@Override
	public void onPause() {
		if (mAdView != null) {
			mAdView.pause();
		}
		super.onPause();
	}

	/** Called when returning to the activity */
	@Override
	public void onResume() {
		super.onResume();
		if (mAdView != null) {
			mAdView.resume();
		}
	}

	/** Called before the activity is destroyed */
	@Override
	public void onDestroy() {
		if (mAdView != null) {
			mAdView.destroy();
		}
		super.onDestroy();
	}

	public void showBanner(){

		// Gets the ad view defined in layout/ad_fragment.xml with ad unit ID set in
		// values/strings.xml.
		mAdView = (AdView) findViewById(R.id.ad_view);

		// Create an ad request. Check your logcat output for the hashed device ID to
		// get test ads on a physical device. e.g.
		// "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
		AdRequest adRequest =new AdRequest.Builder().build();
		mAdView.setAdListener(new AdListener());

		// Start loading the ad in the background.
		mAdView.loadAd(adRequest);
	}
}
