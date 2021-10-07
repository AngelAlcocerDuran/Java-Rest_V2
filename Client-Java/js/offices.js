
let btn1 = document.getElementById("btn1");
let btn2 = document.getElementById("btn2");

btn1.style.display = "block"
btn2.style.display = "none"

const getOffices = () => {
    $.ajax({
        type: 'GET',
        headers: { "Accept": "application/json" },
        url: 'http://localhost:3308/NewProy_war_exploded/office'
    }).done(res => {
        let listOffices = res;
        let table = $("#table");

        table.append(
        "<tr class='bg-dark text-light'>"+
        +"<th scope='col'></th>"
        +"<th scope='col'>#</th>"
        +"<th scope='col'>Teléfono</th>"
        +"<th scope='col'>Direccion 1</th>"
        +"<th scope='col'>Dirección 2</th>"
        +"<th scope='col'>Ciudad</th>"
        +"<th scope='col'>Estado</th>"
        +"<th scope='col'>País</th>"
        +"<th scope='col'>Código Postal</th>"
        +"<th scope='col'>Territorio</th>"
        +"<th scope='col'>Editar</th>"
        +"<th scope='col'>Borrar</th>"
        +"</tr>")

        for(let i = 0; i < listOffices.length; i++){
            table.append("<tr>"
            +"<td>"+res[i].officeCode + "</td>"
            +"<td>"+res[i].phone + "</td>"
            +"<td>"+res[i].addressLine1+ "</td>"
            +"<td>"+res[i].addressLine2 + "</td>"
            +"<td>"+res[i].city + "</td>"
            +"<td>"+res[i].state+ "</td>"
            +"<td>"+res[i].country+ "</td>"
            +"<td>"+res[i].postalCode + "</td>"
            +"<td>"+res[i].territory + "</td>"
            +"<td><button class='btn btn-warning' onclick='findById("+res[i].officeCode+")'>Editar</button></td>"
            +"<td><button class='btn btn-danger' onclick='remove("+res[i].officeCode+")'>Borrar</button></td>"
            +"</tr>")
        }

    });
};

getOffices();

const remove = id => {
    $.ajax({
        type: 'POST',
        headers: { 
            "Accept": "application/json",
            "Content-Type": "application/x-www-form-urlencoded"
        },
        url: 'http://localhost:3308/NewProy_war_exploded/office/delete/'+id
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
        url: 'http://localhost:3308/NewProy_war_exploded/office/'+id
    }).done(res => {
        console.log(res)
        btn1.style.display = "none"
        btn2.style.display = "block"
        
        document.getElementById("officeCode").value=res.officeCode
        document.getElementById("phone").value=res.phone
        document.getElementById("addressLine1").value=res.addressLine1
        document.getElementById("addressLine2").value=res.addressLine2
        document.getElementById("city").value=res.city
        document.getElementById("state").value=res.state
        document.getElementById("postalCode").value=res.postalCode
        document.getElementById("country").value=res.country
        document.getElementById("territory").value=res.territory
        
    });
}

const update = () => {

    let office = new Object();
    let id = document.getElementById("officeCode").value
    office.officeCode = document.getElementById("officeCode").value
    office.phone = document.getElementById("phone").value
    office.addressLine1 = document.getElementById("addressLine1").value
    office.addressLine2 = document.getElementById("addressLine2").value
    office.city = document.getElementById("city").value
    office.state = document.getElementById("state").value
    office.postalCode = document.getElementById("postalCode").value
    office.country = document.getElementById("country").value
    office.territory = document.getElementById("territory").value

    $.ajax({
        type: 'POST',
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/x-www-form-urlencoded"
        },
        url: 'http://localhost:3308/NewProy_war_exploded/office/save/'+id,
        data: office
    }).done(res => {
        console.log(res)
        btn1.style.display = "block"
        btn2.style.display = "none"

        document.getElementById("officeCode").value=""
        document.getElementById("phone").value=res.phone
        document.getElementById("addressLine1").value=""
        document.getElementById("addressLine2").value=""
        document.getElementById("city").value=""
        document.getElementById("state").value=""
        document.getElementById("postalCode").value=""
        document.getElementById("country").value=""
        document.getElementById("territory").value=""
        
    });
}

const save = () => {
    let office = new Object();
    office.officeCode = document.getElementById("officeCode").value
    office.phone = document.getElementById("phone").value
    office.addressLine1 = document.getElementById("addressLine1").value
    office.addressLine2 = document.getElementById("addressLine2").value
    office.city = document.getElementById("city").value
    office.state = document.getElementById("state").value
    office.postalCode = document.getElementById("postalCode").value
    office.country = document.getElementById("country").value
    office.territory = document.getElementById("territory").value

    $.ajax({
        type: 'POST',
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/x-www-form-urlencoded"
        },
        url: 'http://localhost:3308/NewProy_war_exploded/office/save',
        data: office
    }).done(res => {
        console.log(res);
        btn1.style.display = "block"
        btn2.style.display = "none"
    });
}
