package com.code.ecommerce.mapper;

import com.code.ecommerce.dto.request.TransactionRequest;
import com.code.ecommerce.dto.response.TransactionDto;
import com.code.ecommerce.entity.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper extends EntityMapper<TransactionDto, Transaction>{
    Transaction toTransaction(TransactionRequest transactionRequest);

}
