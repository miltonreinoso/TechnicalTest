package miltonreinoso.com.technicaltest.Activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

import miltonreinoso.com.technicaltest.Models.ArticleDetails;
import miltonreinoso.com.technicaltest.Models.ArticleReviews;
import miltonreinoso.com.technicaltest.R;

public class Details extends AppCompatActivity {

    private final String ARTICLE_DETAILS_URL = "http://garbarino-mock-api.s3-website-us-east-1.amazonaws.com/products/";
    private final String ARTICLE_REVIEWS_URL = "http://garbarino-mock-api.s3-website-us-east-1.amazonaws.com/products/%7Bid%7D/reviews";

    private FetchDetailsTask mFetch;

    private TextView mDetail;
    private ArticleDetails mArticleDetails;
    private ArticleReviews mArticleReviews;
    private String mArticleID;
    String TAG = "TAG";


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
        Log.d(TAG, "mArticleID: " + mArticleID);
        Log.d(TAG, "ARTICLE_DETAILS_URL: " + ARTICLE_DETAILS_URL);

        mDetail  = findViewById(R.id.detailtxt);

        mFetch.execute(ARTICLE_DETAILS_URL + mArticleID );


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
        protected void onPostExecute(String s) {

            StringReader jsonString = new StringReader(s);
            mArticleDetails = new Gson().fromJson(jsonString, ArticleDetails.class);

            mDetail.setText(mArticleDetails.getDescription());

        }
    }

}

