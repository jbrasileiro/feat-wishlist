package org.wishlist.domain.exceptions;

import java.io.Serial;

public class ApplicationRuntimeException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 1L;

  public ApplicationRuntimeException(String message, Throwable cause) {
    super(message, cause);
  }

  public ApplicationRuntimeException(String message) {
    super(message);
  }
}
