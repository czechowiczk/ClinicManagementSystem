package com.vaadin.tutorial.gui.view.dashboard;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.tutorial.gui.view.main.MainView;


@Route(value = "dashboard", layout = MainView.class)
@PageTitle("Dashboard | Vaadin CRM")
public class DashboardView extends VerticalLayout {

//    private ContactService contactService;
//    private CompanyService companyService;
//
//    public DashboardView(ContactService contactService, CompanyService companyService) {
//        this.contactService = contactService;
//        this.companyService = companyService;
//        addClassName("dashboard-view");
//        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
//    }
//
//    private Component getContactStats() {
//        Span stats = new Span(contactService.count() + " contacts");
//
//
//        stats.addClassName("contact-stats");
//        return stats;
//    }
}
