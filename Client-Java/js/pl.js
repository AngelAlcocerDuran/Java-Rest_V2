
let btn1 = document.getElementById("btn1");
let btn2 = document.getElementById("btn2");

btn1.style.display = "block"
btn2.style.display = "none"

const getPLs = () => {
    $.ajax({
        type: 'GET',
        headers: { "Accept": "application/json" },
        url: 'http://localhost:3308/NewProy_war_exploded/pl'
    }).done(res => {
        let listPLs = res;
        let table = $("#table");

        table.append(
        "<tr class='bg-dark text-light'>"+
        +"<th scope='col'></th>"
        +"<th scope='col'>Línea de Producto</th>"
        +"<th scope='col'>Descripción</th>"
        +"<th scope='col'>Editar</th>"
        +"<th scope='col'>Borrar</th>"
        +"</tr>")

        for(let i = 0; i < listPLs.length; i++){
            table.append("<tr>"
            +"<td>"+res[i].productLine + "</td>"
            +"<td>"+res[i].textDescription + "</td>"
            +"<td><button class='btn btn-warning' onclick=\"findById('"+res[i].productLine+"')\">Editar</button></td>"
            +"<td><button class='btn btn-danger' onclick=\"remove('"+res[i].productLine+"')\">Borrar</button></td>"
            +"</tr>")
        }

    });
};

getPLs();

const remove = id => {
    $.ajax({
        type: 'POST',
        headers: { 
            "Accept": "application/json",
        },
        url: 'http://localhost:3308/NewProy_war_exploded/pl/delete/'+id
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
        url: 'http://localhost:3308/NewProy_war_exploded/pl/'+id
    }).done(res => {
        console.log(res)
        btn1.style.display = "none"
        btn2.style.display = "block"
        
        document.getElementById("productLine").value=res.productLine
        document.getElementById("textDescription").value=res.textDescription
        
    });
}

const update = () => {

    let pl = new Object();
    let id = document.getElementById("productLine").value
    pl.productLine = document.getElementById("productLine").value
    pl.textDescription = document.getElementById("textDescription").value

    $.ajax({
        type: 'POST',
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/x-www-form-urlencoded"
        },
        url: 'http://localhost:3308/NewProy_war_exploded/pl/save/'+id,
        data: pl
    }).done(res => {
        console.log(res)
        btn1.style.display = "block"
        btn2.style.display = "none"

        document.getElementById("productLine").value=""
        document.getElementById("textDescription").value=""
        
    });
}

const save = () => {
    let pl = new Object();
    pl.productLine = document.getElementById("productLine").value
    pl.textDescription = document.getElementById("textDescription").value

    $.ajax({
        type: 'POST',
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/x-www-form-urlencoded"
        },
        url: 'http://localhost:3308/NewProy_war_exploded/pl/save',
        data: pl
    }).done(res => {
        console.log(res);
        btn1.style.display = "block"
        btn2.style.display = "none"
    });
}
