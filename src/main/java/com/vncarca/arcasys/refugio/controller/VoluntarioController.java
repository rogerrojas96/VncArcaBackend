package com.vncarca.arcasys.refugio.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin ({"*"})
@RestController
@RequestMapping("/api/test")
public class VoluntarioController {


    @GetMapping("/solicitudVoluntariado")
    protected void doGet(HttpServletResponse response) throws ServletException, IOException {
        
        response.sendRedirect("https://docs.google.com/document/d/1WsoM9fOIOvlSo4omltlUt4nMVnBw-w5x/edit?usp=sharing&ouid=110782275495601692476&rtpof=true&sd=true");
    }
}
