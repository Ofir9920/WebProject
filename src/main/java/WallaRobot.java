import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WallaRobot {

    static ArrayList <String> siteUrl;
    static Document site;
    static Map<String, Integer> map;


    public static void main(String[] args) throws IOException {

        siteUrl = new ArrayList<>();
        getWallaUrl();
        String words;
        map = new HashMap<>();
        String[] wordsSplit;

        for (String url: siteUrl) {
            site = Jsoup.connect(url).get();
            Element siteArticles = site.getElementsByClass("item-main-content").get(0);
            //main title
            words = (siteArticles.getElementsByTag("h1").text());
            //sub title
            words += (siteArticles.getElementsByTag("p").get(0).text());
            //body
            words += (siteArticles.getElementsByClass("article-content").text());
            wordsSplit = words.split(" ");
            for (String word: wordsSplit) {
                if (map.containsKey(word)) {
                    map.put(word, map.get(word) + 1);
                } else {
                    map.put(word,1);
                }
            }

        }
        System.out.println(map.size());
    }


    public static void getWallaUrl () throws IOException {

        site = Jsoup.connect("https://www.walla.co.il/").get();


        //link for the main article (1)
        siteUrl.add(site.getElementsByClass("with-roof with-timer").get(0)
                .child(0).attributes().get("href"));


        //links for the teasers (8)
        Element news = site.getElementsByClass("main-taste").get(0);
        for (Element li : news.getElementsByTag("li")) {
            siteUrl.add(li.child(0).attributes().get("href"));
        }

}

}
