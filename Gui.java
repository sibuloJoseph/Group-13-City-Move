import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.geometry.Pos;

public class Gui extends Application {
  
  public void start(Stage primaryStage) throws Exception{
      
      	FlowPane flow = new FlowPane();
      	flow.setAlignment(Pos.CENTER);
    	Label logInUsername = new Label ("Username:");
  		Label logInPassword = new Label ("Password:");
        TextField txtUsername = new TextField();
        TextField txtPassword = new TextField();
      	Button enterToAccount = new Button("Enter");
          
          
          
        Scene scene = new Scene(flow,400,200);
        primaryStage.setTitle("Study Spots App");
        primaryStage.setScene(scene);
        primaryStage.show();  
    }
