package xyz.tuturprdev.swingy.view.pages;

import xyz.tuturprdev.swingy.view.utils.NumericFilter;

import javax.swing.*;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class ConsolePage extends JPanel {
    private final JTextArea textArea;
    private final JTextField inputField;

    public ConsolePage() {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#16161d"));

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setMargin(new Insets(5,5,0,5));
        textArea.setForeground(Color.WHITE);
        textArea.setBackground(Color.decode("#16161d"));
        textArea.setFont(new Font("DejaVu Sans Mono", Font.PLAIN, 14));
        add(textArea, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(Color.decode("#16161d"));

        JPanel line = new JPanel();
        line.setBackground(new Color(80, 80, 90));
        line.setPreferredSize(new Dimension(0, 2));
        bottomPanel.add(line, BorderLayout.NORTH);

        inputField = new JTextField();
        inputField.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));
        inputField.setBackground(Color.decode("#16161d"));
        inputField.setForeground(Color.WHITE);
        inputField.setCaretColor(Color.WHITE);
        inputField.setFont(new Font("DejaVu Sans Mono", Font.PLAIN, 11));
        inputField.setEditable(false);
        bottomPanel.add(inputField, BorderLayout.CENTER);

        add(bottomPanel, BorderLayout.SOUTH);

        textArea.setText("\n" +
                "░██████╗░██╗░░░░░░░██╗██╗███╗░░██╗░██████╗░██╗░░░██╗\n" +
                "██╔════╝░██║░░██╗░░██║██║████╗░██║██╔════╝░╚██╗░██╔╝\n" +
                "╚█████╗░░╚██╗████╗██╔╝██║██╔██╗██║██║░░██╗░░╚████╔╝░\n" +
                "░╚═══██╗░░████╔═████║░██║██║╚████║██║░░╚██╗░░╚██╔╝░░\n" +
                "██████╔╝░░╚██╔╝░╚██╔╝░██║██║░╚███║╚██████╔╝░░░██║░░░\n" +
                "╚═════╝░░░░╚═╝░░░╚═╝░░╚═╝╚═╝░░╚══╝░╚═════╝░░░░╚═╝░░░\n");

        PrintStream ps = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                SwingUtilities.invokeLater(() -> textArea.append(String.valueOf((char) b)));
            }
        });
        System.setOut(ps);
        System.setErr(ps);
    }

    public void appendText(String text) {
        textArea.append(text + "\n");
    }

    public String getInput() {
        return inputField.getText();
    }

    public void clearInput() {
        inputField.setText("");
    }

    public void askToChoose(int choicesNum, String[] choicesContent, Runnable[] actions) {
        System.out.println("\nPick a choice :\n");
        int i = 1;
        for (String choice : choicesContent) {
            System.out.println("    " + i + ". " + choice + '\n');
            i++;
        }
        PlainDocument doc = (PlainDocument) inputField.getDocument();
        doc.setDocumentFilter(new NumericFilter(choicesNum));
        inputField.setVisible(true);
        inputField.setEditable(true);
        inputField.addActionListener(e -> {
            if (inputField.getText().isEmpty())
                return;
            int choice = Integer.parseInt(inputField.getText());
            inputField.setText("");
            inputField.setVisible(false);
            actions[choice - 1].run();
        });
    }
}
