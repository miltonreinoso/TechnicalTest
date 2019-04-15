package miltonreinoso.com.technicaltest.Models;

import java.util.List;

public class ArticleDetails {
    private final String xid;

    private final String description;

    private final String summary;

    private final String brand;

    private final String original_brand;

    private final int list_price;

    private final int price;

    private final int discount;

    private final boolean enabled_for_sale;

    private final PreferredInstallment preferred_installment;

    private final String model;

    private final int categoryId;

    private final MainImage main_image;

    private final boolean virtual;

    private final List<Integer> categories;

    private final String category;

    private final List<Object> product_tags;

    private final int price_matching_discount;

    private final double price_without_vat;

    private final int vat_percentage;

    private final Resources resources;

    public ArticleDetails(String xid, String description, String summary, String brand,
                          String original_brand, int list_price, int price, int discount, boolean enabled_for_sale,
                          PreferredInstallment preferred_installment, String model, int categoryId,
                          MainImage main_image, boolean virtual, List<Integer> categories, String category,
                          List<Object> product_tags, int price_matching_discount, double price_without_vat,
                          int vat_percentage, Resources resources) {
        this.xid = xid;
        this.description = description;
        this.summary = summary;
        this.brand = brand;
        this.original_brand = original_brand;
        this.list_price = list_price;
        this.price = price;
        this.discount = discount;
        this.enabled_for_sale = enabled_for_sale;
        this.preferred_installment = preferred_installment;
        this.model = model;
        this.categoryId = categoryId;
        this.main_image = main_image;
        this.virtual = virtual;
        this.categories = categories;
        this.category = category;
        this.product_tags = product_tags;
        this.price_matching_discount = price_matching_discount;
        this.price_without_vat = price_without_vat;
        this.vat_percentage = vat_percentage;
        this.resources = resources;
    }

    public String getXid() {
        return xid;
    }

    public String getDescription() {
        return description;
    }

    public String getSummary() {
        return summary;
    }

    public String getBrand() {
        return brand;
    }

    public String getOriginal_brand() {
        return original_brand;
    }

    public int getList_price() {
        return list_price;
    }

    public int getPrice() {
        return price;
    }

    public int getDiscount() {
        return discount;
    }

    public boolean isEnabled_for_sale() {
        return enabled_for_sale;
    }

    public PreferredInstallment getPreferred_installment() {
        return preferred_installment;
    }

    public String getModel() {
        return model;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public MainImage getMain_image() {
        return main_image;
    }

    public boolean isVirtual() {
        return virtual;
    }

    public List<Integer> getCategories() {
        return categories;
    }

    public String getCategory() {
        return category;
    }

    public List<Object> getProduct_tags() {
        return product_tags;
    }

    public int getPrice_matching_discount() {
        return price_matching_discount;
    }

    public double getPrice_without_vat() {
        return price_without_vat;
    }

    public int getVat_percentage() {
        return vat_percentage;
    }

    public Resources getResources() {
        return resources;
    }

    public static class PreferredInstallment {
        private final int base_price;

        private final int installments;

        private final double interest;

        private final double surcharge;

        private final double final_price;

        private final double installment_price;

        private final double eapr;

        private final double tfc;

        private final String description;

        private final int gateway_installments;

        private final boolean visa_financing;

        private final int repayment;

        public PreferredInstallment(int base_price, int installments, double interest,
                                    double surcharge, double final_price, double installment_price, double eapr,
                                    double tfc, String description, int gateway_installments, boolean visaFinancing,
                                    int repayment) {
            this.base_price = base_price;
            this.installments = installments;
            this.interest = interest;
            this.surcharge = surcharge;
            this.final_price = final_price;
            this.installment_price = installment_price;
            this.eapr = eapr;
            this.tfc = tfc;
            this.description = description;
            this.gateway_installments = gateway_installments;
            this.visa_financing = visaFinancing;
            this.repayment = repayment;
        }

        public int getBase_price() {
            return base_price;
        }

        public int getInstallments() {
            return installments;
        }

        public double getInterest() {
            return interest;
        }

        public double getSurcharge() {
            return surcharge;
        }

        public double getFinal_price() {
            return final_price;
        }

        public double getInstallment_price() {
            return installment_price;
        }

        public double getEapr() {
            return eapr;
        }

        public double getTfc() {
            return tfc;
        }

        public String getDescription() {
            return description;
        }

        public int getGateway_installments() {
            return gateway_installments;
        }

        public boolean isVisaFinancing() {
            return visa_financing;
        }

        public int getRepayment() {
            return repayment;
        }
    }

    public static class MainImage {
        private final int max_width;

        private final String url;

        public MainImage(int max_width, String url) {
            this.max_width = max_width;
            this.url = url;
        }

        public int getMax_width() {
            return max_width;
        }

        public String getUrl() {
            return url;
        }
    }

    public static class Resources {
        private final List<Images> images;

        private final List<Videos> videos;

        public Resources(List<Images> images, List<Videos> videos) {
            this.images = images;
            this.videos = videos;
        }

        public List<Images> getImages() {
            return images;
        }

        public List<Videos> getVideos() {
            return videos;
        }

        public static class Images {
            private final int max_width;

            private final String url;

            public Images(int max_width, String url) {
                this.max_width = max_width;
                this.url = url;
            }

            public int getMax_width() {
                return max_width;
            }

            public String getUrl() {
                return url;
            }
        }

        public static class Videos {
            private final String url;

            private final String thumb_url;

            public Videos(String url, String thumb_url) {
                this.url = url;
                this.thumb_url = thumb_url;
            }

            public String getUrl() {
                return url;
            }

            public String getThumb_url() {
                return thumb_url;
            }
        }
    }
}
