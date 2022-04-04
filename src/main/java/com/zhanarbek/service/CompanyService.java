package com.zhanarbek.service;

import com.zhanarbek.dto.CompanyDTO;
import com.zhanarbek.model.Company;
import com.zhanarbek.response.Response;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Author: Zhanarbek Abdurasulov
 * Date: 26/3/22
 */
public interface CompanyService {
    Response saveCompany(CompanyDTO companyDTO);

    void doesCompanyExist(CompanyDTO companyDTO);

    List<CompanyDTO> findAllCompanies();

    CompanyDTO getCompanyById (Long id);

    @Transactional
    Response updateCompanyById(Long id, Company newCompany);

    Response deleteCompanyById(Long id);
}
