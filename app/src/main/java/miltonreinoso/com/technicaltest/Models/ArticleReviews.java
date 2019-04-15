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

        private final ReviewStatistics reviewStatistics;

        private final List<Reviews> reviews;

        private final int totalReviewCount;

        public Items(String id, ReviewStatistics reviewStatistics, List<Reviews> reviews,
                     int totalReviewCount) {
            this.id = id;
            this.reviewStatistics = reviewStatistics;
            this.reviews = reviews;
            this.totalReviewCount = totalReviewCount;
        }

        public String getId() {
            return id;
        }

        public ReviewStatistics getReviewStatistics() {
            return reviewStatistics;
        }

        public List<Reviews> getReviews() {
            return reviews;
        }

        public int getTotalReviewCount() {
            return totalReviewCount;
        }

        public static class ReviewStatistics {
            private final double averageOverallRating;

            private final List<RatingDistribution> ratingDistribution;

            public ReviewStatistics(double averageOverallRating,
                                    List<RatingDistribution> ratingDistribution) {
                this.averageOverallRating = averageOverallRating;
                this.ratingDistribution = ratingDistribution;
            }

            public double getAverageOverallRating() {
                return averageOverallRating;
            }

            public List<RatingDistribution> getRatingDistribution() {
                return ratingDistribution;
            }

            public static class RatingDistribution {
                private final int ratingValue;

                private final int count;

                public RatingDistribution(int ratingValue, int count) {
                    this.ratingValue = ratingValue;
                    this.count = count;
                }

                public int getRatingValue() {
                    return ratingValue;
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

            private final String reviewText;

            private final int rating;

            private final String submissionTime;

            private final String productId;

            public Reviews(String id, String usernickname, String title, String reviewText,
                           int rating, String submissionTime, String productId) {
                this.id = id;
                this.usernickname = usernickname;
                this.title = title;
                this.reviewText = reviewText;
                this.rating = rating;
                this.submissionTime = submissionTime;
                this.productId = productId;
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

            public String getReviewText() {
                return reviewText;
            }

            public int getRating() {
                return rating;
            }

            public String getSubmissionTime() {
                return submissionTime;
            }

            public String getProductId() {
                return productId;
            }
        }
    }
}
