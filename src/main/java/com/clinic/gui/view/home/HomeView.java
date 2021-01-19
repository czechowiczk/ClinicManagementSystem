package com.clinic.gui.view.home;

import com.clinic.backend.entity.*;
import com.clinic.backend.service.*;
import com.clinic.gui.view.main.MainView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Route(value = "home", layout = MainView.class)
@CssImport("./styles/home-view.css")
@PageTitle("Home | Clinic")
public class HomeView extends Div {
    private final UserService userService;
    private final VisitService visitService;
    private final DoctorService doctorService;
    private final DiseaseService diseaseService;
    private final PatientService patientService;
    private final EmployeeService employeeService;
    private final TimetableService timetableService;
    private final LaboratoryTestService laboratoryTestService;
    private User user;
    Integer docId = 0;
    Integer patientID;
    Integer employeeID;


    public HomeView(UserService userService, VisitService visitService, DoctorService doctorService, DiseaseService diseaseService, PatientService patientService, EmployeeService employeeService, TimetableService timetableService, LaboratoryTestService laboratoryTestService) {
        this.employeeService = employeeService;
        this.timetableService = timetableService;
        addClassName("home-view");
        this.userService = userService;
        this.visitService = visitService;
        this.doctorService = doctorService;
        this.diseaseService = diseaseService;
        this.patientService = patientService;
        this.laboratoryTestService = laboratoryTestService;
        fetchFreshUser();

        setUpLayoutWithUserCredentials();
    }

    private void setUpLayoutWithUserCredentials() {
        TextField labelFirstName = new TextField("First name:");
        labelFirstName.setValue(user.getName());
        labelFirstName.setReadOnly(true);

        TextField labelLastName = new TextField("Last name:");
        labelLastName.setValue(user.getSurname());
        labelLastName.setReadOnly(true);

        TextField labelPesel = new TextField("Pesel:");
        labelPesel.setValue(user.getPESEL()+"");
        labelPesel.setReadOnly(true);

        TextField labelAge = new TextField("Age:");
        labelAge.setValue(user.getAge()+"");
        labelAge.setReadOnly(true);

        VerticalLayout credentialsLayout = new VerticalLayout(
                labelFirstName,
                labelLastName,
                labelPesel,
                labelAge
        );
        credentialsLayout.getThemeList().set("dark", true);
        credentialsLayout.addClassName("credentials-form");
        credentialsLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        Button buttonBookVisit = new Button("Book visitation", new Icon(VaadinIcon.CALENDAR));
        buttonBookVisit.setIconAfterText(true);
        buttonBookVisit.setAutofocus(true);
        buttonBookVisit.addClickListener(event -> showTopUpDialog());

        Button buttonFillDiseaseHistory = new Button("Add disease history", new Icon(VaadinIcon.CLIPBOARD_PULSE));
        buttonFillDiseaseHistory.setIconAfterText(true);
        buttonFillDiseaseHistory.setAutofocus(true);
        buttonFillDiseaseHistory.addClickListener(event -> showFillDisease());

        Button buttonFillLaboratoryTest = new Button("Add laboratory test", new Icon(VaadinIcon.CLIPBOARD_CHECK));
        buttonFillLaboratoryTest.setIconAfterText(true);
        buttonFillLaboratoryTest.setAutofocus(true);
        buttonFillLaboratoryTest.addClickListener(event -> showAddTest());

        Button buttonFillTimetable = new Button("Add timetable", new Icon(VaadinIcon.TABLE));
        buttonFillTimetable.setIconAfterText(true);
        buttonFillTimetable.setAutofocus(true);
        buttonFillTimetable.addClickListener(event -> showAddTimetable());

        HorizontalLayout patientLayout = new HorizontalLayout(buttonFillDiseaseHistory, buttonBookVisit);
        patientLayout.setAlignItems(FlexComponent.Alignment.START);
        patientLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.START);

        HorizontalLayout doctorLayout = new HorizontalLayout(buttonFillLaboratoryTest);
        patientLayout.setAlignItems(FlexComponent.Alignment.START);
        patientLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.START);

        HorizontalLayout managerLayout = new HorizontalLayout(buttonFillTimetable);
        patientLayout.setAlignItems(FlexComponent.Alignment.START);
        patientLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.START);

        VerticalLayout verticalLayout = new VerticalLayout();

        if (user.getRole() == Role.PATIENT) {
            verticalLayout.add(patientLayout);
        }
        else if(user.getRole() == Role.DOCTOR) {
            verticalLayout.add(doctorLayout);
        }
        else if(user.getRole() == Role.MANAGER) {
            verticalLayout.add(managerLayout);
        }

        verticalLayout.add(
                credentialsLayout
        );

        verticalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        verticalLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        add(verticalLayout);
    }

    private void showFillDisease() {
        Dialog dialog = new Dialog();
        dialog.setCloseOnOutsideClick(false);

        VerticalLayout verticalLayout = new VerticalLayout();
        TextField name = new TextField("Name");
        DatePicker datePicker = new DatePicker("Date");
        TextField description = new TextField("Description");

        verticalLayout.add(
                new Text("Add disease"),
                name,
                datePicker,
                description
        );

        Button confirmButton = new Button("Confirm", event -> {
            if (!(name.getValue().isEmpty() || datePicker.getValue().isAfter(LocalDate.now()))) {
                Disease disease = new Disease(datePicker.getValue(), description.getValue(), name.getValue(), VaadinSession.getCurrent().getAttribute(User.class).getId());
                diseaseService.save(disease);
                Notification.show("Disease added");
                dialog.close();
            } else {
                Notification.show("Wrong date");
                name.setErrorMessage("Name cannot be empty");
            }
        });

        Button cancelButton = new Button("Cancel", event -> {
            dialog.close();
        });

        verticalLayout.add(new HorizontalLayout(confirmButton, cancelButton));
        verticalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        verticalLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        dialog.add(verticalLayout);
        dialog.open();

    }

    private void showTopUpDialog() {
        Dialog dialog = new Dialog();
        dialog.setCloseOnOutsideClick(false);

        VerticalLayout verticalLayout = new VerticalLayout();
        TextField purpose = new TextField("Purpose");
        DatePicker datePicker = new DatePicker("Date");
        TimePicker timePicker = new TimePicker("Time");
        TextField description = new TextField("Description (optional)");
        Select<Doctor> label = new Select<>();
        List<Doctor> doctorsList = doctorService.findAll();
        label.setLabel("Doctor");

        label.setItems(doctorsList);

        label.addValueChangeListener(e ->
        {
//             tutaj dodać sprawdzanie daty
            docId = e.getValue().getId();
        });

        verticalLayout.add(
                new Text("Book visitation"),
                purpose,
                datePicker,
                timePicker,
                description,
                label
        );

        Button confirmButton = new Button("Add", event -> {

            if (!(purpose.getValue().isEmpty() || datePicker.getValue().isBefore(LocalDate.now()) || timePicker.isEmpty() || label.isEmpty())) {
                if(timetableService.getDoctorsWorkingHours(docId, datePicker.getValue(), timePicker.getValue())!=null) {
                    Visit visit = new Visit(docId, user.getId(), datePicker.getValue(), purpose.getValue(), description.getValue(), timePicker.getValue());
                    visitService.save(visit);
                    Notification.show("Visit booked");
                    dialog.close();
                }
                Notification.show("Doctor is not available then");

            } else {
                Notification.show("Wrong data");
            }
        });

        Button cancelButton = new Button("Cancel", event -> {
            dialog.close();
        });

        verticalLayout.add(new HorizontalLayout(confirmButton, cancelButton));
        verticalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        verticalLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        dialog.add(verticalLayout);
        dialog.open();
    }

    private void showAddTest() {
        Dialog dialog = new Dialog();
        dialog.setCloseOnOutsideClick(false);

        VerticalLayout verticalLayout = new VerticalLayout();
        TextField type = new TextField("Type");
        DatePicker datePicker = new DatePicker("Date");
        TextField description = new TextField("Description (optional)");
        Select<Patient> patient = new Select<>();
        patient.setLabel("Select patient");
        patient.setItems(patientService.getPatients());
        patient.addValueChangeListener(event -> {
            patientID = event.getValue().getId();
        });

        verticalLayout.add(
                new Text("Add tests"),
                type,
                datePicker,
                description,
                patient
        );

        Button confirmButton = new Button("Add", event -> {
            if (!(type.getValue().isEmpty() || datePicker.getValue().isAfter(LocalDate.now()) || patient.isEmpty())) {
                LaboratoryTest laboratoryTest = new LaboratoryTest(type.getValue(), datePicker.getValue(), description.getValue(), patientID);
                laboratoryTestService.save(laboratoryTest);
                Notification.show("Test added");
                dialog.close();
            } else {
                Notification.show("Wrong data");
            }
        });

        Button cancelButton = new Button("Cancel", event -> {
            dialog.close();
        });

        verticalLayout.add(new HorizontalLayout(confirmButton, cancelButton));
        verticalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        verticalLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        dialog.add(verticalLayout);
        dialog.open();
    }

    private void showAddTimetable() {
        Dialog dialog = new Dialog();
        dialog.setCloseOnOutsideClick(false);

        VerticalLayout verticalLayout = new VerticalLayout();
        Select<Employee> employee = new Select<>();
        employee.setLabel("Select employee");
        employee.setItems(employeeService.getEmployees());
        employee.addValueChangeListener(event -> {
            employeeID = event.getValue().getId();
        });
        DatePicker datePicker = new DatePicker("Date");
        TimePicker start_hour = new TimePicker("Start hour");
        TimePicker end_hour = new TimePicker("End hour");


        verticalLayout.add(
                new Text("Add timetable"),
                employee,
                datePicker,
                start_hour,
                end_hour
        );

        Button confirmButton = new Button("Add", event -> {
            if (!(datePicker.getValue().isBefore(LocalDate.now()) || start_hour.isEmpty() || end_hour.isEmpty())) {
                Timetable timetable = new Timetable(employeeID, datePicker.getValue(), start_hour.getValue(), end_hour.getValue());
                timetableService.save(timetable);
                Notification.show("Timetable added");
                dialog.close();
            } else {
                Notification.show("Wrong data");
            }
        });

        Button cancelButton = new Button("Cancel", event -> {
            dialog.close();
        });

        verticalLayout.add(new HorizontalLayout(confirmButton, cancelButton));
        verticalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        verticalLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        dialog.add(verticalLayout);
        dialog.open();
    }

    private void fetchFreshUser() {
        try {
            fetchUserById();
        } catch (UserNotFoundException e) {
            System.out.println("User has not been found!");
        }
    }

    private void fetchUserById() throws UserNotFoundException {
        Integer userId = (Integer) VaadinSession.getCurrent().getAttribute("userId");
        Optional<User> fetchedUpdatedUser = userService.get(userId);
        if (fetchedUpdatedUser.isPresent()) {
            user = fetchedUpdatedUser.get();
        } else {
            throw new UserNotFoundException();
        }
    }

    public static class UserNotFoundException extends Exception {

    }
}