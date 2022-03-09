package me.mateus.asciiwebcam.frame;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;

public class WebcamViewerFrame {

    private final Webcam webcam = Webcam.getDefault();

    public WebcamViewerFrame() {

        webcam.setViewSize(WebcamResolution.VGA.getSize());
        webcam.setImageTransformer(new ASCIITransformer());
        webcam.open();

        JFrame window = new JFrame("ASCII CAMERA");

        WebcamPanel panel = new WebcamPanel(webcam);
        panel.setFPSDisplayed(true);
        panel.setDrawMode(WebcamPanel.DrawMode.FILL);

        JButton button = new JButton(new AbstractAction("SCREENSHOT") {


            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    ImageIO.write(webcam.getImage(), "PNG", new File("capture.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        window.setLayout(new FlowLayout(FlowLayout.CENTER));
        window.add(panel);
        window.add(button);
        window.pack();
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
