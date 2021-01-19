package com.clinic.gui.view.manager.addEmployee;

import com.clinic.backend.entity.Employee;
import com.clinic.backend.entity.Timetable;
import com.clinic.backend.entity.User;
import com.clinic.backend.service.TimetableService;
import com.clinic.gui.view.main.MainView;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route(value="timetables_management", layout = MainView.class)
@PageTitle("Timetables | Clinic")
public class ManageTimetable extends VerticalLayout {
    Grid<Timetable> grid = new Grid<>(Timetable.class);
    private TimetableService timetableService;
    private User user;

    public ManageTimetable(TimetableService timetableService) {
        user = VaadinSession.getCurrent().getAttribute(User.class);
        this.timetableService = timetableService;
        addClassName("list-view");
        setSizeFull();
        configureGrid();

        add(grid);
        updateList(user.getId());
    }

    private void updateList(Integer managerId) {
        grid.setItems(timetableService.getEmployeesTimetable(managerId));
        //grid.setItems(timetableService.getAdmEmployeesTimetable(managerId));
    }

    private void updateList() {
        grid.setItems(timetableService.findAll());
    }

    private void configureGrid() {
        grid.addClassName("timetable-grid");
        grid.setSizeFull();
        grid.setColumns("date", "startHour", "endHour");
        grid.addColumn(timetable -> {
            Employee employee = timetable.getEmployee();
            return employee.getName()+" "+employee.getSurname();
        }).setHeader("Employee");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }
}
