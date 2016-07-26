package lab.aikibo.test_app_01;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.Container;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@Widgetset("lab.aikibo.test_app_01.MyAppWidgetset")
public class MyUI extends UI {

    //@Override
    protected void init01(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        
        final TextField name = new TextField();
        name.setCaption("Type your name here:");

        Button button = new Button("Click Me");
        button.addClickListener( e -> {
            layout.addComponent(new Label("Thanks " + name.getValue() 
                    + ", it works!"));
        });
        
        layout.addComponents(name, button);
        layout.setMargin(true);
        layout.setSpacing(true);
        
        setContent(layout);
    }
    
    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	VerticalLayout content = new VerticalLayout();
    	content.setSizeFull();
    	setContent(content);
    	
    	content.addComponent(new Label("Hello!"));
    	
    	HorizontalLayout hor = new HorizontalLayout();
    	hor.setSizeFull();
    	
    	Tree tree = new Tree("My Tree", TreeExample.createTreeContent());
    	hor.addComponent(tree);
    	
    	Table table = new Table("My Table", TableExample.generateContent());
    	table.setSizeFull();
    	//hor.addComponent(table);
    	//hor.setExpandRatio(table, 1);
    	
    	//content.addComponent(hor);
    	//content.setExpandRatio(hor, 1);
    	
    	content.addComponent(new MyView());
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
    
    // --- inner class
    
    private static class TreeExample extends Tree {
    	public static Container createTreeContent() {
    		return null;
    	}
    }
    
    private static class TableExample extends Table {
    	public static Container generateContent() {
    		return null;
    	}
    }
    
    private class MyView extends VerticalLayout {
    	TextField entry = new TextField("Enter this");
    	Label display = new Label("See this");
    	Button click = new Button("Click This");
    	
    	public MyView() {
    		addComponent(entry);
    		addComponent(display);
    		addComponent(click);
    		
    		setSizeFull();
    		addStyleName("myview");
    	}
    }
}
