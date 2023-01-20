<!--
    
    Basic features:
    -Search for products, display results
    -add to cart
    -checkout/billing
-->
<%@ page import="java.sql.*"%>

<html>
    <head>
        <title>Customer Dashboard</title>
        <!-- Latest compiled and minified CSS -->
        <script src="/erp/script.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
        <h1>Customer Dashboard</h1>
        <form method="get" action="/searchProducts">
            <input type="text" name="searchBar">
            <input class="btn btn-primary" type="submit" value="Search">
        </form>
		<button id="showCartBttn" onClick="showCart()">Hide Cart</button>
		<div id="cartTableArea">
            <h2>Cart</h2>
            <table id="cartTable" class="table">
			<tr>
				<th>Product Name</th>
				<th>Per Unit Cost</th>
				<th>No. of units</th>
				<th>Cost</th>
				
			</tr>
		</table>
		<h3 style="display: inline-block;">Total: <div style="display: inline-block;" id="total">0</div></h3>
		</div>
		
        <div class="container">
		<% if(request.getAttribute("isResultsPresent") != null) {%>
            <table class="table">
                <tr>
                    <th>Name of product</th>
                    <th>Price</th>
                    <th>Vendor</th>
                    <th>Available Quantity</th>
					<th>Required Quantity</th>
                    <th></th>
                </tr>
				<!-- If results are there, then only display the table-->
				<%  ResultSet rs = ((ResultSet) request.getAttribute("searchResults"));
				    int count = 0;
                %>
				<%while(rs.next()){%>
					<tr>
						<td id="product_name_<%=count%>"><%=rs.getString("product_name")%></td>
						<td id="price_<%=count%>"><%=rs.getFloat("price")%></td>
						<td id="vendor_id_<%=count%>"><%=rs.getInt("vendor_id")%></td>
						<td id="quantity_available_<%=count%>"><%=rs.getInt("quantity_available")%></td>
						<td ><input id="quantity_required_<%=count%>" type="number" min="0" max="<%=rs.getInt("quantity_available")%>"></td>
						
						<td><button onClick="addToCart(<%=count%>)" class="btn btn-primary">Add to Cart</button></td>
					</tr>
					<%count++;%>
				<%}%>
                
            </table>
			<%}%>
        </div>
		<div class="container" id="cartArea"></div>
        </div>
    </body>
</html>