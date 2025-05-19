import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class BancoDioApp extends Application {
    
    // Variables from your original code
    private double saldo = 0;
    private final double LIMITE = 500;
    private int numeroSaques = 0;
    private final int MAX_SAQUES = 3;
    private StringBuilder extrato = new StringBuilder();
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Banco DIO");
        
        // Create tabs for different operations
        TabPane tabPane = new TabPane();
        
        Tab homeTab = new Tab("Home");
        homeTab.setClosable(false);
        
        Tab depositoTab = new Tab("Depósito");
        depositoTab.setClosable(false);
        
        Tab saqueTab = new Tab("Saque");
        saqueTab.setClosable(false);
        
        Tab extratoTab = new Tab("Extrato");
        extratoTab.setClosable(false);
        
        // Home tab content
        VBox homeContent = createHomeContent();
        homeTab.setContent(homeContent);
        
        // Deposit tab content
        VBox depositoContent = createDepositoContent();
        depositoTab.setContent(depositoContent);
        
        // Withdrawal tab content
        VBox saqueContent = createSaqueContent();
        saqueTab.setContent(saqueContent);
        
        // Statement tab content
        VBox extratoContent = createExtratoContent();
        extratoTab.setContent(extratoContent);
        
        tabPane.getTabs().addAll(homeTab, depositoTab, saqueTab, extratoTab);
        
        Scene scene = new Scene(tabPane, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private VBox createHomeContent() {
        VBox content = new VBox(20);
        content.setPadding(new Insets(20));
        content.setAlignment(Pos.CENTER);
        
        Label welcomeLabel = new Label("Bem-vindo ao Banco DIO!");
        welcomeLabel.setFont(Font.font("System", FontWeight.BOLD, 24));
        
        Label balanceLabel = new Label("Saldo Atual:");
        balanceLabel.setFont(Font.font("System", FontWeight.BOLD, 18));
        
        Label balanceValueLabel = new Label(String.format("R$ %.2f", saldo));
        balanceValueLabel.setFont(Font.font("System", 18));
        
        // Update balance display when this tab is selected
        balanceValueLabel.textProperty().bind(javafx.beans.binding.Bindings.createStringBinding(
            () -> String.format("R$ %.2f", saldo),
            javafx.beans.binding.Bindings.createObjectBinding(() -> saldo)
        ));
        
        VBox balanceBox = new VBox(10, balanceLabel, balanceValueLabel);
        balanceBox.setAlignment(Pos.CENTER);
        balanceBox.setStyle("-fx-padding: 20px; -fx-background-color: #f0f0f0; -fx-background-radius: 10px;");
        
        content.getChildren().addAll(welcomeLabel, balanceBox);
        
        return content;
    }
    
    private VBox createDepositoContent() {
        VBox content = new VBox(20);
        content.setPadding(new Insets(20));
        content.setAlignment(Pos.CENTER);
        
        Label titleLabel = new Label("Realizar Depósito");
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 20));
        
        Label instructionLabel = new Label("Informe o valor a ser depositado:");
        
        TextField valueField = new TextField();
        valueField.setPromptText("Valor (R$)");
        valueField.setPrefWidth(200);
        
        Label messageLabel = new Label("");
        
        Button depositButton = new Button("Depositar");
        depositButton.setOnAction(e -> {
            try {
                double deposito = Double.parseDouble(valueField.getText().replace(",", "."));
                if (deposito > 0) {
                    saldo += deposito;
                    extrato.append(String.format("Depósito: R$ %.2f%n", deposito));
                    messageLabel.setText(String.format("Depósito de R$ %.2f realizado com sucesso!", deposito));
                    messageLabel.setStyle("-fx-text-fill: green;");
                    valueField.clear();
                } else {
                    messageLabel.setText("O valor do depósito deve ser positivo.");
                    messageLabel.setStyle("-fx-text-fill: red;");
                }
            } catch (NumberFormatException ex) {
                messageLabel.setText("Por favor, informe um valor válido.");
                messageLabel.setStyle("-fx-text-fill: red;");
            }
        });
        
        content.getChildren().addAll(titleLabel, instructionLabel, valueField, depositButton, messageLabel);
        
        return content;
    }
    
    private VBox createSaqueContent() {
        VBox content = new VBox(20);
        content.setPadding(new Insets(20));
        content.setAlignment(Pos.CENTER);
        
        Label titleLabel = new Label("Realizar Saque");
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 20));
        
        Label instructionLabel = new Label("Informe o valor a ser sacado:");
        
        TextField valueField = new TextField();
        valueField.setPromptText("Valor (R$)");
        valueField.setPrefWidth(200);
        
        Label limitLabel = new Label(String.format("Limite por saque: R$ %.2f", LIMITE));
        Label countLabel = new Label(String.format("Saques realizados hoje: %d/%d", numeroSaques, MAX_SAQUES));
        
        Label messageLabel = new Label("");
        
        Button withdrawButton = new Button("Sacar");
        withdrawButton.setOnAction(e -> {
            try {
                double saque = Double.parseDouble(valueField.getText().replace(",", "."));
                
                if (numeroSaques >= MAX_SAQUES) {
                    messageLabel.setText("Limite diário de saques atingido.");
                    messageLabel.setStyle("-fx-text-fill: red;");
                } else if (saque > saldo) {
                    messageLabel.setText("Saldo insuficiente para esta operação.");
                    messageLabel.setStyle("-fx-text-fill: red;");
                } else if (saque > LIMITE) {
                    messageLabel.setText(String.format("O valor excede o limite de R$ %.2f por saque.", LIMITE));
                    messageLabel.setStyle("-fx-text-fill: red;");
                } else if (saque <= 0) {
                    messageLabel.setText("O valor do saque deve ser positivo.");
                    messageLabel.setStyle("-fx-text-fill: red;");
                } else {
                    saldo -= saque;
                    numeroSaques++;
                    extrato.append(String.format("Saque: R$ %.2f%n", saque));
                    
                    messageLabel.setText(String.format("Saque de R$ %.2f realizado com sucesso!", saque));
                    messageLabel.setStyle("-fx-text-fill: green;");
                    valueField.clear();
                    
                    // Update the count label
                    countLabel.setText(String.format("Saques realizados hoje: %d/%d", numeroSaques, MAX_SAQUES));
                }
            } catch (NumberFormatException ex) {
                messageLabel.setText("Por favor, informe um valor válido.");
                messageLabel.setStyle("-fx-text-fill: red;");
            }
        });
        
        content.getChildren().addAll(titleLabel, instructionLabel, valueField, limitLabel, countLabel, withdrawButton, messageLabel);
        
        return content;
    }
    
    private VBox createExtratoContent() {
        VBox content = new VBox(20);
        content.setPadding(new Insets(20));
        content.setAlignment(Pos.CENTER);
        
        Label titleLabel = new Label("Extrato Bancário");
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 20));
        
        TextArea extratoArea = new TextArea();
        extratoArea.setEditable(false);
        extratoArea.setPrefRowCount(10);
        extratoArea.setPrefColumnCount(30);
        
        Button refreshButton = new Button("Atualizar Extrato");
        refreshButton.setOnAction(e -> {
            if (extrato.length() == 0) {
                extratoArea.setText("Nenhuma movimentação realizada.");
            } else {
                extratoArea.setText(extrato.toString());
            }
        });
        
        Label balanceLabel = new Label(String.format("Saldo Atual: R$ %.2f", saldo));
        balanceLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
        
        // Update balance display when refresh button is clicked
        refreshButton.setOnAction(e -> {
            if (extrato.length() == 0) {
                extratoArea.setText("Nenhuma movimentação realizada.");
            } else {
                extratoArea.setText(extrato.toString());
            }
            balanceLabel.setText(String.format("Saldo Atual: R$ %.2f", saldo));
        });
        
        content.getChildren().addAll(titleLabel, extratoArea, refreshButton, balanceLabel);
        
        return content;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}