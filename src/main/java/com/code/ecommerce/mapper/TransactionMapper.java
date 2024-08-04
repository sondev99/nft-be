package com.code.ecommerce.mapper;

import com.code.ecommerce.dto.request.TransactionRequest;
import com.code.ecommerce.dto.response.TransactionDto;
import com.code.ecommerce.entity.Transaction;
import com.code.ecommerce.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper extends EntityMapper<TransactionDto, Transaction>{
    @Named("mappingUser")
    default String mappingUser(User user) {
        return user.getId();
    }

    Transaction toTransaction(TransactionRequest transactionRequest);

    @Mapping(target = "userId", source = "user", qualifiedByName = "mappingUser")
    List<TransactionDto> toDto(List<Transaction> transaction);
}
