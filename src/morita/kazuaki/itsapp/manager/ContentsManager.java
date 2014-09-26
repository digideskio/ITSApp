package morita.kazuaki.itsapp.manager;

import java.util.ArrayList;
import java.util.List;

public class ContentsManager {
	
	private static ContentsManager manager;
	
	public static List<ContentsManager.Ranking> getContentsList() {
		if(manager == null) {
			manager = new ContentsManager();
		}
		
		
		List<ContentsManager.Ranking> rankingList = new ArrayList<ContentsManager.Ranking>();
		
		//AppStore
		Ranking ranking1 = manager.new Ranking();
		ranking1.url = "https://itunes.apple.com/jp/rss/topgrossingapplications/limit=100/json";
		ranking1.title = "iOS App Store";
		rankingList.add(ranking1);
		
		Ranking ranking2 = manager.new Ranking();
		ranking2.url = "https://itunes.apple.com/jp/rss/topfreemacapps/limit=100/json";
		ranking2.title = "Mac App Store";
		rankingList.add(ranking2);
		
		return rankingList;
		
	}
	
	public class Ranking{
		private String url;
		private String title;
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
	}

}
