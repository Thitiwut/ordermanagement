package controller;

import classes.service.BranchService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "BranchController", urlPatterns = "/branch")
public class BranchController extends HttpServlet {
    BranchService branchService = new BranchService();

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
        String serviceResult;
        String action = request.getParameter("action");
        String responseBody;
        switch (action) {
            default:
                return "{\"status\":\"unhandle\"}";
        }
    }

    private String getHandler(HttpServletRequest request) {
        String serviceResult;
        String action = request.getParameter("action");
        String responseBody;
        switch (action) {
            case "get_branches":
                serviceResult = branchService.GetAllBranch();
                responseBody = serviceResult;
                return responseBody;
            default:
                return "{\"status\":\"unhandle\"}";
        }
    }

}
