package com.github.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bin
 * @since 2022/11/29
 */
@Tag(
        name = "MybatisPage"
)
@RestController
@RequestMapping("/page")
public class MybatisPageController {

    @Operation(summary = "page", parameters = {
            @Parameter(name = "page", description = "page", allowEmptyValue = true, in = ParameterIn.QUERY),
            @Parameter(name = "size", description = "size", allowEmptyValue = true, in = ParameterIn.QUERY),
    })
    @GetMapping("/")
    public IPage<String> getPage(IPage<String> page) {
        return page;
    }
}
