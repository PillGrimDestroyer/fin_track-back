package kz.hawk.fintrack.model.response;

import lombok.Data;

import java.math.BigInteger;
import java.util.UUID;

@Data
public class PrepareResponse {
  private UUID       id;
  private BigInteger serverResult;
}
