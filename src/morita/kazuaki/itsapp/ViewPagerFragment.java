package morita.kazuaki.itsapp;

import morita.kazuaki.itsapp.manager.ContentsManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ViewPagerFragment extends Fragment {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.view_pager,container, false);
		
		
		
		ViewPager viewPager = (ViewPager)rootView.findViewById(R.id.pager);
		PagerTabStrip tabStrip = (PagerTabStrip)rootView.findViewById(R.id.strip);
		
		
		PagerAdapter pagerAdapter = new PagerAdapter(getFragmentManager(), ContentsManager.getContentsList());
		viewPager.setAdapter(pagerAdapter);
		
		
		return rootView;
	}
	
	
		
	
}
