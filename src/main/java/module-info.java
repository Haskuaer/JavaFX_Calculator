module calc.javafx_calculator {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens calc.javafx_calculator to javafx.fxml;
    exports calc.javafx_calculator;
}