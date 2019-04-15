package miltonreinoso.com.technicaltest.Models;

import java.util.List;

public class ArticleDetails {
    private final String xid;

    private final String description;

    private final String summary;

    private final String brand;

    private final String originalBrand;

    private final int listPrice;

    private final int price;

    private final int discount;

    private final boolean enabledForSale;

    private final PreferredInstallment preferredInstallment;

    private final String model;

    private final int categoryId;

    private final MainImage mainImage;

    private final boolean virtual;

    private final List<Integer> categories;

    private final String category;

    private final List<Object> productTags;

    private final int priceMatchingDiscount;

    private final double priceWithoutVat;

    private final int vatPercentage;

    private final Resources resources;

    public ArticleDetails(String xid, String description, String summary, String brand,
                          String originalBrand, int listPrice, int price, int discount, boolean enabledForSale,
                          PreferredInstallment preferredInstallment, String model, int categoryId,
                          MainImage mainImage, boolean virtual, List<Integer> categories, String category,
                          List<Object> productTags, int priceMatchingDiscount, double priceWithoutVat,
                          int vatPercentage, Resources resources) {
        this.xid = xid;
        this.description = description;
        this.summary = summary;
        this.brand = brand;
        this.originalBrand = originalBrand;
        this.listPrice = listPrice;
        this.price = price;
        this.discount = discount;
        this.enabledForSale = enabledForSale;
        this.preferredInstallment = preferredInstallment;
        this.model = model;
        this.categoryId = categoryId;
        this.mainImage = mainImage;
        this.virtual = virtual;
        this.categories = categories;
        this.category = category;
        this.productTags = productTags;
        this.priceMatchingDiscount = priceMatchingDiscount;
        this.priceWithoutVat = priceWithoutVat;
        this.vatPercentage = vatPercentage;
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

    public String getOriginalBrand() {
        return originalBrand;
    }

    public int getListPrice() {
        return listPrice;
    }

    public int getPrice() {
        return price;
    }

    public int getDiscount() {
        return discount;
    }

    public boolean isEnabledForSale() {
        return enabledForSale;
    }

    public PreferredInstallment getPreferredInstallment() {
        return preferredInstallment;
    }

    public String getModel() {
        return model;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public MainImage getMainImage() {
        return mainImage;
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

    public List<Object> getProductTags() {
        return productTags;
    }

    public int getPriceMatchingDiscount() {
        return priceMatchingDiscount;
    }

    public double getPriceWithoutVat() {
        return priceWithoutVat;
    }

    public int getVatPercentage() {
        return vatPercentage;
    }

    public Resources getResources() {
        return resources;
    }

    public static class PreferredInstallment {
        private final int basePrice;

        private final int installments;

        private final double interest;

        private final double surcharge;

        private final double finalPrice;

        private final double installmentPrice;

        private final double eapr;

        private final double tfc;

        private final String description;

        private final int gatewayInstallments;

        private final boolean visaFinancing;

        private final int repayment;

        public PreferredInstallment(int basePrice, int installments, double interest,
                                    double surcharge, double finalPrice, double installmentPrice, double eapr,
                                    double tfc, String description, int gatewayInstallments, boolean visaFinancing,
                                    int repayment) {
            this.basePrice = basePrice;
            this.installments = installments;
            this.interest = interest;
            this.surcharge = surcharge;
            this.finalPrice = finalPrice;
            this.installmentPrice = installmentPrice;
            this.eapr = eapr;
            this.tfc = tfc;
            this.description = description;
            this.gatewayInstallments = gatewayInstallments;
            this.visaFinancing = visaFinancing;
            this.repayment = repayment;
        }

        public int getBasePrice() {
            return basePrice;
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

        public double getFinalPrice() {
            return finalPrice;
        }

        public double getInstallmentPrice() {
            return installmentPrice;
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

        public int getGatewayInstallments() {
            return gatewayInstallments;
        }

        public boolean isVisaFinancing() {
            return visaFinancing;
        }

        public int getRepayment() {
            return repayment;
        }
    }

    public static class MainImage {
        private final int maxWidth;

        private final String url;

        public MainImage(int maxWidth, String url) {
            this.maxWidth = maxWidth;
            this.url = url;
        }

        public int getMaxWidth() {
            return maxWidth;
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
            private final int maxWidth;

            private final String url;

            public Images(int maxWidth, String url) {
                this.maxWidth = maxWidth;
                this.url = url;
            }

            public int getMaxWidth() {
                return maxWidth;
            }

            public String getUrl() {
                return url;
            }
        }

        public static class Videos {
            private final String url;

            private final String thumbUrl;

            public Videos(String url, String thumbUrl) {
                this.url = url;
                this.thumbUrl = thumbUrl;
            }

            public String getUrl() {
                return url;
            }

            public String getThumbUrl() {
                return thumbUrl;
            }
        }
    }
}
