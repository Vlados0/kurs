package com.example.cyber.api.responses;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Visitor {

    private Long id;

    private String name;

    private String surname;

    private int timeVisited;

    private Date lastVisited;

    private List<VisitorHistory> visitorHistory;
}
