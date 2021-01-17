package com.vaadin.tutorial.gui.view.forms;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.tutorial.backend.service.AuthService;

@Route("register")
@PageTitle("Register | BankAP")
//@CssImport("./styles/views/register/register-view.css")
public class RegisterView extends Composite {
    private final AuthService authService;

    public RegisterView(AuthService authService) {
        this.authService = authService;
    }

    @Override
    protected Component initContent() {
        TextField username = new TextField("Username");
        PasswordField password = new PasswordField("Password");
        PasswordField confirmPassword = new PasswordField("Confirm Password");
        TextField firstName = new TextField("First Name");
        TextField lastName = new TextField("Last Name");
        TextField pesel = createPesel();
        TextField age = new TextField("Your age");
        TextField address = new TextField("Address", "Home Address");
        DatePicker dateOfBirth = new DatePicker("Date of Birth");
        EmailField emailField = createEmail();
        TextField phone = createPhone();


        FormLayout formLayout = new FormLayout(
                //username,
                firstName,
                lastName,
                password,
                confirmPassword,
                pesel,
                age
                //dateOfBirth,
                //emailField,
                //phone
        );

        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("25em", 1),
                new FormLayout.ResponsiveStep("32em", 2),
                new FormLayout.ResponsiveStep("40em", 3));

        VerticalLayout verticalLayout = new VerticalLayout(new H1("Register to clinic"),
                formLayout,
                new Button("Sign up", e -> register(
                        password.getValue(),
                        confirmPassword.getValue(),
                        firstName.getValue(),
                        lastName.getValue(),
                        pesel.getValue(),
                        Integer.parseInt(age.getValue())
                )));

        verticalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        verticalLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        return verticalLayout;
    }

    private void register(String password, String confirmPassword, String firstName, String lastName,
                          String pesel, Integer age) {

        if (password.isEmpty()) {
            Notification.show("Enter a password");
        } else if(pesel.length() != 11) {
            Notification.show("Phone number is not correct!");
        } else if(!password.equals(confirmPassword)) {
            Notification.show("Passwords don't match!");
        } else if(firstName.isEmpty()) {
            Notification.show("Enter a firstName");
        } else if(lastName.isEmpty()) {
            Notification.show("Enter a lastName");
        } else {
            authService.register(firstName, lastName, Long.parseLong(pesel), age, password);
            Notification.show("Registration succeeded.");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                UI.getCurrent().navigate("login");
            }
        }
    }

    private EmailField createEmail() {
        EmailField emailField = new EmailField("Email");
        emailField.setClearButtonVisible(true);
        emailField.setErrorMessage("Please enter a valid email address");
        return emailField;
    }

    private TextField createPhone() {
        TextField phone = new TextField("Phone");
        phone.setPattern("[0-9]*");
        phone.setPreventInvalidInput(true);
        phone.setMaxLength(9);
        phone.setPlaceholder("123456789");

        return phone;
    }

    private TextField createPesel() {
        TextField id = new TextField("Pesel");
        id.setPattern("[0-9]*");
        id.setPreventInvalidInput(true);
        id.setMaxLength(11);
        id.setPlaceholder("10109703223");

        return id;
    }
}
