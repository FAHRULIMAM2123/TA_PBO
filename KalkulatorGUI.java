import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KalkulatorGUI extends JFrame implements ActionListener {
    private JTextField textField;
    private double angkaPertama, angkaKedua, hasil;
    private int operasi;

    public KalkulatorGUI() {
        // Konfigurasi frame
        setTitle("Kalkulator Sederhana");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel utama
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4));

        // TextField untuk menampilkan input dan output
        textField = new JTextField();
        textField.setEditable(false);
        panel.add(textField);

        // Tombol-tombol angka dan operasi matematika
        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.matches("[0-9]|\\.")) {
            textField.setText(textField.getText() + command);
        } else if (command.matches("[*/\\-+]")) {
            angkaPertama = Double.parseDouble(textField.getText());
            switch (command) {
                case "/":
                    operasi = 4;
                    break;
                case "*":
                    operasi = 3;
                    break;
                case "-":
                    operasi = 2;
                    break;
                case "+":
                    operasi = 1;
                    break;
            }
            textField.setText("");
        } else if (command.equals("=")) {
            angkaKedua = Double.parseDouble(textField.getText());
            switch (operasi) {
                case 1:
                    hasil = angkaPertama + angkaKedua;
                    break;
                case 2:
                    hasil = angkaPertama - angkaKedua;
                    break;
                case 3:
                    hasil = angkaPertama * angkaKedua;
                    break;
                case 4:
                    if (angkaKedua == 0) {
                        textField.setText("Error: Tidak bisa dibagi dengan nol");
                        return;
                    } else {
                        hasil = angkaPertama / angkaKedua;
                    }
                    break;
            }
            textField.setText(String.valueOf(hasil));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new KalkulatorGUI());
    }
}
