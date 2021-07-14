package com.javaadvance.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorController.class);

}
