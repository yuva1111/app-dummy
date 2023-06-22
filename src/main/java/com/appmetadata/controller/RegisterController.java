package com.appmetadata.controller;

import com.appmetadata.Register;
import com.appmetadata.repo.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/register")
public class RegisterController {
       @Autowired
       RegisterRepository repo;
       @PostMapping
       public Register addRegister(@RequestBody Register register)
       {
           repo.save(register);
           return register;
       }
}
