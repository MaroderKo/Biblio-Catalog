package com.smart.catalog.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Search items not found exception")
public class SearchItemsNotFoundException extends RuntimeException{

    public SearchItemsNotFoundException() {
        this("Search items not found exception");
    }

    public SearchItemsNotFoundException(String message) {
        super(message);
    }
}
