package forest.fice.feeld.k.itsapp;

import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.Volley;
import forest.fice.feeld.k.itsapp.R;
import forest.fice.feeld.k.itsapp.manager.LruCacheSample;

public class DetailActivity extends ActionBarActivity {
	
	private RequestQueue mQueue;
	private static ImageLoader mImageLoader;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		
		Intent intent = getIntent();
		
		if (savedInstanceState == null) {
			
			Map<String, String> map = new HashMap<String, String>();
			map.put(DetailFragment.ARG_NAME, intent.getStringExtra(DetailFragment.ARG_NAME));
			map.put(DetailFragment.ARG_ARTIST, intent.getStringExtra(DetailFragment.ARG_ARTIST));
			map.put(DetailFragment.ARG_PRICE, intent.getStringExtra(DetailFragment.ARG_PRICE));
			map.put(DetailFragment.ARG_IMAGE, intent.getStringExtra(DetailFragment.ARG_IMAGE));
			map.put(DetailFragment.ARG_LINK, intent.getStringExtra(DetailFragment.ARG_LINK));
			map.put(DetailFragment.ARG_NAME, intent.getStringExtra(DetailFragment.ARG_NAME));
			map.put(DetailFragment.ARG_CATEGORY, intent.getStringExtra(DetailFragment.ARG_CATEGORY));
			map.put(DetailFragment.ARG_SUMMARY, intent.getStringExtra(DetailFragment.ARG_SUMMARY));
			
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, DetailFragment.newInstance(map)).commit();
		}
		
		// 戻るを有効にする
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		mQueue = Volley.newRequestQueue(this);
		mImageLoader = new ImageLoader(mQueue, new LruCacheSample());
		
		
		getSupportActionBar().setTitle(intent.getStringExtra(DetailFragment.ARG_NAME));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		
		switch (id) {
		case android.R.id.home:
			finish();
			return true;
		default:
			break;
		}
		
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class DetailFragment extends Fragment {
		
		public static final String ARG_NAME = "name";
		public static final String ARG_ARTIST = "artist";
		public static final String ARG_PRICE = "price";
		public static final String ARG_IMAGE = "image";
		public static final String ARG_LINK = "link";
		public static final String ARG_CATEGORY = "categoty";
		public static final String ARG_SUMMARY = "summary";
		
		private String mParamName;
		private String mParamArtist;
		private String mParamPrice;
		private String mParamImage;
		private String mParamLink;
		private String mParamCategoty;
		private String mParamSummary;
		
		public static DetailFragment newInstance(Map<String, String> params) {
			DetailFragment fragment = new DetailFragment();
			Bundle bundle = new Bundle();
			bundle.putString(ARG_NAME, params.get(ARG_NAME));
			bundle.putString(ARG_ARTIST, params.get(ARG_ARTIST));
			bundle.putString(ARG_PRICE, params.get(ARG_PRICE));
			bundle.putString(ARG_IMAGE, params.get(ARG_IMAGE));
			bundle.putString(ARG_LINK, params.get(ARG_LINK));
			bundle.putString(ARG_CATEGORY, params.get(ARG_CATEGORY));
			bundle.putString(ARG_SUMMARY, params.get(ARG_SUMMARY));
			fragment.setArguments(bundle);
			return fragment;
		}

		public DetailFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_detail,
					container, false);
			
			Bundle bundle;
			if(savedInstanceState != null){
				bundle  = savedInstanceState;
			} else  if (getArguments() != null) {
				bundle = getArguments();
			} else {
				bundle = new Bundle();
			}
			
			mParamName = bundle.getString(ARG_NAME);
			mParamArtist = bundle.getString(ARG_ARTIST);
			mParamPrice = bundle.getString(ARG_PRICE);
			mParamImage = bundle.getString(ARG_IMAGE);
			mParamLink = bundle.getString(ARG_LINK);
			mParamCategoty = bundle.getString(ARG_CATEGORY);
			mParamSummary = bundle.getString(ARG_SUMMARY);
			
			TextView name = (TextView)rootView.findViewById(R.id.detailName);
			TextView artist = (TextView)rootView.findViewById(R.id.detailArtist);
			TextView price = (TextView)rootView.findViewById(R.id.detailPrice);
			ImageView image = (ImageView)rootView.findViewById(R.id.detailImage);
			TextView link = (TextView)rootView.findViewById(R.id.detailLink);
			TextView category = (TextView)rootView.findViewById(R.id.detailCategory);
			TextView summary = (TextView)rootView.findViewById(R.id.detailSummary);
			
			name.setText(mParamName);
			artist.setText(mParamArtist);
			price.setText(mParamPrice);
			
			link.setText(mParamLink);
			category.setText(mParamCategoty);
			summary.setText(mParamSummary);
			
			ImageListener listener = ImageLoader.getImageListener(image,
					R.drawable.ic_action_refresh/* 表示待ち時の画像 */,
					android.R.drawable.ic_dialog_alert /* エラー時の画像 */);
//			System.out.println(Uri.parse(mParamImage)					.getHost());

			mImageLoader.get(mParamImage, listener); /* URLから画像を取得する */
			
			
			return rootView;
		}
	}
}
