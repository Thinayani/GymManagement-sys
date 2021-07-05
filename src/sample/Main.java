package sample;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import static sample.MyGymManager.mongoCollection;

public class Main extends Application {

    DBObject list = null;
    DBCursor cursor = mongoCollection.find();

    public static final String columnKey1 = "1";
    public static final String columnKey2 = "2";
    public static final String columnKey3 = "3";

    TableView table = new TableView(viewData());

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        Scene scene = new Scene(new Group());
        primaryStage.setTitle("Table View Sample");
        primaryStage.setWidth(700);
        primaryStage.setHeight(700);

        //Gym Membership
        final Label label = new Label("Gym Members");

//label.setStyle("-fx-font-style: oblique");
        label.setStyle("-fx-text-alignment: center");
        label.setFont(new Font("sans-serif", 38));

        //Columns of the table and width
        TableColumn<Map, String> name = new TableColumn("Name");
        name.setMinWidth(200);
        name.setCellValueFactory(new MapValueFactory<>(columnKey1));
        name.setStyle("-fx-background-color: #c8dddd");
        TableColumn<Map, String> idNumber = new TableColumn("ID Number");
        idNumber.setMinWidth(200);
        idNumber.setCellValueFactory(new MapValueFactory<>(columnKey2));
        idNumber.setStyle("-fx-background-color: #c8dddd");
        TableColumn<Map, String> gender = new TableColumn("Gender");
        gender.setMinWidth(200);
        gender.setCellValueFactory(new MapValueFactory<>(columnKey3));
        gender.setStyle("-fx-background-color: #c8dddd");

        table.setEditable(true);
        table.setStyle("-fx-background-color: antiquewhite");
        table.setMinWidth(600);
        table.getColumns().addAll(name, idNumber, gender);

        final VBox vbox = new VBox();
        final HBox hb = new HBox();

        // search text field
        TextField search = new TextField();
        search.setPromptText("Search Member");
        search.setMinWidth(100);
        search.setMaxWidth(name.getPrefWidth());

        // search button
        Button searchButton = new Button("Search");
        searchButton.setMinWidth(100);
        searchButton.setLayoutX(265);
        searchButton.setLayoutY(70);

        //Search button on action.
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

            }
        });


        hb.getChildren().addAll(search, searchButton);
        hb.setSpacing(3);

        vbox.setSpacing(5);
        vbox.setPadding(new Insets(40, 0, 0, 50));
        vbox.getChildren().addAll(label, table,hb);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public ObservableList<Map> viewData(){
        ObservableList<Map> data = FXCollections.observableArrayList();
        while (cursor.hasNext()){
            Map<String, String> listRow = new HashMap<>();

            list = cursor.next();
            listRow.put(columnKey1, list.get("Name").toString());
            listRow.put(columnKey2, list.get("_id").toString());
            listRow.put(columnKey3, list.get("Gender").toString());

            data.add(listRow);
        }
        return data;
    }
}

