package com.api.utilities;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import com.github.javafaker.Number;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FakerApiIntegration {

    static Faker faker = new Faker (  );

    public static String getFirstName(){
        Name firstName  = faker.name ();
        return String.valueOf (firstName);
    }
    public static String getLastName(){
        Name lastName  = faker.name ();
        return  String.valueOf (lastName);
    }

//    public static int getPrice() {
//        Number price = faker.number().numberBetween(1, 1000); // Assuming you want a random price between 1 and 1000 (change the range as needed)
//        return price.intValue(); // Cast the Number to an int
//    }

    public String getCheckinDate(){

        // Get the current date
        Date currentDate = new Date();

        // Generate a random birthday
        Date randomBirthday = faker.date().birthday();

        // Modify the random birthday to include the current year
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(randomBirthday);
        calendar.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));

        // Format the dates to display only the date part (excluding time)
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedCurrentDate = dateFormat.format(currentDate);
        System.out.println("Current Date: " + formattedCurrentDate);
        return formattedCurrentDate;
        }

    public String getCheckOutDate(){

        // Get the current date
        Date currentDate = new Date();

        // Generate a cur
        Date randomBirthday = faker.date().birthday();

        // Modify the random birthday to include the current year
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(randomBirthday);
        calendar.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));

        // Format the dates to display only the date part (excluding time)
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedCurrentDate = dateFormat.format(currentDate);
        System.out.println("Current Date: " + formattedCurrentDate);
        return formattedCurrentDate;
    }

    public static String getNeeds(){
        String dish  = faker.food ().dish ();
        return String.valueOf (dish);
    }

}






