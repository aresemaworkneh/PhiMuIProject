import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class SignUpForm extends JFrame {
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField yearField;
    private JTextField statusField;

    // MySQL database connection details
    private static final String DATABASE_URL = "jdbc:mysql://localhost/database_name";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    public SignUpForm() {
        setTitle("Sign Up Form");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(255, 228, 225)); // Set the background color

        getContentPane().add(panel);

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setFont(new Font("Bookman Old Style", Font.PLAIN, 14)); // Set the font

        panel.add(firstNameLabel);
        firstNameField = new JTextField();
        firstNameField.setPreferredSize(new Dimension(25, 25));
        panel.add(firstNameField);




        panel.add(Box.createVerticalStrut(5));

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
        panel.add(lastNameLabel);
        lastNameField = new JTextField();
        panel.add(lastNameField);

        panel.add(Box.createVerticalStrut(5));

        JLabel yearLabel = new JLabel("Year:");
        yearLabel.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
        panel.add(yearLabel);
        yearField = new JTextField();
        panel.add(yearField);

        panel.add(Box.createVerticalStrut(5));

        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
        panel.add(statusLabel);
        statusField = new JTextField();
        panel.add(statusField);

        panel.add(Box.createVerticalStrut(5));

        // Add a "Sign Up" button to submit the form
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // When the "Sign Up" button is clicked, insert the user's information into the database
                insertUserIntoDatabase();
            }
        });
        panel.add(signUpButton);
    }

    private void insertUserIntoDatabase() {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD)) {
            String sql = "INSERT INTO users (first_name, last_name, year, status) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, firstNameField.getText());
            statement.setString(2, lastNameField.getText());
            statement.setInt(3, Integer.parseInt(yearField.getText()));
            statement.setString(4, statusField.getText());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SignUpForm signUpForm = new SignUpForm();
        signUpForm.setVisible(true);
    }
}


