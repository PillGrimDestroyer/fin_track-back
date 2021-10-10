package kz.hawk.risesecurity.model.in_service.response;

import lombok.Data;

import java.math.BigInteger;
import java.util.UUID;

@Data
public class PrepareResponseInService {
  private UUID       id;
  private BigInteger serverResult;
}
