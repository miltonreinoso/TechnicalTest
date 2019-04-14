package miltonreinoso.com.technicaltest.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import miltonreinoso.com.technicaltest.Models.Article;
import miltonreinoso.com.technicaltest.R;
import miltonreinoso.com.technicaltest.Utils.ArticlesLab;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextView mToolbarTitleTxtV;

    private RecyclerView mArticlesRecyclerView;
    private ArticlesAdapter mArticlesAdapter;
    private List<Article> mArticlesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mArticlesRecyclerView = findViewById(R.id.articles_recycler_view);
        mArticlesRecyclerView.setHasFixedSize(false);
        mArticlesRecyclerView.setLayoutManager(new GridLayoutManager(getApplication(), 2));
    }

    @Override
    public void onResume() {
        super.onResume();
        setupAdapter();
    }

    private void setupAdapter() {
        mArticlesList = ArticlesLab.getClient().getsArticleList();
        mArticlesAdapter =  new ArticlesAdapter(mArticlesList);
        mArticlesRecyclerView.setAdapter(mArticlesAdapter);
    }




    /**ViewHolder
     *
     */

    private class ArticlesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        private ImageView mArticleImageView;
        private Article mArticle;

        public ArticlesViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mArticleImageView = itemView.findViewById(R.id.item_picture_imgv);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "asdasd", Toast.LENGTH_SHORT).show();
        }

        public void bindArticle(Article article) {
            mArticle = article;
        }

    }

    /**Adapter
     *
     */
    private class ArticlesAdapter extends RecyclerView.Adapter<ArticlesViewHolder>  {
        private List<Article> mArticleList;

        public ArticlesAdapter(List<Article> articleList) {
            mArticleList = articleList;
        }

        @Override
        public ArticlesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getApplication());
            View view = null;
            switch (viewType){
                case 0:
                    view = layoutInflater
                            .inflate(R.layout.ads_item, parent, false);

            }

            return new ArticlesViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ArticlesViewHolder holder, int position) {
            Article mArticle = mArticlesList.get(position);
            holder.bindArticle(mArticle);
        }

        @Override
        public int getItemViewType(int position) {

            return  0;
        }

        @Override
        public int getItemCount() {
            return mArticleList.size();
        }
    }

}
