package com.exam.exam.management.service.impl;

import com.exam.exam.management.request.EmployeeRequest;
import com.exam.exam.management.service.DocumentService;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import java.io.FileOutputStream;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DocumentServiceImpl implements DocumentService {

    //    Data Mapper
    @Override
    public Context setData(List<EmployeeRequest> empolyeeList) {
        Context context = new Context();
        Map<String, Object> data = new HashMap();
    //     below "employees" will represent to template
        data.put("employees", empolyeeList);
        context.setVariables(data);
        return context;
    }
    // Document Generator
    @Override
    public String htmlToPdf(String processedHtml) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            PdfWriter pdfwriter = new PdfWriter(byteArrayOutputStream);
            DefaultFontProvider defaultFont = new DefaultFontProvider(false, true, false);
            ConverterProperties converterProperties = new ConverterProperties();
            converterProperties.setFontProvider(defaultFont);
            HtmlConverter.convertToPdf(processedHtml, pdfwriter, converterProperties);

            long currentTimestamp = Instant.now().toEpochMilli();
            FileOutputStream fout = new FileOutputStream("asset/"+currentTimestamp+".pdf");
//            System.out.println(currentTimestamp+".pdf");
            byteArrayOutputStream.writeTo(fout);
            byteArrayOutputStream.close();
            byteArrayOutputStream.flush();
            fout.close();
//            return null;
            return currentTimestamp+".pdf";
        } catch (Exception var7) {
            return null;
        }
    }

}
