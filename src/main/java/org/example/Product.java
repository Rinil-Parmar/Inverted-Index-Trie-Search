package org.example;

/**
 * Product
 * --------------------------------------------------
 * This class represents a single product entity loaded
 * from the CSV file. Each product contains basic details
 * like name, price, category, availability, etc.
 *
 * The Product class acts as a data model (POJO) that
 * stores all attributes for search and display operations.
 */
public class Product {

    // ==============================
    // ===== Product Attributes =====
    // ==============================

    // Name of the product (e.g., "Cheetos", "Kozy Shack")
    private String productName;

    // Product price as text (can include "$" symbol)
    private String price;

    // Optional description field (may be missing)
    private String description;

    // URL link to the product image
    private String imageUrl;

    // Availability status (e.g., "In-stock", "Out-of-stock")
    private String availability;

    // Product category (e.g., "Snacks & Candy")
    private String category;

    // Store or vendor name where product is available
    private String storeName;

    // ==============================
    // ===== Constructors ===========
    // ==============================

    /**
     * Default constructor
     * (Used when creating a blank product to fill values later)
     */
    public Product() {
    }

    /**
     * Parameterized constructor for quick object creation
     *
     * @param productName  product name
     * @param price        product price
     * @param description  product description
     * @param imageUrl     product image URL
     * @param availability product stock status
     * @param category     product category
     * @param storeName    store name
     */
    public Product(String productName, String price, String description,
                   String imageUrl, String availability, String category,
                   String storeName) {
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
        this.availability = availability;
        this.category = category;
        this.storeName = storeName;
    }

    // ==============================
    // ===== Getters & Setters ======
    // ==============================

    // These methods provide controlled access to private attributes.

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    // ==============================
    // ===== Display Formatting =====
    // ==============================

    /**
     * Returns a simplified product summary for display or debugging.
     * (Overrides the default Object.toString() method)
     */
    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", price='" + price + '\'' +
                ", storeName='" + storeName + '\'' +
                '}';
    }
}
