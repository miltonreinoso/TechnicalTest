package miltonreinoso.com.technicaltest.Utils;

import java.util.ArrayList;
import java.util.List;

import miltonreinoso.com.technicaltest.Models.Article;

public class ArticlesLab {

    private static ArticlesLab sInstance;
    private static List<Article> sArticleList;

    private ArticlesLab() {

    }

    public static ArticlesLab getClient(){
        if (sInstance == null) {
            sInstance = new ArticlesLab();
            sArticleList = new ArrayList<>();

            sArticleList.add(new Article("asd", "","","","",""));
            sArticleList.add(new Article("asd", "","","","",""));
            sArticleList.add(new Article("asd", "","","","",""));
            sArticleList.add(new Article("asd", "","","","",""));
        }

        return sInstance;

    }

    public void setsArticleList(List<Article> articleList) {
        sArticleList = articleList;
    }

    public List<Article> getsArticleList() {
        return sArticleList;
    }
}

