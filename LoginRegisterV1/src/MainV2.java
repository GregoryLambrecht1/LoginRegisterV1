import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;



public class MainV2 extends Application  {
    Stage window;
    Scene scene1, scene2;
    private Users users = new Users();

    public static void main(String[] args) {
        launch(args);
        }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

       //adding png to the icon + adding title
       window.getIcons().add(new Image("chickenpf.png"));
       window.setTitle("Test login & register");

       //making new grid + adding to scene + calling the method addUiControls to the gridpane
       GridPane gridPane = createLoginFormPane();
       scene1 = new Scene(gridPane,600,600);
       addUIControls(gridPane);

       //setting scene + showing
       window.setScene(scene1);
       window.show();

       //making new grid + adding grid to the screen + calling the method addUiControls to the gridpaneregister
       GridPane gridPaneRegister = createRegisterFormPane();
       scene2 = new Scene(gridPaneRegister,600,600);
       addUIControls2(gridPaneRegister);


    }

    //adding all the buttons,fiels,labels + the setOnAction for the loginform
    private void addUIControls(GridPane gridPane){
        //making label + textfields
        Label usernameLabel = new Label("Username");
        TextField usernameText = new TextField();

        Label passwordLabel = new Label("password");
        PasswordField passwordText = new PasswordField();

        //making labels for the successfully login / wrong details
        Label loginSucces = new Label("you logged in succesfully");
        Label loginNotSucces = new Label("wrong details , try again");

        //making the buttons for login and register
        Button loginButton = new Button("Login");

        //when the loginbutton is clicked -> remove all labels first -> check with login method if user + pw is the same
        //-> add label to grid with loginsucces message -> if not correct , add label to grid with message login not successfully
        loginButton.setOnAction(e->{
            gridPane.getChildren().removeAll(loginNotSucces,loginSucces);
            if (users.login(usernameText.getText(),passwordText.getText())){
                gridPane.add(loginSucces,0,4);
            }else{
                gridPane.add(loginNotSucces,0,5);
            }
        });

        //making new button + when button clicked , it swaps to new scene and shows it
        Button registerButton = new Button("register");
        registerButton.setOnAction(e->{
            window.setScene(scene2);
            window.setTitle("register here");
            window.show();
        });

        //adding the labels + textareas to gridpane
        gridPane.add(usernameLabel,0,0);
        gridPane.add(usernameText,1,0);

        gridPane.add(passwordLabel,0,1);
        gridPane.add(passwordText,1,1);

        gridPane.add(loginButton,0,2);
        gridPane.add(registerButton,1,2);
    }

    //adding all the buttons,fiels,labels + the setOnAction for the registerform
    private void addUIControls2(GridPane gridPaneRegister){
        //making new labels + button + textfields
        TextField registerUsernameText = new TextField();
        PasswordField registerPasswordText = new PasswordField();
        TextField registerEmailText = new TextField();
        TextField registerBirthText = new TextField();

        Label registerUsername = new Label("Username");
        Label registerPassword = new Label("Password");
        Label registerEmail = new Label("E-mail");
        Label registerBirthDate = new Label("Date of birth");
        Label registerSuccesfull = new Label("register succesfull");
        Label alreadyExcists = new Label("Username already excist");

        //making new register button
        Button register = new Button("Register");
        register.setOnAction(e-> {

          User user = new User();
          //removing all the nodes with that name , to make sure u dont get an error if u try to register again with same details
          gridPaneRegister.getChildren().removeAll(alreadyExcists);

          //with checkIfUserExistAlready i check if the registerusernametext exist already -> if its new , add the data to user + add to Users (list)
            if (!users.checkIfUserExistAlready(registerUsernameText.getText())) {
                user.addNewUser(registerUsernameText.getText(), registerPasswordText.getText(), registerEmailText.getText(), registerBirthText.getText());
                users.add(user);

                //to check if codes works
                users.print();

                //going back to scene1 if its successfully registered
                window.setScene(scene1);

            }else {
                //gonna check here first if the label is not empty (otherwise u get error if u try to place the same label again)
                if (!alreadyExcists.getText().isEmpty()) {
                    gridPaneRegister.add(alreadyExcists, 1, 6);
                }
            }
        }
        );

        //making new button + when clicked switches back to scene 1
        Button goBack = new Button("Go back");
        goBack.setOnAction(e->{
            window.setScene(scene1);
            window.show();
        });

        //adding new labels to grid
        gridPaneRegister.add(registerUsername,0,0);
        gridPaneRegister.add(registerUsernameText,1,0);

        gridPaneRegister.add(registerPassword,0,1);
        gridPaneRegister.add(registerPasswordText,1,1);

        gridPaneRegister.add(registerEmail,0,2);
        gridPaneRegister.add(registerEmailText,1,2);

        gridPaneRegister.add(registerBirthDate,0,3);
        gridPaneRegister.add(registerBirthText,1,3);

        gridPaneRegister.add(register,0,5);

        gridPaneRegister.add(goBack,1,5);

    }

    //making this method to make the structure of the program better , so its not all in the main method
    //all the setttings from the loginform are declared here
    private GridPane createLoginFormPane(){
        GridPane gridPane = new GridPane();

        //making new grid + setting size , padding , allignment , and horizon + vert gaps
        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.setVgap(10);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);

        return gridPane;
    }

    //making this method to make the structure of the program better , so its not all in the main method
    //all the setttings from the registerform are declared here
    private GridPane createRegisterFormPane(){
        GridPane gridPaneRegister = new GridPane();

        //making new grid + setting size , padding , allignment , and horizon + vert gaps
        gridPaneRegister.setMinSize(300,500);
        gridPaneRegister.setAlignment(Pos.CENTER);
        gridPaneRegister.setPadding(new Insets(10,10,10,10));
        gridPaneRegister.setVgap(10);
        gridPaneRegister.setHgap(10);

        return gridPaneRegister;
    }

}
