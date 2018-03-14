package com.gmail.dmitrypashko.dmitry.modelDTO;

import java.util.ArrayList;
import java.util.List;

public class OrderDTO {
    private Long id;
    private String status;
    private List<ProductInOrderDTO> productInOrderDTO = new ArrayList<>();


    public List<ProductInOrderDTO> getProductInOrderDTO() {
        return productInOrderDTO;
    }

    public void setProductInOrderDTO(List<ProductInOrderDTO> productInOrderDTO) {
        this.productInOrderDTO = productInOrderDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
