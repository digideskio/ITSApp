package morita.kazuaki.itsapp.entity;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class FeedEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4136992893063798700L;
	public Feed feed;

	public class Feed implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = -9085431754103835327L;

		public Label id;
		public Label icon;
		public Author author;
		public Label title;
		public Label updated;
		public Attributes[] link;
		public Entry[] entry;
		public Label rights;
	}

	public class Author implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 3942535931197698884L;
		public Label uri;
		public Label name;
	}

	public class Attributes implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 2382304265873850255L;
		public String type = "";
		public String href = "";
		public String amount = "";
		public String currency = "";
		public String label = "";
		public String scheme = "";
		public String term = "";
		@SerializedName("im:id")
		public String id = "";
	}

	public class LabelAndAttributes implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 2180549900767081084L;
		public Attributes attributes;
		@SerializedName("label")
		public String text = "";
	}

	public class Entry implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = -860801943212993324L;

		@SerializedName("im:artist")
		public LabelAndAttributes artist;
		public LabelAndAttributes id;
		@SerializedName("im:price")
		public LabelAndAttributes price;
		public Label summary;
		public LabelAndAttributes category;
		public Label title;
		@SerializedName("im:releaseDate")
		public LabelAndAttributes releaseDate;
		@SerializedName("im:image")
		public LabelAndAttributes[] image;
		public LabelAndAttributes link;
		public Attributes rights;
		@SerializedName("im:contentType")
		public Attributes contentType;
		@SerializedName("im:name")
		public Label name;
	}

	public class Label implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 2006115764264656313L;
		@SerializedName("label")
		public String text = "";
	}

}
