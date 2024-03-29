package main.gui;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * The window of the client that can be set to different {@code StatePanel}s
 */
public class Window extends JFrame {
    
    private static final long serialVersionUID = 1L;

    private StatePanel currentPanel = null;
    
    /**
     * Displays when the user is not logged in
     */
    public LoginWindow loginWindow;
    
    /**
     * Displays after the user logs in and allows messages to be sent
     */
    public MessagingWindow messagingWindow;
    
    /**
     * The icons for the window
     */
    public static final List<BufferedImage> ICONS = getImages();
    
    /**
     * Constructs the window setting it to the login window
     */
    public Window() {

    		loginWindow = new LoginWindow();
    	
        messagingWindow = new MessagingWindow();
        
        this.setIconImages(ICONS);
        
        this.setResizable(false);
        loginWindow.setVisible(true);
        
        this.setTitle(loginWindow.getName());
        this.setVisible(true);
    }
    
    /**
     * Sets the panel that the window should display
     * 
     * @param panel The panel to display
     */
    public void setStatePanel(StatePanel panel) {
        if(currentPanel == panel) {
            return;
        }
        
        this.setTitle(panel.getTitle());
        this.setSize(panel.getWidth(), panel.getHeight());
        this.getRootPane().setDefaultButton(panel.getSubmitButton());
        
        this.add(panel);
        
        if(currentPanel != null)
            this.remove(currentPanel);
        
        currentPanel = panel;
        
        panel.setVisible(true);
        
        SwingUtilities.updateComponentTreeUI(this);
        
        if(!System.getProperty("os.name").toLowerCase().contains("win")) {
            int width = this.getWidth();
            this.setSize(width + 1, this.getHeight());
            SwingUtilities.updateComponentTreeUI(this);
            this.setSize(width, this.getHeight());
            SwingUtilities.updateComponentTreeUI(this);
        }
    }
    
    private static List<BufferedImage> getImages() {
        List<BufferedImage> icons = new ArrayList<>(4);
        
        try {
            icons.add(ImageIO.read(Window.class.getResourceAsStream("/resources/icon16.png")));
            icons.add(ImageIO.read(Window.class.getResourceAsStream("/resources/icon16.png")));
            icons.add(ImageIO.read(Window.class.getResourceAsStream("/resources/icon16.png")));
            icons.add(ImageIO.read(Window.class.getResourceAsStream("/resources/icon16.png")));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        
        return Collections.unmodifiableList(icons);
    }
}
