package kz.hawk.risesecurity.in_service;

import kz.hawk.risesecurity.model.in_service.request.PrepareRequestInService;
import kz.hawk.risesecurity.model.in_service.response.PrepareResponseInService;

public interface RequestDefenderInService {
  PrepareResponseInService prepare(PrepareRequestInService requestData);
}
