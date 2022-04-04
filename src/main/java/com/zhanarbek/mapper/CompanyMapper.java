package com.zhanarbek.mapper;

import com.zhanarbek.dto.CompanyDTO;
import com.zhanarbek.model.Company;
import org.mapstruct.Mapper;


import java.util.List;

/**
 * Author: Zhanarbek Abdurasulov
 * Date: 3/4/22
 */
//This interfaces will be automatically implemented by library
@Mapper(componentModel = "spring")
public interface CompanyMapper {

    //Converting dto to entity
    Company dtoToEntity(CompanyDTO dto);

    //Converting entity to dto
    CompanyDTO entityToDto(Company company);

    //Converting list to dto list
    List<CompanyDTO> entityListToDtoList(List<Company> companies);

    List<Company> dtoListToEntityList(List<CompanyDTO> dtoCompanies);


}
