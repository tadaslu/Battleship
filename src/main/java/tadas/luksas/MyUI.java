package tadas.luksas;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.ui.VerticalLayout;
import tadas.luksas.beans.User;
import tadas.luksas.client.LaivuKurimas;
import tadas.luksas.services.GameServiceImpl;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {
    User user = new User();
    GameServiceImpl gameService = new GameServiceImpl();
    LaivuKurimas laivuKurimas = new LaivuKurimas();
    GridLayout sample = new GridLayout();


    @Override
    protected void init(VaadinRequest vaadinRequest) {

        Button button = new Button("Set Player");

        VerticalLayout layout = new VerticalLayout();
        HorizontalLayout layoutH = new HorizontalLayout();
        TextField fullNameField = new TextField("Full Name");
        TextField emailField = new TextField("Email");

        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                user.setEmail(emailField.getValue()); //cia supaprastintai
                user = gameService.createUser(fullNameField.getValue(), user.getEmail());
                Notification.show("Let the games begins");
            }
        });


        sample.setSizeFull();
        generateMatrixGrid(10, 10);





        Button k1 = new NativeButton();



        Label label = new Label("Registration");

        layout.setMargin(true);
        layout.setSpacing(true);
        layout.addComponents(label, layoutH, button, sample);
        layoutH.addComponents(fullNameField, emailField);


        setContent(layout);


//        final VerticalLayout layout = new VerticalLayout();
//
//        final TextField name = new TextField();
//        name.setCaption("Type your name here:");
//
//
//        Button button = new Button("Click Me");
//        button.addClickListener(e -> {
//            layout.addComponent(new Label("Thanks " + name.getValue()
//                    + ", it works!"));
//        });
//
//        layout.addComponents(name, button);
//
//        setContent(layout);
    }
    private void generateMatrixGrid(final int rows, final int columns) {
        sample.removeAllComponents();
        sample.setRows(rows);
        sample.setColumns(columns);

        for (int row = 0; row < sample.getRows(); row++) {
            for (int col = 0; col < sample.getColumns(); col++) {
                sample.addComponent(new NativeButton());

            }
        }
    }


    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
