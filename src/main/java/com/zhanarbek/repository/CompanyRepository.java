package com.zhanarbek.repository;

import com.zhanarbek.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Author: Zhanarbek Abdurasulov
 * Date: 25/3/22
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query("select case when count(s) > 0 then true else false end " +
            "from Company s where s.companyName = ?1 and s.locatedCity = ?2")
    boolean existsByCompanyName(String companyName, String locatedCity);
}
