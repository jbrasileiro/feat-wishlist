package org.wishlist.domain.commons;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PaginationTO {

  private static final Integer DEFAULT_SIZE = 50;
  private static final Integer DEFAULT_PAGE = 0;

@Schema(
      description = "Page",
      defaultValue = "0",
      minimum = "0",
      requiredMode = RequiredMode.NOT_REQUIRED
  )
  @Min(0)
  private Integer page = DEFAULT_PAGE;

  @Schema(
      description = "Number of elements per page",
      defaultValue = "50",
      minimum = "1",
      maximum = "100",
      requiredMode = RequiredMode.NOT_REQUIRED
  )
  @Min(1)
  @Max(100)
  private Integer size = DEFAULT_SIZE;

}
