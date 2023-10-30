import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class StudentManagementGUI extends JFrame {
    private StudentManagementSystem system = new StudentManagementSystem();
    private JTextField nameField, rollNumberField, gradeField;
    private JTextArea outputArea;

    public StudentManagementGUI() {
        setTitle("Student Management System");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        JLabel rollNumberLabel = new JLabel("Roll Number:");
        rollNumberField = new JTextField();
        JLabel gradeLabel = new JLabel("Grade:");
        gradeField = new JTextField();
        JButton addButton = new JButton("Add Student");


        JButton updateButton = new JButton("Update Student");
        JButton deleteButton = new JButton("Delete Student");


        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                int rollNumber = Integer.parseInt(rollNumberField.getText());
                String grade = gradeField.getText();

                if (!name.isEmpty() && !grade.isEmpty()) {
                    Student student = new Student(name, rollNumber, grade);
                    system.addStudent(student);
                    nameField.setText("");
                    rollNumberField.setText("");
                    gradeField.setText("");
                    refreshOutputArea();
                } else {
                    JOptionPane.showMessageDialog(null, "Name and grade are required fields.");
                }
            }
        });



        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int rollNumber = Integer.parseInt(rollNumberField.getText());
                Student updatedStudent = new Student(nameField.getText(), rollNumber, gradeField.getText());
                system.updateStudent(rollNumber, updatedStudent);
                refreshOutputArea();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int rollNumber = Integer.parseInt(rollNumberField.getText());
                system.removeStudent(rollNumber);
                refreshOutputArea();
            }
        });




        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(rollNumberLabel);
        inputPanel.add(rollNumberField);
        inputPanel.add(gradeLabel);
        inputPanel.add(gradeField);
        inputPanel.add(addButton);

        inputPanel.add(updateButton);
        inputPanel.add(deleteButton);

        outputArea = new JTextArea();
        outputArea.setEditable(false);

        add(inputPanel, BorderLayout.NORTH);
        add(outputArea, BorderLayout.CENTER);

        refreshOutputArea();
    }

    private void refreshOutputArea() {
        outputArea.setText("");
        List<Student> students = system.getAllStudents();
        for (Student student : students) {
            outputArea.append(student.toString() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StudentManagementGUI().setVisible(true);
            }
        });
    }
}
