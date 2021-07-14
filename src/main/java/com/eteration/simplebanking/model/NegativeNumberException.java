package com.eteration.simplebanking.model;

import java.io.IOException;

public class NegativeNumberException extends IOException {
    public NegativeNumberException(double irritant) {
        super("non-negative number expected, not " + irritant);
    }

    public NegativeNumberException() {
        super("non-negative number expected");
    }
}
