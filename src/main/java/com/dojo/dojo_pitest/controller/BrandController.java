package com.dojo.dojo_pitest.controller;

import com.dojo.dojo_pitest.model.Brand;
import com.dojo.dojo_pitest.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping(path = "/brand")
@RestController
public class BrandController {

    private final BrandService brandService;

    @GetMapping(path = "/get")
    public ResponseEntity<Brand> getBrand(@RequestParam long id) {
        return new ResponseEntity<>(brandService.get(id), HttpStatus.OK);
    }

    @GetMapping(path = "/getAll")
    public ResponseEntity<List<Brand>> getAllBrands() {
        return new ResponseEntity<>(brandService.getAll(), HttpStatus.OK);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<Brand> create(@RequestParam String brandName) {
        return new ResponseEntity<>(brandService.create(brandName.toUpperCase()), HttpStatus.OK);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Brand> update(@RequestParam long id, @RequestParam String brandName) {
        return new ResponseEntity<>(brandService.update(id, brandName), HttpStatus.OK);
    }
}
