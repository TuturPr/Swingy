package xyz.tuturprdev.swingy.view.utils;

import javax.swing.text.*;

public class NumericFilter extends DocumentFilter {
    private final int choices;

    public NumericFilter(int choice) {
        this.choices = choice;
    }

    private boolean isValid(String text) {
        if (!text.matches("\\d*")) return false;
        if (text.isEmpty()) return false;
        try {
            int value = Integer.parseInt(text);
            return value >= 1 && value <= choices;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (string.matches("\\d*") && (fb.getDocument().getLength() + string.length() <= 1) && isValid(string)) {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (text.matches("\\d*") && (fb.getDocument().getLength() - length + text.length() <= 1) && isValid(text)) {
            super.replace(fb, offset, length, text, attrs);
        }
    }
}
