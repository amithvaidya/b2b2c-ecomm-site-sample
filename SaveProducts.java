

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.xml.ws.Response;

public class SaveProducts extends HttpServlet{

  /*
     * Setup DB connection
     */
    Connection conn = null;
    PreparedStatement pStmt=null;

    public void init(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                /*Update DB info */
                "jdbc:mysql://localhost:3306/erp", 
                "root", 
                "amith"
            );
            pStmt = conn.prepareStatement("insert into products(product_name, price, quantity_available, vendor_id) values (?,?,?,?)");
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    /*
     * -Fetch the list of products by vendor id
     * -Set it to request attribute
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException{
        
        
        try{
            int vendorId = Integer.parseInt(request.getParameter("vendorId").toString());
            request.setAttribute("vendorId", vendorId);
            int productCount = Integer.parseInt(request.getParameter("productCount").toString());

            for(int i=0; i<productCount; i++){
                pStmt.setString(1, request.getParameter("product_name_"+i).toString());
                pStmt.setFloat(2, Float.parseFloat(request.getParameter("price_"+i).toString()));
                pStmt.setInt(3, Integer.parseInt(request.getParameter("quantity_available_"+i)));
                pStmt.setInt(4, vendorId);
                pStmt.executeUpdate();
            }

            response.sendRedirect("/vendor?vendorId="+vendorId);
        }catch(Exception e){
            e.printStackTrace();
        }


        System.out.println("Data Saved.");
        
    }

    /*
     * Close DB connection
     */
    public void destroy(){
        try {
            pStmt.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
