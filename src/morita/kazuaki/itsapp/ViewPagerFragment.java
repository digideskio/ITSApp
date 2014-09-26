package morita.kazuaki.itsapp;

import morita.kazuaki.itsapp.manager.ContentsManager;
import morita.kazuaki.itsapp.manager.ContentsManager.ENUM_TYPE;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ViewPagerFragment extends Fragment {
	
	private static final String ARG_PARAM1 = "param1";
	
	private String mParam1;
	
	private View rootView;
	private ViewPager viewPager;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Bundle bundle;		
		if(savedInstanceState != null){
			bundle  = savedInstanceState;
		} else  if (getArguments() != null) {
			bundle = getArguments();
		} else {
			bundle = new Bundle();
		}
		mParam1 = bundle.getString(ARG_PARAM1);
	}
	
	public static ViewPagerFragment newInstance(ENUM_TYPE type) {
		ViewPagerFragment fragment = new ViewPagerFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, type.name());
		fragment.setArguments(args);
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		 rootView = inflater.inflate(R.layout.view_pager,container, false);
		
		viewPager = (ViewPager)rootView.findViewById(R.id.pager);
		PagerTabStrip tabStrip = (PagerTabStrip)rootView.findViewById(R.id.strip);
		
		
		
		
		PagerAdapter pagerAdapter = new PagerAdapter(getFragmentManager(), ContentsManager.getContentsList(ENUM_TYPE.valueOf(mParam1)));
		viewPager.setAdapter(pagerAdapter);
		
		return rootView;
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState = getArguments();
		super.onSaveInstanceState(outState);
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
	}
}
