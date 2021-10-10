package kz.hawk.risesecurity.in_service.impl;

import kz.hawk.risesecurity.in_service.RequestDefenderInService;
import kz.hawk.risesecurity.model.in_service.request.PrepareRequestInService;
import kz.hawk.risesecurity.model.in_service.response.PrepareResponseInService;
import lombok.RequiredArgsConstructor;
import org.springframework.util.IdGenerator;

@RequiredArgsConstructor
public class RequestDefenderInServiceFake implements RequestDefenderInService {

  private final IdGenerator idGenerator;

  @Override
  public PrepareResponseInService prepare(PrepareRequestInService requestData) {
    return null;
  }
}
