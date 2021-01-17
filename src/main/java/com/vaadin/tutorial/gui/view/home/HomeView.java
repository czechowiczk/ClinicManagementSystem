package com.vaadin.tutorial.gui.view.home;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.tutorial.backend.entity.Role;
import com.vaadin.tutorial.backend.entity.User;
import com.vaadin.tutorial.backend.service.UserService;
import com.vaadin.tutorial.gui.view.main.MainView;

import java.util.Optional;


@Route(value = "home", layout = MainView.class)
@CssImport("./styles/home-view.css")
@PageTitle("Home")
public class HomeView extends Div {
    private final UserService userService;
    private User user;
    private final H2 balanceH2 = new H2();

    public HomeView(UserService userService) {
        addClassName("home-view");
        this.userService = userService;

        fetchFreshUser();

        setUpLayoutWithUserCredentials();
    }

    private void setUpLayoutWithUserCredentials() {
        TextField labelFirstName = new TextField("First name:");
        labelFirstName.setValue(user.getName());
        labelFirstName.setReadOnly(true);

        TextField labelLastName = new TextField("Surname:");
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
        credentialsLayout.addClassName("credentials-form");
        credentialsLayout.setAlignItems(FlexComponent.Alignment.CENTER);


        Button buttonTopUp = new Button("Book visitation", new Icon(VaadinIcon.PLUS_CIRCLE));
        buttonTopUp.setId("button-top-up");
        buttonTopUp.setIconAfterText(true);
        buttonTopUp.setAutofocus(true);
        buttonTopUp.addClickListener(event -> showTopUpDialog());

        HorizontalLayout patientLayout = new HorizontalLayout(balanceH2, buttonTopUp);
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
        TextField textFieldTransactionTitle = new TextField("Tytuł ");

        verticalLayout.add(
                new Text("Book visitation"),
                textFieldTransactionTitle
                );

        Button confirmButton = new Button("Potwierdź", event -> {

            if (!textFieldTransactionTitle.getValue().isEmpty()) {
                // dodawanie wizyty dorobić
                //transactionService.save(transaction);

                //balanceH2.setText(roundOff(newBalance));
                dialog.close();
            } else {
                textFieldTransactionTitle.setErrorMessage("Tytuł wpłaty nie może być pusty");
            }

        });

        Button cancelButton = new Button("Anuluj", event -> {
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