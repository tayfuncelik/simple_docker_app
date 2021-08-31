package com.example.dockerapp.payload;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReverseReq {

  @NotEmpty(message = "Please provide a string value")
  @ApiModelProperty(value = "reverseParameter", name = "reverseParameter", dataType = "String", example = "exercise")
  private String reverseParameter;
}
