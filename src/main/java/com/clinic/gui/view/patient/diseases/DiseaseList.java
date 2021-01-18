package com.clinic.gui.view.patient.diseases;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.clinic.backend.entity.Disease;
import com.clinic.backend.entity.User;
import com.clinic.backend.service.DiseaseService;
import com.clinic.gui.view.main.MainView;

@Route(value="diseases", layout = MainView.class)
@PageTitle("Diseases | Clinic")
public class DiseaseList extends VerticalLayout {

    Grid<Disease> grid = new Grid<>(Disease.class);
    private DiseaseService diseaseService;
    private User user;

    public DiseaseList(DiseaseService diseaseService) {
        user = VaadinSession.getCurrent().getAttribute(User.class);
        this.diseaseService = diseaseService;
        addClassName("list-view");
        setSizeFull();
        configureGrid();

        add(grid);
        updateList(user.getId());
    }

    private void updateList(Integer patientId) {
        grid.setItems(diseaseService.findAll(patientId));
    }

    private void updateList() {
        grid.setItems(diseaseService.findAll());
    }

    private void configureGrid() {
        grid.addClassName("disease-grid");
        grid.setSizeFull();
        grid.setColumns("name", "date", "description");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }
}