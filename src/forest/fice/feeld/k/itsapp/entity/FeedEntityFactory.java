package forest.fice.feeld.k.itsapp.entity;

import java.io.StringReader;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import com.google.samples.apps.iosched.util.LogUtils;

public class FeedEntityFactory {

	public static FeedEntity getEntity(String jsonString) {
		FeedEntity feedEntity;
		try {
			LogUtils.LOGD("JSON", jsonString);
			Gson gson = new Gson();
			JsonReader reader = new JsonReader(new StringReader(jsonString));
			reader.setLenient(true);
			feedEntity = gson.fromJson(reader, FeedEntity.class);
		} catch (JsonSyntaxException e) {
			LogUtils.LOGE("FeedEntityFactory", e.toString());
			feedEntity = null;
		}

		return feedEntity;
	}

}
