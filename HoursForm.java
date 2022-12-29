import java.awt.Dimension;
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
public class HoursForm extends JFrame {
    private JTextField hoursField;
    private JTextField qPointsField;
    private JTextField phiPointsField;

    // MySQL database connection details
    private static final String DATABASE_URL = "jdbc:mysql://localhost/database_name";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    public HoursForm() {
        setTitle("Hours Form");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Hours:"));
        hoursField = new JTextField();
        hoursField.setPreferredSize(new Dimension(150, 25));
        panel.add(hoursField);

        panel.add(Box.createVerticalStrut(15));

        panel.add(new JLabel("qPoints:"));
        qPointsField = new JTextField();
        qPointsField.setPreferredSize(new Dimension(150, 25));
        panel.add(qPointsField);

        panel.add(Box.createVerticalStrut(15));

        panel.add(new JLabel("phiPoints:"));
        phiPointsField = new JTextField();
        phiPointsField.setPreferredSize(new Dimension(150, 25));
        panel.add(phiPointsField);

        panel.add(Box.createVerticalStrut(15));

        // Add a "Submit" button to submit the form
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // When the "Submit" button is clicked, insert the user's information into the database
                insertHoursIntoDatabase();
            }
        });
        panel.add(submitButton);
    }

    private void insertHoursIntoDatabase() {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD)) {
            String sql = "INSERT INTO hours (hours, q_points, phi_points) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(hoursField.getText()));
            statement.setInt(2, Integer.parseInt(qPointsField.getText()));
            statement.setInt(3, Integer.parseInt(phiPointsField.getText()));
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HoursForm hoursForm = new HoursForm();
        hoursForm.setVisible(true);
    }
}

