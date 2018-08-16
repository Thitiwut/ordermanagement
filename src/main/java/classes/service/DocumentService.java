package classes.service;

import classes.dao.POProductDAO;
import classes.dao.POProductDAOImpl;
import classes.dao.PurchaseOrderDAO;
import classes.dao.PurchaseOrderDAOImpl;
import classes.model.Product;
import classes.model.PurchaseOrder;
import classes.object.DataSourceProvider;
import classes.object.PDFCreator;
import com.google.gson.Gson;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class DocumentService {
    private PurchaseOrderDAO purchaseOrderDAO = new PurchaseOrderDAOImpl(new DataSourceProvider().GetDataSource());
    private POProductDAO poProductDAO = new POProductDAOImpl(new DataSourceProvider().GetDataSource());

    public void setPurchaseOrderDAO(PurchaseOrderDAO purchaseOrderDAO) {
        this.purchaseOrderDAO = purchaseOrderDAO;
    }

    public void setPoProductDAO(POProductDAO poProductDAO) {
        this.poProductDAO = poProductDAO;
    }

    public String downloadPurchaseOrderPDF(String po_number) throws IOException, SQLException, NamingException {
            PurchaseOrder purchaseOrder = purchaseOrderDAO.GetPurchaseOrderByNumber(po_number);
            ArrayList<Product> po_product = purchaseOrderDAO.GetPurchaseOrderProductsByPOID(purchaseOrder.getPo_id());
            purchaseOrder.setPo_product(po_product);
            PDFCreator pdfCreator = new PDFCreator();
            return pdfCreator.createPurchaseOrderPDF(purchaseOrder);
    }

}
