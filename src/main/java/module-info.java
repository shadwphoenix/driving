module com.project.driving {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.project.driving to javafx.fxml;
    exports com.project.driving;
}