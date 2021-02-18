package com.warehouse_accounting.services.impl;

import com.warehouse_accounting.models.dto.ProductGroupDto;
import com.warehouse_accounting.services.interfaces.ProductGroupService;
import com.warehouse_accounting.services.interfaces.api.ProductGroupApi;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Log4j2
@Service
public class ProductGroupServiceImpl implements ProductGroupService {
    private final ProductGroupApi productGroupApi;
    private final String productGroupUrl;

    public ProductGroupServiceImpl(@Value("${retrofit.restServices.product_group_url}") String productGroupUrl, Retrofit retrofit) {
        this.productGroupUrl = productGroupUrl;
        this.productGroupApi = retrofit.create(ProductGroupApi.class);
    }

    @Override
    public List<ProductGroupDto> getAll() {
        List<ProductGroupDto> contractorGroupDtoList = Collections.emptyList();
        Call<List<ProductGroupDto>> groupApiAll = productGroupApi.getAll(productGroupUrl);
        try {
            contractorGroupDtoList = groupApiAll.execute().body();
            log.info("Успешно выполнен запрос на получение списка ProductGroupDto");
        } catch (IOException e) {
            log.error("Произошла ошибка при выполнении запроса на получение списка ProductGroupDto", e);
        }
        return contractorGroupDtoList;
    }

    @Override
    public ProductGroupDto getById(Long id) {
        ProductGroupDto contractorGroupDto = null;
        Call<ProductGroupDto> callSync = productGroupApi.getById(productGroupUrl, id);
        try {
            Response<ProductGroupDto> response = callSync.execute();
            contractorGroupDto = response.body();
            log.info("Успешно выполнен запрос на получение ProductGroupDto по id: {}", id);
        } catch (Exception e) {
            log.error("Произошла ошибка при выполнении запроса на получение ProductGroupDto по id", e);
        }
        return contractorGroupDto;
    }

    @Override
    public void create(ProductGroupDto dto) {
        Call<Void> call = productGroupApi.create(productGroupUrl, dto);
        try {
            if (call.execute().isSuccessful()) {
                log.info("Успешно выполнен запрос на создание ProductGroupDto");
            } else {
                log.error("Произошла ошибка при выполнении запроса на создании ProductGroupDto");
            }
        } catch (IOException e) {
            log.error("Произошла ошибка при выполнении запроса на создании ProductGroupDto", e);
        }
    }

    @Override
    public void update(ProductGroupDto dto) {
        Call<Void> call = productGroupApi.update(productGroupUrl, dto);
        try {
            if (call.execute().isSuccessful()) {
                log.info("Успешно выполнен запрос на изменении ProductGroupDto");
            } else {
                log.error("Произошла ошибка при выполнении запроса на изменении ProductGroupDto");
            }
        } catch (IOException e) {
            log.error("Произошла ошибка при выполнении запроса на изменении ProductGroupDto", e);
        }
    }

    @Override
    public void deleteById(Long id) {
        Call<Void> call = productGroupApi.deleteById(productGroupUrl, id);
        try {
            if (call.execute().isSuccessful()) {
                log.info("Успешно выполнен запрос на удаление ProductGroupDto");
            } else {
                log.error("Произошла ошибка при выполнении запроса на удаление ProductGroupDto по id: {}", id);
            }
        } catch (IOException e) {
            log.error("Произошла ошибка при выполнении запроса на удаление ProductGroupDto по id", e);
        }
    }
}
