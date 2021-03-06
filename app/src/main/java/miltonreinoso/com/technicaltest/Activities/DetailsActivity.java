package miltonreinoso.com.technicaltest.Activities;

import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
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

import miltonreinoso.com.technicaltest.Adapters.ReviewsRecyclerAdapter;
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

    public ViewPager mPicturesViewPager;
    private TextView mNumberOfPicturesTxtv;
    private TextView mNumberOfVideosTxtv;
    private TextView mItemTitleTxtv;
    private RatingBar mRatingBar;
    private TextView mPriceTxtv;
    private TextView mListPriceTxtv;
    private TextView mDiscountTxtv;
    private TextView mAverageRating;
    private RatingBar mRatingBarGeneralReviews;
    private ImageView mBackToolbarTxtv;
    private RecyclerView mReviewRecyclerView;
    private ReviewsRecyclerAdapter mReviewsAdapter;


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
        mAverageRating = findViewById(R.id.general_rating_reviews_txtv);
        mRatingBarGeneralReviews = findViewById(R.id.star_rating_reviews_general);
        mReviewRecyclerView = findViewById(R.id.reviews_recycler_view);
        mBackToolbarTxtv = findViewById(R.id.details_back_toolbar_imgb);
        mBackToolbarTxtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        mFetch.execute(
                ARTICLE_DETAILS_URL + mArticleID,
                ARTICLE_DETAILS_URL + mArticleID + "/reviews" );
    }

    private void setupView () {



        String[] mImagesURLs = new String[mArticleDetails.getResources().getImages().size()];

        for (int index =0; index < mImagesURLs.length; index++){
            mImagesURLs [index] = "http:" + mArticleDetails.getResources().getImages().get(index).getUrl();
        }
        ViewPagerAdapter mViewPagerAdapter = new ViewPagerAdapter(this, mImagesURLs);
        mPicturesViewPager.setAdapter(mViewPagerAdapter);



        String price = "$" + mArticleDetails.getPrice();
        String listPrice = "$" + mArticleDetails.getList_price();
        String discount = mArticleDetails.getDiscount()+ "% OFF";
        String numberOfPictures = mArticleDetails.getResources().getImages().size()+"";
        String numberOfVideos = mArticleDetails.getResources().getVideos().size()+"";



        mItemTitleTxtv.setText(mArticleDetails.getDescription());
        mPriceTxtv.setText(price);

        if (!discount.contains("null") && mArticleDetails.getDiscount() != 0){

            mListPriceTxtv.setText(listPrice);
            mListPriceTxtv.setPaintFlags(mListPriceTxtv.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            mDiscountTxtv.setText(discount);
        }
        else {
            mListPriceTxtv.setVisibility(View.GONE);
            mDiscountTxtv.setVisibility(View.GONE);
        }

        mNumberOfPicturesTxtv.setText(numberOfPictures);
        mNumberOfVideosTxtv.setText(numberOfVideos);

        if (mArticleReviewsList != null){

            mRatingBar.setProgress((int) Math.round(mArticleReviewsList.get(0).getReview_statistics().getAverage_overall_rating() * 2));
            mRatingBarGeneralReviews.setProgress((int) Math.round(mArticleReviewsList.get(0).getReview_statistics().getAverage_overall_rating() * 2));
            mAverageRating.setText(mArticleReviewsList.get(0).getReview_statistics().getAverage_overall_rating() + "");
            mReviewsAdapter = new ReviewsRecyclerAdapter(mArticleReviewsList.get(0).getReviews());
            mReviewRecyclerView.setAdapter(mReviewsAdapter);
            mReviewRecyclerView.setHasFixedSize(false);
            mReviewRecyclerView.setLayoutManager(new LinearLayoutManager( getApplicationContext()));
        }
    }


    private class FetchDetailsTask extends AsyncTask<String,Void,String[]> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String[] doInBackground(String... params) {

            String [] bufferedStrings ={
                buildHTTPConnection(params[0]),
                buildHTTPConnection(params[1])
            };

            return bufferedStrings;

        }


        protected String buildHTTPConnection(String urlString){
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(urlString);
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
        protected void onPostExecute(String[] detailsStringURL ) {

            StringReader jsonStringDetails = new StringReader(detailsStringURL[0]);
            StringReader jsonStringReviews;
            try {
                    jsonStringReviews = new StringReader(detailsStringURL [1]);
                    }
            catch (Exception exception){
                jsonStringReviews = new StringReader("");
            }

            mArticleDetails = new Gson().fromJson(jsonStringDetails, ArticleDetails.class);
            mArticleReviews = new Gson().fromJson(jsonStringReviews, ArticleReviews.class);
            if (mArticleReviews!= null) mArticleReviewsList = mArticleReviews.getItems();
            setupView();
        }
    }
}

