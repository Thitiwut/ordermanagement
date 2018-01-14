package classes.object;

public class Product {
    private int product_id;
    private String product_name;
    private String product_type;
    private String package_component;
    private String product_number;

    public Product(){

    }

    public Product(int product_id, String product_name, String product_number, String product_type, String package_component) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_number = product_number;
        this.product_type = product_type;
        this.package_component = package_component;
    }

    public Product(int product_id, String product_name, String product_number, String product_type) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_number = product_number;
        this.product_type = product_type;
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
}
