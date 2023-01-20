

let productCount = 0;

function addProduct() {
	
	var prodAddArea = document.getElementById("productTable");
	
	prodAddArea.innerHTML = prodAddArea.innerHTML+'<tr>' +
		'<td>'+'X'+'</td>'+
		'<td>'+'<input name="product_name_'+productCount+'" type="text">'+'</td>'+
		'<td>'+'<input type="number" name="price_'+productCount+'">'+'</td>'+
		'<td>'+'<input type="number" name="quantity_available_'+productCount+'">'+'</td>'+
		'<td>'+'X'+'</td>'+
		'</tr>';
	productCount++;
	
	document.getElementById("productCounter").value = productCount;
		
}

let cartCounter = 0;

function addToCart(id){
	let productName = document.getElementById('product_name_'+id).innerText;
	let price = document.getElementById('price_'+id).innerText;
	let requiredQty = document.getElementById('quantity_required_'+id).value;
	let vendorId = document.getElementById('vendor_id_'+id).innerText;
	let availableQty = document.getElementById('quantity_available_'+id);
	let total = Number(document.getElementById('total').innerText) + requiredQty*price;
	document.getElementById('total').innerText = total;
	availableQty.innerText = (availableQty.innerText) - requiredQty;
	
	
	document.getElementById('quantity_required_'+id).setAttribute("max", availableQty.innerText);
	document.getElementById('quantity_required_'+id).value = 0;
	
	let cartArea = document.getElementById('cartArea');
	cartArea.innerHTML = cartArea.innerHTML + 
		'<input name="product_name_'+cartCounter+'" type="text" value="'+productName+'" hidden>'+
		'<input name="price_'+cartCounter+'" type="number" value="'+price+'" hidden>'+
		'<input name="required_qty_'+cartCounter+'" type="number" value="'+requiredQty+'" hidden>'+
		'<input name="vendor_id_'+cartCounter+'" type="number" value="'+vendorId+'" hidden>';
		
	addToCartTable(productName, price, requiredQty, vendorId);
		
}


function addToCartTable(productName, price, requiredQty, vendorId){
	let cartTable = document.getElementById('cartTable');
	cartTable.innerHTML += '<tr>'+
		'<td>'+productName+'</td>'+
		'<td>'+price+'</td>'+
		'<td>'+requiredQty+'</td>'+
		'<td>'+price*requiredQty+'</td>'+
		'</tr>';
}

let isShowCart = false;

function showCart(){
	if(!isShowCart){
		document.getElementById('cartTableArea').style.display = "none";
		document.getElementById('showCartBttn').innerText = 'Show Cart';
		isShowCart = true;
	}
	else {
		document.getElementById('cartTableArea').style.display = "block";
		document.getElementById('showCartBttn').innerText = 'Hide Cart';
		isShowCart = false;
	}
	
}