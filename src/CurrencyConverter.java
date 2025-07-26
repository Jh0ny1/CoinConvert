import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class CurrencyConverter {

    private static final String[] CURRENCIES = {
            "USD", "EUR", "BRL", "GBP", "JPY",
            "CAD", "AUD", "CNY", "INR", "MXN"
    };

    private static final String API_KEY = "1ef93c3bd42a54f6bf0cc3ba"; // Substitua pela sua chave
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/";

    private JFrame frame;
    private JComboBox<String> fromCurrencyComboBox;
    private JComboBox<String> toCurrencyComboBox;
    private JTextField amountField;
    private JLabel resultLabel;
    private JLabel lastUpdateLabel;

    public CurrencyConverter() {
        initializeUI();
    }

    private void initializeUI() {
        frame = new JFrame("Conversor de Moedas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(240, 240, 240));

        amountField = new JTextField();
        fromCurrencyComboBox = new JComboBox<>(CURRENCIES);
        toCurrencyComboBox = new JComboBox<>(CURRENCIES);

        JButton convertButton = new JButton("Converter");
        convertButton.setBackground(new Color(70, 130, 180));
        convertButton.setForeground(Color.WHITE);
        convertButton.addActionListener(e -> convertCurrency());

        resultLabel = new JLabel();
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));
        resultLabel.setForeground(new Color(0, 100, 0));

        lastUpdateLabel = new JLabel();
        lastUpdateLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        lastUpdateLabel.setForeground(Color.GRAY);

        panel.add(new JLabel("Valor:"));
        panel.add(amountField);
        panel.add(new JLabel("De:"));
        panel.add(fromCurrencyComboBox);
        panel.add(new JLabel("Para:"));
        panel.add(toCurrencyComboBox);
        panel.add(new JLabel());
        panel.add(convertButton);
        panel.add(new JLabel("Resultado:"));
        panel.add(resultLabel);
        panel.add(new JLabel());
        panel.add(lastUpdateLabel);

        frame.getContentPane().add(panel);
    }

    private void convertCurrency() {
        try {
            String fromCurrency = (String) fromCurrencyComboBox.getSelectedItem();
            String toCurrency = (String) toCurrencyComboBox.getSelectedItem();
            String amountText = amountField.getText().trim().replace(",", ".");

            if (amountText.isEmpty()) {
                showError("Digite um valor.");
                return;
            }

            double amount = Double.parseDouble(amountText);

            if (fromCurrency.equals(toCurrency)) {
                resultLabel.setText(String.format("%.2f %s = %.2f %s", amount, fromCurrency, amount, toCurrency));
                lastUpdateLabel.setText("Taxa de câmbio: 1.0 (mesma moeda)");
                return;
            }

            double exchangeRate = getExchangeRate(fromCurrency, toCurrency);
            double converted = amount * exchangeRate;

            resultLabel.setText(String.format("%.2f %s = %.2f %s", amount, fromCurrency, converted, toCurrency));
            lastUpdateLabel.setText("Última atualização: " + new java.util.Date());

        } catch (NumberFormatException ex) {
            showError("Digite um número válido.");
        } catch (Exception ex) {
            showError("Erro: " + ex.getMessage());
        }
    }

    private double getExchangeRate(String fromCurrency, String toCurrency) throws Exception {
        String urlStr = API_URL + API_KEY + "/latest/" + fromCurrency;
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();

        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("Falha na API: código " + connection.getResponseCode());
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONObject rates = jsonResponse.getJSONObject("conversion_rates");

            return rates.getDouble(toCurrency);
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(frame, message, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public void show() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CurrencyConverter converter = new CurrencyConverter();
            converter.show();
        });
    }
}
