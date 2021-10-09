package mx.edu.utez.controller;

import mx.edu.utez.model.productline.DaoProductLine;
import mx.edu.utez.model.productline.ProductLine;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.util.List;

@Path("pl")
public class ServiceProductLines {

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductLine> getProductLines() {
        return new DaoProductLine().findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ProductLine getProductLines(@PathParam("id") String productLine) {
        return new DaoProductLine().findByProductLine(productLine);
    }

    @POST
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public ProductLine save(MultivaluedMap<String, String> formParams) {
        String productLine = formParams.get("productLine").get(0);

        if (new DaoProductLine().saveProductLine(getParams(productLine, formParams), true)){
            return new DaoProductLine().findByProductLine(productLine);
        }else{
            return null;
        }
    }

    @POST
    @Path("/save/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public ProductLine save(@PathParam("id") String productLine, MultivaluedMap<String, String> formParams) {
        if (new DaoProductLine().saveProductLine(getParams(productLine, formParams), false)){
            return new DaoProductLine().findByProductLine(productLine);
        }else{
            return null;
        }
    }

    @POST
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public boolean deleteCustomer(@PathParam("id") String productLine) {
        return new DaoProductLine().deleteProductLine(productLine);
    }

    private ProductLine getParams(String productLine, MultivaluedMap<String, String> formParams) {
        String textDescription = formParams.get("city").get(0);

        ProductLine p = new ProductLine(productLine, textDescription);
        System.out.println(p);
        return p;
    }
}
