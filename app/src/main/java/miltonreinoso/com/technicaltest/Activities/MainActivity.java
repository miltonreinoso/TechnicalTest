package miltonreinoso.com.technicaltest.Activities;

import android.content.Intent;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.novoda.merlin.Connectable;
import com.novoda.merlin.Disconnectable;
import com.novoda.merlin.Merlin;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import miltonreinoso.com.technicaltest.Models.ArticleContent;
import miltonreinoso.com.technicaltest.R;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    public static final String ARTICLE_ID = "ARTICLE ID";

    private Merlin mMerlin;


    private static final String ARTICLE_CONTENT_URL= "http://garbarino-mock-api.s3-website-us-east-1.amazonaws.com/products/";

    private RecyclerView mArticlesRecyclerView;
    private ArticlesAdapter mArticlesAdapter;
    private List<ArticleContent.Items> mArticlesList;
    private FetchAllTask mFetch;
    private ArticleContent mArticleContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Merlin instance to handle conection related problems with fetching data
        mMerlin = new Merlin.Builder().withConnectableCallbacks()
                .withDisconnectableCallbacks()
                .build(this);
        mMerlin.bind();
        networkingStatusListener();

        setSupportActionBar(mToolbar);

        mArticlesRecyclerView = findViewById(R.id.articles_recycler_view);
    }

    @Override
    protected void onDestroy() {
        cancelTask();
        mMerlin.unbind();
        super.onDestroy();
    }

    private void setupAdapter() {

        mArticlesAdapter =  new ArticlesAdapter(mArticlesList);
        mArticlesRecyclerView.setAdapter(mArticlesAdapter);
        mArticlesRecyclerView.setHasFixedSize(false);
        mArticlesRecyclerView.setLayoutManager(new GridLayoutManager(getApplication(), 2));
    }

    public void networkingStatusListener() {
        mMerlin.registerDisconnectable(new Disconnectable() {
            @Override
            public void onDisconnect() {
                Log.d("Conman Test", "onLost");
                cancelTask();
            }
        });
        mMerlin.registerConnectable(new Connectable() {
            @Override
            public void onConnect() {
                mFetch = new FetchAllTask();
                mFetch.execute(ARTICLE_CONTENT_URL);

                Log.d("Conman Test", "onAvailable");
            }
        });
    }

    private void cancelTask() {
        if (mFetch != null && !mFetch.isCancelled()) {
            mFetch.cancel(true);
        }
        mFetch = null;
    }


    /**Adapter
     *
     */
    private class ArticlesAdapter extends RecyclerView.Adapter<ArticlesViewHolder>  {
        private List<ArticleContent.Items> mArticleList;

        public ArticlesAdapter(List<ArticleContent.Items> articleList) {
            mArticleList = articleList;
        }
        @Override
        public ArticlesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getApplication());
            View view = layoutInflater.inflate(R.layout.article_item, parent, false);
            return new ArticlesViewHolder(view);
        }
        @Override
        public void onBindViewHolder(ArticlesViewHolder holder, int position) {
            ArticleContent.Items mArticle = mArticlesList.get(position);
            holder.bindArticle(mArticle);
        }
        @Override
        public int getItemViewType(int position) {
            return  0;
        }
        @Override
        public int getItemCount() {
            return mArticlesList.size();
        }
    }



    /**ViewHolder
     *
     */
    private class ArticlesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ArticleContent.Items mArticle;

        private ImageView mArticleImageView;
        private TextView mItemDescriptionTxtV;
        private TextView mPriceTxtV;
        private TextView mListPriceTxtv;
        private TextView mDiscountTxtv;


        public ArticlesViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mArticleImageView = itemView.findViewById(R.id.item_picture_imgv);
            mItemDescriptionTxtV = itemView.findViewById(R.id.details_title_item_txtv);
            mPriceTxtV = itemView.findViewById(R.id.details_price_txtv);
            mListPriceTxtv = itemView.findViewById(R.id.details_list_price_txtv);
            mDiscountTxtv = itemView.findViewById(R.id.details_discount_txtv);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "Will show more details on new activity", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
            intent.putExtra(ARTICLE_ID, mArticle.getId());
            startActivity(intent);
        }

        public void bindArticle(ArticleContent.Items article) {

            // Check if article will have a valid price and description, or will not bind it
            if ((article.getPrice() != "0" && !article.getPrice().contains("null") &&
                    article.getDescription() != null && article.getDescription().length()>0)) {
                mArticle = article;
                Picasso.get()
                        .load("http:" + mArticle.getImageUrl())
                        .placeholder(R.drawable.ic_arrow_back)
                        .into(mArticleImageView);
                mItemDescriptionTxtV.setText(mArticle.getDescription());

                String price = "$" + mArticle.getPrice();
                String listPrice = "$" + mArticle.getList_price();
                String discount = mArticle.getDiscount() + "% OFF";

                if (!discount.contains("null") && !mArticle.getDiscount().equals("0")){
                    mListPriceTxtv.setText(listPrice);
                    mDiscountTxtv.setText(discount);
                    mListPriceTxtv.setPaintFlags(mListPriceTxtv.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    mListPriceTxtv.setVisibility(View.GONE);
                    mDiscountTxtv.setVisibility(View.GONE);
                }

                mPriceTxtV.setText(price);
            }
        }
    }



    /** Fetching AsyncTask
     *
     */
    private class FetchAllTask extends AsyncTask<String,Void,String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line+"\n");
                }

                return buffer.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {

            StringReader jsonString = new StringReader(s);
            mArticleContent = new Gson().fromJson(jsonString, ArticleContent.class);
            mArticlesList = mArticleContent.getItems();
            setupAdapter();
        }
    }
}
