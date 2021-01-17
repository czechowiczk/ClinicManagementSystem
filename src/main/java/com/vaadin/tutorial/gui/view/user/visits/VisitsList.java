package com.vaadin.tutorial.gui.view.user.visits;

import com.vaadin.tutorial.backend.service.VisitService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.tutorial.backend.entity.User;
import com.vaadin.tutorial.gui.view.main.MainView;

@Route(value="visits", layout = MainView.class)
@PageTitle("Visits | Clinic")
public class VisitsList extends VerticalLayout {

    private Grid<User> grid = new Grid<>(User.class);
    TextField filterText = new TextField();
    private VisitService visitService;
//
//    public VisitsList(UserService userService, VisitService visitService) {
//        this.visitService = visitService;
//        addClassName("list-view"); //nazwa klasy do css
//        setSizeFull();
//
//        configureGrid();
//
//        form = new ContactForm(companyService.findAll());
//        form.addListener(ContactForm.SaveEvent.class, this::saveContact);
//        form.addListener(ContactForm.DeleteEvent.class, this::deleteContact);
//        form.addListener(ContactForm.CloseEvent.class, e -> closeEditor());
//
//
//        Div content = new Div(grid, form);
//        content.addClassName("content");
//        content.setSizeFull();
//
//        add(getToolbar(), content);
//        updateList();
//
//        closeEditor();
//    }
//
//    private void saveContact(ContactForm.SaveEvent event) {
//        userService.save(event.getContact());
//        updateList();
//        closeEditor();
//    }
//
//    private void deleteContact(ContactForm.DeleteEvent event) {
//        contactService.delete(event.getContact());
//        updateList();
//        closeEditor();
//    }
//
//    private void configureGrid() {
//        grid.addClassName("contact-grid");
//        grid.setSizeFull();
//        grid.removeColumnByKey("company");
//        grid.setColumns("firstName", "lastName", "email", "status");
//        grid.addColumn(contact -> {
//            Company company = contact.getCompany();
//
//            return company == null ? "-" : company.getName();
//        }).setHeader("Company");
//
//        grid.getColumns().forEach(col -> col.setAutoWidth((true)));
//
//        grid.asSingleSelect().addValueChangeListener(event ->
//                editContact(event.getValue()));
//    }
//
//    private void editContact(User user) {
//        if (contact == null) {
//            closeEditor();
//        } else {
//            form.setContact(contact);
//            form.setVisible(true);
//            addClassName("editing");
//        }
//    }
//
//    private void closeEditor() {
//        form.setContact(null);
//        form.setVisible(false);
//        removeClassName("editing");
//    }
//
//    private HorizontalLayout getToolbar() {
//        filterText.setPlaceholder("Filter by name...");
//        filterText.setClearButtonVisible(true);
//        filterText.setValueChangeMode(ValueChangeMode.LAZY);
//        filterText.addValueChangeListener(e -> updateList());
//
//        Button addContactButton = new Button("Add contact");
//        addContactButton.addClickListener(click -> addContact());
//
//        HorizontalLayout toolbar = new HorizontalLayout(filterText, addContactButton);
//
//
//        toolbar.addClassName("toolbar");
//        return toolbar;
//    }
//
//    void addContact() {
//        grid.asSingleSelect().clear();
//        editContact(new Contact());
//    }
//
//    private void updateList() {
//        grid.setItems(contactService.findAll(filterText.getValue()));
//    }

}