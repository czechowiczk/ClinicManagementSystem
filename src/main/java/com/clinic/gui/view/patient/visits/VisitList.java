package com.clinic.gui.view.patient.visits;

import com.clinic.backend.entity.Doctor;
import com.clinic.backend.service.VisitService;
import com.clinic.gui.view.main.MainView;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.clinic.backend.entity.User;
import com.clinic.backend.entity.Visit;
import com.clinic.backend.service.DoctorService;

@Route(value="visits", layout = MainView.class)
@PageTitle("Visits | Clinic")
public class VisitList extends VerticalLayout {

    Grid<Visit> grid = new Grid<>(Visit.class);
    private final VisitService visitService;
    private final DoctorService doctorService;
    private User user;
    public VisitList(VisitService visitService, DoctorService doctorService) {
        user = VaadinSession.getCurrent().getAttribute(User.class);
        this.visitService = visitService;
        this.doctorService = doctorService;
        addClassName("list-view");
        setSizeFull();
        configureGrid();

        add(grid);
        updateList(user.getId());
    }

    private void updateList(Integer patientId) {
        grid.setItems(visitService.findAll(patientId));
    }

    private void updateList() {
        grid.setItems(visitService.findAll());
    }


    private void configureGrid() {
        grid.addClassName("visit-grid");
        grid.setSizeFull();
        grid.setColumns("date","time", "purpose", "description");
        grid.addColumn(visit -> {
            Doctor doctor = visit.getDoctor();
            return doctor.getName()+" "+doctor.getSurname();
        }).setHeader("Doctor");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

}