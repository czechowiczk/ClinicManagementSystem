package com.vaadin.tutorial.gui.view.manager.addEmployee;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.tutorial.backend.entity.Role;
import com.vaadin.tutorial.backend.entity.User;
import com.vaadin.tutorial.backend.service.UserService;
import com.vaadin.tutorial.gui.view.main.MainView;

@Route(value="add_employee", layout = MainView.class)
@PageTitle("Add new employee | Clinic")
@CssImport("./styles/add-employee.css")
public class AddEmployee extends VerticalLayout {

    private final UserService userService;

    Role role;


    public AddEmployee(UserService userService) {
        this.userService = userService;
        showAddUserPanel();
    }

    public void showAddUserPanel() {
        TextField name = new TextField("name") ;
        TextField surname = new TextField("surname") ;
        TextField specialization = new TextField("specialization") ;
        TextField pesel = new TextField("Pesel") ;
        TextField age = new TextField("Age");
        TextField type = new TextField("type") ;
        PasswordField password = new PasswordField("Password");
        Select<Role> roleSelect = new Select<>();

        HorizontalLayout layout = new HorizontalLayout();
        layout.setId("add-employee");

        roleSelect.setItems(Role.DOCTOR, Role.ADM_EMPLOYEE, Role.MANAGER);

        roleSelect.addValueChangeListener(event -> {
            role = event.getValue();
        });

        layout.add(name,
                surname,
                age,
                pesel,
                password,
                roleSelect);
        layout.getThemeList().set("dark", true);
        layout.setAlignItems(Alignment.CENTER);



        Button addUser = new Button("Add employee", event -> {
            if(role == Role.DOCTOR) {
                if (specialization.isEmpty()) {
                    new Notification("You must specify doctor's specialization");
                }
            } else if (role == Role.ADM_EMPLOYEE) {
                if (type.isEmpty()) {
                    new Notification("You must specify employee type");
                }
            }
            User user = new User(name.getValue(), surname.getValue(), Long.parseLong(pesel.getValue()), Integer.parseInt(age.getValue()), password.getValue(), role);
            userService.save(user);
        });

        add(layout, addUser);
    }

}
