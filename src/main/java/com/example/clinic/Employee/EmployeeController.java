package com.example.clinic.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("employee")
public class EmployeeController {
    @Qualifier("PatientService")
    private final EmployeeService employeeService;
    @Qualifier("DiseaseService")
    private final AdmEmployeeService admEmployeeService;
    @Qualifier("Manager")
    private final ManagerService managerService;
    @Qualifier("Doctor")
    private final DoctorService doctorService;
    @Qualifier("Timetable")
    private final  TimetableService timetableService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, AdmEmployeeService admEmployeeService, ManagerService managerService, DoctorService doctorService, TimetableService timetableService) {
        this.employeeService = employeeService;
        this.admEmployeeService = admEmployeeService;
        this.managerService = managerService;
        this.doctorService = doctorService;
        this.timetableService = timetableService;
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping("/admEmployees")
    public List<AdmEmployee> getAdmEmployees() {
        return admEmployeeService.getAdmEmployees();
    }

    @GetMapping("/managers")
    public List<Manager> getManagers() { return managerService.getManagers(); }

    @GetMapping("/doctors")
    public List<Doctor> getDoctors() { return doctorService.getDoctors(); }

    @GetMapping("/timetables")
    public List<Timetable> getTimetables() { return timetableService.getTimetables(); }
}
