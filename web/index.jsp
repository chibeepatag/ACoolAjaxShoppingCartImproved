<%@ page import="java.util.*" %>
<%@ page import="developerworks.ajax.store.*" %>
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <link rel="stylesheet" type="text/css" href="css/index.css"/>

            <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
            <script src="jquery-1.10.2.min.js"></script>
            <script type="text/javascript" language="javascript" src="ajax1.js"></script>
            <script type="text/javascript" language="javascript" src="cart.js"></script>
            <script type="text/javascript" language="javascript" src="dynamicCss.js"></script>
            
    </head>
    <body>
        <div class="banner">
            <img src="images/PFM_logo.png"/>
            <span class="title">Patag Family Mart</span>
            <span class="tagline">We've got it all for you.</span>
            <span class="date">                
                <% java.util.Date d = new java.util.Date(); %>
                <%= d.toString() %>
            </span>
        </div>
        <div class="catalog">
            <h2>Catalog</h2>
            <table id="catalogTable">
                <thead><th>Name</th><th>Description</th><th>Unit Price</th><th></th></thead>
                <tbody class="item">
                    <%
                        for (Iterator<Item> I = new Catalog().getAllItems().iterator(); I.hasNext();) {
                            Item item = I.next();
                    %>
                    <tr><td><%= item.getName()%></td><td><%= item.getDescription()%></td><td><%= item.getFormattedPrice()%></td><td><button onclick="addToCart('<%= item.getCode()%>')">Add to Cart</button></td></tr>
                    <% }%>
                </tbody>
            </table>
        </div>
            <div class="cart">
                <h2>Cart Contents</h2>                
                <table id="contents">
                    <thead>
                        <th>Name</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th></th>
                    </thead>
                    <tbody id="purchases"></tbody>
                </table>
                Total cost: <span id="total">$0.00</span>
            </div>
                
    </body>
</html>
