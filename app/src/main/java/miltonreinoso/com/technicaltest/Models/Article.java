package miltonreinoso.com.technicaltest.Models;

import java.util.UUID;

public class Article {

    private String mArticleID;
    private String mDesciption;
    private String mImageURL;
    private String mPrice;
    private String mListPrice;
    private String mDiscount;
    private UUID mId;


    public Article(String mArticleID, String mDesciption, String mImageURL, String mPrice, String mListPrice, String mDiscount){
        mId = UUID.randomUUID();
    }

    public String getmArticleID() {
        return mArticleID;
    }

    public String getmDesciption() {
        return mDesciption;
    }

    public String getmImageURL() {
        return mImageURL;
    }

    public String getmPrice() {
        return mPrice;
    }

    public String getmListPrice() {
        return mListPrice;
    }

    public String getmDiscount() {
        return mDiscount;
    }
}
