package kz.hawk.risesecurity.in_service;

import kz.hawk.risesecurity.model.in_service.request.CheckRequestInService;
import kz.hawk.risesecurity.model.request.PrepareRequest;
import kz.hawk.risesecurity.model.in_service.response.CheckResponseInService;
import kz.hawk.risesecurity.model.response.PrepareResponse;

public interface RequestDefenderInService {
  PrepareResponse prepare(PrepareRequest requestData);

  CheckResponseInService check(CheckRequestInService requestData);
}
