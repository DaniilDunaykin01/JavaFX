import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.jetbrains.annotations.NotNull;

public class Controller1 {

    @FXML
    private Button but1, but2;
    @FXML
    private TableView<Person> tab1;
    @FXML
    private TableColumn<Person, String> col1;
    @FXML
    private TableColumn<Person, Integer> col2;
    @FXML
    private TextField name, age;

    @FXML
    private void initialize() {
        // указываем каким образом колонка в TableView получает данные
        col1.setCellValueFactory(new PropertyValueFactory<>("name"));
        col2.setCellValueFactory(new PropertyValueFactory<>("age"));
        tab1.setItems(Start.personsList);
    }

    @FXML
    private void add(@NotNull ActionEvent e) {
        String msg = "Источник события: " + e.getSource();
        System.out.println(msg);
        if (name.getText().isEmpty() || age.getText().isEmpty()) {
            System.out.println("Не все поля формы заполнены");
            Alert alertMessage = new Alert(Alert.AlertType.WARNING, "Внимание");
            alertMessage.setTitle("Предупреждаю!");
            alertMessage.setContentText("Не все поля формы заполнены");
            alertMessage.showAndWait();
            return;
        }
        String addingName = name.getText().matches("[А-я|A-z]{1,30}") ? name.getText() : "Default";
        int addingAge = !age.getText().isEmpty() && age.getText().matches("[0-9]{1,2}") ? Integer.parseInt(age.getText()) : 0;
        Start.personsList.addFirst(new Person(addingName, addingAge));

        name.clear();
        age.clear();
    }

    @FXML
    private void show(ActionEvent e) {
        String msg = "Нажата кнопка: " + but2.getText();
        System.out.println(msg);

//        TableView.TableViewSelectionModel<Person> rows = tab1.getSelectionModel();
//        ObservableList<Person> ps = rows.getSelectedItems();
//        for (Person p : ps) {
//            System.out.println(p.getName() + ": " + p.getAge());
//        }

        Person selectedPerson = tab1.getSelectionModel().getSelectedItem();
        if (selectedPerson == null) {
            Alert alarm = new Alert(Alert.AlertType.ERROR);
            alarm.setTitle("Error");
            alarm.setContentText("Ни один элемент таблицы не был выбран");
            alarm.showAndWait();
            return;
        }
        name.setText(selectedPerson.getName());
        age.setText(Integer.toString(selectedPerson.getAge()));
    }

    @FXML
    private void edit(ActionEvent e) {
        ObservableList<Person> l = tab1.getItems();
        if (l.isEmpty()) {
            System.err.println("В таблице нет записей для Изменения");
            return;
        }
        if (name.getText().isEmpty() || age.getText().isEmpty()) {
            System.err.println("Не выбран элемент для обновления");
            return;
        }
        int selectedPersonIndex = tab1.getSelectionModel().getSelectedIndex();
        if (selectedPersonIndex == -1) {
            System.err.println("Элемент не выбран");
            return;
        }
        Person p = Start.personsList.get(selectedPersonIndex);
        p.setName(name.getText());
        p.setAge(Integer.parseInt(age.getText()));
        tab1.getItems().set(selectedPersonIndex, p);
    }
}