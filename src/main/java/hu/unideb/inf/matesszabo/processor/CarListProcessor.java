package hu.unideb.inf.matesszabo.processor;

import hu.unideb.inf.matesszabo.model.Image;
import hu.unideb.inf.matesszabo.model.Price;
import hu.unideb.inf.matesszabo.model.ResultItem;
import hu.unideb.inf.matesszabo.model.ResultList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by mates on 2018. 01. 03..
 */
public class CarListProcessor {

    private Element element;
    private Document doc;
    private Integer maxItems;
    private String s;

    public CarListProcessor() {
    }

    public ResultList parse(Document doc, String size) throws IOException {
        ResultList resultList = null;
        //maxItems=Integer.parseInt(size);
        maxItems=50;
        try {
            this.doc = doc;
            resultList = doProcess(doc);

        } catch (Exception e) {
            throw new IOException("Malformed document");
        }

        return resultList;
    }



    private int getTotalItems(Document doc) throws IOException {
        /*if (doc.select("#search-info > span.search-count").isEmpty()) {
            return doc.select("div.book-item").size();
        }
        int totalItems = 0;
        try {
            totalItems = Integer.parseInt(doc.select("#search-info > span.search-count").get(0).text().trim());
        } catch(Exception e) {
            throw new IOException("Malformed document");
        }*/
        return 30;
    }

    private List<ResultItem> extractItems(Document doc) throws IOException {
        List<ResultItem> items = new LinkedList<ResultItem>();
        for (Element element : doc.select("div.vehicle-card")) {

            ResultItem ri = new ResultItem();


            try {
                ri.setName(element.select("p.h4 > a > span").text().trim());
            } catch(Exception e) {
                throw new IOException("Malformed document");
            }


            try {
                ri.setUri(element.select("div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > p:nth-child(1) > a:nth-child(1)").get(0).attr("abs:href"));
            } catch(Exception e) {
                throw new IOException("Malformed document");
            }

            try {
                ri.setMiles(Integer.parseInt(element.select("div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > ul:nth-child(2) > li:nth-child(1)").text().trim().substring(9).split(" ")[0].replaceAll(",","")));
            } catch(Exception e) {
                throw new IOException("Malformed document");
            }

            try {
                ri.setLocation(element.select("div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > ul:nth-child(2) > li:nth-child(2)").text().trim().substring(10));
            } catch(Exception e) {
                throw new IOException("Malformed document");
            }

            try {
                ri.setExteriorColor(element.select("div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > ul:nth-child(2) > li:nth-child(3)").text().trim().substring(10));
            } catch(Exception e) {
                throw new IOException("Malformed document");
            }

            try {
                ri.setVIN(ri.getUri().split("/")[5]);
            } catch(Exception e) {
                throw new IOException("Malformed document");
            }

            try {
                ri.setPrice(new Price(new BigDecimal(Integer.parseInt(element.select("p.price").text().trim().replaceAll("\\$","").replaceAll(",",""))),"usd"));
                //div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > p:nth-child(2)
            } catch(Exception e) {
                throw new IOException("Malformed document");
            }

            try {
                ri.setImage(new Image(element.select("img.vehicle-thumbnail").attr("src")));
                //div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > p:nth-child(2)
            } catch(Exception e) {
                throw new IOException("Malformed document");
            }

            /*try {
                s=element.select("div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > ul:nth-child(2) > li:nth-child(4)").text().trim().substring(0,4);
                //System.out.println("!!!!!!!!!!!!!!!!!!!!!!!   5try   !!!!!!!!!!!!!!!!!!!!!!!!"+s+" "+element.select("div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > ul:nth-child(2) > li:nth-child(4)").text().trim().substring(10));

                if(s.contains("Int")){
                    ri.setVIN(element.select("div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > ul:nth-child(2) > li:nth-child(4)").text().trim().substring(4));
                    ri.setInteriorColor("a");
                } else {
                    ri.setInteriorColor(element.select("div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > ul:nth-child(2) > li:nth-child(4)").text().trim().substring(10));
                    //ri.setVIN(element.select("div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > ul:nth-child(2) > li:nth-child(5)").text().trim().substring(4));
                }
                ri.setInteriorColor(element.select("div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > ul:nth-child(2) > li:nth-child(4)").text().trim().substring(10));
            } catch(Exception e) {
                throw new IOException("Malformed document");
            }*/

            /*try {
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!   5try   !!!!!!!!!!!!!!!!!!!!!!!!"+element.select("div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > ul:nth-child(2) > li:nth-child(5)").text().trim());
                ri.setVIN(element.select("div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > ul:nth-child(2) > li:nth-child(4)").text().trim());
                if(!ri.getVIN().isEmpty() && ri.getVIN()!=null && ri.getVIN().length()>4)
                ri.setVIN(ri.getExteriorColor().substring(4));
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!   5tryvege   !!!!!!!!!!!!!!!!!!!!!!!!"+items.size());
            } catch(Exception e) {
                throw new IOException("Malformed document");
            }*/
            items.add(ri);
           /* String uri = null;
            try {
                uri = element.select("div.item-info > h3.title > a").get(0).attr("abs:href").trim().split("\\?")[0];
            } catch(Exception e) {
                throw new IOException("Malformed document");
            }
            logger.debug("Uri: {}", uri);

            String title = null;
            try {
                title = element.select("div.item-info > h3.title > a").get(0).text().trim();
            } catch(Exception e) {
                throw new IOException("Malformed document");
            }
            logger.debug("Title: {}", title);

            String author = null;
            try {
                author = element.select("div.item-info > p.author").get(0).text().trim();
            } catch(Exception e) {
                logger.warn("No author provided");
            }
            logger.debug("Author: {}", author);

            LocalDate date = null;
            try {
                date = LocalDate.parse(
                        element.select("div.item-info > p.published").get(0).text().trim(),
                        formatter
                );
            } catch(Exception e) {
                //logger.warn("No publication date provided");
            }
            //logger.debug("Date: {}", date);

            String format = null;
            try {
                format = element.select("div.item-info > p.format").get(0).text().trim();
            } catch(Exception e) {
                throw new IOException("Malformed document");
            }
            //logger.debug("Format: {}", format);

            SearchResultItem item = new SearchResultItem(uri, author, title, date, format);
            items.add(item);
        */}

        return items;
    }

    private Document getNextPage(Document doc) throws IOException {
        String nextPage = null;
        try {
            //nextPage = doc.select("ul.pagination > li:nth-last-child(2)").get(0).attr("abs:href");
            nextPage = doc.select(".pagination > li:nth-last-child(2) > a:nth-child(1)").get(0).attr("abs:href");
            //logger.info("Next page: {}", nextPage);

        } catch(Exception e) {
            // no more pages
        }
        return nextPage != null ? Jsoup.connect(nextPage).userAgent("Mozilla").get() : null;
    }

    /*public SearchResults parse(Document doc) throws IOException {
        List<SearchResultItem> items = new LinkedList<SearchResultItem>();
        int totalItems = getTotalItems(doc);
        logger.info("Total number of items: {}", totalItems);
        loop:		while (totalItems != 0 && doc != null) {
            for (SearchResultItem item : extractItems(doc)) {
                if (0 <= maxItems && maxItems <= items.size()) {
                    break loop;
                }
                items.add(item);
            }
            if (0 <= maxItems && maxItems <= items.size()) break;
            doc = getNextPage(doc);
        }
        return new SearchResults(totalItems, 1, items.size(), items);
    }*/

    private ResultList doProcess(Document doc) throws IOException {
        List<ResultItem> items = new LinkedList<ResultItem>();

        int totalItems = getTotalItems(doc);
        loop:   while (totalItems != 0 && doc != null) {
            for (ResultItem item : extractItems(doc)) {

                if (0 <= maxItems && maxItems <= items.size()) {
                    break loop;
                }
                items.add(item);
            }
            if (0 <= maxItems && maxItems <= items.size()) break;

            doc = getNextPage(doc);
        }
        return new ResultList("", 1, items.size(), items);
        //return null;
    }

}
