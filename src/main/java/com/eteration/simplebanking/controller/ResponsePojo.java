package com.eteration.simplebanking.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResponsePojo {
    String status;
    String approvalCode;
}
