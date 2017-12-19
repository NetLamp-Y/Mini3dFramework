package netlamp;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.AmbientLight;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public abstract class App3d extends Application{
	
	protected Pane pane;
	private TabPane objectControllers;
	protected SubScene subScene;
	
	@Override
	public void start(Stage s) throws Exception {		
		pane = new Pane();
		pane.setStyle("-fx-background-color: black;");
		SplitPane layout = new SplitPane();
		
		objectControllers = new TabPane();
		
		layout.setOrientation(Orientation.VERTICAL);
		layout.getItems().add(subScene = new SubScene(pane, 800, 500));
		layout.getItems().add(objectControllers);
		
		//add 3d objects
		add3dObjects();
		
		Scene sc = new Scene(layout);
		s.setScene(sc);
		s.show();
	}
	
	protected void addControllerTab(String name, Node... children){
		Tab t = new Tab(name);
		t.setContent(new HBox(children));
		objectControllers.getTabs().add(t);
	}
	
	protected abstract void add3dObjects();
	
}
