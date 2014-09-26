package morita.kazuaki.itsapp;

import morita.kazuaki.itsapp.entity.FeedEntity;
import morita.kazuaki.itsapp.entity.FeedEntity.Entry;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.Volley;

public class ListAdapter extends ArrayAdapter<FeedEntity.Entry> {

	// private RequestQueue mQueue;
	private ImageLoader mImageLoader;

	private LayoutInflater layoutInflater_;

	public ListAdapter(Context context, int resource, int textViewResourceId,
			Entry[] entrys, RequestQueue mQueue) {
		super(context, resource, textViewResourceId, entrys);

		layoutInflater_ = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		mQueue = Volley.newRequestQueue(getContext());
		mImageLoader = new ImageLoader(mQueue, new LruCacheSample());
	}

	public class ViewHolder {
		public ImageView imageView;
		public TextView name;
		public TextView artist;
		public TextView category;
		public TextView no;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			convertView = layoutInflater_.inflate(R.layout.fragment_list_row,
					null);
			holder = new ViewHolder();
			holder.imageView = (ImageView) convertView
					.findViewById(R.id.rowImage);
			holder.name = (TextView) convertView.findViewById(R.id.rowName);
			holder.artist = (TextView) convertView.findViewById(R.id.rowArtist);
			holder.category = (TextView) convertView
					.findViewById(R.id.rowCategory);
			holder.no = (TextView) convertView.findViewById(R.id.rowNo);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		FeedEntity.Entry entry = (FeedEntity.Entry) getItem(position);
		
		holder.name.setText(entry.name.text);
		holder.artist.setText(entry.artist.text);
		holder.category.setText(entry.category.attributes.label);
		holder.no.setText(String.valueOf(position+1));

		ImageListener listener = ImageLoader.getImageListener(holder.imageView,
				R.drawable.ic_action_refresh/* 表示待ち時の画像 */,
				android.R.drawable.ic_dialog_alert /* エラー時の画像 */);
		System.out.println(Uri.parse(entry.image[entry.image.length - 1].text)
				.getHost());

		mImageLoader.get(entry.image[entry.image.length - 1].text, listener); /* URLから画像を取得する */

		return convertView;

	}

	public class LruCacheSample implements ImageCache {

		private LruCache<String, Bitmap> mMemoryCache;

		LruCacheSample() {
			int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
			int cacheSize = maxMemory / 8; // 最大メモリに依存
			// int cacheSize = 5 * 1024 * 1024; // 5MB

			mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
				@Override
				protected int sizeOf(String key, Bitmap bitmap) {
					// 使用キャッシュサイズ(KB単位)
					return bitmap.getRowBytes() * bitmap.getHeight();
				}
			};
		}

		// ImageCacheのインターフェイス実装
		@Override
		public Bitmap getBitmap(String url) {
			return mMemoryCache.get(url);
		}

		@Override
		public void putBitmap(String url, Bitmap bitmap) {
			mMemoryCache.put(url, bitmap);
		}
	}
}
