package netlamp;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Displayer3d extends VBox{
	
	public Displayer3d(DoubleProperty... ps){
		for(int i = 0; i < ps.length; i++){
			Label l = new Label();
			l.textProperty().bind(
				new SimpleStringProperty(ps[i].getName() + ": ").concat(ps[i].asString())
			);
			getChildren().add(l);
		}
	}
	
	public static Displayer3d translation(Node n){
		return new Displayer3d(n.translateXProperty(), n.translateYProperty(), n.translateZProperty());
	}
	
	public static Displayer3d scale(Node n){
		return new Displayer3d(n.scaleXProperty(), n.scaleYProperty(), n.scaleZProperty());
	}
	
}
