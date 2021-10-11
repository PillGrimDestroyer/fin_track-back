package kz.hawk.risesecurity.model.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Data
@Builder
public class PrepareRequest {

  @NotNull(message = "The 'a' is required")
  @Min(value = 1, message = "The 'a' is must be greater or equal 1")
  @JsonAlias("a")
  private BigInteger generator;

  @NotNull(message = "The 'b' is required")
  @Min(value = 1, message = "The 'b' is must be greater or equal 1")
  @JsonAlias("b")
  private BigInteger module;

  @NotNull(message = "The 'c' is required")
  @Min(value = 1, message = "The 'c' is must be greater or equal 1")
  @JsonAlias("c")
  private BigInteger userResult;

}
