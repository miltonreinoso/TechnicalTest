package miltonreinoso.com.technicaltest.Activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import miltonreinoso.com.technicaltest.Adapters.ViewPagerAdapter;
import miltonreinoso.com.technicaltest.Models.ArticleDetails;
import miltonreinoso.com.technicaltest.Models.ArticleReviews;
import miltonreinoso.com.technicaltest.R;

public class DetailsActivity extends AppCompatActivity {

    private final String ARTICLE_DETAILS_URL = "http://garbarino-mock-api.s3-website-us-east-1.amazonaws.com/products/";

    private FetchDetailsTask mFetch;
    private ArticleDetails mArticleDetails;
    private ArticleReviews mArticleReviews;
    private List<ArticleReviews.Items> mArticleReviewsList;
    private String mArticleID;

    private TextView mNumberOfPicturesTxtv;
    private TextView mNumberOfVideosTxtv;
    public TextView mItemTitleTxtv;
    private RatingBar mRatingBar;
    private TextView mPriceTxtv;
    private TextView mListPriceTxtv;
    public TextView mDiscountTxtv;

    private TextView mReview;
    public ViewPager mPicturesViewPager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);



        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                mArticleID = null;
            } else {
                mArticleID= extras.getString(MainActivity.ARTICLE_ID);
            }
        } else {
            mArticleID= (String) savedInstanceState.getSerializable( MainActivity.ARTICLE_ID);
        }

        mFetch = new FetchDetailsTask();

        mPicturesViewPager = findViewById(R.id.pictures_view_pager);
        mNumberOfPicturesTxtv = findViewById(R.id.number_of_pictures_txtv);
        mNumberOfVideosTxtv = findViewById(R.id.number_of_videos_txtv);
        mItemTitleTxtv = findViewById(R.id.details_title_item_txtv);
        mRatingBar = findViewById(R.id.star_rating);
        mPriceTxtv = findViewById(R.id.details_price_txtv);
        mListPriceTxtv = findViewById(R.id.details_list_price_txtv);
        mDiscountTxtv = findViewById(R.id.details_discount_txtv);




        mFetch.execute(ARTICLE_DETAILS_URL + mArticleID, ARTICLE_DETAILS_URL + mArticleID + "/reviews" );


    }

    private void setupView () {

        String[] mImagesURLs = new String[mArticleDetails.getResources().getImages().size()];

        for (int index =0; index < mImagesURLs.length; index++){
            mImagesURLs [index] = "http:" + mArticleDetails.getResources().getImages().get(index).getUrl();
        }
        ViewPagerAdapter mViewPagerAdapter = new ViewPagerAdapter(this, mImagesURLs);
        mPicturesViewPager.setAdapter(mViewPagerAdapter);

        mNumberOfPicturesTxtv.setText(mArticleDetails.getResources().getImages().size()+"");
        mNumberOfVideosTxtv.setText(mArticleDetails.getResources().getVideos().size()+"");

        mItemTitleTxtv.setText(mArticleDetails.getDescription());
        String price = "$" + mArticleDetails.getPrice();
        mPriceTxtv.setText(price);
        String listPrice = "$" + mArticleDetails.getPrice();
        mListPriceTxtv.setText(listPrice);
        String discount = mArticleDetails.getDiscount()+ "% de descuento";
        mDiscountTxtv.setText(discount);

//        mReview.setText(mArticleReviewsList.get(0).getReviews().get(0).getReviewText());

    }



    private class FetchDetailsTask extends AsyncTask<String,Void,String> {

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
        protected void onPostExecute(String detailsStringURL ) {

            StringReader jsonStringDetails = new StringReader(detailsStringURL);
//            StringReader jsonStringReviews = new StringReader(detailsStringURL+"/reviews");

            mArticleDetails = new Gson().fromJson(jsonStringDetails, ArticleDetails.class);
//            mArticleReviews = new Gson().fromJson(jsonStringReviews, ArticleReviews.class);
//            mArticleReviewsList = mArticleReviews.getItems();
            setupView();
        }
    }
}

