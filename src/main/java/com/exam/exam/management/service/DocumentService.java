package com.exam.exam.management.service;

import com.exam.exam.management.request.EmployeeRequest;
import org.thymeleaf.context.Context;

import java.util.List;

public interface DocumentService {
    public Context setData(List<EmployeeRequest> empolyeeList);
    public String htmlToPdf(String processedHtml);
}
