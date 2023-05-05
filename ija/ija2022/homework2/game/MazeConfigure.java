package ija.ija2022.homework2.game;

import ija.ija2022.homework2.tool.MazePresenter;
import ija.ija2022.homework2.tool.common.CommonMaze;
import ija.ija2022.homework2.tool.common.MazePlan;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Pattern;

import static ija.ija2022.homework2.PacmanGame.sleep;

public class MazeConfigure extends Object{
    private String[] lines;
    private int rows;
    private int cols;
    private int numOfLines;
    private boolean errors;
    public CommonMaze maze;

    public MazeConfigure(){
        this.numOfLines = 0;
        this.rows = 0;
        this.cols = 0;
        this.errors = false;
    }
    //Load maze from file
    public void loadMaze(String str){
        try {
            Scanner myReader = new Scanner(new File(str));
            int count = 0;
            while (myReader.hasNextLine()) {
                if (count == 0) {
                    String[] param = myReader.nextLine().split(" ");
                    this.startReading(Integer.parseInt(param[0]), Integer.parseInt(param[1]));
                } else {
                    this.processLine(myReader.nextLine());
                }
                count++;
            }
            this.stopReading();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void startReading(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        if (rows <= 0 || cols <= 0){
            this.errors = true;
        }
        this.lines = new String[rows]; 
    }
    public void clean(){
        this.numOfLines = 0;
        this.lines = null;
        this.rows = 0;
        this.cols = 0;
        this.errors = false;
    }
    public boolean processLine(String line) {
        if (line.isEmpty() || line.length() != this.cols || !(Pattern.matches("^[XSGPKT.]+$", line)) || this.numOfLines >= this.rows ){
            this.errors = true;
            return false;
        }else{
            this.lines[this.numOfLines] = line;
            this.numOfLines += 1;
            return true;
        }
    }


    //Load state of maze like walls, points, keys, targets and pacman from saved file, also load state of pacman
    public void loadSave(String str) {
        final MazePresenter presenter = new MazePresenter(null, null, null, 0);
    
        
        Thread thread1 = new Thread(() -> {
        try (Scanner myReader = new Scanner(new File(str))) {
                int count_line = 0;
                boolean firstState = false;
                String data = myReader.nextLine();

                
                String[] parm = data.split(" ");
                int row = Integer.parseInt(parm[0]);
                int col = Integer.parseInt(parm[1]);
                this.startReading(row, col);

                CommonMaze maze = null;


                while (myReader.hasNextLine()) {
                    data = myReader.nextLine();
    
                    // System.out.println(data);
                    if (Pattern.compile("^[0-9]{1,9} state$").matcher(data).matches()) {
                        if (data.equals("0 state")) {
                            firstState = true;
                            continue;
                        } else if (firstState) {
                            this.stopReading();
                            firstState = false;
                            count_line = 0;
                            maze = this.createMaze();
    
                        }
                        this.clean();
                        this.startReading(row, col);
                    } else {
                        if (!Pattern.compile("^[0-9]{1,9} [0-9]{1,9} (true|false)$").matcher(data).matches()) {
                            this.processLine(data);
                            count_line++;
                            if (count_line == row && !firstState) {
                                count_line = 0;
                                this.stopReading();
                                maze = this.createMaze();
                                // presenter.update(maze);
                            }
                        } else {
                            String[] param = data.split(" ");
                            int health = Integer.parseInt(param[0]);
                            int score = Integer.parseInt(param[1]);
                            String key = param[2];
                            PacmanObject.load(health, score, key);
                        }
                    }
                    
                    Thread.sleep(40);
                }
            } catch (FileNotFoundException | InterruptedException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            });
            Thread thread2 = new Thread(()->{
                try{
                    while (true) {
                        Thread.sleep(40);
                        presenter.updateMaze(maze);
                        presenter.initializeInterfaceSaves(PacmanObject.staticGetLives(), PacmanObject.staticGetPoints(), false);//
                    }
            }catch( InterruptedException e){
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            });
            thread1.start(); // start the thread
            thread2.start(); // start the thread
    }
    
    // private int currentStateIndex = 0;
    // private MazePresenter presenter = new MazePresenter(this, null, null, 0);

public void loadSaveOneByOne(String str) {
     final MazePresenter presenter = new MazePresenter(null, null, null, 2);
    
        
        Thread thread1 = new Thread(() -> {
        try (Scanner myReader = new Scanner(new File(str))) {
                int count_line = 0;
                boolean firstState = false;

                String data = myReader.nextLine();

                
                String[] parm = data.split(" ");
                int row = Integer.parseInt(parm[0]);
                int col = Integer.parseInt(parm[1]);

                boolean canMove_next = false;
                CommonMaze maze = null;
    
                boolean enter = false;

                while(myReader.hasNextLine()) {

                    data = myReader.nextLine();
                    if (data.equals("0 state")){
                        this.startReading(row, col);
                    } else if(!Pattern.compile("^[0-9]{1,9} [0-9]{1,9} (true|false)$").matcher(data).matches()) {

                        this.processLine(data);
                    }else {
                        String[] param = data.split(" ");
                        int health = Integer.parseInt(param[0]);
                        int score = Integer.parseInt(param[1]);
                        String key = param[2];
                        PacmanObject.load(health, score, key);

                        this.stopReading();
                        maze = this.createMaze();
                        //System.out.println(maze);
                        break;
                    }

                }
                boolean mustMove = false;
                while (myReader.hasNextLine()) {



                    if ( presenter.getKey() != '~' || mustMove) {
                        data = myReader.nextLine();


                    // System.out.println(data);
                    if (Pattern.compile("^[0-9]{1,9} state$").matcher(data).matches() ) {
                        this.clean();
                        this.startReading(row, col);
                        mustMove = true;

                    } else {
                        if (!Pattern.compile("^[0-9]{1,9} [0-9]{1,9} (true|false)$").matcher(data).matches()) {
                            this.processLine(data);
                            count_line++;
                        } else {
                            mustMove = false;
                            String[] param = data.split(" ");
                            int health = Integer.parseInt(param[0]);
                            int score = Integer.parseInt(param[1]);
                            String keys = param[2];
                            PacmanObject.load(health, score, keys);
                            count_line = 0;
                            this.stopReading();
                            maze = this.createMaze();
                        }
                    }
                    
                    Thread.sleep(40);

                }
                }
            } catch (FileNotFoundException | InterruptedException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            });
            Thread thread2 = new Thread(()->{
                try{
                    while (true) {
                        Thread.sleep(40);
                        presenter.updateMaze(maze);
                        presenter.initializeInterfaceSaves(PacmanObject.staticGetLives(), PacmanObject.staticGetPoints(), false);//
                    }
            }catch( InterruptedException e){
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            });
            thread1.start(); // start the thread
            thread2.start(); // start the thread





}


//     public void loadSaveOneByOne(String str) {
        
    
    
//     try (Scanner myReader = new Scanner(new File(str))) {
        
//         int count_line = 0;
//         boolean firstState = false;
//         String data= myReader.nextLine();

//         String[] parm = data.split(" ");
//         int row = Integer.parseInt(parm[0]);
//         int col = Integer.parseInt(parm[1]);
        
//         if (currentStateIndex !=0){
//             while (myReader.hasNextLine()) {
//             data = myReader.nextLine();

//             if (Pattern.compile("^"+currentStateIndex+" state$").matcher(data).matches()) {
//                 break;
//             }
//         }
//         }else{
//             data = myReader.nextLine();
//             this.startReading(row, col);
//             CommonMaze maze = null;
//         }
        

//         System.out.println(currentStateIndex);

        
        
        
//         boolean FlaggState = false;
//         while (myReader.hasNextLine()) {

//             if (currentStateIndex != 0 || presenter.getKey() != '~'){

            
//             if (FlaggState){
//                 data = myReader.nextLine();
//             }
//             System.out.println(data);
            
//             //System.out.println(currentStateIndex);
//             if (Pattern.compile("^[0-9]{1,9} state$").matcher(data).matches()) {
//                 FlaggState =true;
//                 System.out.println(currentStateIndex);
//                 if (data.equals("0 state")) {
//                     System.out.println("ZALUPAOCHKOGOVNO#2");
//                     firstState = true;
//                     currentStateIndex++;
//                     continue;
//                 } else if (firstState) {
//                     System.out.println("ZALUPAOCHKOGOVNO#3");
//                     this.stopReading();
//                     firstState = false;
//                     count_line = 0;
//                     maze = this.createMaze();
//                     currentStateIndex++;
//                 }
//                 this.clean();
//                 this.startReading(row, col);
//                 //System.out.println("ZALUPAOCHKOGOVNO");
                
//             } else {
//                  System.out.println("ZALUPAOCHKO6");
//                 if (!Pattern.compile("^[0-9]{1,9} [0-9]{1,9} (true|false)$").matcher(data).matches()) {
//                     this.processLine(data);
//                     count_line++;
//                     if (count_line == row && !firstState) {
//                         System.out.println("ZALUPAOCHKO62");
//                         count_line = 0;
//                         System.out.println(this.stopReading());
//                         maze = this.createMaze();
//                         System.out.println(maze);
//                         currentStateIndex++;
//                         // presenter.update(maze);
//                     }
//                 } else {
//                     String[] param = data.split(" ");
//                     int health = Integer.parseInt(param[0]);
//                     int score = Integer.parseInt(param[1]);
//                     String key = param[2];
//                     PacmanObject.load(health, score, key);
//                 }
//             }
//             }
//         }
//         this.presenter.updateMaze(maze);
//         this.presenter.initializeInterfaceSaves(PacmanObject.staticGetLives(), PacmanObject.staticGetPoints(), true);
//     } catch (FileNotFoundException e) {
//         System.out.println("An error occurred.");
//         e.printStackTrace();
//     }
// }

    public void loadReverseSave(String str){
        final MazePresenter presenter = new MazePresenter(null, null, null, 0);
        Thread thread1 = new Thread(()->{
            Path path = Paths.get(str);
            CommonMaze maze;
            
    
            long lines;
            String line_cur = "";
            try {
                String[] param = Files.readAllLines(path).get(0).split(" ");
                int row = Integer.parseInt(param[0]);
                int col = Integer.parseInt(param[1]);
    
                lines = Files.lines(path).count();
                int max = (int)lines-(row+2);
                lines = (int)lines-(row+2);
    
                while (!line_cur.equals("0 state")){
                    int count = 0;
                    this.startReading(row, col);
    
                    while ( count != row){
                        try {
                            String line_curs = Files.readAllLines(path).get((int)lines+count+1);
                            //System.out.println(line_curs + " " + count+(int)count+1);
                            this.processLine(line_curs);
                            count++;
                        }catch (IndexOutOfBoundsException e){
                            System.out.println("Index out of bounds");
                            System.exit(0);
                            
                        }
                        Thread.sleep(40);
                    }
                

                    String[] param1 = Files.readAllLines(path).get((int)lines+count+1).split(" ");
                    int health = Integer.parseInt(param1[0]);
                    int score = Integer.parseInt(param1[1]);
                    String key = param1[2];
                    PacmanObject.load(health, score, key);
                    this.stopReading();
                    if (lines == max){

                        maze = this.createMaze();

                    }else {

                        maze = this.createMaze();

                    }
                    this.clean();
                    line_cur = Files.readAllLines(path).get((int)lines);
                    lines = lines-(row+2);
                    Thread.sleep(75);
                }
        }catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
        });
       

        Thread thread2 = new Thread(()->{
            try{
                while (true) {
                    Thread.sleep(75);
                    // System.out.println(maze+"llllll");
                    presenter.updateMaze(maze);
                    presenter.initializeInterfaceSaves(PacmanObject.staticGetLives(), PacmanObject.staticGetPoints(), true);//

                }
        }catch( InterruptedException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        });
        thread1.start();
        thread2.start();
    }

    public boolean stopReading() {
        if (this.numOfLines == this.rows){

            return true;
        }else{
            this.errors = true;
            return false;
        } 
    }
    
    public CommonMaze createMaze() {
        if (this.errors){
            return null;
        };
        int rws = this.rows + 2;
        int cls = this.cols + 2;
        if (this.rows == 0 || this.cols == 0){

            return null;
        }
        this.maze = new MazePlan(rws, cls, this.lines);
        return this.maze;
    }

}

