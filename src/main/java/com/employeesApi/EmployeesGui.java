package main.java.com.employeesApi;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import main.java.com.employeesApi.entities.Project;
import main.java.com.employeesApi.models.EmployeePair;
import main.java.com.employeesApi.models.WorkedTime;
import main.java.com.employeesApi.services.EmployeeService;
import main.java.com.employeesApi.services.EmployeeServiceImpl;

public class EmployeesGui {

    private JFrame frame;
    private JButton findFileButton;
    private JPanel mainPanel;
    private JPanel subPanel;
    private JTable employeeTable;
    private EmployeeService service;
    private DefaultTableModel model;

    public EmployeesGui() {
        service = new EmployeeServiceImpl();
        frame = new JFrame();

        findFileButton = new JButton();
        findFileButton.setText("Choose file");
        findFileButton.setPreferredSize(new Dimension(40, 40));

        String[] columns = new String[]{"EmployeeID #1", "EmloyeeID#2", "ProjectID", "DaysWorked"};
        model = new DefaultTableModel(null, columns);

        employeeTable = new JTable();
        employeeTable.setModel(model);
        employeeTable.setPreferredScrollableViewportSize(new Dimension(500, 200));
        employeeTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(employeeTable);

        subPanel = new JPanel();
        subPanel.add(scrollPane);

        mainPanel = new JPanel();
        mainPanel.add(findFileButton);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(subPanel);

        frame.add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        frame.setSize(500, 310);

        findFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(null);
                if (chooser.getSelectedFile() != null) {
                    File f = chooser.getSelectedFile();

                    if (employeeTable.getRowCount() > 0) {
                        for (int i = employeeTable.getRowCount() - 1; i > -1; i--) {
                            model.removeRow(i);
                        }
                    }

                    Map.Entry<EmployeePair, WorkedTime> result = service.getEmployeesFromFile(f);

                    Long firstEmployeeId = result.getKey().getFirst().getId();
                    Long secondEmployeeId = result.getKey().getSecond().getId();

                    for (Map.Entry<Project, Long> entry : result.getValue().getTimeForProject().entrySet()) {
                        Long days = TimeUnit.MILLISECONDS.toDays(entry.getValue());
                        model.addRow(new Object[]{firstEmployeeId.toString(), secondEmployeeId.toString(), entry.getKey().getId().toString(), days.toString()});
                    }

                }
            }
        });
    }

    public static void main(String[] args) {
        new EmployeesGui();
    }
}
