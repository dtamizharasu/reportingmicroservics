package com.reporting.controller;

import com.netflix.discovery.converters.Auto;
import com.reporting.model.EmployeeReports;
import com.reporting.service.ReportingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@Api(value = "Reports", tags = {"Reports"})
@RestController
@RequestMapping("/api/reporting")
public class ReportingController {

    @Autowired
    private ReportingService service;

    /**
     * Get Reports All the Employees Details
     * @return - Return List Of Employees Reports
     */

    @ApiOperation(value = "Get All Reports Employees with Project Details",
            response = EmployeeReports.class, tags = "Reports")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @GetMapping("/get/report/{month}")
    public Set<EmployeeReports> getAllEmployees(@PathVariable String month){
        return service.findAllEmployeeReport(month);
    }
}
