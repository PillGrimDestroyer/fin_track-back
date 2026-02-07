package kz.hawk.fintrack.mapper;


import kz.hawk.fintrack.beans.MapperConfiguration;
import kz.hawk.fintrack.model.TransactionDataSliceFilter;
import kz.hawk.fintrack.model.dao.TransactionDto;
import kz.hawk.fintrack.model.request.TransactionFilteredDataSliceRequest;
import kz.hawk.fintrack.model.response.TransactionResponse;
import kz.hawk.fintrack.register.SessionRegister;
import org.mapstruct.*;

import java.util.Optional;

/**
 * @author megam
 * @since 24.01.2026 16:10
 */
@Mapper(config = MapperConfiguration.class, uses = {CategoryMapper.class})
public interface TransactionMapper {

  TransactionResponse toResponse(TransactionDto transactionDto);

  @Mappings({
    @Mapping(target = TransactionDataSliceFilter.Fields.userId, ignore = true),
    @Mapping(source = TransactionFilteredDataSliceRequest.Fields.transactionType, target = TransactionDataSliceFilter.Fields.type),
    @Mapping(source = TransactionFilteredDataSliceRequest.Fields.transactionRangeFrom, target = TransactionDataSliceFilter.Fields.rangeFrom),
    @Mapping(source = TransactionFilteredDataSliceRequest.Fields.transactionRangeTo, target = TransactionDataSliceFilter.Fields.rangeTo),
    @Mapping(source = TransactionFilteredDataSliceRequest.Fields.categoryNameEn, target = TransactionDataSliceFilter.Fields.category)
  })
  TransactionDataSliceFilter toFilter(TransactionFilteredDataSliceRequest request, @Context SessionRegister sessionRegister);

  @AfterMapping
  default void refineFilter(
    TransactionFilteredDataSliceRequest request,
    @MappingTarget TransactionDataSliceFilter filter,
    @Context SessionRegister sessionRegister
  ) {
    filter.setUserId(sessionRegister.currentUserId());

    filter.setDescription(Optional.ofNullable(request.getDescription())
                                  .filter(x -> !x.isBlank())
                                  .map(String::trim)
                                  .orElse(null));

    filter.setCategory(Optional.ofNullable(request.getCategoryNameEn())
                               .filter(x -> !x.isBlank())
                               .map(String::trim)
                               .orElse(null));
  }
}
