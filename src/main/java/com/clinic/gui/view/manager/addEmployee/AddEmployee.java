package com.clinic.gui.view.manager.addEmployee;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
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
import com.vaadin.flow.server.VaadinSession;
import com.clinic.backend.entity.Role;
import com.clinic.backend.entity.User;
import com.clinic.backend.service.AuthService;
import com.clinic.backend.service.UserService;
import com.clinic.gui.view.main.MainView;

@Route(value="add_employee", layout = MainView.class)
@PageTitle("Add new employee | Clinic")
@CssImport("./styles/add-employee.css")
public class AddEmployee extends Composite {

    private final UserService userService;
    private final AuthService authService;

    Role role;
    User user;


    public AddEmployee(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @Override
    public Component initContent() {
        TextField name = new TextField("Name");
        TextField surname = new TextField("Surname");
        TextField specialization = new TextField("Specialization");
        TextField pesel = createPesel();
        TextField age = new TextField("Age");
        TextField type = new TextField("Type");
        PasswordField password = new PasswordField("Password");
        PasswordField confirmPassword = new PasswordField("Confirm password");
        Select<Role> roleSelect = new Select<>();
        roleSelect.setLabel("Role");

        roleSelect.setItems(Role.DOCTOR, Role.ADM_EMPLOYEE, Role.MANAGER);

        roleSelect.addValueChangeListener(event -> {
            role = event.getValue();
        });

        FormLayout formLayout = new FormLayout(
                name,
                surname,
                password,
                confirmPassword,
                pesel,
                age,
                roleSelect,
                specialization,
                type
        );

        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("25em", 1),
                new FormLayout.ResponsiveStep("32em", 2),
                new FormLayout.ResponsiveStep("40em", 3));

        VerticalLayout verticalLayout = new VerticalLayout(new H1("Hire new employee"),
                formLayout,
                new Button("Hire", e -> {

            if(role == Role.DOCTOR) {
                if (specialization.isEmpty())
                    Notification.show("You must specify doctor's specialization");
                else
                    register(name.getValue(),
                            surname.getValue(),
                            password.getValue(),
                            confirmPassword.getValue(),
                            pesel.getValue(),
                            Integer.parseInt(age.getValue()),
                            role,
                            specialization.getValue(),
                            type.getValue()
                    );

            } else if (role == Role.ADM_EMPLOYEE) {
                if (type.isEmpty())
                    Notification.show("You must specify employee type");
                else
                    register(name.getValue(),
                            surname.getValue(),
                            password.getValue(),
                            confirmPassword.getValue(),
                            pesel.getValue(),
                            Integer.parseInt(age.getValue()),
                            role,
                            specialization.getValue(),
                            type.getValue()
                    );
            } else if(role == Role.MANAGER) {
                if(type.isEmpty() && specialization.isEmpty())
                    register(name.getValue(),
                            surname.getValue(),
                            password.getValue(),
                            confirmPassword.getValue(),
                            pesel.getValue(),
                            Integer.parseInt(age.getValue()),
                            role,
                            specialization.getValue(),
                            type.getValue()
                    );
                else Notification.show("Manager does not have specialization or type");
            }

            else
                register(
                        name.getValue(),
                        surname.getValue(),
                        password.getValue(),
                        confirmPassword.getValue(),
                        pesel.getValue(),
                        Integer.parseInt(age.getValue()),
                        role,
                        specialization.getValue(),
                        type.getValue()
                    );
                }
                ));

        verticalLayout.getThemeList().set("dark", true);
        verticalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        verticalLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        return verticalLayout;
    }

    private void register(String firstName, String lastName, String password, String confirmPassword,
                          String pesel, Integer age, Role role, String specialization, String type) {

        if (password.isEmpty()) {
            Notification.show("Enter a password");
        } else if(pesel.length() != 11) {
            Notification.show("PESEL is wrong");
        } else if(!password.equals(confirmPassword)) {
            Notification.show("Passwords don't match!");
        } else if(firstName.isEmpty()) {
            Notification.show("Enter a firstName");
        } else if(lastName.isEmpty()) {
            Notification.show("Enter a lastName");
        } else if((age+"").equals("")){
            Notification.show("Enter your age");
        } else if(role == null)
            Notification.show("Enter role");
        else {
            authService.hire(firstName, lastName, Long.parseLong(pesel), age, password, role, VaadinSession.getCurrent().getAttribute(User.class).getId(), specialization, type);
            Notification.show("Employee hired.");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                UI.getCurrent().navigate("add_employee");
            }
        }
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
