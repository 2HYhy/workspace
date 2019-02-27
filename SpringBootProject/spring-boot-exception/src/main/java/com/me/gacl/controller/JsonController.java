package com.me.gacl.controller;

import com.me.gacl.domain.R;
import com.me.gacl.exception.JsonException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CH-yfy
 * @date 2018/6/25
 */
@RestController
@RequestMapping("/json")
public class JsonController {

    @GetMapping("/")
    public R index() {
        throw new JsonException(501, "This is JsonException");
    }
}
