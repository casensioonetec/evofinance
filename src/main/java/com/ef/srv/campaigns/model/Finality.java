package com.ef.srv.campaigns.model;

import lombok.Builder;
import lombok.Data;
import com.ef.srv.campaigns.model.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Finality
 */

@Data
@Builder
public class Finality  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("name")
  private String name;
  
  @JsonProperty("products")

  @ApiModelProperty(required = true, value = "Productos")
  private List<Product> products = new ArrayList<>();
  
}

