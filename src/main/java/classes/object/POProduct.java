package classes.object;

public class POProduct extends Product {
    private int po_product_id;
    private Double order_amount;

    public POProduct(){
    }

    public POProduct(int po_product_id, int product_id, String product_name, String product_number, String product_type, double price, double order_amount) {
        this.po_product_id = po_product_id;
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_number = product_number;
        this.product_type = product_type;
        this.product_price = price;
        this.order_amount = order_amount;
    }

    public int getPo_product_id() {
        return po_product_id;
    }

    public void setPo_product_id(int po_product_id) {
        this.po_product_id = po_product_id;
    }

    public Double getOrder_amount() {
        return order_amount;
    }

    public void setOrder_amount(Double order_amount) {
        this.order_amount = order_amount;
    }
}
