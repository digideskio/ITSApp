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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = layoutInflater_.inflate(R.layout.fragment_list_row,
					null);
		}

		FeedEntity.Entry entry = (FeedEntity.Entry) getItem(position);

		TextView text = (TextView) convertView.findViewById(R.id.rowName);
		text.setText(entry.name.text);

		text = (TextView) convertView.findViewById(R.id.rowArtist);
		text.setText(entry.artist.text);

		text = (TextView) convertView.findViewById(R.id.rowSummary);
		text.setText(entry.summary.text);

		ImageView image = (ImageView) convertView.findViewById(R.id.rowImage);

		ImageListener listener = ImageLoader.getImageListener(image,
				android.R.drawable.spinner_background /* 表示待ち時の画像 */,
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
					return bitmap.getByteCount() / 1024;
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
