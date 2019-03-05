import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.control.PasswordField;
import javafx.scene.paint.*;


public class Gui extends Application {
  private UserAccountList userAccountList;
  
  public void start(Stage primaryStage) throws Exception{
     	VBox centerBox = new VBox();
    	centerBox.setAlignment(Pos.CENTER);
    
        final ImageView selectedImage = new ImageView();   
        Image image1 = new Image(Gui.class.getResourceAsStream("Logo.jpg"));
		selectedImage.setImage(image1);
		centerBox.getChildren().add(selectedImage);
    
    	Label logInUsername = new Label ("Username:");
  		Label logInPassword = new Label ("Password:");
        TextField txtUsername = new TextField();
        PasswordField txtPassword = new PasswordField();
    	txtUsername.setMaxWidth(200);
    	txtPassword.setMaxWidth(200);
      	Button enterToAccount = new Button("Login");
    
    	centerBox.setSpacing(20);	
      	logInUsername.setFont(Font.font("Verdana", 20));
      	logInPassword.setFont(Font.font("Verdana", 20));
    	logInUsername.setTextFill(Color.WHITE);
    	logInPassword.setTextFill(Color.WHITE);
    
        centerBox.getChildren().addAll(logInUsername, txtUsername, logInPassword, txtPassword, enterToAccount);
    	centerBox.setStyle("-fx-background-color: #980E0E;");
    	
    	enterToAccount.setOnAction(new EventHandler <ActionEvent>() {
        	public void handle (ActionEvent event) {
            	String username = txtUsername.getText();
              	String password = txtPassword.getText();
            	if(userAccountList.hasAccount(username)){
                  if(userAccountList.credentialsValid(username, password)){
                    primaryStage.setScene()
                  }
                }
                else {
                	userAccountList.addAccount(username, password);
                }
              	
        });
        
                      
    
    
        Scene sceneForLogin = new Scene(centerBox, 300,500);
        primaryStage.setTitle("Study Spots App");
        primaryStage.setScene(sceneForLogin);
        primaryStage.show();  
    }
}
