package kz.hawk.fintrack.in_service.impl;

import kz.hawk.fintrack.in_service.RequestDefenderInService;
import kz.hawk.fintrack.model.in_service.request.CheckRequestInService;
import kz.hawk.fintrack.model.request.PrepareRequest;
import kz.hawk.fintrack.model.in_service.response.CheckResponseInService;
import kz.hawk.fintrack.model.response.PrepareResponse;
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
