// Timestamp of cart that page was last updated with
var lastCartUpdate = 0;

/*
 * Adds the specified item to the shopping cart, via Ajax call
 * itemCode - product code of the item to add
 */
function addToCart(itemCode) {

    var req = newXMLHttpRequest();

    req.onreadystatechange = getReadyStateHandler(req, updateCart);

    req.open("POST", "cart.do", true);
    req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    req.send("action=add&item=" + itemCode);
}

/*
 * Removes the specified item from the shopping cart, via Ajax call
 * itemCode - product code of the item to remove
 */
function removeFromCart(itemCode){    
    var req = newXMLHttpRequest();

    req.onreadystatechange = getReadyStateHandler(req, updateCart);

    req.open("POST", "cart.do", true);
    req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    req.send("action=remove&item=" + itemCode);     
}


/*
 * Update shopping-cart area of page to reflect contents of cart
 * described in XML document.
 */
function updateCart(cartXML) {
    var cart = cartXML.getElementsByTagName("cart")[0];
    var generated = cart.getAttribute("generated");
    
    if (generated > lastCartUpdate) {
        lastCartUpdate = generated;
        var contents = document.getElementById("purchases");
        contents.innerHTML = "";

        var items = cart.getElementsByTagName("item");
        for (var I = 0; I < items.length; I++) {

            var item = items[I];
            var name = item.getElementsByTagName("name")[0].firstChild.nodeValue;
            var quantity = item.getElementsByTagName("quantity")[0].firstChild.nodeValue;
            var price = item.getElementsByTagName("price")[0].firstChild.nodeValue;
            var itemCode = item.getAttribute("code");
           
            var row = document.createElement("tr");
            var nameCell = document.createElement("td");
            var quantityCell = document.createElement("td");
            var priceCell = document.createElement("td");
            var removeBtnCell = document.createElement("td");
            var removeButton = document.createElement("button");
            removeButton.appendChild(document.createTextNode("Remove"));                                                               
            removeBtnCell.appendChild(removeButton);                                      

            nameCell.appendChild(document.createTextNode(name));
            quantityCell.appendChild(document.createTextNode(quantity));
            priceCell.appendChild(document.createTextNode(price));
            removeBtnCell.appendChild(removeButton);      
            
            removeButton.addEventListener("click", removeButtonListener(itemCode));

            row.appendChild(nameCell);
            row.appendChild(quantityCell);
            row.appendChild(priceCell);
            row.appendChild(removeBtnCell);
            contents.appendChild(row);
        }

    }

    document.getElementById("total").innerHTML = cart.getAttribute("total");
    tableRowColor();
}


/*
 * @param itemCode of the item to be removed
 * @returns function call to remove item from the cart
 */
function removeButtonListener(itemCode){   
    var removeFromCart1 = function() {
        removeFromCart(itemCode);
    };
 return removeFromCart1;
}
