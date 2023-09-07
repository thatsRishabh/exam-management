package com.exam.exam.management.controller;

import com.exam.exam.management.request.EmployeeRequest;
import com.exam.exam.management.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.List;

@RestController
public class DocumentController {
    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    @Autowired
    private DocumentService documentService;


    @PostMapping({"/generate/document"})
    public String generateDocument(@RequestBody List<EmployeeRequest> employeeList) {
        String finalHtml = null;
        Context dataContext = this.documentService.setData(employeeList);
        finalHtml = this.springTemplateEngine.process("template", dataContext);
        this.documentService.htmlToPdf(finalHtml);
        return "Success";
    }
}
