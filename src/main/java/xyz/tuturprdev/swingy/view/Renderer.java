package xyz.tuturprdev.swingy.view;

import xyz.tuturprdev.swingy.view.pages.ConsolePage;

import javax.swing.*;
import java.awt.*;

public class Renderer {
    private final JPanel cards;
    private final CardLayout cardLayout;

    public Renderer() {
        JFrame frame = new JFrame("Swingy");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        cards.add(new ConsolePage(), "CONSOLE");

        frame.add(cards);
        frame.setVisible(true);
    }

    public void showPage(String pageName) {
        cardLayout.show(cards, pageName);
    }

    public JPanel getCards() {
        return cards;
    }
}
