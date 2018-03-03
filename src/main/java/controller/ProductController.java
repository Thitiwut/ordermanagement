package controller;

import classes.object.RequestBodyParser;
import classes.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet(name = "ProductController", urlPatterns = "/product")
public class ProductController extends HttpServlet {
    ProductService prodService = new ProductService();

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
            case "add_product":
                serviceResult = prodService.AddNewProduct(
                        (String)map.get("product_name"),
                        (String)map.get("product_type"),
                        (Integer)map.get("supplier_id"),
                        0);
                responseBody = "{\"status\":\"" + serviceResult + "\",\"action\":\"add_product\"}";
                return responseBody;
            case "delete_product":
                serviceResult = prodService.DeleteProduct(
                        (Integer)map.get("product_id")
                        );
                responseBody = "{\"status\":\"" + serviceResult + "\",\"action\":\"delete_product\"}";
                return responseBody;
            case "edit_product":
                serviceResult = prodService.EditProduct(
                        (Integer)map.get("product_id"),
                        (String)map.get("product_name"));
                responseBody = "{\"status\":\"" + serviceResult + "\",\"action\":\"edit_product\"}";
                return responseBody;
            case "set_product_price":
                serviceResult = prodService.SetNewProductPrice(
                        (Integer)map.get("product_id"),
                        (Integer)map.get("supplier_id"),
                        (String)map.get("active_date"),
                        (Double)map.get("price"));
                responseBody = "{\"status\":\"" + serviceResult + "\",\"action\":\"set_product_price\"}";
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
                serviceResult = prodService.GetProductList(
                        Integer.valueOf(request.getParameter("supplier_id")));
                responseBody = serviceResult;
                return responseBody;
            default:
                return "{\"status\":\"unhandle\"}";
        }
    }

}