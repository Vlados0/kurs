package com.example.cyber.api.responses;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitorHistory {

    private Long id;

    private Visitor visitor;

    private Date date;
}