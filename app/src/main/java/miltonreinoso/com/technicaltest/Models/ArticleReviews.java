package miltonreinoso.com.technicaltest.Models;

import java.util.List;

public class ArticleReviews {
    private final List<Items> items;

    public ArticleReviews(List<Items> items) {
        this.items = items;
    }

    public List<Items> getItems() {
        return items;
    }

    public static class Items {
        private final String id;

        private final ReviewStatistics review_statistics;

        private final List<Reviews> reviews;

        private final int total_review_count;

        public Items(String id, ReviewStatistics reviewStatistics, List<Reviews> reviews,
                     int total_review_count) {
            this.id = id;
            this.review_statistics = reviewStatistics;
            this.reviews = reviews;
            this.total_review_count = total_review_count;
        }

        public String getId() {
            return id;
        }

        public ReviewStatistics getReview_statistics() {
            return review_statistics;
        }

        public List<Reviews> getReviews() {
            return reviews;
        }

        public int getTotal_review_count() {
            return total_review_count;
        }

        public static class ReviewStatistics {
            private final double average_overall_rating;

            private final List<RatingDistribution> rating_distribution;

            public ReviewStatistics(double average_overall_rating,
                                    List<RatingDistribution> rating_distribution) {
                this.average_overall_rating = average_overall_rating;
                this.rating_distribution = rating_distribution;
            }

            public double getAverage_overall_rating() {
                return average_overall_rating;
            }

            public List<RatingDistribution> getRating_distribution() {
                return rating_distribution;
            }

            public static class RatingDistribution {
                private final int rating_value;

                private final int count;

                public RatingDistribution(int ratingValue, int count) {
                    this.rating_value = ratingValue;
                    this.count = count;
                }

                public int getRating_value() {
                    return rating_value;
                }

                public int getCount() {
                    return count;
                }
            }
        }

        public static class Reviews {
            private final String id;

            private final String usernickname;

            private final String title;

            private final String review_text;

            private final int rating;

            private final String submission_time;

            private final String product_id;

            public Reviews(String id, String usernickname, String title, String review_text,
                           int rating, String submission_time, String product_id) {
                this.id = id;
                this.usernickname = usernickname;
                this.title = title;
                this.review_text = review_text;
                this.rating = rating;
                this.submission_time = submission_time;
                this.product_id = product_id;
            }

            public String getId() {
                return id;
            }

            public String getUsernickname() {
                return usernickname;
            }

            public String getTitle() {
                return title;
            }

            public String getReview_text() {
                return review_text;
            }

            public int getRating() {
                return rating;
            }

            public String getSubmission_time() {
                return submission_time;
            }

            public String getProduct_id() {
                return product_id;
            }
        }
    }
}
