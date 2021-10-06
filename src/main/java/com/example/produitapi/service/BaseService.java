package com.example.produitapi.service;

import java.util.List;

public interface BaseService<TDTO, TFORMU, TFORMI, TID> {

    TDTO getOne(TID id);
    List<TDTO> getAll();
    TDTO insert(TFORMI form);
    TDTO delete(TID id);
    TDTO update(TID id, TFORMU form);


}
