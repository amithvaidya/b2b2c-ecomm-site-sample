<!-- 

    Basic Features:
    -View list of products and their info
    -Add/Remove/Update product info
-->
<%@ page import="java.sql.*" %>
<html>
    <head>
        <title>Vendor Dashboard</title>
		<!-- Latest compiled and minified CSS -->
		<script src="/erp/script.js"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    </head>
    <body>
	<div class="container">
        <h1>Vendor: <%=request.getAttribute("vendorName").toString()%></h1>

        <button  class="btn btn-primary" onClick="addProduct()">Add Product.</button>
		
		<h2>Add Products</h2>
		<form method="post" action="/saveProducts">
		<input type="text" id="productCounter" name="productCount" hidden>
		<table class="table" id="productTable">
			<tr>
                <th>ID</th>
                <th>Name</th>
                <th>Price</th>
                <th>Quantity Available</th>
                <th>Total units sold</th>
            </tr>
		</table>
		<input name="vendorId" type="text" value="<%=request.getParameter("vendorId")%>" hidden>
		<input type="submit" class="btn btn-success" value="Save Products">
		</form>
		
        <h2>Product List</h2>
        <table class="table">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Price</th>
                <th>Quantity Available</th>
                <th>Total units sold</th>
            </tr>
            <%ResultSet productList = ((ResultSet) request.getAttribute("vendorProductList")); %>
            <%while(productList.next()){%>
            <tr>
                <td><%=productList.getInt("product_id")%></td>
                <td><%=productList.getString("product_name")%></td>
                <td><%=productList.getFloat("price")%></td>
                <td><%=productList.getInt("quantity_available")%></td>
                <td><%=productList.getInt("total_sales")%></td>
               <!-- <td><button onClick="alert('')">Update Details.</button></td> -->
            </tr>
            <%}%>
        </table>
		</div>
    </body>
</html>