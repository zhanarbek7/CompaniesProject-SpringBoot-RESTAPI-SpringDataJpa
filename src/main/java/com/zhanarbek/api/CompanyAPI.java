package com.zhanarbek.api;

import com.zhanarbek.dto.CompanyDTO;
import com.zhanarbek.exception.BadRequestException;
import com.zhanarbek.exception.NotFoundException;
import com.zhanarbek.response.Response;
import lombok.RequiredArgsConstructor;
import com.zhanarbek.model.Company;
import com.zhanarbek.service.CompanyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Author: Zhanarbek Abdurasulov
 * Date: 25/3/22
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/company")
public class CompanyAPI {

    private final CompanyService service;

    @GetMapping("/getCompany")
    public List<CompanyDTO> getAllCompanies(){
        return service.findAllCompanies();
    }

    @GetMapping("/get/{id}")
    public CompanyDTO getCompanyById(@PathVariable("id") Long id) {
        return (service.getCompanyById(id));
    }

    @PostMapping("/save")
    public Response saveCompany(@RequestBody CompanyDTO companyDTO){
        return service.saveCompany(companyDTO);
    }

    @PatchMapping("/update/{id}")
    public Response updateCompany(@RequestBody Company company, @PathVariable("id") Long id){
        return service.updateCompanyById(id, company);
    }

    @DeleteMapping("/delete/{id}")
    public Response delete(@PathVariable("id") Long id) {
        return service.deleteCompanyById(id);
    }



    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(BAD_REQUEST)
    public Response handleBadRequestException(BadRequestException badRequestException) {
        return Response.builder()
                .httpStatus(BAD_REQUEST)
                .message(badRequestException.getMessage())
                .build();
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public Response handleNotFoundException(NotFoundException notFoundException) {
        return Response.builder()
                .httpStatus(NOT_FOUND)
                .message(notFoundException.getMessage())
                .build();
    }

}
