import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

//Exercise 3D
public class TemperatureApp {
    public static void main(String[] args) {
        //creating a temperatureData object and defining the filename and path
        TemperatureData point = new TemperatureData("src/heatmap.csv");
        //adding to the temperatureDisplay object the temperatureData object
        TemperatureDisplay display = new TemperatureDisplay(point);

        //creating the frame to display the map
        JFrame f = new JFrame("London Heatmap");

        // Create 2 buttons to control whether the map is colored or grayscale
        JButton coloredButton = new JButton("Colored map");
        JButton grayscaleButton = new JButton("Grayscale map");
        // Create a panel to hold the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        // Add buttons to the panel
        buttonPanel.add(coloredButton);
        buttonPanel.add(grayscaleButton);

        JLabel labelOfColor = new JLabel("Choose option:"); //create a label to let the user know what to do

        //add a new panel to hold the buttons and the label
        JPanel colorMenu = new JPanel(new BorderLayout());
        colorMenu.add(labelOfColor,BorderLayout.NORTH); //adding the label above of the buttons
        colorMenu.add(buttonPanel,BorderLayout.CENTER); //adding the buttons below

        // Add the panel with the menu to the frame
        f.add(colorMenu,BorderLayout.WEST);

        //adding an action listener to the colored button to make a colored map
        coloredButton.addActionListener(new ActionListener() { //adding an actionListener object of the interface class
            @Override
            public void actionPerformed(ActionEvent e) { //a method action performed to control the action when the button is pressed
                display.setUseColor(true); //setting the variable true so that the method will repaint the component colorful
            }
        });

        //adding an action listener to the grayscale button to make a grayscale map
        grayscaleButton.addActionListener(new ActionListener() { //adding an actionListener object of the interface class
            @Override
            public void actionPerformed(ActionEvent e) { //a method action performed to control the action when the button is pressed
                display.setUseColor(false); //setting the variable true so that the method will repaint the component colorful
            }
        });

        //creating 2 zoom buttons
        JButton zoom = new JButton("( + )");
        JButton sZoom = new JButton("( - )");
        JPanel zoomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); //create a panel to hold the buttons with a flow layout to keep them next to each other
        //add the buttons the the panel
        zoomPanel.add(zoom);
        zoomPanel.add(sZoom);

        JLabel zoomLabel = new JLabel("Zoom options:"); //create a label to let the user know what the buttons are

        //add the label and the buttons to a panel
        JPanel zoomMenu = new JPanel(new BorderLayout());
        zoomMenu.add(zoomLabel,BorderLayout.NORTH);
        zoomMenu.add(zoomPanel,BorderLayout.CENTER);
        f.add(zoomMenu,BorderLayout.EAST); //adding the panel that holds the label and buttons to the frame

        zoom.addActionListener(new ActionListener() { //adding anonymous class of ActionListener implementation on the zoom button
            //actionPerformed method that when button is pressed the method zoomIn of temperatureDisplay triggers and zooms the window
            @Override
            public void actionPerformed(ActionEvent e) {
                display.zoomIn();
            }
        });

        sZoom.addActionListener(new ActionListener() { //adding anonymous class of ActionListener implementation on the zoom out button
            //actionPerformed method that when button is pressed the method zoomOut of temperatureDisplay triggers and the window zooms out
            @Override
            public void actionPerformed(ActionEvent e) {
                display.zoomOut();
            }
        });


        //creating a scrollpane to hold the panel with the display
        JScrollPane scrollPane = new JScrollPane(display);
        scrollPane.setPreferredSize(new Dimension(display.componentWidth(), display.componentHeight())); //setting the dimensions of the pane
        scrollPane.setWheelScrollingEnabled(true); //giving the control of scrolling with the wheel of the mouse as well
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); //adding a scrollbar to the side and always be visible
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); //adding a scrollbar below and always be visible

        //with this method we ensure that the scroll pane with the map inside has a fixed size of components dimensions even when the map or the frame change sizes
        //add to the frame a component listener
        f.addComponentListener(new ComponentAdapter() { //anonymous instance of component adapter that has the componentResized method
            //when the component is resized the method is being called and set the size again to components dimensions
            @Override
            public void componentResized(ComponentEvent e) {
                scrollPane.setSize(display.componentWidth(), display.componentHeight());// Set the size of the scroll pane to the preferred size
            }
        });

        f.add(scrollPane,BorderLayout.CENTER); //add the scroll pane that contains the map inside the frame
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack(); //pack the preferred size dimensions set for the map
        f.setVisible(true);
    }
}
