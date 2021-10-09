
let btn1 = document.getElementById("btn1");
let btn2 = document.getElementById("btn2");

btn1.style.display = "block"
btn2.style.display = "none"


const getProd = () => {
    $.ajax({
        type: 'GET',
        headers: { "Accept": "application/json" },
        url: 'http://localhost:3308/NewProy_war_exploded/product'
    }).done(res => {
        let listProducts = res;
        let table = $("#table");

        table.append(
        "<tr class='bg-dark text-light'>"+
        +"<th scope='col'></th>"
        +"<th scope='col'>Código</th>"
        +"<th scope='col'>Nombre</th>"
        +"<th scope='col'>Línea</th>"
        +"<th scope='col'>Escala</th>"
        +"<th scope='col'>Vendedor</th>"
        +"<th scope='col'>Cantidad en Stock</th>"
        +"<th scope='col'>Precio</th>"
        +"<th scope='col'>MSRP</th>"
        +"<th scope='col'>Editar</th>"
        +"<th scope='col'>Borrar</th>"
        +"</tr>")

        for(let i = 0; i < listProducts.length; i++){
            table.append("<tr>"
            +"<td>"+res[i].productCode + "</td>"
            +"<td>"+res[i].productName + "</td>"
            +"<td>"+res[i].productLine + "</td>"
            +"<td>"+res[i].productScale + "</td>"
            +"<td>"+res[i].productVendor + "</td>"
            +"<td>"+res[i].quantityInStock + "</td>"
            +"<td>"+res[i].buyPrice + "</td>"
            +"<td>"+res[i].MSRP + "</td>"
            +"<td><button class='btn btn-warning' onclick=\"findById('"+res[i].productCode+"')\">Editar</button></td>"
            +"<td><button class='btn btn-danger' onclick=\"remove('"+res[i].productCode+"')\">Borrar</button></td>"
            +"</tr>")
        }

    });
};

getProd();

const remove = id => {
    $.ajax({
        type: 'POST',
        headers: { 
            "Accept": "application/json",
            "Content-Type": "application/x-www-form-urlencoded"
        },
        url: 'http://localhost:3308/NewProy_war_exploded/product/delete/'+id
    }).done(res => {
        console.log(res);
    });
}

const findById = id => {
    $.ajax({
        type: 'GET',
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/x-www-form-urlencoded"
        },
        url: 'http://localhost:3308/NewProy_war_exploded/product/'+id
    }).done(res => {
        console.log(res)
        btn1.style.display = "none"
        btn2.style.display = "block"
        
        document.getElementById("productCode").value=res.productCode
        document.getElementById("productName").value=res.productName
        document.getElementById("productLine").value=res.productLine
        document.getElementById("productScale").value=res.productScale
        document.getElementById("productVendor").value=res.phone
        document.getElementById("productDescription").value=res.productDescription
        document.getElementById("quantityInStock").value=res.quantityInStock
        document.getElementById("buyPrice").value=res.buyPrice
        document.getElementById("MSRP").value=res.MSRP
        
    });
}

const update = () => {

    let product = new Object();
    let id = document.getElementById("productCode").value
    product.productCode = document.getElementById("productCode").value
    product.productName = document.getElementById("productName").value
    product.productLine = document.getElementById("productLine").value
    product.productScale = document.getElementById("productScale").value
    product.productDescription = document.getElementById("productDescription").value
    product.quantityInStock = document.getElementById("quantityInStock").value
    product.buyPrice = document.getElementById("buyPrice").value
    product.MSRP = document.getElementById("MSRP").value

    $.ajax({
        type: 'POST',
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/x-www-form-urlencoded"
        },
        url: 'http://localhost:3308/NewProy_war_exploded/product/save/'+id,
        data: product
    }).done(res => {
        console.log(res)
        btn1.style.display = "block"
        btn2.style.display = "none"

        document.getElementById("productCode").value=""
        document.getElementById("productName").value=""
        document.getElementById("productLine").value=""
        document.getElementById("productScale").value=""
        document.getElementById("productDescription").value=""
        document.getElementById("quantityInStock").value=""
        document.getElementById("buyPrice").value=""
        document.getElementById("MSRP").value=""
        document.getElementById("postalCode").value=""
        document.getElementById("country").value=""
        document.getElementById("salesRepEmployeeNumber").value=""
        document.getElementById("creditLimit").value=""
        
    });
}

const save = () => {
    let product = new Object();
    product.productCode = document.getElementById("productCode").value
    product.productName = document.getElementById("productName").value
    product.productLine = document.getElementById("productLine").value
    product.productScale = document.getElementById("productScale").value
    product.productDescription = document.getElementById("productDescription").value
    product.quantityInStock = document.getElementById("quantityInStock").value
    product.buyPrice = document.getElementById("buyPrice").value
    product.MSRP = document.getElementById("MSRP").value

    $.ajax({
        type: 'POST',
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/x-www-form-urlencoded"
        },
        url: 'http://localhost:3308/NewProy_war_exploded/product/save',
        data: product
    }).done(res => {
        console.log(res);
        btn1.style.display = "block"
        btn2.style.display = "none"
    });
}
