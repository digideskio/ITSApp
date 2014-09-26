package morita.kazuaki.itsapp.manager;

import java.util.ArrayList;
import java.util.List;

public class ContentsManager {
	
	public enum ENUM_TYPE{IOS, MAC
	}
	
	private static ContentsManager manager;
	
	public static List<ContentsManager.Ranking> getContentsList(ENUM_TYPE type){
		switch (type) {
		case IOS:
			return getIOSContentsList();
		case MAC:
		default:
			return getMacContentsList();
		}
	}
	
	
	public static List<ContentsManager.Ranking> getIOSContentsList() {
		if(manager == null) {
			manager = new ContentsManager();
		}
		List<ContentsManager.Ranking> rankingList = new ArrayList<ContentsManager.Ranking>();
		
		rankingList.add(createRanking("https://itunes.apple.com/jp/rss/topgrossingapplications/limit=100/json", "iOS App トップセールス App"));
		rankingList.add(createRanking("https://itunes.apple.com/jp/rss/topfreeapplications/limit=100/json", "トップ無料 App"));
		rankingList.add(createRanking("https://itunes.apple.com/jp/rss/toppaidapplications/limit=100/json", "トップ有料 App"));
		rankingList.add(createRanking("https://itunes.apple.com/jp/rss/newapplications/limit=100/json", "新規 App" ));
		rankingList.add(createRanking("https://itunes.apple.com/jp/rss/newfreeapplications/limit=100/json", "新規 無料 App"));
		rankingList.add(createRanking("https://itunes.apple.com/jp/rss/newpaidapplications/limit=100/json", "新規 有料 App"));
		
		return rankingList;
	}
	
	public static List<ContentsManager.Ranking> getMacContentsList() {
		if(manager == null) {
			manager = new ContentsManager();
		}
		List<ContentsManager.Ranking> rankingList = new ArrayList<ContentsManager.Ranking>();
		
		rankingList.add(createRanking("https://itunes.apple.com/jp/rss/topfreemacapps/limit=100/json", "Top Free Mac Apps"));
		rankingList.add(createRanking("https://itunes.apple.com/jp/rss/topgrossingmacapps/limit=100/json", "Top Grossing mac Apps"));
		rankingList.add(createRanking("https://itunes.apple.com/jp/rss/topmacapps/limit=100/json", "Top Mac Apps"));
		rankingList.add(createRanking("https://itunes.apple.com/jp/rss/toppaidmacapps/limit=100/json", "Top Paid Mac Apps"));
		
		return rankingList;
	}
	
	private static Ranking createRanking(String url, String title){
		Ranking rankiing = manager.new Ranking();
		rankiing.url = url;
		rankiing.title = title;
		return rankiing;
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
