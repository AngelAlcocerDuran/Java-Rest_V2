package mx.edu.utez.controller;

import mx.edu.utez.model.product.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.util.List;

@Path("/product")
public class ServiceProducts {

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getProducts() {
        return new DaoProduct().findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product getProduct(@PathParam("id") String productCode) {
        return new DaoProduct().findByProductCode(productCode);
    }

    @POST
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public Product save(MultivaluedMap<String, String> formParams) {
        String productCode = formParams.get("productCode").get(0);

        if (new DaoProduct().saveProduct(getParams(productCode, formParams), true)){
            return new DaoProduct().findByProductCode(productCode);
        }else{
            return null;
        }
    }

    @POST
    @Path("/save/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public Product save(@PathParam("id") String productCode, MultivaluedMap<String, String> formParams) {
        if (new DaoProduct().saveProduct(getParams(productCode, formParams), false)){
            return new DaoProduct().findByProductCode(productCode);
        }else{
            return null;
        }
    }

    @POST
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean deleteCustomer(@PathParam("id") String productCode) {
        return new DaoProduct().deleteProduct(productCode);
    }

    private Product getParams(String productCode, MultivaluedMap<String, String> formParams) {
        String productName = formParams.get("productName").get(0);
        String productLine = formParams.get("productLine").get(0);
        String productScale = formParams.get("productScale").get(0);
        String productVendor = formParams.get("productVendor").get(0);
        String productDescription = formParams.get("productDescription").get(0);
        int quantityInStock = Integer.parseInt(formParams.get("quantityInStock").get(0));
        double buyPrice = Double.parseDouble(formParams.get("buyPrice").get(0));
        double MSRP = Double.parseDouble(formParams.get("MSRP").get(0));

        Product p = new Product(productCode, productName, productLine, productScale, productVendor, productDescription, quantityInStock, buyPrice, MSRP);
        System.out.println(p);
        return p;
    }
    
}
