package controller;

import classes.object.RequestBodyParser;
import classes.service.PurchaseOrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet(name = "PurchaseOrderController", urlPatterns = "/po")
public class PurchaseOrderController extends HttpServlet {
    PurchaseOrderService poService = new PurchaseOrderService();

    //Preflight
    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With, Content-Type");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        PrintWriter out = response.getWriter();
        out.print(postHandler(request));
    }

    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET");
        PrintWriter out = response.getWriter();
        out.print(getHandler(request));
    }

    private String postHandler(HttpServletRequest request){
        RequestBodyParser rbp = new RequestBodyParser();
        HashMap<String,Object> map = rbp.JSONBodyToMap(request);
        String serviceResult;
        String action = map.get("action").toString();
        String responseBody;
        switch (action) {
            case "new_purchase_order":
                serviceResult = poService.AddNewPurchaseOrder(
                        (Integer)map.get("po_number"),
                        (Integer)map.get("supplier_id"),
                        (Integer)map.get("branch_id"),
                        (String)map.get("order_date"),
                        (String)map.get("delivery_date"),
                        (String)map.get("status"),
                        (String)map.get("user")
                        );
                responseBody = serviceResult;
                return responseBody;
            case "add_purchase_order_product":
                serviceResult = poService.AddProductToPurchaseOrder(
                        (Integer)map.get("product_id"),
                        (Integer)map.get("po_id"),
                        (Integer) map.get("order_amount"),
                        Double.parseDouble((String)map.get("order_price")));
                responseBody = serviceResult;
                return responseBody;
            case "cancel_purchase_order":
                serviceResult = poService.CancelPurchaseOrder(
                        (Integer)map.get("po_id"));
                responseBody = "{\"status\":\"" + serviceResult + "\",\"action\":\"cancel_purchase_order\"}";
                return responseBody;
            case "edit_purchase_order":
                serviceResult = poService.EditPurchaseOrder(
                        (Integer)map.get("po_id"),
                        (Integer)map.get("supplier_id"),
                        (Integer)map.get("customer_branch_id"),
                        (String)map.get("expect_delivery_date"));
                responseBody = "{\"status\":\"" + serviceResult + "\",\"action\":\"cancel_purchase_order\"}";
                return responseBody;
            case "enter_uob_doc":
                serviceResult = poService.EnterUOBDocument(
                        (String)map.get("fax_date"),
                        (String)map.get("payee"),
                        (String)map.get("paid_date"),
                        (Integer)map.get("uob_number"),
                        (String)map.get("remark"),
                        (String)map.get("po_number"));
                responseBody = "{\"status\":\"" + serviceResult + "\",\"action\":\"enter_uob_doc\"}";
                return responseBody;
            case "update_status":
                serviceResult = poService.UpdateStatus(
                        (Integer)map.get("po_id"),
                        (String)map.get("status"),
                        (String)map.get("user")
                );
                responseBody = "{\"status\":\"" + serviceResult + "\",\"action\":\"update_status\"}";
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
            case "get_purchase_order":
                serviceResult = poService.GetPurchaseOrderByNumber(
                        request.getParameter("po_number"));
                responseBody = serviceResult;
                return responseBody;
            case "get_purchase_order_list":
                serviceResult = poService.GetPurchaseOrderList(
                        request.getParameter("po_number"),
                        request.getParameter("status"),
                        request.getParameter("supplier_name"),
                        request.getParameter("branch_name"));
                responseBody = serviceResult;
                return responseBody;
            default:
                return "{\"status\":\"unhandle\"}";
        }
    }

}
