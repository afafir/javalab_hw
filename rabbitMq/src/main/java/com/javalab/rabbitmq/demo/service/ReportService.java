package com.javalab.rabbitmq.demo.service;

import com.javalab.rabbitmq.demo.models.forDirect.VacationDto;
import com.javalab.rabbitmq.demo.models.forFanout.UserDto;
import com.javalab.rabbitmq.demo.models.forTopic.DepartmentDto;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import org.springframework.core.io.Resource;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public interface ReportService {
    void getVacationDocument(VacationDto vacationDto) throws IOException, XDocReportException;

    void getDepartmentDocument(DepartmentDto departmentDto) throws IOException, XDocReportException;

    void getHelpDocument(UserDto userDto) throws IOException, XDocReportException;

}
