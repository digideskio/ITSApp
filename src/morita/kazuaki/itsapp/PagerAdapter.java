package morita.kazuaki.itsapp;

import java.util.List;

import morita.kazuaki.itsapp.manager.ContentsManager.Ranking;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {

	private List<Ranking> contentsList;
	
	public PagerAdapter(FragmentManager fragmentManager,
			List<Ranking> contentsList) {
		super(fragmentManager);
		this.contentsList = contentsList;
	}

	@Override
	public Fragment getItem(int position) {
		
//		String url = "";

//		switch (position) {
//		case 0:
//			url = "https://itunes.apple.com/jp/rss/topgrossingapplications/limit=100/json";
//			break;
//		case 1:
//			url = "https://itunes.apple.com/jp/rss/topfreemacapps/limit=100/json";
//			break;
//		case 2:
//		default:
//			url = "https://itunes.apple.com/jp/rss/topgrossingapplications/limit=100/json";
//			break;
//		}
//		
		return AppListFragment.newInstance(contentsList.get(position).getUrl(), null);
	}

	@Override
	public int getCount() {
		return contentsList.size();
	}
	
	@Override
	public CharSequence getPageTitle(int position) {
		return contentsList.get(position).getTitle();
	}
	
	

}
