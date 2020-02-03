package com.denisa.factfulness.controllers;

import com.denisa.factfulness.dto.FactDTO;
import com.denisa.factfulness.service.FactService;
import com.denisa.factfulness.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/facts")
public class FactController {
    @Autowired
    private FactService factService;

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public FactDTO saveFact(@RequestBody FactDTO dto) {
        return factService.addFact(dto);
    }

    @PutMapping("/update")
    public FactDTO updateFact(@RequestBody FactDTO dto) {
        dto.setCreatedBy(userService.getCurrentUser());
        return factService.updateFact(dto);
    }

    @DeleteMapping("/{factId}")
    public void deleteFact(@PathVariable Integer factId) {
        factService.deleteFact(factId);
    }

    @GetMapping("/{factId}")
    public FactDTO getFact(@PathVariable Integer factId) {
        return factService.getFact(factId);
    }

    @GetMapping("/random")
    public FactDTO getRandomFact() {
        return factService.getRandomFact();
    }

    @GetMapping("/all")
    public List<FactDTO> getAllForUser() {
        return factService.getFactsForUser();
    }
}
