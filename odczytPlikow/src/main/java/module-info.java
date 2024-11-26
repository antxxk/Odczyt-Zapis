module com.example.odczytplikow {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.odczytplikow to javafx.fxml;
    exports com.example.odczytplikow;
}