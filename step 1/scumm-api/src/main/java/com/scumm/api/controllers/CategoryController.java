package com.scumm.api.controllers;

import com.scumm.api.contracts.CategoryContract;
import com.scumm.core.domain.entities.Category;
import com.scumm.core.domain.repositories.CategoryRepository;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public List<CategoryContract> getAll() {
        List<Category> categoryList = repository.findAll();
        return  categoryList.stream()
                .map(entity -> mapper.map(entity, CategoryContract.class))
                .collect(Collectors.toList());
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<CategoryContract> getById(@PathVariable String id) {
        Optional<Category> byId = repository.findById(new ObjectId(id));
        if (byId.isPresent())
        {
            CategoryContract contract = mapper.map(byId.get(), CategoryContract.class);
            return new ResponseEntity<>(contract, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    public CategoryContract createCategory(@Valid @RequestBody CategoryContract categoryContract) {

        Category category = new Category();
        category.setName(categoryContract.getName());
        repository.save(category);
        return mapper.map(category, CategoryContract.class);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<CategoryContract> updateById(@PathVariable String id, @Valid @RequestBody CategoryContract categoryContract) {
        Optional<Category> byId = repository.findById(new ObjectId(id));
        if (byId.isPresent())
        {
            mapper.map(categoryContract, byId.get());
            repository.save(byId.get());
            CategoryContract contract = mapper.map(byId.get(), CategoryContract.class);
            return new ResponseEntity<>(contract, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<CategoryContract> deleteById(@PathVariable String id) {
        Optional<Category> byId = repository.findById(new ObjectId(id));
        if (byId.isPresent())
        {
            repository.deleteById(byId.get().getId());
            CategoryContract contract = mapper.map(byId.get(), CategoryContract.class);
            return new ResponseEntity<>(contract, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
