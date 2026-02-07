package xyz.tuturprdev.swingy;

import xyz.tuturprdev.swingy.view.Renderer;
import xyz.tuturprdev.swingy.view.pages.ConsolePage;

import javax.swing.*;
import java.awt.*;

public class Swingy {
    public static void main(String[] args) {
        Renderer renderer = new Renderer();
        renderer.showPage("CONSOLE");

        ConsolePage console = (ConsolePage) renderer.getCards().getComponent(0);
        console.askToChoose(3, new String[]{"New Game", "Resume Game", "Exit"}, new Runnable[]{
                () -> System.out.println("New game selected"),
                () -> System.out.println("Resuming game"),
                () -> System.out.println("Exiting")
        });

        // renderer.showPage("MENU");
    }
}
