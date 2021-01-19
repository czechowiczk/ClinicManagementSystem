package com.clinic.gui.view.manager.paySalaries;

import com.clinic.backend.entity.*;
import com.clinic.backend.service.EmployeeService;
import com.clinic.backend.service.SalaryService;
import com.clinic.backend.service.TimetableService;
import com.clinic.gui.view.main.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import java.time.LocalDate;

@Route(value="salaries_management", layout = MainView.class)
@PageTitle("Salaries | Clinic")
public class PaySalaries extends VerticalLayout {
    Grid<Employee> grid = new Grid<>(Employee.class);
    private SalaryService salaryService;
    private EmployeeService employeeService;
    private TimetableService timetableService;
    private User user;

    public PaySalaries(SalaryService salaryService, EmployeeService employeeService, TimetableService timetableService) {
        this.timetableService = timetableService;
        //Notification.show(String.valueOf(LocalDate.now().getMonthValue()));
        user = VaadinSession.getCurrent().getAttribute(User.class);
        this.salaryService = salaryService;
        this.employeeService = employeeService;
        addClassName("list-view");
        setSizeFull();
        configureGrid();

        add(grid);
        updateList();
    }

    private void updateList() {
        grid.setItems(employeeService.findAll());
    }

//    private void updateList() {
//        grid.setItems(employeeService.findAll());
//    }

    private void configureGrid() {
        grid.addClassName("timetable-grid");
        grid.setSizeFull();
        grid.setColumns("name", "surname", "PESEL");
        grid.addComponentColumn(employee -> {
            Label alreadyPayed = new Label("Already payed");
            Button pay = new Button(new Icon(VaadinIcon.CHECK));
            Salary s = new Salary();
            Integer workHours = timetableService.getWorkHours(employee.getId(), LocalDate.now().getMonthValue());
            s.setCashAmount(employee.getRate() * workHours);
            s.setHoursAmount(workHours);
            s.setEmployeeId(employee.getId());
            s.setManagerId(user.getId());
            s.setDate(LocalDate.now());
            pay.addClickListener(evt -> {
                salaryService.save(s);
                updateList();
                Notification.show("Payed " + (employee.getRate() * workHours) + "$");
            });

            if(salaryService.checkIfPayed(employee.getId(), LocalDate.now().getMonthValue()) == null) {
                return pay;
            } else if(employee.getId() == user.getId()) {
                return new Label("This is you");
            } else {
                return alreadyPayed;
            }

        }).setHeader("Pay");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

}