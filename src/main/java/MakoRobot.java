import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MakoRobot extends BaseRobot {

    private ArrayList <String> siteUrl;
    private Document site;
    private Map<String, Integer> map = new HashMap<>();


    public MakoRobot() {
        super("https://www.mako.co.il/");
        this.siteUrl = new ArrayList<>();
        try {
            this.site = Jsoup.connect(getRootWebsiteUrl()).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        getMakoUrl();
    }



    public String getWordsArticle(){
        String words ="";
        for (String url : siteUrl) {
            try {
                site = Jsoup.connect(url).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Element siteArticles = site.getElementById("article-wrap");
            //main title
            words = (siteArticles.getElementsByTag("h1").text());
            //sub title
            words += (siteArticles.getElementsByTag("h2").text());
            //body
            words += (site.getElementsByClass("article-body").text());
            String[] wordsSplit = words.split(" ");
            insertMap(wordsSplit);
            return words;
        }



    }
    public void insertMap (String [] wordsSplit){
        for (String word : wordsSplit) {
            if (map.containsKey(word)) {
                map.put(word, map.get(word) + 1);
            } else {
                map.put(word, 1);
            }
        }
    }

    public void getMakoUrl ()  {
        try {
            site = Jsoup.connect("https://www.mako.co.il/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String url = "https://www.mako.co.il/";

        //links for  main article (1) + teasers (4)
        for (Element teasersLinks : site.getElementsByClass("slider_image_inside")) {
            siteUrl.add(url + teasersLinks.child(0).attributes().get("href"));
        }
        //links for news (5)
        Element teasers = site.getElementsByClass("mako_main_portlet_group_container_td side_bar_width").get(0);
        Element news = teasers.getElementsByClass("mako_main_portlet_container").get(0);

        for (Element element : news.getElementsByClass("element")) {
            String element1 = element.child(0).child(0).attributes().get("href");
            siteUrl.add(url + element.child(0).child(0).attributes().get("href"));








        }


    }


    @Override
    public Map<String, Integer> getWordsStatistics() {
        return null;
    }

    @Override
    public int countInArticlesTitles(String text) {
        int count = 0 ;
        return count;
    }

    @Override
    public String getLongestArticleTitle() {
        String ofir = getWordsArticle();
        return null;
    }
}
