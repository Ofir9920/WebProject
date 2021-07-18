import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class YnetRobot {

    static ArrayList <String> siteUrl;
    static Document site;
    static Map<String, Integer> map;

    public static void main(String[] args) throws IOException {

            siteUrl = new ArrayList <>();
            getYnetUrl();
            String words;
            map = new HashMap<>();
            String[] wordsSplit;


            for (String url : siteUrl) {
        site = Jsoup.connect(url).get();
        words = (site.getElementsByClass("mainTitle").text());
        words += (site.getElementsByClass("subTitle").text());
        words += (site.getElementsByClass("text_editor_paragraph rtl").text());
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

    private static void getYnetUrl() throws IOException {

        site = Jsoup.connect("https://www.ynet.co.il/home/0,7340,L-8,00.html").get() ;
        //link for the main article (contain 1)
        siteUrl.add(site.getElementsByClass("slotTitle").get(0).child(0)
                .attributes().get("href"));
        //links for teasers (contains 4 links)
        Element teasers = site.getElementsByClass("YnetMultiStripRowsComponenta colorBackground").get(0);
        for (Element mediaItems : teasers.getElementsByClass("mediaItems")) {
            siteUrl.add(mediaItems.child(0).child(0).attributes().get("href"));
        }
        //news1 (contains 2 links)
        Element newsWithImages = site.getElementsByClass("withImagePreview coupleItems").get(0);
        for (Element mediaItems : newsWithImages.getElementsByClass("mediaItems")) {
            siteUrl.add(mediaItems.child(0).child(0).attributes().get("href"));
        }
        //news2 (contains 8 links)
        Element news = site.getElementsByClass("MultiArticleComponenta1280").get(0);
        for (Element slotTitleSmall : news.getElementsByClass("slotTitle small")) {
            siteUrl.add(slotTitleSmall.child(0).attributes().get("href"));
        }






    }


}

