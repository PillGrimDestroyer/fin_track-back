package kz.hawk.risesecurity.model.in_service.request;

import lombok.Data;

import java.math.BigInteger;

@Data
public class PrepareRequestInService {
  private BigInteger userResult;
  private BigInteger generator;
  private BigInteger module;
}
