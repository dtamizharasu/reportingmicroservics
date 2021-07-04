package com.reporting.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeReports {

    private String id;
    private Integer employeeId;
    private String employeeName;
    private String month;
    private Integer totalHrs;
    private Set<ProjectDetails> projectList;

}
