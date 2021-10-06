package com.example.produitapi.mapper;

public interface FormMapper<TFORM, TENTITY> {

    TENTITY formToEntity(TFORM form);
}
