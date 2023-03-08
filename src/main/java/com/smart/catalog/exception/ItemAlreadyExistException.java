package com.smart.catalog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "Search items not found exception")
public class ItemAlreadyExistException extends RuntimeException{
    public ItemAlreadyExistException() {
        super("Item already exist in database");
    }
}
