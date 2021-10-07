
let btn1 = document.getElementById("btn1");
let btn2 = document.getElementById("btn2");

btn1.style.display = "block"
btn2.style.display = "none"

const getEmployees = () => {
    $.ajax({
        type: 'GET',
        headers: { "Accept": "application/json" },
        url: 'http://localhost:3308/NewProy_war_exploded/customer'
    }).done(res => {
        let listCustomers = res;
        let table = $("#table");

        table.append(
        "<tr class='bg-dark text-light'>"+
        +"<th scope='col'></th>"
        +"<th scope='col'>#</th>"
        +"<th scope='col'>Nombre</th>"
        +"<th scope='col'>Nombre del Contacto</th>"
        +"<th scope='col'>Teléfono</th>"
        +"<th scope='col'>Dirección</th>"
        +"<th scope='col'>Ubicación</th>"
        +"<th scope='col'>Representante de Venta</th>"
        +"<th scope='col'>Límite de Crédito</th>"
        +"<th scope='col'>Editar</th>"
        +"<th scope='col'>Borrar</th>"
        +"</tr>")

        for(let i = 0; i < listCustomers.length; i++){
            table.append("<tr>"
            +"<td>"+res[i].customerNumber + "</td>"
            +"<td>"+res[i].customerName + "</td>"
            +"<td>"+res[i].contactLastName+" "+res[i].contactFirstName + "</td>"
            +"<td>"+res[i].phone + "</td>"
            +"<td>"+res[i].addressLine1 + "</td>"
            +"<td>"+res[i].city+", "+ res[i].country+ "</td>"
            +"<td>"+res[i].salesRepEmployeeNumber + "</td>"
            +"<td>"+res[i].creditLimit + "</td>"
            +"<td><button class='btn btn-warning' onclick='findById("+res[i].customerNumber+")'>Editar</button></td>"
            +"<td><button class='btn btn-danger' onclick='remove("+res[i].customerNumber+")'>Borrar</button></td>"
            +"</tr>")
        }

    });
};

getEmployees();

const remove = id => {
    $.ajax({
        type: 'DELETE',
        headers: { 
            "Accept": "application/json",
        },
        url: 'http://localhost:3308/NewProy_war_exploded/customer/delete/'+id
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
        url: 'http://localhost:3308/NewProy_war_exploded/customer/'+id
    }).done(res => {
        console.log(res)
        btn1.style.display = "none"
        btn2.style.display = "block"
        
        document.getElementById("customerNumber").value=res.customerNumber
        document.getElementById("customerName").value=res.customerName
        document.getElementById("contactLastName").value=res.contactLastName
        document.getElementById("contactFirstName").value=res.contactFirstName
        document.getElementById("phone").value=res.phone
        document.getElementById("addressLine1").value=res.addressLine1
        document.getElementById("addressLine2").value=res.addressLine2
        document.getElementById("city").value=res.city
        document.getElementById("state").value=res.state
        document.getElementById("postalCode").value=res.postalCode
        document.getElementById("country").value=res.country
        document.getElementById("salesRepEmployeeNumber").value=res.salesRepEmployeeNumber
        document.getElementById("creditLimit").value=res.creditLimit
        
    });
}

const update = () => {

    let customer = new Object();
    let id = document.getElementById("customerNumber").value
    customer.customerNumber = document.getElementById("customerNumber").value
    customer.customerName = document.getElementById("customerName").value
    customer.contactLastName = document.getElementById("contactLastName").value
    customer.contactFirstName = document.getElementById("contactFirstName").value
    customer.phone = document.getElementById("phone").value
    customer.addressLine1 = document.getElementById("addressLine1").value
    customer.addressLine2 = document.getElementById("addressLine2").value
    customer.city = document.getElementById("city").value
    customer.state = document.getElementById("state").value
    customer.postalCode = document.getElementById("postalCode").value
    customer.country = document.getElementById("country").value
    customer.salesRepEmployeeNumber = document.getElementById("salesRepEmployeeNumber").value
    customer.creditLimit = document.getElementById("creditLimit").value

    $.ajax({
        type: 'POST',
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/x-www-form-urlencoded"
        },
        url: 'http://localhost:3308/NewProy_war_exploded/customer/save/'+id,
        data: customer
    }).done(res => {
        console.log(res)
        btn1.style.display = "block"
        btn2.style.display = "none"

        document.getElementById("customerNumber").value=""
        document.getElementById("customerName").value=""
        document.getElementById("contactLastName").value=""
        document.getElementById("contactFirstName").value=""
        document.getElementById("phone").value=res.phone
        document.getElementById("addressLine1").value=""
        document.getElementById("addressLine2").value=""
        document.getElementById("city").value=""
        document.getElementById("state").value=""
        document.getElementById("postalCode").value=""
        document.getElementById("country").value=""
        document.getElementById("salesRepEmployeeNumber").value=""
        document.getElementById("creditLimit").value=""
        
    });
}

const save = () => {
    let customer = new Object();
    customer.customerNumber = document.getElementById("customerNumber").value
    customer.customerName = document.getElementById("customerName").value
    customer.contactLastName = document.getElementById("contactLastName").value
    customer.contactFirstName = document.getElementById("contactFirstName").value
    customer.phone = document.getElementById("phone").value
    customer.addressLine1 = document.getElementById("addressLine1").value
    customer.addressLine2 = document.getElementById("addressLine2").value
    customer.city = document.getElementById("city").value
    customer.state = document.getElementById("state").value
    customer.postalCode = document.getElementById("postalCode").value
    customer.country = document.getElementById("country").value
    customer.salesRepEmployeeNumber = document.getElementById("salesRepEmployeeNumber").value
    customer.creditLimit = document.getElementById("creditLimit").value

    $.ajax({
        type: 'POST',
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/x-www-form-urlencoded"
        },
        url: 'http://localhost:3308/NewProy_war_exploded/customer/save',
        data: customer
    }).done(res => {
        console.log(res);
        btn1.style.display = "block"
        btn2.style.display = "none"
    });
}
