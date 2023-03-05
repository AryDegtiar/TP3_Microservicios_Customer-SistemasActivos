package com.api.sistemasactivos.costumer.controller;

import com.api.sistemasactivos.costumer.exception.BusinessException;
import com.api.sistemasactivos.costumer.interfaces.BaseController;
import com.api.sistemasactivos.costumer.model.Base;
import com.api.sistemasactivos.costumer.service.BaseServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

public abstract class BaseControllerImpl < E extends Base, S extends BaseServiceImpl<E,Integer>> implements BaseController<E,Integer> {
    @Autowired
    protected S service;

    @Override
    @GetMapping(path = {""})
    public ResponseEntity<?> getAllRecords() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
        } catch (Exception e) {
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    @GetMapping(path = {"/page"})
    public ResponseEntity<?> getRecordBy(Pageable pageable,
                                         @RequestParam(name = "sort", required = false) String sort) {
        try {
            if (sort != null) {
                pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(sort));
            }
            return ResponseEntity.status(HttpStatus.OK).body(service.findAll(pageable));
        } catch (Exception e) {
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
/*
    @Override
    @GetMapping(path = {"/{id}"})
    public ResponseEntity<?> getRecordById(@PathVariable Integer id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
        } catch (Exception e) {
            if (Objects.equals(e.getMessage(), "No se encontró el registro con ese id")) {
                throw new BusinessException(HttpStatus.NOT_FOUND, e.getMessage());
            }else{
                throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            }
        }
    }
 */
    @Override
    @GetMapping(path = {"/{id}"})
    public ResponseEntity<?> getRecordById(@PathVariable Integer id) throws Exception {
        if (service.existId(id) == false)
            throw new BusinessException(HttpStatus.NOT_FOUND, "No se encontró el registro con ese id");

        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
        } catch (Exception e) {
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    @PostMapping(path = {""})
    public ResponseEntity<?> saveRecord(@Valid @RequestBody E entity, BindingResult result) {
        if (result.hasErrors()) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, result.getFieldError().getDefaultMessage());
        } else {
            try {
                return ResponseEntity.status(HttpStatus.CREATED).body(service.save(entity));
            } catch (Exception e) {
                if (Objects.equals(e.getMessage(), "No se encontró el registro con ese id")) {
                    throw new BusinessException(HttpStatus.NOT_FOUND, e.getMessage());
                }else{
                    throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
                }
            }
        }
    }

    @Override
    @PutMapping(path = {"/{id}"})
    public ResponseEntity<?> updateRecord(@PathVariable Integer id,@Valid @RequestBody E entity, BindingResult result) {
        if (result.hasErrors()) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, result.getFieldError().getDefaultMessage());
        } else {
            try {
                return ResponseEntity.status(HttpStatus.OK).body(service.update(id, entity));
            } catch (Exception e) {
                if (Objects.equals(e.getMessage(), "No se encontró el registro con ese id")) {
                    throw new BusinessException(HttpStatus.NOT_FOUND, e.getMessage());
                } else {
                    throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
                }
            }
        }
    }

    @Override
    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<?> deleteRecord(@PathVariable Integer id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
        } catch (Exception e) {
            if (Objects.equals(e.getMessage(), "No se encontró el registro con ese id")) {
                throw new BusinessException(HttpStatus.NOT_FOUND, e.getMessage());
            }else{
                throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            }
        }
    }

}

