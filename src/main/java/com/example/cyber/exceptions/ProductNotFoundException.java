package com.example.cyber.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProductNotFoundException extends RuntimeException{

    private final Long id;
}
