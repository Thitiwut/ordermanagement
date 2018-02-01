package controller;

import classes.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ProductController", urlPatterns = "/product")
public class ProductController extends HttpServlet {
    ProductService prodService = new ProductService();

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
            case "add_product":
                serviceResult = prodService.AddNewProduct(
                        request.getParameter("product_name"),
                        request.getParameter("product_type"),
                        Integer.parseInt(request.getParameter("supplier_id")),
                        0);
                responseBody = "{\"status\":\"" + serviceResult + "\",\"action\":\"add_product\"}";
                return responseBody;
            case "delete_product":
                serviceResult = prodService.DeleteProduct(
                        Integer.parseInt(request.getParameter("product_id"))
                        );
                responseBody = "{\"status\":\"" + serviceResult + "\",\"action\":\"delete_product\"}";
                return responseBody;
            case "edit_product":
                serviceResult = prodService.EditProduct(
                        Integer.parseInt(request.getParameter("product_id")),
                        request.getParameter("product_name"));
                responseBody = "{\"status\":\"" + serviceResult + "\",\"action\":\"edit_product\"}";
                return responseBody;
            case "set_product_price":
                serviceResult = prodService.SetNewProductPrice(
                        Integer.parseInt(request.getParameter("product_id")),
                        Integer.parseInt(request.getParameter("supplier_id")),
                        request.getParameter("active_date"),
                        Double.parseDouble(request.getParameter("price")));
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
                        Integer.parseInt(request.getParameter("supplier_id")));
                responseBody = serviceResult;
                return responseBody;
            default:
                return "{\"status\":\"unhandle\"}";
        }
    }

}