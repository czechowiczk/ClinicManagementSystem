package com.vaadin.tutorial.gui.view.home;

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
import com.vaadin.tutorial.backend.entity.*;
import com.vaadin.tutorial.backend.service.DiseaseService;
import com.vaadin.tutorial.backend.service.DoctorService;
import com.vaadin.tutorial.backend.service.UserService;
import com.vaadin.tutorial.backend.service.VisitService;
import com.vaadin.tutorial.gui.view.main.MainView;

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
    private User user;
    Integer docId = 0;

    public HomeView(UserService userService, VisitService visitService, DoctorService doctorService, DiseaseService diseaseService) {
        addClassName("home-view");
        this.userService = userService;
        this.visitService = visitService;
        this.doctorService = doctorService;
        this.diseaseService = diseaseService;
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

        Button buttonBookVisit = new Button("Book visitation", new Icon(VaadinIcon.PLUS_CIRCLE));
        buttonBookVisit.setIconAfterText(true);
        buttonBookVisit.setAutofocus(true);
        buttonBookVisit.addClickListener(event -> showTopUpDialog());

        Button buttonFillDiseaseHistory = new Button("Fill disease history", new Icon(VaadinIcon.CLIPBOARD_PULSE));
        buttonFillDiseaseHistory.setIconAfterText(true);
        buttonFillDiseaseHistory.setAutofocus(true);
        buttonFillDiseaseHistory.addClickListener(event -> showFillDisease());

        HorizontalLayout patientLayout = new HorizontalLayout(buttonFillDiseaseHistory, buttonBookVisit);
        patientLayout.addClassName("patient-layout");
        patientLayout.setAlignItems(FlexComponent.Alignment.START);
        patientLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.START);

        VerticalLayout verticalLayout = new VerticalLayout();

        if (user.getRole() == Role.PATIENT) {
            verticalLayout.add(patientLayout);
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
            if (!name.getValue().isEmpty()) {
                Disease disease = new Disease(datePicker.getValue(), description.getValue(), name.getValue(), VaadinSession.getCurrent().getAttribute(User.class).getId());
                diseaseService.save(disease);
                Notification.show("Disease added");
                dialog.close();
            } else {
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
            if (!purpose.getValue().isEmpty()) {
                Visit visit = new Visit(docId, user.getId(), datePicker.getValue(), purpose.getValue(), description.getValue(), timePicker.getValue());
                visitService.save(visit);
                Notification.show("Visit booked");
                dialog.close();
            } else {
                purpose.setErrorMessage("Purpose cannot be empty");
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