package com.github.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bin
 * @since 2022/11/29
 */
@Tag(
        name = "HelloWorld"
)
@RestController
@RequestMapping
public class HelloWorld {
    @Operation(summary = "HelloWorld")
    @GetMapping("/")
    public String getHelloWorld() {
        return "HelloWorld";
    }
}
