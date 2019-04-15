package miltonreinoso.com.technicaltest.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import miltonreinoso.com.technicaltest.Models.ArticleReviews;
import miltonreinoso.com.technicaltest.R;

public class ReviewsRecyclerAdapter extends RecyclerView.Adapter<ReviewsRecyclerAdapter.ReviewsRecyclerViewHolder> {

    private List<ArticleReviews.Items.Reviews> mArticlesReviewList;
    private ArticleReviews.Items.Reviews mReviews;



    public ReviewsRecyclerAdapter (List<ArticleReviews.Items.Reviews> articleReviews){
        mArticlesReviewList = articleReviews;
    }

    @Override
    public ReviewsRecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

    LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());        //  Check Later
        View view = layoutInflater.inflate(R.layout.review_item, viewGroup , false);
        return new ReviewsRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewsRecyclerViewHolder holder, int position) {
        ArticleReviews.Items.Reviews reviews = mArticlesReviewList.get(position);
        holder.bindReviews(reviews);
    }

    @Override
    public int getItemCount() {
        return 3;   // "Hardcoded" to Restrain to only 3 views
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    public class ReviewsRecyclerViewHolder extends RecyclerView.ViewHolder {

        private RatingBar mRatingBar;
        private TextView mDateReviewTxtv;
        private TextView mReviewTitleTxtv;
        private TextView mReviewTextTxtv;

        public ReviewsRecyclerViewHolder(View itemView) {
            super(itemView);
            mRatingBar = itemView.findViewById(R.id.single_review_opinion_rating_bar);
            mDateReviewTxtv = itemView.findViewById(R.id.single_review_date_txtv);
            mReviewTitleTxtv = itemView.findViewById(R.id.single_review_title_txtv);
            mReviewTextTxtv = itemView.findViewById(R.id.single_review_text_txtv);
        }

        public void bindReviews (ArticleReviews.Items.Reviews reviews){
            mReviews = reviews;
            mRatingBar.setProgress(mReviews.getRating());
            mDateReviewTxtv.setText(mReviews.getSubmission_time()+"");
            mReviewTitleTxtv.setText(mReviews.getTitle());
            mReviewTextTxtv.setText(mReviews.getReview_text());
        }
    }
}
