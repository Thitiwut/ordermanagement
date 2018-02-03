package controller;

import classes.service.PurchaseOrderService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "PurchaseOrderController", urlPatterns = "/po")
public class PurchaseOrderController extends HttpServlet {
    PurchaseOrderService poService = new PurchaseOrderService();

    @Override
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        PrintWriter out = response.getWriter();
        out.print(postHandler(request));
    }

    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        PrintWriter out = response.getWriter();
        out.print(getHandler(request));
    }

    private String postHandler(HttpServletRequest request){
        String serviceResult;
        String action = request.getParameter("action");
        String responseBody;
        switch (action) {
            case "new_purchase_order":
                serviceResult = poService.AddNewPurchaseOrder(
                        Integer.parseInt(request.getParameter("supplier_id")),
                        Integer.parseInt(request.getParameter("branch_id")),
                        request.getParameter("order_date"),
                        request.getParameter("delivery_date"),
                        request.getParameter("status"));
                responseBody = serviceResult;
                return responseBody;
            case "add_purchase_order_product":
                serviceResult = poService.AddProductToPurchaseOrder(
                        Integer.parseInt(request.getParameter("product_id")),
                        Integer.parseInt(request.getParameter("po_id")),
                        Double.parseDouble(request.getParameter("order_amount")),
                        Double.parseDouble(request.getParameter("order_price")));
                responseBody = serviceResult;
                return responseBody;
            case "cancel_purchase_order":
                serviceResult = poService.CancelPurchaseOrder(
                        Integer.parseInt(request.getParameter("product_id")));
                responseBody = "{\"status\":\"" + serviceResult + "\",\"action\":\"cancel_purchase_order\"}";
                return responseBody;
            case "edit_purchase_order":
                serviceResult = poService.EditPurchaseOrder(
                        Integer.parseInt(request.getParameter("po_id")),
                        Integer.parseInt(request.getParameter("supplier_id")),
                        Integer.parseInt(request.getParameter("customer_branch_id")),
                        request.getParameter("expect_delivery_date"));
                responseBody = "{\"status\":\"" + serviceResult + "\",\"action\":\"cancel_purchase_order\"}";
                return responseBody;
            case "enter_uob_doc":
                serviceResult = poService.EnterUOBDocument(
                        request.getParameter("fax_date"),
                        request.getParameter("payee"),
                        request.getParameter("paid_date"),
                        Integer.parseInt(request.getParameter("uob_number")),
                        request.getParameter("remark"),
                        request.getParameter("po_number"));
                responseBody = "{\"status\":\"" + serviceResult + "\",\"action\":\"enter_uob_doc\"}";
                return responseBody;
            default:
                return "{\"status\":\"unhandle\"}";
        }
    }

    private String getHandler(HttpServletRequest request) {
        String serviceResult;
        String action = request.getParameter("action");
        String responseBody;
        switch (action) {
            case "get_products":
                serviceResult = poService.GetPurchaseOrderByNumber(
                        request.getParameter("po_number"));
                responseBody = serviceResult;
                return responseBody;
            default:
                return "{\"status\":\"unhandle\"}";
        }
    }

}
