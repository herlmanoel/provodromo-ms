package com.provodromo.institucional.services.base;

import java.util.Set;

public interface BaseService<RequestDTO, ResponseDTO, ID> {

    ResponseDTO findById(ID id);
    Set<ResponseDTO> findAll();
    ResponseDTO create(RequestDTO dto);
    void deleteById(ID id);
}
