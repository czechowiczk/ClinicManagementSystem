package com.vaadin.tutorial.gui.view.home;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.tutorial.backend.entity.Role;
import com.vaadin.tutorial.backend.entity.User;
import com.vaadin.tutorial.backend.entity.Visit;
import com.vaadin.tutorial.backend.service.UserService;
import com.vaadin.tutorial.backend.service.VisitService;
import com.vaadin.tutorial.gui.view.main.MainView;

import java.util.Optional;


@Route(value = "home", layout = MainView.class)
@CssImport("./styles/home-view.css")
@PageTitle("Home | Clinic")
public class HomeView extends Div {
    private final UserService userService;
    private final VisitService visitService;
    private User user;

    public HomeView(UserService userService, VisitService visitService) {
        addClassName("home-view");
        this.userService = userService;
        this.visitService = visitService;
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


        Button buttonTopUp = new Button("Book visitation", new Icon(VaadinIcon.PLUS_CIRCLE));
        buttonTopUp.setId("button-top-up");
        buttonTopUp.setIconAfterText(true);
        buttonTopUp.setAutofocus(true);
        buttonTopUp.addClickListener(event -> showTopUpDialog());


        HorizontalLayout patientLayout = new HorizontalLayout(buttonTopUp);
        patientLayout.addClassName("patient-layout");
        patientLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        patientLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

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

    private void showTopUpDialog() {
        Dialog dialog = new Dialog();
        dialog.setId("dialog-top-up");
        dialog.setCloseOnOutsideClick(false);


        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setId("vert-layout-top-up");
        TextField purpose = new TextField("Purpose");
        DatePicker datePicker = new DatePicker("Date");
        TimePicker timePicker = new TimePicker("Time");
        TextField description = new TextField("Description (optional)");


        verticalLayout.add(
                new Text("Book visitation"),
                purpose,
                datePicker,
                timePicker,
                description
                );

        Button confirmButton = new Button("Confirm", event -> {

            if (!purpose.getValue().isEmpty()) {
                // all user's visits
                //visitService.getUsersVisits(user.getId());
                // dodać wybieranie lekarza !!!!!!!!!!!!!!!!! BARTI
                // konstruktor jest gotowy :)
                Visit visit = new Visit(datePicker.getValue(), purpose.getValue(), description.getValue(), timePicker.getValue());
                visitService.save(visit);
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