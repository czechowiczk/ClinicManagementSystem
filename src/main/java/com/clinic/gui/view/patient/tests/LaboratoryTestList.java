package com.clinic.gui.view.patient.tests;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.clinic.backend.entity.LaboratoryTest;
import com.clinic.backend.entity.User;
import com.clinic.backend.service.LaboratoryTestService;
import com.clinic.gui.view.main.MainView;

@Route(value="tests", layout = MainView.class)
@PageTitle("Laboratory test | Clinic")
public class LaboratoryTestList extends VerticalLayout {

    Grid<LaboratoryTest> grid = new Grid<>(LaboratoryTest.class);

    private LaboratoryTestService laboratoryTestService;
    private User user;

    public LaboratoryTestList(LaboratoryTestService laboratoryTestService) {
        user = VaadinSession.getCurrent().getAttribute(User.class);
        this.laboratoryTestService = laboratoryTestService;
        addClassName("list-view");
        setSizeFull();
        configureGrid();

        add(grid);
        updateList(user.getId());
    }

    private void updateList() {
        grid.setItems(laboratoryTestService.findAll());
    }

    private void updateList(Integer patient_id) {
        grid.setItems(laboratoryTestService.findAll(patient_id));
    }

    private void configureGrid() {
        grid.addClassName("laboratorytest-grid");
        grid.setSizeFull();
        //grid.removeColumnByKey("company");
        grid.setColumns("type", "date", "description");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));

    }

}
