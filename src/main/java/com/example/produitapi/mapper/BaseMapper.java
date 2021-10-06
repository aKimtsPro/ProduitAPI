package com.example.produitapi.mapper;

public interface BaseMapper<TDTO, TENTITY> {

    TDTO toDto(TENTITY entity);
    TENTITY toEntity(TDTO dto);

}
