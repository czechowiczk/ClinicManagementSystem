package com.vaadin.tutorial.backend.service;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.tutorial.backend.entity.Role;
import com.vaadin.tutorial.backend.entity.User;
import com.vaadin.tutorial.backend.repository.UserRepository;
import com.vaadin.tutorial.gui.view.home.HomeView;
import com.vaadin.tutorial.gui.view.main.MainView;
import com.vaadin.tutorial.gui.view.user.diseases.DiseaseList;
import com.vaadin.tutorial.gui.view.user.tests.LaboratoryTestList;
import com.vaadin.tutorial.gui.view.user.visits.VisitList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService {
    @Autowired
    private final UserRepository userRepository;

    public AuthService(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void authenticate(String username, String password) throws AuthException {
        User user = userRepository.getByPESEL(Long.parseLong(username));
        if (user != null && user.getPassword().equals(password)) {
            VaadinSession.getCurrent().setAttribute("userId", user.getId());
            VaadinSession.getCurrent().setAttribute(User.class, user);
            createRoutes(user.getRole());
        } else {
            throw new AuthException();
        }
    }

    private void createRoutes(Role role) {
        getAuthorizedRoutes(role)
                .forEach(route ->
                        RouteConfiguration.forSessionScope().setRoute(route.route, route.view, MainView.class));
    }

    public List<AuthorizedRoute> getAuthorizedRoutes(Role role) {
        ArrayList<AuthorizedRoute> routes = new ArrayList<>();
        if(role.equals(Role.PATIENT)) {
            routes.add(new AuthorizedRoute("home", "Home", HomeView.class));
            routes.add(new AuthorizedRoute("visits", "Visit", VisitList.class));
            routes.add(new AuthorizedRoute("diseases", "Disease", DiseaseList.class));
            routes.add(new AuthorizedRoute("tests", "Test", LaboratoryTestList.class));
        }
        else if(role.equals(Role.ADM_EMPLOYEE)){

        }
        else if(role.equals(Role.DOCTOR)){

        }
        else if(role.equals(Role.MANAGER)){

        }
        else {
            routes.add(new AuthorizedRoute("list", "List", VisitList.class));
        }

        return routes;
    }


    public void register(String firstName, String lastName, Long pesel, Integer age, String password, Role userRole) {
        User user = new User(firstName, lastName, pesel, age, password, userRole);
//
//        user.setAccount(account);
//        account.setUser(user);

        userRepository.save(user);
        //accountRepository.save(account);
    }
    public void register(String firstName, String lastName, Long pesel, Integer age, String password) {
        //Account account = new Account(BankUtils.generateRandomAccountNumber());
        User user = new User(firstName, lastName, pesel, age, password, Role.PATIENT);

        //user.setAccount(account);
        //account.setUser(user);

        userRepository.save(user);
        //accountRepository.save(account);
    }


    public static class AuthorizedRoute {
        final String route;
        final String name;
        final Class<? extends Component> view;

        public AuthorizedRoute(String route, String name, Class<? extends Component> view) {
            this.route = route;
            this.name = name;
            this.view = view;
        }

        public String getRoute() {
            return route;
        }

        public String getName() {
            return name;
        }

        public Class<? extends Component> getView() {
            return view;
        }
    }

    public static class AuthException extends Exception {
    }
}
