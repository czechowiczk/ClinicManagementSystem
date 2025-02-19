package com.clinic.gui.view.forms;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.clinic.backend.service.AuthService;

@Route(value = "login")
@PageTitle("Login | Clinic")
@CssImport("./styles/login-view.css")
public class LoginView extends Div {


    public LoginView(AuthService authService) {
        setId("login-view");
        setWidth("20%");

        TextField username = new TextField("Username");
        PasswordField password = new PasswordField("Password");
        username.setId("login-view-username");
        password.setId("login-view-password");

        add(
                new H1("Welcome"),
                username,
                password,
                new Button("Login", event -> {
                    try {
                        authService.authenticate(username.getValue(), password.getValue());
                        UI.getCurrent().navigate("home");
                    } catch (AuthService.AuthException e) {
                        Notification.show("Wrong credentials.");
                    }
                }),
                new RouterLink("Register", RegisterView.class)
        );
    }
}
