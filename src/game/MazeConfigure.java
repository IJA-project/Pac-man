/**
 * Project name: Pac-man
 * File name: MazeConfigure.java
 * Date: 06.05.2023
 * Last update: 06.05.2023
 * Author: Andrei Kulinkovich(xkulin01)
 * Description: MazeConfigure class represents maze configuration from file, also it can load maze from file and interpret it.
 */

package game;

import tool.MazePresenter;
import tool.common.CommonMaze;
import tool.common.MazePlan;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * MazeConfigure class represents maze configuration from file, also it can load
 * maze from file and interpret it.
 */
public class MazeConfigure extends Object {
    private String[] lines;
    private int rows;
    private int cols;
    private int numOfLines;
    private boolean errors;

    public CommonMaze maze;

    /**
     * Create maze from configuration
     */
    public MazeConfigure() {
        this.numOfLines = 0;
        this.rows = 0;
        this.cols = 0;
        this.errors = false;
    }

    /**
     * Create maze from file
     * 
     * @param str path to file with maze configuration
     */
    public void loadMaze(String str) {
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

    /**
     * Start reading maps parameters
     * 
     * @param rows number of rows in maze
     * @param cols number of columns in maze
     */
    public void startReading(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        if (rows <= 0 || cols <= 0) {
            this.errors = true;
        }
        this.lines = new String[rows];
    }

    /**
     * Clean maze configuration to load new one configuration, use for logging
     */
    public void clean() {
        this.numOfLines = 0;
        this.lines = null;
        this.rows = 0;
        this.cols = 0;
        this.errors = false;
    }

    /**
     * Processing of reading maze configuration from file
     * 
     * @param line line of maze plan
     * @return true if processing was successful, false otherwise
     */
    public boolean processLine(String line) {
        if (line.isEmpty() || line.length() != this.cols || !(Pattern.matches("^[XSGPKT.]+$", line))
                || this.numOfLines >= this.rows) {
            this.errors = true;
            return false;
        } else {
            this.lines[this.numOfLines] = line;
            this.numOfLines += 1;
            return true;
        }
    }

    /**
     * Load logging record from file, and interpret it.
     * 
     * @param str path to file with logging record
     */
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
                            }
                        } else {
                            String[] param = data.split(" ");
                            int health = Integer.parseInt(param[0]);
                            int score = Integer.parseInt(param[1]);
                            String key = param[2];
                            PacmanObject.load(health, score, key);
                            if (presenter.getKey() == '!') {
                                break;
                            }
                            presenter.updateMaze(maze);
                            presenter.initializeInterfaceSaves(health, score, false);
                        }
                    }

                    Thread.sleep(40);
                    if (presenter.getKey() == '!') {
                        break;
                    }
                }
                presenter.addEndLoadLable();
            } catch (FileNotFoundException | InterruptedException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        });

        thread1.start(); // start the thread
    }

    /**
     * Load logging record from file, and interpret it. The interpretation of next
     * state is after pressing button.
     * 
     * @param str path to file with logging record
     */
    public void loadSaveStepByStep(String str) {
        final MazePresenter presenter = new MazePresenter(null, null, null, 2);

        Thread thread1 = new Thread(() -> {
            try (Scanner myReader = new Scanner(new File(str))) {

                String data = myReader.nextLine();

                String[] parm = data.split(" ");
                int row = Integer.parseInt(parm[0]);
                int col = Integer.parseInt(parm[1]);

                CommonMaze maze = null;
                while (myReader.hasNextLine()) {
                    data = myReader.nextLine();
                    if (data.equals("0 state")) {
                        this.startReading(row, col);
                    } else if (!Pattern.compile("^[0-9]{1,9} [0-9]{1,9} (true|false)$").matcher(data).matches()) {

                        this.processLine(data);
                    } else {
                        String[] param = data.split(" ");
                        int health = Integer.parseInt(param[0]);
                        int score = Integer.parseInt(param[1]);
                        String key = param[2];
                        PacmanObject.load(health, score, key);
                        this.stopReading();
                        maze = this.createMaze();
                        break;
                    }
                }
                boolean mustMove = false;
                while (myReader.hasNextLine()) {
                    if (presenter.getKey() != '~' || mustMove) {
                        data = myReader.nextLine();

                        if (Pattern.compile("^[0-9]{1,9} state$").matcher(data).matches()) {
                            this.clean();
                            this.startReading(row, col);
                            mustMove = true;
                        } else {
                            if (!Pattern.compile("^[0-9]{1,9} [0-9]{1,9} (true|false)$").matcher(data).matches()) {

                                this.processLine(data);
                            } else {
                                mustMove = false;
                                String[] param = data.split(" ");
                                int healths = Integer.parseInt(param[0]);
                                int scores = Integer.parseInt(param[1]);
                                String keyss = param[2];

                                PacmanObject.load(healths, scores, keyss);
                                this.stopReading();
                                maze = this.createMaze();
                                presenter.updateMaze(maze);
                                presenter.initializeInterfaceSaves(healths, scores, false);
                            }
                        }

                    }
                    Thread.sleep(40);

                }
                presenter.addEndLoadLable();
            } catch (FileNotFoundException | InterruptedException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        });

        thread1.start();
    }

    /**
     * Load logging record from file, and interpret it in reverse order.
     * 
     * @param str path to file with logging record
     */
    public void loadReverseSave(String str) {
        final MazePresenter presenter = new MazePresenter(null, null, null, 0);
        Thread thread1 = new Thread(() -> {
            Path path = Paths.get(str);
            CommonMaze maze;

            long lines;
            String line_cur = "";
            try {
                String[] param = Files.readAllLines(path).get(0).split(" ");
                int row = Integer.parseInt(param[0]);
                int col = Integer.parseInt(param[1]);

                lines = Files.lines(path).count();
                lines = (int) lines - (row + 2);

                while (!line_cur.equals("0 state")) {

                    int count = 0;
                    this.startReading(row, col);

                    while (count != row) {
                        try {
                            String line_curs = Files.readAllLines(path).get((int) lines + count + 1);
                            this.processLine(line_curs);
                            count++;
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Index out of bounds");
                            System.exit(0);
                        }
                        Thread.sleep(40);
                    }

                    String[] param1 = Files.readAllLines(path).get((int) lines + count + 1).split(" ");
                    int health = Integer.parseInt(param1[0]);
                    int score = Integer.parseInt(param1[1]);
                    String key = param1[2];
                    PacmanObject.load(health, score, key);
                    this.stopReading();

                    maze = this.createMaze();

                    presenter.updateMaze(maze);
                    presenter.initializeInterfaceSaves(health, score, true);

                    this.clean();

                    line_cur = Files.readAllLines(path).get((int) lines);
                    lines = lines - (row + 2);

                    Thread.sleep(30);
                }
                presenter.addEndLoadLable();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
    }

    /**
     * Load logging record from file, and interpret it in reverse order. The
     * interpretation of next state is after pressing button.
     * 
     * @param str path to file with logging record
     */
    public void loadReverseSaveStepByStep(String str) {
        final MazePresenter presenter = new MazePresenter(null, null, null, 2);
        Thread thread1 = new Thread(() -> {
            Path path = Paths.get(str);
            CommonMaze maze;

            long lines;
            String line_cur = "";
            try {
                String[] param = Files.readAllLines(path).get(0).split(" ");
                int row = Integer.parseInt(param[0]);
                int col = Integer.parseInt(param[1]);

                lines = Files.lines(path).count();
                int max = (int) lines - (row + 2);
                lines = (int) lines - (row + 2);
                char keys = presenter.getKey();
                while (!line_cur.equals("0 state")) {

                    if (keys != '~') {
                        keys = '~';

                        int count = 0;
                        this.startReading(row, col);

                        while (count != row) {
                            try {
                                String line_curs = Files.readAllLines(path).get((int) lines + count + 1);
                                this.processLine(line_curs);
                                count++;
                            } catch (IndexOutOfBoundsException e) {
                                System.out.println("Index out of bounds");
                                System.exit(0);
                            }
                            Thread.sleep(40);
                        }

                        String[] param1 = Files.readAllLines(path).get((int) lines + count + 1).split(" ");
                        int health = Integer.parseInt(param1[0]);
                        int score = Integer.parseInt(param1[1]);
                        String key = param1[2];
                        PacmanObject.load(health, score, key);
                        this.stopReading();
                        maze = this.createMaze();

                        presenter.updateMaze(maze);
                        presenter.initializeInterfaceSaves(health, score, true);
                        this.clean();
                        line_cur = Files.readAllLines(path).get((int) lines);
                        lines = lines - (row + 2);
                    }
                    Thread.sleep(85);
                    keys = presenter.getKey();

                }
                presenter.addEndLoadLable();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
    }

    /**
     * Stop reading from file.
     * 
     * @return true if reading was successful, false otherwise
     */
    public boolean stopReading() {
        if (this.numOfLines == this.rows) {
            return true;
        } else {
            this.errors = true;
            return false;
        }
    }

    /**
     * Create maze from read data.
     * 
     * @return maze if data was read successfully, null otherwise
     */
    public CommonMaze createMaze() {
        if (this.errors) {
            return null;
        }
        ;
        int rws = this.rows + 2;
        int cls = this.cols + 2;
        if (this.rows == 0 || this.cols == 0) {
            return null;
        }
        this.maze = new MazePlan(rws, cls, this.lines);
        return this.maze;
    }

}
