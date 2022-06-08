package model.order;

import lombok.val;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class Order {
    private  Long id;



    private  Long petId;
    private Integer quantity;
    private String shipDate=LocalDateTime.now()+"";
    private String status;
    private Boolean complete;

    @Override
    public boolean equals(Object that){
        if(!(that instanceof Order)) return false;
        val order= (Order) that;
        return   (id.equals(order.getId()))&&
                petId.equals(order.getPetId())&&
                quantity.equals(order.getQuantity())&&
                shipDate.equals(order.getShipDate())&&
                status.equals(order.getStatus())&&
                complete.equals(order.getComplete());

    }
    public Order() {
    }

    public Order(Long id, Long petId, Integer quantity,String status, Boolean complete) {
        this.id = id;
        this.petId = petId;
        this.quantity = quantity;
        this.status = status;
        this.complete = complete;
    }
    public Order( Long petId, Integer quantity,String status, Boolean complete) {

        this.petId = petId;
        this.quantity = quantity;
        this.status = status;
        this.complete = complete;
    }

    public Long getId() {
        return id;
    }

    public Long getPetId() {
        return petId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getShipDate() {

       return shipDate;
    }

    public String getStatus() {
        return status;
    }

    public Boolean getComplete() {
        return complete;
    }
}
