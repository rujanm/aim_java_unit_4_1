package com.mysql.project;

import java.sql.*;
import java.util.Scanner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import org.apache.ibatis.jdbc.ScriptRunner;

public class App 
{

    
    public static void main( String[] args ) throws FileNotFoundException
    {
        Scanner obj = new Scanner(System.in);
        
        
        try 
            
          {

            //Registering the Driver
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            //Getting the connection
            String mysqlUrl = "jdbc:mysql://localhost:3306/";
            Connection con = DriverManager.getConnection(mysqlUrl, "root", "xxxxx");
            System.out.println("Connection established......");
            //Creating the Statement
            Statement stmt = con.createStatement();
            //Query to create a database
            
            
            ScriptRunner sr = new ScriptRunner(con);
            //Creating a reader object
            Reader reader = new BufferedReader(new FileReader("/Users/rujan/aim_java_unit_4_1/src/main/java/com/mysql/project/query.sql"));
            //Running the script
            sr.runScript(reader);


            System.out.println("Welcome to movie DB \n");
            System.out.print("Please enter a movie name: ");
            String movie = obj.nextLine();
            String movieSearch = "SELECT title " +
                            "FROM movies " +
                         "WHERE title Like \"" + movie + "\";" ;
            System.out.println(movieSearch);
            ResultSet rs = stmt.executeQuery(movieSearch); 
            while(rs.next()){
                System.out.println("The movie already exists in DB");
                System.out.println("Please enter a different movie:");
                movie = obj.nextLine();
                movieSearch = "SELECT title " +
                            "FROM movies " +
                         "WHERE title Like \"" + movie + "\";" ;
                rs = stmt.executeQuery(movieSearch);
            }
            System.out.println("a) G");
            System.out.println("b) PG");
            System.out.println("c) PG-13");
            System.out.println("d) R");
            System.out.println("Please enter the rating of the movie from the above (example: c):");
            String rating = obj.nextLine();
            switch(rating){
                case "a":
                rating = "1";
                break;
                case "b":
                rating = "2";
                break;
                case "c":
                rating = "3";
                break;
                case "d":
                rating = "4";
                break;
                
            }
            System.out.println("Please enter the director's name: ");
            String directorName = obj.nextLine();
            System.out.println("Enter 3 actors who were in the movie: ");
            String actor1 = obj.nextLine();
            String actor2 = obj.nextLine();
            String actor3 = obj.nextLine();

            String addMovie = "INSERT INTO movie_library.movies (title, primary_director, actors, rating_id)" +
            " VALUES (" + "'" + movie +"'"  + ", " +  "'" +directorName + "'"  + ", " + 
            "'" + actor1 + ","+ actor2+ "," + actor3 + "'" + ", " 
            + rating + ")";
            

            
            System.out.println(stmt.executeUpdate(addMovie));

            System.out.println(addMovie);
    
         } catch(SQLException ex) {
            ex.printStackTrace();
         }  // Step 5: Close conn and stmt - Done automatically by try-with-resources (JDK 7)
      


    }
}
