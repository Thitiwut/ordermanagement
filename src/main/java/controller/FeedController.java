package controller;

import classes.object.RequestBodyParser;
import classes.service.BranchService;
import classes.service.FeedService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet(name = "FeedController", urlPatterns = "/feed")
public class FeedController extends HttpServlet {
    FeedService feedService = new FeedService();

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
            case "add_feed":
                serviceResult = feedService.AddNewFeed(
                        (String)map.get("feed_action"),
                        (String)map.get("feed_po_number"),
                        (String)map.get("feed_product"),
                        (String)map.get("feed_supplier" ));
                responseBody = "{\"status\":\"" + serviceResult + "\",\"action\":\"add_feed\"}";
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
            case "get_feed":
                serviceResult = feedService.GetFeed();
                responseBody = serviceResult;
                return responseBody;
            default:
                return "{\"status\":\"unhandle\"}";
        }
    }

}
