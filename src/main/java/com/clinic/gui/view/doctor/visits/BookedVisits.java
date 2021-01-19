package com.clinic.gui.view.doctor.visits;

import com.clinic.backend.entity.Patient;
import com.clinic.backend.entity.User;
import com.clinic.backend.entity.Visit;
import com.clinic.backend.service.VisitService;
import com.clinic.gui.view.main.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Route(value="doctor-visits", layout = MainView.class)
@PageTitle("Visits | Clinic")
public class BookedVisits extends VerticalLayout {

    Grid<Visit> grid = new Grid<>(Visit.class);
    Button deleteButton = new Button("Delete visit");
    private VisitService visitService;
    private User user;

    public BookedVisits(VisitService visitService) {
        user = VaadinSession.getCurrent().getAttribute(User.class);
        this.visitService = visitService;
        addClassName("list-view");
        setSizeFull();
        configureGrid();
        add(deleteButton);
        add(grid);
        updateList(user.getId());
        deleteVisit();
        visitExpiration();
    }

    private void updateList(Integer patientId) {
        grid.setItems(visitService.findAllDoctorsVisits(patientId));
    }

    private void deleteVisit() {
        deleteButton.addClickListener(event -> {
           visitService.delete(grid.asSingleSelect().getValue());
           updateList(user.getId());
        });
    }

    private void visitExpiration() {
        List<Date> dates = visitService.findAllVisitsDates();
        for (Date date : dates) {
            if (date.toLocalDate().isBefore(LocalDate.now())) {
                visitService.deleteVisitByDate(date);
                updateList(user.getId());
            }
        }
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
