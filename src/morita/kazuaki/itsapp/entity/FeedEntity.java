package morita.kazuaki.itsapp.entity;

import com.google.gson.annotations.SerializedName;

public class FeedEntity {

	public Feed feed;

	public class Feed {
		public Label id;
		public Label icon;
		public Author author;
		public Label title;
		public Label updated;
		public Attributes[] link;
		public Entry[] entry;
		public Label rights;
	}

	public class Author {
		public Label uri;
		public Label name;
	}

	public class Attributes {
		public String type;
		public String href;
		public String amount;
		public String currency;
	}

	public class LabelAndAttributes {
		public Attributes attributes;
		@SerializedName("label")
		public String text;
	}

	public class Entry {
		@SerializedName("im:artist")
		public LabelAndAttributes artist;
		public LabelAndAttributes id;
		@SerializedName("im:price")
		public LabelAndAttributes price;
		public Label summary;
		public Attributes category;
		public Label title;
		@SerializedName("im:releaseDate")
		public LabelAndAttributes releaseDate;
		@SerializedName("im:image")
		public LabelAndAttributes[] image;
		public Attributes link;
		public Attributes rights;
		@SerializedName("im:contentType")
		public Attributes contentType;
		@SerializedName("im:name")
		public Label name;
	}

	public class Label {
		@SerializedName("label")
		public String text;
	}

}
