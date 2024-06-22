package com.example.cyber.api;


import com.example.cyber.api.responses.Visitor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "visitor", url = "${visitor.url}")
public interface VisitorClient {

    @PostMapping("/visitors")
    ResponseEntity<Visitor> registerVisitor(@RequestBody Visitor visitor);
}