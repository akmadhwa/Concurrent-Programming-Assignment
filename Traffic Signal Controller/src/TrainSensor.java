
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Siti Sholiha
 */
public class TrainSensor {
    
    private long initial;
    private long stamp;
    private String fileName;
    Controller ctrl;
    private boolean trainArriving;
    
    public TrainSensor(){
        
    }
    public TrainSensor(long timer, String fileName, Controller ctrl) {
        this.initial = timer;
        this.fileName = fileName;
        this.ctrl = ctrl;
    }
    
    synchronized public void run() {
        String input;
        Random rand = new Random();
        int sleepTime = rand.nextInt(10000) + 20000;

        try {
            Thread.sleep(100); // Give time to write input file
            BufferedReader rd = new BufferedReader(new FileReader(fileName));

            while ((input = rd.readLine()) != null) {
                Thread.sleep(sleepTime);
                stamp = (System.currentTimeMillis() - initial) / 100 * 100;
                System.out.println(stamp + " S " + input);
                if(input == "TA" ){
                    trainArriving = true;
                    wait();
                }
                else{
                    trainArriving = false;
                }
            }

        } catch (IOException | InterruptedException e) {
        }
    }
}