package io.swagger.petstore.apiautomation.definitions;

import com.google.gson.Gson;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.swagger.petstore.apiautomation.util.RestAPIClient;
import lombok.val;
import model.order.Order;
import model.pet.Pet;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class PetDefinition {

   @Autowired
    RestAPIClient getPet;
    @Autowired
    RestAPIClient clientPlaceAnOrder;
    @Autowired
    RestAPIClient getOrder;
    Order clientPlaceAnOrderResponse;


    @Given("Client perform post operation for end point {string}")
    public void clientPerformPostOperationForEndPoint(String endPoint, DataTable dataTable) {
          val data= dataTable.asMaps().get(0);
          val status=data.get("pet status");
        getPet.setParameter("status",status);
        val pets= Arrays.stream(getPet.requestSpecification().param("status",status)
                        .get("pet/findByStatus").andReturn().as(Pet[].class))
                .filter(pet->pet.getStatus().equalsIgnoreCase(status)).collect(Collectors.toList());
        val petNames=pets.stream().map(Pet::getName).collect(Collectors.toList());
        val petId=petNames.stream().filter(name->pets.stream().collect(Collectors.groupingBy(Pet::getName)).get(name).size()>=1)
                .map(name->pets.stream().collect(Collectors.groupingBy(Pet::getName)).get(name))
                .flatMap(List::stream)
                .collect(Collectors.toList()).stream().map(Pet::getId).findFirst().orElseGet(null);
        val order= new Order(0L,petId,Integer.parseInt(data.get("quantity")),data.get("status"),
                Boolean.parseBoolean(data.get("complete")));
       clientPlaceAnOrderResponse= clientPlaceAnOrder.post(endPoint, new Gson().toJson(order)).andReturn().as(Order.class);

    }

    @Then("Client should be able to a get the order using end point {string}")
    public void clientShouldBeAbleToAGetTheOrderUsingEndPoint(String endpoint) {
        if(endpoint.contains("{orderId}")) {
            val url = endpoint.replace("{orderId}", clientPlaceAnOrderResponse.getId().toString());
       val response=getOrder.get(url);
            assertEquals("the end point "+endpoint+" should return 200 code",200, response.getStatusCode());
            val actual=response.andReturn().as(Order.class);
            val expected=clientPlaceAnOrderResponse;
            int i=0;
            assertThat("the end point "+endpoint+" should return the order which was added", response.andReturn().as(Order.class)
                    ,equalTo(clientPlaceAnOrderResponse) );
        }
    }
}
