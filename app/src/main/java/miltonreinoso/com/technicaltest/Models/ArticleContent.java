package miltonreinoso.com.technicaltest.Models;

import java.util.List;

@SuppressWarnings("all")
public class ArticleContent {
    private final List<Items> items;

    public ArticleContent(List<Items> items) {
        this.items = items;
    }

    public List<Items> getItems() {
        return items;
    }

    public static class Items {
        private final String id;

        private final String description;

        private final String image_url;

        private final String price;

        private final String list_price;

        private final String discount;

        public Items(String id, String description, String image_url, String price, String list_price,
                     String discount) {
            this.id = id;
            this.description = description;
            this.image_url = image_url;
            this.price = price;
            this.list_price = list_price;
            this.discount = discount;
        }

        public String getId() {
            return id;
        }

        public String getDescription() {
            return description;
        }

        public String getImageUrl() {
            return image_url;
        }

        public String getPrice() {
            return price;
        }

        public String getList_price() {
            return list_price;
        }

        public String getDiscount() {
            return discount;
        }
    }
}
