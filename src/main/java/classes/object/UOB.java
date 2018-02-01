package classes.object;

public class UOB {
    private int uob_id;
    private int uob_number;
    private String payee;
    private String fax_date;
    private String po_number;
    private String direct_dc;

    public UOB(){

    }

    public UOB(int uob_id, int uob_number, String payee, String fax_date, String po_number, String direct_dc, String remark) {
        this.uob_id = uob_id;
        this.uob_number = uob_number;
        this.payee = payee;
        this.fax_date = fax_date;
        this.po_number = po_number;
        this.direct_dc = direct_dc;
        this.remark = remark;
    }

    public int getUob_id() {
        return uob_id;
    }

    public void setUob_id(int uob_id) {
        this.uob_id = uob_id;
    }

    public int getUob_number() {
        return uob_number;
    }

    public void setUob_number(int uob_number) {
        this.uob_number = uob_number;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getFax_date() {
        return fax_date;
    }

    public void setFax_date(String fax_date) {
        this.fax_date = fax_date;
    }

    public String getPo_number() {
        return po_number;
    }

    public void setPo_number(String po_number) {
        this.po_number = po_number;
    }

    public String getDirect_dc() {
        return direct_dc;
    }

    public void setDirect_dc(String direct_dc) {
        this.direct_dc = direct_dc;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    private String remark;
}
