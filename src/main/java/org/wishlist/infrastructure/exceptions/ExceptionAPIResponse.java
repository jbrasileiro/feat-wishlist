package org.wishlist.infrastructure.exceptions;

import java.time.LocalDateTime;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(Include.NON_EMPTY)
public class ExceptionAPIResponse {

  @Schema(description = "Description")
  private String description;

  @Schema(description = "HTTP status code")
  private int status;

  @Schema(description = "URI", example = "/v1/some/path")
  private String path;

  @Schema(description = "Date and time")
  private LocalDateTime timestamp;

  @Schema(description = "List of detail")
  private Collection<ExceptionAPIDetail> detail;

  public record ExceptionAPIDetail(String detail, String message) {
  }

}
