package morita.kazuaki.itsapp.entity;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class FeedEntityFactory {

	public static FeedEntity getEntity(String jsonString) {
		FeedEntity feedEntity;
		try {
			Gson gson = new Gson();
			feedEntity = gson.fromJson(jsonString, FeedEntity.class);
		} catch (JsonSyntaxException e) {
			feedEntity = null;
		}

		return feedEntity;
	}

}
