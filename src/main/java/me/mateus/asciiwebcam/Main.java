package me.mateus.asciiwebcam;

import me.mateus.asciiwebcam.frame.WebcamViewerFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);

        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new WebcamViewerFrame();
        });
    }
}
