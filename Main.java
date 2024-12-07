package bank;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    private BankAccount currentAccount;

    @Override
    public void start(Stage primaryStage) {
        SavingsAccount savings = new SavingsAccount("Kawtar", 7000.6);
        CheckingAccount checking = new CheckingAccount("Hiba", 1700.0);
        BusinessAccount business = new BusinessAccount("Alae", 200.5);
        currentAccount = savings;

        Label balanceLabel = new Label("Current Balance: " + currentAccount.getBalance());
        TextField amountField = new TextField();
        amountField.setPromptText("Entrer the amount");

        Button depositButton = new Button("deposit");
        Button withdrawButton = new Button("withdrawal");
        Button checkBalanceButton = new Button("check balance");
        Button switchAccountButton = new Button("change the acoount's type");

        depositButton.setOnAction(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                currentAccount.deposit(amount);
                balanceLabel.setText("Current balance: " + currentAccount.getBalance());
            } catch (NumberFormatException ex) {
                showAlert("ERROR", "Enter a valid amount");
            }
        });

        withdrawButton.setOnAction(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                currentAccount.withdraw(amount);
                balanceLabel.setText("Current Balance: " + currentAccount.getBalance());
            } catch (InsufficientFundsException ex) {
                showAlert("ERROR", ex.getMessage());
            } catch (NumberFormatException ex) {
                showAlert("ERROR", "Enter a valid amount");
            }
        });

        checkBalanceButton.setOnAction(e -> {
            balanceLabel.setText("Current Balance: " + currentAccount.getBalance());
        });

        switchAccountButton.setOnAction(e -> {
            if (currentAccount instanceof SavingsAccount) {
                currentAccount = checking;
            } else if (currentAccount instanceof CheckingAccount) {
                currentAccount = business;
            } else {
                currentAccount = savings;
            }
            balanceLabel.setText("Account Type: " + currentAccount.getClass().getSimpleName() + "\nCurrent Balance: " + currentAccount.getBalance());
        });

        VBox root = new VBox(10);
        root.getChildren().addAll(balanceLabel, amountField, depositButton, withdrawButton, checkBalanceButton, switchAccountButton);

        Scene scene = new Scene(root, 300, 250);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setTitle("Banking Operations Simulation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}