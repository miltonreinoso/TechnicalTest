package miltonreinoso.com.technicaltest.Activities;

import android.content.Intent;
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
    private TextView mToolbarTitleTxtV;
    public static final String ARTICLE_ID = "ARTICLE ID";


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

        setSupportActionBar(mToolbar);

        mFetch = new FetchAllTask();
        mFetch.execute(ARTICLE_CONTENT_URL);

        mArticlesRecyclerView = findViewById(R.id.articles_recycler_view);



    }

    @Override
    public void onResume() {
        super.onResume();
//        setupAdapter();
    }

    private void setupAdapter() {

        mArticlesAdapter =  new ArticlesAdapter(mArticlesList);
        mArticlesRecyclerView.setAdapter(mArticlesAdapter);
        mArticlesRecyclerView.setHasFixedSize(false);
        mArticlesRecyclerView.setLayoutManager(new GridLayoutManager(getApplication(), 2));
    }



    /**ViewHolder
     *
     */

    private class ArticlesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {




        private ImageView mArticleImageView;
        private TextView mItemDescriptionTxtV;
        private TextView mPriceTxtV;
        private TextView mListPriceTxtV;
        private TextView mDiscountTxtv;


        private ArticleContent.Items mArticle;

        public ArticlesViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mArticleImageView = itemView.findViewById(R.id.item_picture_imgv);
            mItemDescriptionTxtV = itemView.findViewById(R.id.details_title_item_txtv);
            mPriceTxtV = itemView.findViewById(R.id.details_price_txtv);
            mListPriceTxtV = itemView.findViewById(R.id.details_list_price_txtv);
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
            mArticle = article;
            Picasso.get()
                    .load("http:" + mArticle.getImageUrl())
                    .placeholder(R.drawable.ic_arrow_back)
                    .into(mArticleImageView);
            mItemDescriptionTxtV.setText(mArticle.getDescription());
            mPriceTxtV.setText("$"+mArticle.getPrice());
            mListPriceTxtV.setText("$"+mArticle.getListPrice());
            mDiscountTxtv.setText(mArticle.getDiscount());

        }

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
            View view = layoutInflater.inflate(R.layout.ads_item, parent, false);
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
                    Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)
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
