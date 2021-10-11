package kz.hawk.risesecurity.in_service.impl;

import kz.hawk.risesecurity.in_service.RequestDefenderInService;
import kz.hawk.risesecurity.model.in_service.request.CheckRequestInService;
import kz.hawk.risesecurity.model.request.PrepareRequest;
import kz.hawk.risesecurity.model.in_service.response.CheckResponseInService;
import kz.hawk.risesecurity.model.response.PrepareResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.util.IdGenerator;

@RequiredArgsConstructor
public class RequestDefenderInServiceFake implements RequestDefenderInService {

  private final IdGenerator idGenerator;

  @Override
  public PrepareResponse prepare(PrepareRequest requestData) {
    return null;
  }

  @Override
  public CheckResponseInService check(CheckRequestInService requestData) {
    return null;
  }
}
