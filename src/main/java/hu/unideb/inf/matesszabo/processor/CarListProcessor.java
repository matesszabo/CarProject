package hu.unideb.inf.matesszabo.processor;

import hu.unideb.inf.matesszabo.model.ResultItem;
import hu.unideb.inf.matesszabo.model.ResultList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by mates on 2018. 01. 03..
 */
public class CarListProcessor {

    private static final String BASE_URI = "https://ipon.hu";
    private Element element;
    private Document doc;
    private Integer maxItems;

    public CarListProcessor() {
    }

    public ResultList parse(Document doc, String size) throws IOException {
        ResultList resultList = null;
        maxItems=Integer.parseInt(size);
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
        for (Element element : doc.select("div.vehicle-card small")) {
            ResultItem ri = new ResultItem();


            try {
                ri.setName(element.select("p.h4 > a > span[1]").get(0).attr("abs:href").trim().split("\\?")[0]);
            } catch(Exception e) {
                throw new IOException("Malformed document");
            }

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
        */items.add(ri);}

        return items;
    }

    private Document getNextPage(Document doc) throws IOException {
        String nextPage = null;
        try {
            nextPage = doc.select("ul.pagination > li:nth-last-child(2)").get(0).attr("abs:href");
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
