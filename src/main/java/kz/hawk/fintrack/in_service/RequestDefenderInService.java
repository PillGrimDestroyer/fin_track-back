package kz.hawk.fintrack.in_service;

import kz.hawk.fintrack.model.in_service.request.CheckRequestInService;
import kz.hawk.fintrack.model.request.PrepareRequest;
import kz.hawk.fintrack.model.in_service.response.CheckResponseInService;
import kz.hawk.fintrack.model.response.PrepareResponse;

public interface RequestDefenderInService {
  PrepareResponse prepare(PrepareRequest requestData);

  CheckResponseInService check(CheckRequestInService requestData);
}
