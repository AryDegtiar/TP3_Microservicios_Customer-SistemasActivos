package com.api.sistemasactivos.costumer.controller;

import com.api.sistemasactivos.costumer.model.Costumer;
import com.api.sistemasactivos.costumer.service.CostumerServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/v1/costumers")
public class CostumerController extends BaseControllerImpl<Costumer, CostumerServiceImpl> {
}