import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PasswordGeneratorGUI extends JFrame {
    private PasswordGenerator passwordGenerator;

    public PasswordGeneratorGUI() {
        super("Password Generator");

        setSize(540, 570);
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        passwordGenerator = new PasswordGenerator();

        addGuiComponents();
    }

    private void addGuiComponents() {
        JLabel titleLabel = new JLabel("Password Generator");
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 32));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(0, 10, 540, 39);
        add(titleLabel);

        JTextArea passwordOutput = new JTextArea();
        passwordOutput.setEditable(false);
        passwordOutput.setFont(new Font("Dialog", Font.BOLD, 32));

        JScrollPane passwordOutputPane = new JScrollPane(passwordOutput);
        passwordOutputPane.setBounds(25, 97, 479, 70);
        passwordOutputPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(passwordOutputPane);

        JLabel passwordLengthLabel = new JLabel("Password Length: ");
        passwordLengthLabel.setFont(new Font("Dialog", Font.PLAIN, 32));
        passwordLengthLabel.setBounds(25, 215, 272, 39);
        add(passwordLengthLabel);

        JTextArea passwordLengthInputArea = new JTextArea();
        passwordLengthInputArea.setFont(new Font("Dialog", Font.PLAIN, 32));
        passwordLengthInputArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        passwordLengthInputArea.setBounds(310, 215, 192, 39);
        add(passwordLengthInputArea);

        JToggleButton upperCaseToggle = new JToggleButton("Uppercase");
        upperCaseToggle.setFont(new Font("Dialog", Font.PLAIN, 26));
        upperCaseToggle.setBounds(25, 302, 225, 56);
        add(upperCaseToggle);

        JToggleButton lowerCaseToggle = new JToggleButton("Lowercase");
        lowerCaseToggle.setFont(new Font("Dialog", Font.PLAIN, 26));
        lowerCaseToggle.setBounds(282, 302, 225, 56);
        add(lowerCaseToggle);

        JToggleButton numbersToggle = new JToggleButton("Numbers");
        numbersToggle.setFont(new Font("Dialog", Font.PLAIN, 26));
        numbersToggle.setBounds(25, 373, 225, 56);
        add(numbersToggle);

        JToggleButton symbolsToggle = new JToggleButton("Symbols");
        symbolsToggle.setFont(new Font("Dialog", Font.PLAIN, 26));
        symbolsToggle.setBounds(282, 373, 225, 56);
        add(symbolsToggle);

        JButton generateButton = new JButton("Generate");
        generateButton.setFont(new Font("Dialog", Font.PLAIN, 32));
        generateButton.setBounds(155, 477, 222, 41);
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (passwordLengthInputArea.getText().length() <= 0) return;

                try {
                    int passwordLength = Integer.parseInt(passwordLengthInputArea.getText());
                    boolean anyToggleSelected = lowerCaseToggle.isSelected() ||
                            upperCaseToggle.isSelected() ||
                            numbersToggle.isSelected() ||
                            symbolsToggle.isSelected();

                    if (anyToggleSelected) {
                        String generatedPassword = passwordGenerator.generatePassword(passwordLength,
                                upperCaseToggle.isSelected(), lowerCaseToggle.isSelected(), numbersToggle.isSelected(), symbolsToggle.isSelected());
                        passwordOutput.setText(generatedPassword);
                    } else {
                        passwordOutput.setText("Select at least one option");
                    }
                } catch (NumberFormatException ex) {
                    passwordOutput.setText("Invalid length");
                }
            }
        });
        add(generateButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PasswordGeneratorGUI().setVisible(true);
            }
        });
    }
}
