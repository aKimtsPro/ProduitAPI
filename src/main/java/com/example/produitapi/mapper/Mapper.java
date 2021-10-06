package com.example.produitapi.mapper;

public interface Mapper<TDTO, TFORM, TENTITY> extends BaseMapper<TDTO, TENTITY>, FormMapper<TFORM, TENTITY> {
}
