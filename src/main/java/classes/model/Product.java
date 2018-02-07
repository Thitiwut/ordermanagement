package classes.model;

public class Product {
    protected int product_id;
    protected String product_name;
    protected String product_type;
    protected String package_component;
    protected String product_number;
    protected Double product_price;

    public Product(){
    }

    public Product(int product_id, String product_name, String product_number, String product_type, String package_component, double price) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_number = product_number;
        this.product_type = product_type;
        this.package_component = package_component;
        this.product_price = price;
    }

    public Product(int product_id, String product_name, String product_number, String product_type, double price) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_number = product_number;
        this.product_type = product_type;
        this.product_price = price;
    }

    public int getProduct_id() {
        return product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getProduct_type() {
        return product_type;
    }

    public String getPackage_component() {
        return package_component;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public void setPackage_component(String package_component) {
        this.package_component = package_component;
    }

    public String getProduct_number() {
        return product_number;
    }

    public void setProduct_number(String product_number) {
        this.product_number = product_number;
    }

    public double getPrice() {
        return product_price;
    }

    public void setPrice(double price) {
        this.product_price = price;
    }

}
