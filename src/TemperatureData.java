import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

//Exercise 3B
public class TemperatureData {
    String fileName; //the variable to inisialize the file name
    private List<DataPoint> points = new ArrayList<>(); //a data structure to store the points in london

    //constructor
    TemperatureData(String fileName){
        this.fileName = fileName;
        try{
            readTemperatures(this.fileName); //initialize the file name to the method
        }catch (FileNotFoundException e){
            System.out.println("ERROR!! File was not Found");
        }

    }

    //getter method to return the private data structure
    public List<DataPoint> getPoints(){
        return points;
    }

    //method to read the heatmap.csv file
    public List<DataPoint> readTemperatures(String fileName)
    throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName)); //the scanner
        List<DataPoint> points = getPoints(); //an array list to store the data that are being read from the file

        while(scanner.hasNext()){ //a while loop to iterate through the file
            String input = scanner.nextLine(); //read the next line of the file

            //Split the line into three parts to store the  latitude, longitude and temperature by comma
            String[] parts = input.split(",");
            if( parts.length != 3){
                System.out.println("ERROR!! The file does not have the right data"); //check whether the file has the right information
                continue;
            }

            //add a new DataPoint object to the arraylist
            points.add( new DataPoint(Double.parseDouble(parts[0]),Double.parseDouble(parts[1]),Double.parseDouble(parts[2])) );
        }

        return points;
    }

   //Finding the maximum(index 0) and minimum(index 1) latitude
    public double[] getMaxMinLatitude(){
        List<DataPoint> points = getPoints(); //Getting the list that the data from the file are stored
        double[] maxMinLatitude = new double[2]; //an array of 2 double objects to store the max and min latitude
        double maxLatitude = points.get(0).getLatitude();   //an object double to store the max latitude setting default the first latitude in the list
        double minLatitude = points.get(0).getLatitude(); //an object double to store the min latitude setting default the first latitude in the list

        //iterate through the list to find the max latitude
        ListIterator<DataPoint> iter = points.listIterator();
        while(iter.hasNext()){
            DataPoint elem = iter.next();

            //checking if the current line's latitude is bigger than the current max latitude
            if(elem.getLatitude() > maxLatitude){
                maxLatitude = elem.getLatitude();
            }

            //checking if the current line's latitude is less than the current min latitude
            if(elem.getLatitude() < minLatitude){
                minLatitude = elem.getLatitude();
            }

        }
        maxMinLatitude[0] = maxLatitude;  //store at the 0 index the max latitude
        maxMinLatitude[1] = minLatitude; //store at the 1 index the min latitude

        return maxMinLatitude; //return the array that has the min and max latitude
    }

    //Finding the maximum(index 0) and minimum(index 1) latitude
    public double[] getMaxMinLongitude(){
        List<DataPoint> points = getPoints(); //Getting the list that the data from the file are stored
        double[] maxMinLongitude = new double[2]; //an array of 2 double objects to store the max and min longitude
        double maxLongitude = points.get(0).getLongitude();   //an object double to store the max longitude setting default the first longitude in the list
        double minLongitude = points.get(0).getLongitude(); //an object double to store the min longitude setting default the first longitude in the list

        //iterate through the list to find the max longitude
        ListIterator<DataPoint> iter = points.listIterator();
        while(iter.hasNext()){
            DataPoint elem = iter.next();

            //checking if the current line's latitude is bigger than the current max longitude
            if(elem.getLongitude() > maxLongitude){
                maxLongitude= elem.getLongitude();
            }

            //checking if the current line's latitude is less than the current min longitude
            if(elem.getLatitude() < minLongitude){
                minLongitude = elem.getLongitude();
            }

        }
        maxMinLongitude[0] = maxLongitude;  //store at the 0 index the max longitude
        maxMinLongitude[1] = minLongitude; //store at the 1 index the min longitude

        return maxMinLongitude; //return the array that has the min and max Longitude
    }

    //Finding the maximum(index 0) and minimum(index 1) Temperature
    public double[] getMaxMinTemperature(){
        List<DataPoint> points = getPoints(); //Getting the list that the data from the file are stored
        double[] maxMinTemperature = new double[2]; //an array of 2 double objects to store the max and min temperature
        double maxTemperature = 0.0;   //an object double to store the max Temperature
        double minTemperature = 99999.9999; //an object double to store the min Temperature

        //iterate through the list to find the max Temperature
        ListIterator<DataPoint> iter = points.listIterator();
        while(iter.hasNext()){
            DataPoint elem = iter.next();

            //checking if the current line's latitude is bigger than the current max Temperature
            if(elem.getTemperature() > maxTemperature){
                maxTemperature = elem.getTemperature();
            }

            //checking if the current line's latitude is less than the current min Temperature
            if(elem.getTemperature() < minTemperature){
                minTemperature = elem.getTemperature();
            }

        }
        maxMinTemperature[0] = maxTemperature;  //store at the 0 index the max Temperature
        maxMinTemperature[1] = minTemperature; //store at the 1 index the min Temperature

        return maxMinTemperature; //return the array that has the min and max Temperature
    }

}
