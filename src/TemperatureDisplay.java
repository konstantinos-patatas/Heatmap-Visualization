import javax.swing.*;
import java.awt.*;
import java.util.List;

//Exercise 3C
public class TemperatureDisplay extends JComponent {
    TemperatureData point; //an object of type temperature data to help as use data from that class

    //all variables are set to final so they cannot be changed
    private final double minLatitude;     //Variable to store the minimum latitude
    private final double maxLatitude;    //Variable to store the maximum latitude
    private final double minLongitude;  //Variable to store the minimum longitude
    private final double maxLongitude; //Variable to store the maximum longitude
    private final double maxTemperature; //Variable to store the maximum temperature
    private final double minTemperature; //Variable to store the maximum temperature

    private int width = 600;    //set the dimension width to use to scale the drawing area when printing the area to the map
    private int height = 600; //set the dimension height to use to scale the drawing area when printing the area to the map
    private double scale = 0.9; //define the scale of the component(also updates when zoom in and out)

    private Boolean useColor; //the Boolean variable to determine whether the map will be grayscale or colored

    //constructor
    TemperatureDisplay(TemperatureData point) {
        this.point = point; //an object of temperatureData class
        this.useColor = true; //setting the map to be colored as default

        //use the methods that return double arrays with (index 0) for max and (index 1) for min in the temperatureData to initialize the variables
        this.minLatitude = point.getMaxMinLatitude()[1];
        this.maxLatitude = point.getMaxMinLatitude()[0];
        this.minLongitude = point.getMaxMinLongitude()[1];
        this.maxLongitude = point.getMaxMinLongitude()[0];
        this.minTemperature = point.getMaxMinTemperature()[1];
        this.maxTemperature = point.getMaxMinTemperature()[0];

        setPreferredSize( new Dimension(width,height) );  //set the dimensions of the frame
    }



    //overriding the paint component to create the display of the temperature data
    @Override
    protected void paintComponent(Graphics graphics) {
        //call the superclass of paint component to ensure that any necessary painting operations defined in the superclass are executed
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics; //typecast the graphics variable to a 2D
        List<DataPoint> points = point.getPoints(); //get the data structure that stores the temperature data

        //flipping the map vertically to match the angle of the sample taken by the satellite
        g.scale(scale, -scale); //use the scale method to flip the graphics context vertically by a factor of -y
        g.translate(0, -getHeight()); //and we use the translate method to flip the component vertically by setting x to 0 and the original height to negative

        for (DataPoint elem : points) { //iterate through the list with the temperature data
            //Process of turning map longitude and latitude coordinates into integer x and y
            //we use Linear interpolation method
            /*explanation:
            1)(elem.getLongitude() - minLongitude) we subtract the current longitude or latitude from its minimum value to find the distance in the dataset
            2)(maxLongitude - minLongitude) then by dividing the result to the subtraction of the max and min we normalize the coordinates to the range [0,1]
            3) and we scale the x to the range of the frame by multiplying the normalization with the width or height of the frame
            4) then we typecast into an integer to use to set the postition of the rectangles */
            int x = (int) ((elem.getLongitude() - minLongitude) / (maxLongitude - minLongitude) * width);
            int y = (int) ((elem.getLatitude() - minLatitude) / (maxLatitude - minLatitude) * height);

            if(useColor){ //if useColor variable is true then the map will be colored
                //using the method setColor to color the map
                g.setPaint(setColor(elem.getTemperature(), minTemperature, maxTemperature));
            }else{ //if its not true then the map will have a grayscale representation
                g.setPaint(greyScale(elem.getTemperature(), minTemperature, maxTemperature));
            }

            g.fillOval(x , y , 5, 4); //create the map with ovals of width 5 pixels and height of 4 pixels
        }

    }


    //a method to determine the color of the area based on kelvin blue to cyan for cold/ yellow to green for medium/ yellow to red for hot
    private Color setColor(double temperature, double minTemperature, double maxTemperature) {
        double range = maxTemperature - minTemperature; // define the range by subtracting the max temperature from the minimum
        double third = range / 3;   // find the 1/3 of the range to help in if statements and splitting the colorways into three parts


        if (temperature <= minTemperature + third) { //check if temperature is in the 1/3 of the temperature range (cooler temperatures)
            //subtract to find the distance form current temperature to the minimum in the dataset
            // divide from 1/3 to get to normalize the current temperature to the range[0,1] that are the first third range of temperatures
            //and multiply to get to [0,255] range of color that we use to set a specific color value
            int blue = (int) (255 * ((temperature - minTemperature) / third));
            return  new Color(0, 0, blue); //get the [0,255] range defined above to find a nice gradient from  0(blue) to 255(cyan)  for cold temps
        }
        else if (temperature <= minTemperature + 2 * third) { //check if temperature is in the 2/3 of the temperature range (medium temperatures)
            //multiplication by 255 and division by third does the same think as above,
            // but we also subtract the third to fall in the second part of the range of temperatures(medium temps)
            int green = (int) (255 * ((temperature - minTemperature - third) / third));
            //subtracting from 255 the green component will give a smooth transition from green to yellow as the temperature increases
            int blue = (int) (255 - green);
            return new Color(0, green, blue); //create the color with the green and blue component giving the smooth transition from cyan to yellow/green color
        }
        else { //else falls in the third part of the range fot temperatures
            //again we do the same as above but we subtract 2/3 to fall in the last third range of the temperatures
            int red = (int) (255 * ((temperature - minTemperature - 2 * third) / third));
            //having red color full on 255 and giving the [0,255] to the green component to have smooth transition form yellow to red
            return new Color(255, red, 0);
        }
    }

    //method to set the grayscale representation of the map
    private Color greyScale(double temperature, double minTemperature, double maxTemperature) {
        double range = maxTemperature - minTemperature; // define the range by subtracting the max temperature from the minimum
        double third = range / 3;   // find the 1/3 of the range to help in if statements and splitting the colorways into three parts

        if (temperature <= minTemperature + third) { //check if temperature is in the 1/3 of the temperature range (cooler temperatures)
            //subtract to find the distance form current temperature to the minimum,divide from 1/3 to get to normalize the range[0,1] and multiply to get to [0,255] range of color
            int gray = (int) (255 * ((temperature - minTemperature) / third)); //get to the 1/3 temperature range and assign a color from [0,255] value for this range
            return new Color(gray, gray, gray); //in all 3 values we give the 1/3 int value  to create a gray to white color gradient
        }
        else if (temperature <= minTemperature + 2 * third) { //check if temperature is in the 2/3 of the temperature range (medium temperatures)
            //multiplication by 255 and division by third does the same think as above but we also subtract the third to fall in the second part of the range of temperatures
            int gray = (int) (255 * ((temperature - minTemperature - third) / third)); //get to the 2/3 temperature range and normalize a value[0,255] for this range
            return new Color(gray, gray, gray); //give a more gray dominant color for the medium temperature range
        }
        else {//else falls in the third part of the range fot temperatures
            //again we do the same as above but we subtract 2/3 to fall in the last third range of the temperatures
            int gray = (int) (255 * ((temperature - minTemperature - 2 * third) / third)); //assign to the last third temperature range a color value[0,255]
            return new Color(gray, gray, gray); //giving a gray to black transition
        }
    }

    //a method to help as setting the useColor variable either true or false to determine the color of the map and when called it repaints the comp
    public void setUseColor(Boolean useColor){
        this.useColor = useColor;
        repaint(); //a swing library method that calls the paintcomponent to repaint the component
    }

    //getter method to return the private width of the component
    public int componentWidth(){
        return this.width;
    }

    //getter method to return the private height of the component
    public int componentHeight(){
        return this.height;
    }

    //a method to use when the zoom button is pressed in the frame
    public void zoomIn() {
        scale *= 1.1; // Increase scale by 10%
        repaint(); // Repaint component
    }

    //a method to use when the zoom out button is pressed in the frame
    public void zoomOut() {
        scale /= 1.1; // Decrease scale by 10%
        repaint(); // Repaint component
    }

}








