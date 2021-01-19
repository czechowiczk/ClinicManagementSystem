package com.clinic.backend.service;

import com.clinic.backend.entity.Role;
import com.clinic.backend.entity.User;
import com.clinic.backend.repository.*;
import com.clinic.gui.view.doctor.timetable.DoctorTimetable;
import com.clinic.gui.view.doctor.visits.BookedVisits;
import com.clinic.gui.view.home.HomeView;
import com.clinic.gui.view.main.MainView;
import com.clinic.gui.view.manager.addEmployee.AddEmployee;
import com.clinic.gui.view.manager.addEmployee.ManageTimetable;
import com.clinic.gui.view.patient.diseases.DiseaseList;
import com.clinic.gui.view.patient.tests.LaboratoryTestList;
import com.clinic.gui.view.patient.visits.VisitList;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final EmployeeRepository employeeRepository;

    @Autowired
    private final DoctorRepository doctorRepository;

    @Autowired
    private final AdmEmployeeRepository admEmployeeRepository;

    @Autowired
    private final ManagerRepository managerRepository;

    @Autowired
    private final PatientRepository patientRepository;

    public AuthService(@Autowired UserRepository userRepository, @Autowired EmployeeRepository employeeRepository, DoctorRepository doctorRepository, AdmEmployeeRepository admEmployeeRepository, ManagerRepository managerRepository, PatientRepository patientRepository) {
        this.userRepository = userRepository;
        this.employeeRepository = employeeRepository;
        this.doctorRepository = doctorRepository;
        this.admEmployeeRepository = admEmployeeRepository;
        this.managerRepository = managerRepository;
        this.patientRepository = patientRepository;
    }

    public void authenticate(String username, String password) throws AuthException {
        User user = userRepository.getByPESEL(Long.parseLong(username));
        if (user != null && user.getPassword().equals(password)) {
            VaadinSession.getCurrent().setAttribute("userId", user.getId());
            VaadinSession.getCurrent().setAttribute(User.class, user);
            createRoutes(user.getRole());
        } else {
            throw new AuthException();
        }
    }

    private void createRoutes(Role role) {
        getAuthorizedRoutes(role)
                .forEach(route ->
                        RouteConfiguration.forSessionScope().setRoute(route.route, route.view, MainView.class));
    }

    public List<AuthorizedRoute> getAuthorizedRoutes(Role role) {
        ArrayList<AuthorizedRoute> routes = new ArrayList<>();
        if(role.equals(Role.PATIENT)) {
            routes.add(new AuthorizedRoute("home", "Home", HomeView.class));
            routes.add(new AuthorizedRoute("visits", "Visit", VisitList.class));
            routes.add(new AuthorizedRoute("diseases", "Disease", DiseaseList.class));
            routes.add(new AuthorizedRoute("tests", "Test", LaboratoryTestList.class));
        }
        else if(role.equals(Role.ADM_EMPLOYEE)){
            routes.add(new AuthorizedRoute("home", "Home", HomeView.class));

        }
        else if(role.equals(Role.DOCTOR)){
            routes.add(new AuthorizedRoute("home", "Home", HomeView.class));
            routes.add(new AuthorizedRoute("doctorVisits", "Visits", BookedVisits.class));
            routes.add(new AuthorizedRoute("doctor-timetable", "DoctorTimetable", DoctorTimetable.class));
        }
        else if(role.equals(Role.MANAGER)){
            routes.add(new AuthorizedRoute("home", "Home", HomeView.class));
            routes.add(new AuthorizedRoute("add_employee", "AddEmployee", AddEmployee.class));
            routes.add(new AuthorizedRoute("timetables_management", "Show timetables", ManageTimetable.class));
        }
        else {
            routes.add(new AuthorizedRoute("list", "List", VisitList.class));
        }

        return routes;
    }


    public void hire(String firstName, String lastName, Long pesel, Integer age, String password, Role userRole, Integer managerId, String specialization, String type) {
        User user = new User(firstName, lastName, pesel, age, password, userRole);
        userRepository.save(user);

        employeeRepository.insertUser(user.getId());

        if(userRole == Role.DOCTOR)
            doctorRepository.insertDoctor(managerId, specialization, user.getId());
        else if(userRole == Role.ADM_EMPLOYEE)
            admEmployeeRepository.insertAdmEmployee(type, user.getId(), managerId);
        else if(userRole == Role.MANAGER)
            managerRepository.insertManager(user.getId());


    }
    public void register(String firstName, String lastName, Long pesel, Integer age, String password, String sex) {
        User user = new User(firstName, lastName, pesel, age, password, Role.PATIENT);
        userRepository.save(user);

        patientRepository.insertPatient(sex, user.getId());
    }


    public static class AuthorizedRoute {
        final String route;
        final String name;
        final Class<? extends Component> view;

        public AuthorizedRoute(String route, String name, Class<? extends Component> view) {
            this.route = route;
            this.name = name;
            this.view = view;
        }

        public String getRoute() {
            return route;
        }

        public String getName() {
            return name;
        }

        public Class<? extends Component> getView() {
            return view;
        }
    }

    public static class AuthException extends Exception {
    }
}
