//Exercise 3A
public class DataPoint {
    final private double latitude,longitude,temperature; //the three variables that cant be changed for the class user


    //Constructor
    DataPoint(double latitude, double longitude, double temperature){
        this.latitude = latitude;
        this.longitude = longitude;
        this.temperature = temperature;
    }

    //getter methods to retrieve the private variables latitude,longitude,temperature
    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public double getTemperature() {
        return this.temperature;
    }
}
