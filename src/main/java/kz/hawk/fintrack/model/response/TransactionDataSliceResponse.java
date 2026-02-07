package kz.hawk.fintrack.model.response;


import lombok.Data;

import java.util.List;

/**
 * @author Work
 * @since 07.02.2026 9:24
 */
@Data
public class TransactionDataSliceResponse {

  private List<TransactionResponse> content;
  private int                       totalPages;

}
