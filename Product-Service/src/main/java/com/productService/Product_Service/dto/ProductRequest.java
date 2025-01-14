package com.productService.Product_Service.dto;

import java.math.BigDecimal;

public record ProductRequest(
    String id,
    String name,
    String description,
    BigDecimal price)
{

}
