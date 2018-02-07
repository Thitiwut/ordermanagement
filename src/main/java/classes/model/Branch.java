package classes.model;

public class Branch{
    private int branch_id;
    private int branch_owner_id;
    private String branch_name;
    private int branch_number;
    private String branch_address;

    public Branch() {
    }

    public Branch(int branch_id, int branch_owner_id, String branch_name, int branch_number, String branch_address) {
        this.branch_id = branch_id;
        this.branch_owner_id = branch_owner_id;
        this.branch_name = branch_name;
        this.branch_number = branch_number;
        this.branch_address = branch_address;
    }

    public int getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(int branch_id) {
        this.branch_id = branch_id;
    }

    public int getBranch_owner_id() {
        return branch_owner_id;
    }

    public void setBranch_owner_id(int branch_owner_id) {
        this.branch_owner_id = branch_owner_id;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }

    public int getBranch_number() {
        return branch_number;
    }

    public void setBranch_number(int branch_number) {
        this.branch_number = branch_number;
    }

    public String getBranch_address() {
        return branch_address;
    }

    public void setBranch_address(String branch_address) {
        this.branch_address = branch_address;
    }
}
