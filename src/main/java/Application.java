import Factory.ControllerFactory;
import Menu.MenuController;
import database.Database;
import org.jooq.DSLContext;

public class Application {

    public void start() throws Exception {


        System.out.println("Initializing database connection...");

        DSLContext context = Database.getDslContext(); // DatabaseManager is your singleton class for managing the database.

        ControllerFactory controllerFactory = ControllerFactory.getInstance();

        MenuController menuController = controllerFactory.getMenuController();

        menuController.show();
    }


    public void exit() {

    }

}
