

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.*;
import javax.servlet.http.*;

// Mapping: /productList?vendorId=<vendor-id>
public class VendorProducts extends HttpServlet{
    
    /*
     * Setup DB connection
     */
    Connection conn = null;
    PreparedStatement pStmt=null, pStmt1=null;

    public void init(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                /*Update DB info */
                "jdbc:mysql://localhost:3306/erp", 
                "root", 
                "amith"
            );
            pStmt = conn.prepareStatement("select * from products where vendor_id = ?");
            pStmt1 = conn.prepareStatement("select vendor_name from vendors where vendor_id = ?");
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    /*
     * -Fetch the list of products by vendor id
     * -Set it to request attribute
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException{
        int vendorId = Integer.parseInt(request.getParameter("vendorId"));
        request.setAttribute("vendorId", vendorId);

        ResultSet rs=null, rs1=null;
        try{
            pStmt.setInt(1, vendorId);
            pStmt1.setInt(1, vendorId);

            rs = pStmt.executeQuery();
            rs1 = pStmt1.executeQuery();
            

            
        if(rs != null)  request.setAttribute("vendorProductList", rs);

        if(rs1 != null)
            if(rs1.next())
                request.setAttribute("vendorName", rs1.getString("vendor_name"));

        }catch(Exception e){
            e.printStackTrace();
        }


        try{
            RequestDispatcher rd = request.getRequestDispatcher("vendor_home.jsp");
            rd.forward(request, response);
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    /*
     * Close DB connection
     */
    public void destroy(){
        try {
            pStmt.close();
            pStmt1.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
