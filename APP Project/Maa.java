import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.net.URI;              
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.font.TextAttribute;

class CycleData {
    int cycleLength;
    String startDate;
    LocalDate currentDate;

    public CycleData(int cycleLength, String startDate, LocalDate currentDate) {
        this.cycleLength = cycleLength;
        this.startDate = startDate;
        this.currentDate = currentDate;
    }
}

public class Main {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/nog";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";


    private static List<CycleData> cycleDataList = new ArrayList<>();

    public static void main(String[] args) {

        // Load the MySQL JDBC driver
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
        // Handle driver loading error
    }

        JFrame frame = new JFrame("BloomEase");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1)); // Single column layout

        // Create a JLabel for the title with HTML formatting
        JLabel titleLabel = new JLabel("<html><div style='text-align: center; font-size: 20px; font-weight: bold;'>Welcome to \"BloomEase\"</div></html>");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel);

        // Create a JLabel for the additional text with HTML formatting to make it bold
        JLabel additionalTextLabel = new JLabel("<html><div style='text-align: center; font-size: 14px; font-weight: bold;'>( Nurturing Women's Cycle )</div></html>");
        additionalTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(additionalTextLabel);

        JButton[] buttons = new JButton[6];
        String[] buttonLabels = {
                "Period Problems",
                "Contact a Doctor",
                "Track Menstrual Cycle",
                "Set Custom Reminders",
                "View Menstrual Cycle Data",
                "View Custom Reminders"
        };

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            panel.add(buttons[i]);
        }

        for (int i = 0; i < buttons.length; i++) {
            final int index = i;
            buttons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    handleButtonClick(index);
                }
            });
        }

        frame.add(panel);
        frame.setVisible(true);
    }

    private static void handleButtonClick(int choice) {
        switch (choice) {
            case 0:
                // Code for "Period Problems"
                displayPeriodProblems();
                break;
            case 1:
                // Code for "Contact a Doctor"
                displayDoctorContacts();
                break;
            case 2:
                // Code for "Track Menstrual Cycle"
                trackMenstrualCycle();
                break;
            case 3:
                // Code for "Set Custom Reminders"
                setCustomReminders();
                break;
            case 4:
                // Code for "View Menstrual Cycle Data"
                viewMenstrualCycleData();
                break;
            case 5:
                // Code for "View Custom Reminders"
                viewCustomReminders();
                break;
        }
    }

    private static void displayPeriodProblems() {
        // Create a new JFrame for period problems and remedies
        JFrame problemsFrame = new JFrame("Period Problems");
        problemsFrame.setSize(400, 300);
        problemsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this frame

        JPanel problemsPanel = new JPanel();
        problemsPanel.setLayout(new GridLayout(0, 1)); // Single column layout

        // Create a JLabel for the title with HTML formatting
        JLabel problemsTitleLabel = new JLabel("<html><div style='text-align: center; font-size: 20px; font-weight: bold;'>Period Problems</div></html>");
        problemsTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        problemsPanel.add(problemsTitleLabel);

        String[] problems = {
                "Menstrual Cramps",
                "Irregular Periods",
                "Heavy Bleeding",
                "Mood Swings",
                "Fatigue",
                "Bloating",
                "Headache",
                "Acne",
                "Back Pain",
                "Nausea"
        };

        for (int i = 0; i < problems.length; i++) {
            final int index = i;
            JButton problemButton = new JButton(problems[i]);
            problemButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    displayRemedy(index + 1); // Index starts from 1
                }
            });
            problemsPanel.add(problemButton);
        }

        problemsFrame.add(problemsPanel);
        problemsFrame.setVisible(true);
    }

    private static void displayRemedy(int choice) {
        // Implement your code to display remedies here, similar to the original program
        // You can use JOptionPane or another JFrame for displaying the remedy details
        String remedy = "";

        switch (choice) {
            case 1:
                remedy = "Remedy for Menstrual Cramps: Rest, apply a heating pad, take over-the-counter pain relievers.";
                break;
            case 2:
                remedy = "Remedy for Irregular Periods: Maintain a healthy lifestyle, manage stress, consult a doctor.";
                break;
            case 3:
                remedy = "Remedy for Heavy Bleeding: Use proper hygiene products, iron-rich diet, consult a doctor.";
                break;
            case 4:
                remedy = "Remedy for Mood Swings: Practice relaxation techniques, regular exercise, talk to a counselor.";
                break;
            case 5:
                remedy = "Remedy for Fatigue: Get enough sleep, stay hydrated, eat balanced meals, light exercise.";
                break;
            case 6:
                remedy = "Remedy for Bloating: Stay hydrated, avoid salty foods, exercise, herbal teas.";
                break;
            case 7:
                remedy = "Remedy for Headache: Rest in a quiet, dark room, stay hydrated, use pain relievers.";
                break;
            case 8:
                remedy = "Remedy for Acne: Maintain a good skincare routine, avoid excessive oily foods.";
                break;
            case 9:
                remedy = "Remedy for Back Pain: Apply heat or cold packs, gentle stretching, maintain good posture.";
                break;
            case 10:
                remedy = "Remedy for Nausea: Ginger tea, peppermint, avoid greasy or spicy foods.";
                break;
        }

        JOptionPane.showMessageDialog(null, remedy);
    }

    private static void displayDoctorContacts() {
        // Create a new JFrame to display doctor contacts
        JFrame doctorFrame = new JFrame("Contact a Doctor");
        doctorFrame.setSize(400, 300);
        doctorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this frame

        JPanel doctorPanel = new JPanel();
        doctorPanel.setLayout(new GridLayout(0, 1)); // Single column layout

        // Create a JLabel for the title with HTML formatting
        JLabel doctorTitleLabel = new JLabel("<html><div style='text-align: center; font-size: 20px; font-weight: bold;'>Contact a Doctor</div></html>");
        doctorTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        doctorPanel.add(doctorTitleLabel);

        String[] doctors = {
                "Dr. Ditya Gupta - Phone: 12345 67890",
                "Dr. Nand Avasti - Phone: 98765 43210",
                "Dr. Janjal Bhajpai - Phone: 55512 34567",
                "Dr. Prats Cati - Phone: 77788 89999",
                "Dr. Shalini Joshi - Phone: 11122 23333"
        };

        for (String doctor : doctors) {
            JLabel doctorLabel = new JLabel(doctor);
            doctorPanel.add(doctorLabel);
        }

        // Inside your ActionListener for the "Contact a Doctor" button
        JButton bookAppointmentButton = new JButton("Book an appointment");
        
        bookAppointmentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Define the URL
                    URI uri = new URI("https://www.lybrate.com/chennai/doctors-for-menstrual-problems-treatment");
                    
                    // Open the default web browser
                    Desktop.getDesktop().browse(uri);
                } catch (IOException | URISyntaxException ex) {
                    ex.printStackTrace(); // Handle any exceptions here
                }
            }
        });
        
        doctorPanel.add(bookAppointmentButton);
        doctorFrame.add(doctorPanel);
        doctorFrame.setVisible(true);
    }

    private static void trackMenstrualCycle() {
        JFrame trackCycleFrame = new JFrame("Track Menstrual Cycle");
        trackCycleFrame.setSize(400, 300);
        trackCycleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this frame

        JPanel trackCyclePanel = new JPanel();
        trackCyclePanel.setLayout(new GridLayout(0, 1)); // Single column layout

        // Create a JLabel for the title with HTML formatting
        JLabel trackCycleTitleLabel = new JLabel("<html><div style='text-align: center; font-size: 20px; font-weight: bold;'>Track Menstrual Cycle</div></html>");
        trackCycleTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        trackCyclePanel.add(trackCycleTitleLabel);

        JTextField cycleLengthField = new JTextField(10);
        JTextField startDateField = new JTextField(10);
        JButton doneButton = new JButton("Done");

        trackCyclePanel.add(new JLabel("Cycle Length (in days):"));
        trackCyclePanel.add(cycleLengthField);
        trackCyclePanel.add(new JLabel("Start Date (YYYY-MM-DD):"));
        trackCyclePanel.add(startDateField);
        trackCyclePanel.add(doneButton);

        doneButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get data from input fields
                String cycleLengthStr = cycleLengthField.getText();
                String startDate = startDateField.getText();

                // Validate input and save data
                if (isValidInput(cycleLengthStr, startDate)) {
                    int cycleLength = Integer.parseInt(cycleLengthStr);
                    LocalDate currentDate = LocalDate.now();
                    CycleData cycleData = new CycleData(cycleLength, startDate, currentDate);
                    cycleDataList.add(cycleData);
                    saveCycleDataToDatabase(cycleData);
                    JOptionPane.showMessageDialog(null, "Cycle data recorded.");
                    trackCycleFrame.dispose(); // Close the input frame
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid data.");
                }
            }
        });

        trackCycleFrame.add(trackCyclePanel);
        trackCycleFrame.setVisible(true);
    }

    private static boolean isValidInput(String cycleLengthStr, String startDate) {
        try {
            int cycleLength = Integer.parseInt(cycleLengthStr);
            LocalDate.parse(startDate); // Validate start date format
            return cycleLength > 0;
        } catch (NumberFormatException | NullPointerException | DateTimeException e) {
            return false;
        }
    }

    private static void saveCycleDataToDatabase(CycleData cycleData) {
    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
        String sql = "INSERT INTO menstrual_cycle_data (cycle_length, start_date, cycle_date) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, cycleData.cycleLength);
            preparedStatement.setString(2, cycleData.startDate);
            preparedStatement.setString(3, cycleData.currentDate.toString());
            preparedStatement.executeUpdate();
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle database connection or query errors
    }
}


private static List<CycleData> readCycleDataFromDatabase() {
    List<CycleData> cycleDataList = new ArrayList<>();
    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
        String sql = "SELECT cycle_length, start_date, cycle_date FROM menstrual_cycle_data";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int cycleLength = resultSet.getInt("cycle_length");
                    String startDate = resultSet.getString("start_date");
                    LocalDate currentDate = LocalDate.parse(resultSet.getString("cycle_date"));
                    CycleData cycleData = new CycleData(cycleLength, startDate, currentDate);
                    cycleDataList.add(cycleData);
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle database connection or query errors
    }
    return cycleDataList;
}


private static void setCustomReminders() {

    JFrame remindersFrame = new JFrame("Reminders");
    remindersFrame.setSize(400, 300);
    remindersFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this frame

    JPanel remindersPanel = new JPanel();
    remindersPanel.setLayout(new GridLayout(0, 1)); // Single column layout

    // Create a JLabel for the title with HTML formatting
    JLabel remindersTitleLabel = new JLabel("<html><div style='text-align: center; font-size: 20px; font-weight: bold;'>Reminders</div></html>");
    remindersTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
    remindersPanel.add(remindersTitleLabel);

    JTextField reminderText = new JTextField(20);
    JButton addReminderButton = new JButton("Add Reminder");
    JButton doneButton = new JButton("Done");

    remindersPanel.add(new JLabel("Reminder Text:"));
    remindersPanel.add(reminderText);
    remindersPanel.add(addReminderButton);
    remindersPanel.add(doneButton);

    addReminderButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String reminder = reminderText.getText();
            if (!reminder.isEmpty()) {
                // Save the reminder to the file
                saveReminderToDatabase(reminder);
                reminderText.setText("");
            }
        }
    });

    doneButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            remindersFrame.dispose(); // Close the input frame
        }
    });

    remindersFrame.add(remindersPanel);
    remindersFrame.setVisible(true);
}

private static void saveReminderToDatabase(String reminder) {
    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
        String sql = "INSERT INTO custom_reminders (reminder_text) VALUES (?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, reminder);
            preparedStatement.executeUpdate();
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle database connection or query errors
    }
}


private static void viewMenstrualCycleData() {
    // Create a new JFrame to display menstrual cycle data
    JFrame dataFrame = new JFrame("View Menstrual Cycle Data");
    dataFrame.setSize(400, 300);
    dataFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this frame

    JPanel dataPanel = new JPanel();
    dataPanel.setLayout(new GridLayout(0, 1)); // Single column layout

    // Create a JLabel for the title with HTML formatting
    JLabel dataTitleLabel = new JLabel("<html><div style='text-align: center; font-size: 20px; font-weight: bold;'>Menstrual Cycle Data</div></html>");
    dataTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
    dataPanel.add(dataTitleLabel);

    JTextArea cycleDataTextArea = new JTextArea(10, 30);
    cycleDataTextArea.setEditable(false); // Make the text area read-only

    // Load menstrual cycle data and display it in the text area
    List<CycleData> cycleDataList = readCycleDataFromDatabase();
    for (CycleData cycleData : cycleDataList) {
        cycleDataTextArea.append("Cycle Length: " + cycleData.cycleLength + " days\n");
        cycleDataTextArea.append("Start Date: " + cycleData.startDate + "\n");
        cycleDataTextArea.append("Current Date: " + cycleData.currentDate + "\n");
        cycleDataTextArea.append("\n");
    }

    JScrollPane scrollPane = new JScrollPane(cycleDataTextArea);
    dataPanel.add(scrollPane);

    dataFrame.add(dataPanel);
    dataFrame.setVisible(true);
}

private static void viewCustomReminders() {
    JFrame remindersFrame = new JFrame("View Custom Reminders");
    remindersFrame.setSize(400, 400);
    remindersFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    JPanel remindersPanel = new JPanel();
    remindersPanel.setLayout(new BoxLayout(remindersPanel, BoxLayout.Y_AXIS));

    JLabel remindersTitleLabel = new JLabel("<html><div style='text-align: center; font-size: 20px; font-weight: bold;'>Custom Reminders</div></html>");
    remindersTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
    remindersPanel.add(remindersTitleLabel);

    // Load your custom reminders from the text file or data structure here
    List<String> customReminders = loadCustomRemindersFromDatabase();


    List<JCheckBox> checkBoxes = new ArrayList<>();

    for (String reminder : customReminders) {
        JCheckBox checkBox = new JCheckBox(reminder);
        checkBox.setFont(new Font("Arial", Font.PLAIN, 14));
        checkBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (checkBox.isSelected()) {
                    Font font = checkBox.getFont();
                    Map<TextAttribute, Object> attributes = new HashMap<TextAttribute, Object>();
                    attributes.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
                    checkBox.setFont(font.deriveFont(attributes));
                } else {
                    Font font = checkBox.getFont();
                    Map<TextAttribute, Object> attributes = new HashMap<TextAttribute, Object>();
                    attributes.put(TextAttribute.STRIKETHROUGH, -1);
                    checkBox.setFont(font.deriveFont(attributes));
                }
            }
        });

        checkBoxes.add(checkBox);
        remindersPanel.add(checkBox);

        remindersPanel.add(Box.createVerticalStrut(5));
    }

    JScrollPane scrollPane = new JScrollPane(remindersPanel);
    remindersFrame.add(scrollPane);
    remindersFrame.setVisible(true);
}

private static List<String> loadCustomRemindersFromDatabase() {
    List<String> reminders = new ArrayList<>();
    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
        String sql = "SELECT reminder_text FROM custom_reminders";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String reminder = resultSet.getString("reminder_text");
                    reminders.add(reminder);
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle database connection or query errors
    }
    return reminders;
}


}