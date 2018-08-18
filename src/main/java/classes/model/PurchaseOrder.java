package classes.model;

import java.util.ArrayList;

public class PurchaseOrder {
    private int po_id;
    private int supplier_id;
    private String supplier_name;
    private String po_number;
    private int customer_branch_id;
    private String customer_branch_name;
    private String order_date;
    private String expect_delivery_date;
    private String status;
    private ArrayList<Product> po_product;
    private String last_mnt_user;
    private String create_user;

    public int getPo_id() {
        return po_id;
    }

    public void setPo_id(int po_id) {
        this.po_id = po_id;
    }

    public int getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getPo_number() {
        return po_number;
    }

    public void setPo_number(String po_number) {
        this.po_number = po_number;
    }

    public int getCustomer_branch_id() {
        return customer_branch_id;
    }

    public void setCustomer_branch_id(int customer_branch_id) {
        this.customer_branch_id = customer_branch_id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getExpect_delivery_date() {
        return expect_delivery_date;
    }

    public void setExpect_delivery_date(String expect_delivery_date) {
        this.expect_delivery_date = expect_delivery_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    public String getCustomer_branch_name() {
        return customer_branch_name;
    }

    public void setCustomer_branch_name(String customer_branch_name) {
        this.customer_branch_name = customer_branch_name;
    }

    public void setPo_product(ArrayList<Product> po_product) {
        this.po_product = po_product;
    }

    public ArrayList<Product> getPo_product() {
        return po_product;
    }

    public String getLast_mnt_user() {
        return last_mnt_user;
    }

    public void setLast_mnt_user(String last_mnt_user) {
        this.last_mnt_user = last_mnt_user;
    }

    public String getCreate_user() {
        return create_user;
    }

    public void setCreate_user(String create_user) {
        this.create_user = create_user;
    }
}
