package com.data.generator.controller;

import com.data.generator.service.NumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/number")
public class NumberController {
    private final NumberService numberService;

    @Autowired
    public NumberController(NumberService numberService) {
        this.numberService = numberService;
    }

    @GetMapping("/longs")
    public List<Long> generateLongs(
        @RequestParam(required = false, defaultValue = "10") Integer limit
    ) {
        return numberService.generateLongs(limit);
    }
}
