package com.clinic.gui.view.forms;

import com.clinic.backend.service.AuthService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("register")
@PageTitle("Register | Clinic")
public class RegisterView extends Composite {
    private final AuthService authService;
    String sex;

    public RegisterView(AuthService authService) {
        this.authService = authService;
    }

    @Override
    protected Component initContent() {
        PasswordField password = new PasswordField("Password");
        PasswordField confirmPassword = new PasswordField("Confirm Password");
        TextField firstName = new TextField("First Name");
        TextField lastName = new TextField("Last Name");
        TextField pesel = createPesel();
        TextField age = new TextField("Your age");
        Select<String> sexPicker = new Select<>();
        sexPicker.setLabel("Gender");
        sexPicker.setItems("MAN", "WOMAN", "OTHER");

        sexPicker.addValueChangeListener(event -> sex = event.getValue()+"");


        FormLayout formLayout = new FormLayout(
                firstName,
                lastName,
                password,
                confirmPassword,
                pesel,
                age,
                sexPicker
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
                        Integer.parseInt(age.getValue()),
                        sex
                )));

        verticalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        verticalLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        return verticalLayout;
    }

    private void register(String password, String confirmPassword, String firstName, String lastName,
                          String pesel, Integer age, String sex) {

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
        } else if((age+"").equals("")){
            Notification.show("Enter your age");
        }
        else {
            authService.register(firstName, lastName, Long.parseLong(pesel), age, password, sex);
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

    private TextField createPesel() {
        TextField id = new TextField("Pesel");
        id.setPattern("[0-9]*");
        id.setPreventInvalidInput(true);
        id.setMaxLength(11);
        id.setPlaceholder("12312312312");

        return id;
    }
}
