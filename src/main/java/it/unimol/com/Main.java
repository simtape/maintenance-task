package it.unimol.com;

import it.unimol.com.gui.PhotoViewer;

import javax.swing.*;

public class Main {
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            PhotoViewer photoViewer = new PhotoViewer();
            photoViewer.setVisible(true);
        });
    }
}

