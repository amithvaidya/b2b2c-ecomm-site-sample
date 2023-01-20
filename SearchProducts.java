

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.xml.ws.Response;

public class SearchProducts extends HttpServlet{

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
            pStmt = conn.prepareStatement("select * from products where product_name like ?");
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    /*
     * -Fetch the list of products by vendor id
     * -Set it to request attribute
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException{
        
        
        try{
            
            pStmt.setString(1, "%"+request.getParameter("searchBar").toString()+"%");

            ResultSet rs = pStmt.executeQuery();
            request.setAttribute("isResultsPresent", true);
            request.setAttribute("searchResults", rs);


            RequestDispatcher rd = request.getRequestDispatcher("customer_home.jsp");
            rd.forward(request, response);

        }catch(Exception e){
            e.printStackTrace();
        }
        
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
