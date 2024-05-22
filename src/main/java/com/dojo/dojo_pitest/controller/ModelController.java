package com.dojo.dojo_pitest.controller;

import com.dojo.dojo_pitest.model.Model;
import com.dojo.dojo_pitest.model.dto.CreateModelDTO;
import com.dojo.dojo_pitest.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping(path = "/model")
@RestController
public class ModelController {

    private final ModelService modelService;

    @GetMapping(path = "/get")
    public ResponseEntity<Model> getModel(@RequestParam long id) {
        return new ResponseEntity<>(modelService.get(id), HttpStatus.OK);
    }

    @GetMapping(path = "/getAll")
    public ResponseEntity<List<Model>> getAllModels() {
        return new ResponseEntity<>(modelService.getAll(), HttpStatus.OK);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<Model> create(@RequestBody CreateModelDTO modelDTO) {
        modelDTO.setName(modelDTO.getName().toUpperCase());
        return new ResponseEntity<>(modelService.create(modelDTO), HttpStatus.OK);
    }
}
