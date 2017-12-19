package netlamp;

import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.AmbientLight;
import javafx.scene.Camera;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.control.Slider;

//m.setBumpMap(new Image("https://www.filterforge.com/filters/477-normal.jpg"));
//m.setSpecularMap(new Image("https://cgiknowledge.files.wordpress.com/2013/02/specular_maps_bricks_image.jpg"));
//b.setMaterial(m);

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Shape3D;
import javafx.scene.shape.Sphere;

public class StuffIn3d extends App3d{
	
	protected void add3dObjects(){
		
		Random r = new Random();
		
		for(int y = 10; y > -10; y--){
			for(int x = 10; x > -10; x--){
				Shape3D b;
				if(r.nextBoolean()){
					b = new Box(512, 512, 512);
				}else{
					b = new Cylinder(512 / 2, 512);
				}
				PhongMaterial m = new PhongMaterial(Color.rgb(
					r.nextInt(256), r.nextInt(256), r.nextInt(256)
				));
				b.setMaterial(m);
				b.setTranslateX(1000 * x);
				b.setTranslateZ(1000 * y);
				pane.getChildren().add(b);
				
				int addX = r.nextInt(10) - 5;
				int addY = r.nextInt(10) - 5;
				int addZ = r.nextInt(10) - 5;
				
				int rotX = r.nextInt(10) - 5;
				int rotY = r.nextInt(10) - 5;
				int rotZ = r.nextInt(10) - 5;
				int rotSpeed = r.nextInt(3);
				
				b.setRotationAxis(new Point3D(rotX, rotY, rotZ));
				
				new AnimationTimer() {
					@Override
					public void handle(long now) {
						b.setTranslateX(b.getTranslateX() + addX);
						b.setTranslateY(b.getTranslateY() + addY);
						b.setTranslateZ(b.getTranslateZ() + addZ);
						
						b.setRotate(b.getRotate() + rotSpeed);
					}
				}.start();
			}
		}
		
		//pane.getChildren().add(new AmbientLight());
		
		PerspectiveCamera cam = new PerspectiveCamera();
		
		addControllerTab("Camera", 
			Controller3d.translation(cam, 1000),
			Controller3d.rotation(cam, 360),
			Controller3d.rotationAxis(cam, 3)
		);
		
		PointLight light = new PointLight(Color.WHITE);
		light.translateXProperty().bind(cam.translateXProperty());
		light.translateYProperty().bind(cam.translateYProperty());
		light.translateZProperty().bind(cam.translateZProperty());
		pane.getChildren().add(light);
		
		pane.setOnMouseClicked(event -> {
			Node clicked = event.getPickResult().getIntersectedNode();
			pane.getChildren().remove(clicked);
		});
		
		cam.setTranslateY(-1000);
		subScene.setCamera(cam);
		
	}
	
	public static void main(String[] args){
		launch(args);
	}

}
