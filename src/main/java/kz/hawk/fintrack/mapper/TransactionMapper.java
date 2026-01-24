package kz.hawk.fintrack.mapper;


import kz.hawk.fintrack.beans.MapperConfiguration;
import kz.hawk.fintrack.model.dao.TransactionDto;
import kz.hawk.fintrack.model.response.TransactionResponse;
import org.mapstruct.Mapper;

/**
 * @author megam
 * @since 24.01.2026 16:10
 */
@Mapper(config = MapperConfiguration.class, uses = {CategoryMapper.class})
public interface TransactionMapper {

  TransactionResponse toResponse(TransactionDto transactionDto);

}
