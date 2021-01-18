package com.clinic.gui.view.doctor.visits;

import com.clinic.backend.entity.Patient;
import com.clinic.backend.entity.User;
import com.clinic.backend.entity.Visit;
import com.clinic.backend.service.VisitService;
import com.clinic.gui.view.main.MainView;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route(value="doctorVisits", layout = MainView.class)
@PageTitle("Visits | Clinic")
public class BookedVisits extends VerticalLayout {

    Grid<Visit> grid = new Grid<>(Visit.class);
    private VisitService visitService;
    private User user;

    public BookedVisits(VisitService visitService) {
        user = VaadinSession.getCurrent().getAttribute(User.class);
        this.visitService = visitService;
        addClassName("list-view");
        setSizeFull();
        configureGrid();

        add(grid);
        updateList(user.getId());
    }

    private void updateList(Integer patientId) {
        grid.setItems(visitService.findAllDoctorsVisits(patientId));
    }

    private void configureGrid() {
        grid.addClassName("visit-grid");
        grid.setSizeFull();
        grid.setColumns("date", "time", "purpose", "description");

        grid.addColumn(visit -> {
            Patient patient = visit.getPatient();
            return patient.getName()+" "+patient.getSurname();
        }).setHeader("Patient");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));

    }
}
