import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class LogIn extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    // MySQL database connection details
    private static final String DATABASE_URL = "jdbc:mysql://localhost/database_name";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    public LogIn() {
        setTitle("Login Form");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(255, 228, 225)); // Set the background color

        getContentPane().add(panel);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Bookman Old Style", Font.PLAIN, 14)); // Set the font
        panel.add(usernameLabel);
        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(150, 25));
        panel.add(usernameField);

        panel.add(Box.createVerticalStrut(5));

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
        panel.add(passwordLabel);
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(150, 25));
        panel.add(passwordField);

        panel.add(Box.createVerticalStrut(5));

        // Add a "Login" button to submit the form
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // When the "Login" button is clicked, check the user's credentials
                checkCredentials();
            }
        });
        panel.add(loginButton);
    }

    private void checkCredentials() {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD)) {
            String sql = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, usernameField.getText());
            statement.setString(2, new String(passwordField.getPassword()));
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                System.out.println("Logged in successfully!");
                // do something else here, like open another window
            } else {
                System.out.println("Incorrect username or password.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogIn loginForm = new LogIn();
        loginForm.setVisible(true);
    }
}
