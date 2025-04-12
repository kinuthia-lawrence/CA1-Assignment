module com.larrykin.ca1assignment {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;

    opens com.larrykin.ca1assignment to javafx.fxml;
    exports com.larrykin.ca1assignment;
}