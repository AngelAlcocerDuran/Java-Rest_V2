package mx.edu.utez.controller;

import mx.edu.utez.model.office.Office;
import mx.edu.utez.model.office.DaoOffice;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.util.List;

//ANGEL YAZVECK ALCOCER DURÁN 4B DSM

@Path("/office")
public class ServiceOffices {

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Office> getOffices() {
        return new DaoOffice().findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Office getCustomer(@PathParam("id") String officeCode) {
        return new DaoOffice().findByOfficeCode(officeCode);
    }

    @POST
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public Office save(MultivaluedMap<String, String> formParams) {
        String officeCode = formParams.get("officeCode").get(0);

        if (new DaoOffice().saveOffice(getParams(officeCode, formParams), true)){
            return new DaoOffice().findByOfficeCode(officeCode);
        }else{
            return null;
        }
    }

    @POST
    @Path("/save/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public Office save(@PathParam("id") String officeCode, MultivaluedMap<String, String> formParams) {
        if (new DaoOffice().saveOffice(getParams(officeCode, formParams), false)){
            return new DaoOffice().findByOfficeCode(officeCode);
        }else{
            return null;
        }
    }

    @POST
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public boolean deleteCustomer(@PathParam("id") String officeCode) {
        return new DaoOffice().deleteOffice(officeCode);
    }

    private Office getParams(String officeCode, MultivaluedMap<String, String> formParams) {
        String city = formParams.get("city").get(0);
        String phone = formParams.get("phone").get(0);
        String addressLine1 = formParams.get("addressLine1").get(0);
        String addressLine2 = formParams.get("addressLine2").get(0);
        String state = formParams.get("state").get(0);
        String country = formParams.get("country").get(0);
        String postalCode = formParams.get("postalCode").get(0);
        String territory = formParams.get("territory").get(0);

        Office o = new Office(officeCode, city, phone, addressLine1, addressLine2, state, country, postalCode, territory);
        System.out.println(o);
        return o;
    }
}
