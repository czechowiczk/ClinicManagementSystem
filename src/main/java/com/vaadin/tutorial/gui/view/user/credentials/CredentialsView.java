package com.vaadin.tutorial.gui.view.user.credentials;


import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.tutorial.backend.entity.Doctor;
import com.vaadin.tutorial.backend.entity.Role;
import com.vaadin.tutorial.backend.entity.User;
import com.vaadin.tutorial.backend.entity.Visit;
import com.vaadin.tutorial.backend.service.DoctorService;
import com.vaadin.tutorial.backend.service.UserService;
import com.vaadin.tutorial.backend.service.VisitService;
import com.vaadin.tutorial.gui.view.home.HomeView;
import com.vaadin.tutorial.gui.view.main.MainView;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

@Route(value = "credentials", layout = MainView.class)
@CssImport("./styles/credentials-view.css")
@PageTitle("Credentials | Clinic")
public class CredentialsView extends VerticalLayout {
    private final UserService userService;
    private User user;

    public CredentialsView(UserService userService) {
        addClassName("home-view");
        this.userService = userService;

        fetchFreshUser();
        setUpLayoutWithUserCredentials();
    }

    private void setUpLayoutWithUserCredentials() {
        TextField labelFirstName = new TextField("First name:");
        labelFirstName.setValue(user.getName());
        //labelFirstName.setReadOnly(true);

        TextField labelLastName = new TextField("Last name:");
        labelLastName.setValue(user.getSurname());
        //labelLastName.setReadOnly(true);

        TextField labelPesel = new TextField("Pesel:");
        labelPesel.setValue(user.getPESEL() + "");
        //labelPesel.setReadOnly(true);

        TextField labelAge = new TextField("Age:");
        labelAge.setValue(user.getAge() + "");
        //labelAge.setReadOnly(true);

        VerticalLayout credentialsLayout = new VerticalLayout(
                labelFirstName,
                labelLastName,
                labelPesel,
                labelAge
        );
        credentialsLayout.getThemeList().set("dark", true);
        credentialsLayout.addClassName("credentials-form");
        credentialsLayout.setAlignItems(FlexComponent.Alignment.CENTER);


        Button confirmButton = new Button("Save changes", new Icon(VaadinIcon.CHECK_CIRCLE));
        confirmButton.setId("button-top-up");
        confirmButton.setIconAfterText(true);
        confirmButton.setAutofocus(true);
        confirmButton.addClickListener(event -> {
            userService.modifyUser(labelFirstName.getValue(), labelLastName.getValue(), Integer.parseInt(labelAge.getValue()), Long.parseLong(labelPesel.getValue()));
            UI.getCurrent().navigate("home");
        });

        H1 header = new H1("Edit your user data");

        HorizontalLayout patientLayout = new HorizontalLayout(confirmButton);
        patientLayout.addClassName("patient-layout");
        patientLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        patientLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        VerticalLayout verticalLayout = new VerticalLayout();

        if (user.getRole() == Role.PATIENT) {
            verticalLayout.add(header, credentialsLayout, patientLayout);
        }

        verticalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        verticalLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        add(verticalLayout);
    }

    private void fetchFreshUser() {
        try {
            fetchUserById();
        } catch (HomeView.UserNotFoundException e) {
            System.out.println("User has not been found!");
        }
    }

    private void fetchUserById() throws HomeView.UserNotFoundException {
        Integer userId = (Integer) VaadinSession.getCurrent().getAttribute("userId");
        Optional<User> fetchedUpdatedUser = userService.get(userId);
        if (fetchedUpdatedUser.isPresent()) {
            user = fetchedUpdatedUser.get();
        } else {
            throw new HomeView.UserNotFoundException();
        }
    }

    public static class UserNotFoundException extends Exception {

    }
}