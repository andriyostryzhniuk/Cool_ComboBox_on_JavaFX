package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

public class Controller {

    public GridPane rootGridPane;

    @FXML
    public void initialize (){
        rootGridPane.add(initComboBox(), 0 , 0);
    }

    public ComboBox initComboBox(){
        ComboBox comboBox = new ComboBox();
        ComboBox comboBoxListener = new ComboBox();

        comboBox.getStylesheets().add(getClass().getResource("ComboBoxStyle.css").toExternalForm());
        comboBox.getItems().add("Всі об'єкти");
        comboBox.getItems().addAll("Andrii", "Java", "Java", "item4", "item5", "item6", "item7", "item8", "item9");

        new AutoCompleteComboBoxListener<>(comboBox, comboBoxListener);

        comboBox.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                final ListCell<String> cell = new ListCell<String>() {
                    {
                        super.setOnMousePressed(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                System.out.println("mouse pressed");
                                comboBoxListener.setValue(comboBox.getValue());
                            }
                        });
                    }

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(item);
                    }
                };
                return cell;
            }
        });

        comboBoxListener.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue observableValue, String oldValue, String newValue) {
                System.out.println("change detected");
                System.out.println(comboBox.getValue().toString());
            }
        });
        comboBox.setValue(comboBox.getItems().get(0));
        comboBoxListener.setValue(comboBox.getValue());
        return comboBox;
    }
}
