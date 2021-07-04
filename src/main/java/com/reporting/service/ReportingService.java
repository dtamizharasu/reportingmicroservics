package com.reporting.service;

import com.reporting.model.EmployeeReports;
import com.reporting.model.Project;
import com.reporting.model.ProjectDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportingService {

    @Autowired
    RestTemplate restTemplate;

    public Set<EmployeeReports> findAllEmployeeReport(String monthNum){

        Set<EmployeeReports> employeeReportsSet = new HashSet<>();
        int[] employeeIds = getAllEmployees();

       List<Integer> ids = Arrays.stream(employeeIds).boxed().collect(Collectors.toList());

        ids.forEach(id ->{
            EmployeeReports employeeReports;
            ResponseEntity<EmployeeReports> responseEntity =
                    restTemplate.
         getForEntity("http://Timesheet-Service/api/timesheet/get/report/"+id+"/"+monthNum,
                                    EmployeeReports.class);
            employeeReports = responseEntity.getBody();
            if (employeeReports != null && employeeReports.getEmployeeName() !=null){
//  Handled in Timesheet Services
//                Set<ProjectDetails> projectList = mapDefaultProjects(employeeReports.getProjectList());
//                employeeReports.setProjectList(projectList);
                employeeReportsSet.add(employeeReports);
            }
        });


        return employeeReportsSet;
    }

    public int[] getAllEmployees(){
        int[] employees;
        ResponseEntity<int[]> responseEntity =
                restTemplate.
                getForEntity("http://Employee-Service/api/employee/get/all/employees", int[].class);
        employees = responseEntity.getBody();

        return employees;
    }

    // Handled in Timesheet Service

//    public Set<ProjectDetails> mapDefaultProjects(Set<ProjectDetails> projectDetails){
//        Set<ProjectDetails> updatedSet = projectDetails;
//        List<Integer> projectIds = projectDetails.stream()
//                .map(ProjectDetails::getProjectId).collect(Collectors.toList());
//        ParameterizedTypeReference<List<Project>> typeRef =
//                new ParameterizedTypeReference<List<Project>>() {};
//        ResponseEntity<List<Project>> response =
//                restTemplate.
//                        exchange("http://Project-Service/api/project//get/all/projects",
//                                HttpMethod.GET,null,
//                                typeRef);
//        Set<Project> projectSet = new HashSet<>(response.getBody());
//        if(!projectSet.isEmpty()){
//            projectSet.forEach(project -> {
//                if(!projectIds.contains(project.getProjectId())){
//                    updatedSet.add(new ProjectDetails(project.getProjectId(),project.getProjectName(),
//                            "NA","NA",0));
//                }
//            });
//        }
//        return updatedSet;
//    }

    
}
