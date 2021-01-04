package com.javalab.rabbitmq.demo.service;

import com.javalab.rabbitmq.demo.models.forDirect.VacationDto;
import com.javalab.rabbitmq.demo.models.forFanout.UserDto;
import com.javalab.rabbitmq.demo.models.forTopic.DepartmentDto;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.images.IImageProvider;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ResourceLoader resourceLoader;

    @Override
    public void getVacationDocument(VacationDto vacationDto) throws IOException, XDocReportException {
        Resource resource = resourceLoader.getResource("classpath:vacation.docx");
        IXDocReport report = XDocReportRegistry.getRegistry().loadReport(resource.getInputStream(), TemplateEngineKind.Velocity);
        IContext context = report.createContext();
        context.put("dto", vacationDto);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        report.process(context, baos);
        try(OutputStream outputStream = new FileOutputStream("vacation.docx")) {
            baos.writeTo(outputStream);
        }
    }


    @Override
    public void getDepartmentDocument(DepartmentDto departmentDto) throws IOException, XDocReportException {
        Resource resource = resourceLoader.getResource("classpath:department.docx");
        IXDocReport report = XDocReportRegistry.getRegistry().loadReport(resource.getInputStream(), TemplateEngineKind.Velocity);
        IContext context = report.createContext();
        context.put("dto", departmentDto);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        report.process(context, baos);
        try(OutputStream outputStream = new FileOutputStream("department.docx")) {
            baos.writeTo(outputStream);
        }
    }


    @Override
    public void getHelpDocument(UserDto userDto) throws IOException, XDocReportException {
        Resource resource = resourceLoader.getResource("classpath:help.docx");
        IXDocReport report = XDocReportRegistry.getRegistry().loadReport(resource.getInputStream(), TemplateEngineKind.Velocity);
        IContext context = report.createContext();
        context.put("dto", userDto);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        report.process(context, baos);
        try(OutputStream outputStream = new FileOutputStream("help.docx")) {
            baos.writeTo(outputStream);
        }
    }
}
