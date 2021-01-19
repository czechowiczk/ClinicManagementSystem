package com.clinic.gui.view.doctor.timetable;

import com.clinic.backend.entity.Timetable;
import com.clinic.backend.entity.User;
import com.clinic.backend.service.TimetableService;
import com.clinic.gui.view.main.MainView;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route(value="doctor-timetable", layout = MainView.class)
@PageTitle("Timetable | Clinic")
public class DoctorTimetable extends VerticalLayout {
    Grid<Timetable> grid = new Grid<>(Timetable.class);
    private final TimetableService timetableService;
    private User user;
    public DoctorTimetable(TimetableService timetableService) {
        this.timetableService = timetableService;
        user = VaadinSession.getCurrent().getAttribute(User.class);
        setSizeFull();
        configureGrid();

        add(grid);
        updateList(user.getId());
    }

    private void updateList(Integer patientId) {
        grid.setItems(timetableService.getDoctorsTimetables(patientId));
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.setColumns("date", "startHour", "endHour");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }
}
