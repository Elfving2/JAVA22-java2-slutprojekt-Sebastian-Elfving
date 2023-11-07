package view;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
public class GUI extends JFrame {
    int height = 400; // Adjusted height for more space
    int width = 600; // Adjusted width for more space
    public JProgressBar progressBar = new JProgressBar(0, 100);
    public JButton addWorkerButton = new JButton("Add Worker");
    public JButton removeWorkerButton = new JButton("Remove Worker");
    public JTextArea textArea = new JTextArea(15, 30); // Adjusted rows and columns for better visibility
    public JScrollPane scroll = new JScrollPane(textArea());
    public JButton saveButton = new JButton("Save");
    public JButton loadButton = new JButton("Load");

    public GUI() {
        initiateGUI();
        populateGUI();
    }

    private void populateGUI() {
        this.add(populateJp());
    }

    private void initiateGUI() {
        this.setSize(new Dimension(width, height));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public JPanel populateJp() {
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(workerButton());
        topPanel.add(removeButton());
        topPanel.add(progressBar());

        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.add(save());
        bottomPanel.add(load());

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scroll, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        return mainPanel;
    }
    public JProgressBar progressBar() {
        progressBar.setSize(new Dimension(50, 15));
        progressBar.setStringPainted(true);
        return progressBar;
    }
    public JTextArea textArea() {
        textArea.setEditable(false);
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.WHITE);
        textArea.setMargin(new Insets(5,5,5,5));
        textArea.setFont(textArea.getFont().deriveFont(Font.BOLD, 14f));
        textArea.setColumns(30);
        textArea.setRows(15);
        return textArea;
    }
    private JButton save() {
        styleButton(saveButton);
        return saveButton;
    }
    private JButton workerButton() {
        styleButton(addWorkerButton);
        return addWorkerButton;
    }
    private JButton removeButton() {
        styleButton(removeWorkerButton);
        return removeWorkerButton;
    }
    private void styleButton(JButton btn) {
        btn.setForeground(Color.BLACK);
        btn.setBackground(Color.WHITE);
        Border line = new LineBorder(Color.BLACK);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
        btn.setBorder(compound);
    }

    public JButton load() {
        // style later!
        styleButton(loadButton);
        return loadButton;
    }
}

