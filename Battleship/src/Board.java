import java.util.Random;

public class Board {

    private Cell[][] cellboard;
    private Boat[] boats;
    private int column;
    private int row;
    private int total_shots;
    private int turns;
    private int ships_rmn;
    private int ships_hit;
    private int total_length;

    public Board(int r, int c) {
        column = c;
        row = r;
        cellboard = new Cell[r][c];
    }
    public void setTotal_shots(int s) {
        total_shots = s;
    }
    public void setTurns(int t) {
        turns = t;
    }
    public void setShips_rmn(int r) {
        ships_rmn = r;
    }
    public int getTurns() {
        return turns;
    }
    public int getTotal_shots() {
        return total_shots;
    }
    public int getShips_rmn() {
        return ships_rmn;
    }
    public Cell[][] getCellboard() {
        return cellboard;
    }
    public Boat[] getBoats() {
        return boats;
    }
    public int getColumn() {
        return column;
    }
    public int getRow() {
        return row;
    }
    public int getShips_hit() {
        return ships_hit;
    }
    public int getTotal_length() {
        return total_length;
    }
    public boolean genRan(int randr, int randc, int l, boolean orientation) { //Checks if the cords don't intercept or go out of bounds,
        // Checks one direction a length amount of times to see if it hits out or a boat
        if (orientation == true) {
            for (int i = 0; i < l; i++) {
                if (!((randr + i) < row && cellboard[randr + i][randc].getStatus() == '-')) {
                    return false;
                }
            }
        } else {
            for (int i = 0; i < l; i++) {
                if (!((randc + i) < column && cellboard[randr][randc + i].getStatus() == '-')) {
                    return false;
                }
            }
        }
        return true;
    }

    public void boatplacement(int r, int c, int l) { //A helper that palces a random boat
        Random rand = new Random(); //creating a random
        int ycord = rand.nextInt(c); //creating the random coordinates from 0-3
        int xcord = rand.nextInt(r);
        boolean pos = rand.nextBoolean();
        Boat boat1 = new Boat(l, pos); //create a boat with a random boolean
        while (!genRan(xcord, ycord, l, boat1.getOrientation())) {
            ycord = rand.nextInt(c); //creating the random coordinates from 0-3
            xcord = rand.nextInt(r);
        }
        Cell newCell = new Cell(xcord, ycord, 'B'); //create a new cell with random coordinates
        boat1.addCell(newCell); //adds the cell to boats cell array
        for (int i = 0; i < boats.length; i++) { //adds the boat to boat array
            if (boats[i] == null) {
                boats[i] = boat1;
                break;
            }
        }
        if (boat1.getOrientation() == true) {
            for (int i = 0; i < l; i++) {
                Cell addon = new Cell(xcord + i, ycord, 'B');
                cellboard[xcord + i][ycord].setStatus('B');
                boat1.addCell(addon);
            }
        }
        else {
            for (int i = 0; i < l; i++) {
                Cell addon = new Cell(xcord, ycord + i, 'B');
//                System.out.println(xcord + " " + ycord);
                cellboard[xcord][ycord + i].setStatus('B');
                boat1.addCell(addon);
            }
        }
    }

    public void placeboats() { //Places all boats manually

        for (int i = 0; i < cellboard.length; i++) {
            for (int f = 0; f < cellboard[i].length; f++) {
                cellboard[i][f] = new Cell(row, column, '-');
            }
        }
        int placement;
        if (row == 3 && column == 3) { //3x3 Board
            boats = new Boat[1]; //set length of boats
            boatplacement(row, column, 2);
            total_length = 2;
        }
        else if ((row == 4) || (column == 4)) {
            boats = new Boat[2];
            boatplacement(row, column, 2); //first boat has length of 2
            boatplacement(row, column, 3); //second boat with length 3
            total_length = 5;
        }
        else if ((row > 4 && row <= 6) || (column > 4 && column <= 6)){
                    boats = new Boat[3];
                    boatplacement(row, column, 2); //first boat has length of 2
                    boatplacement(row, column, 3); //2nd boat with length 3
                    boatplacement(row, column, 3); //3rdd boat with length 3
            total_length = 8;
                }
        else if ((row > 6 && row <= 8) || (column > 6 && column <= 8)) {
                boats = new Boat[4];
                boatplacement(row, column, 2); //first boat has length of 2
                boatplacement(row, column, 3); //boat with length 3
                boatplacement(row, column, 3); //3rd boat with length 3
                boatplacement(row, column, 4); // 4th boat with length 4
            total_length = 12;
                }
        else if ((row > 8 && row <= 10) || (column > 8 && column <= 10)) {
            boats = new Boat[4];
            boatplacement(row, column, 2); //first boat has length of 2
            boatplacement(row, column, 3); //boat with length 3
            boatplacement(row, column, 3); //3rd boat with length 3
            boatplacement(row, column, 4); // 4th boat with length 4
            boatplacement(row, column, 5); // 5th boat with length 5
            total_length = 17;
            }
        }
        public void fire(int x, int y) {
        if (cellboard[x][y].getStatus() == 'B') {
            cellboard[x][y].setStatus('H');
            ships_hit++;
        }
        else if (cellboard[x][y].getStatus() == '-') {
            cellboard[x][y].setStatus('M');
        }
        }
        public void display() {
            for (int i = 0; i < cellboard.length; i++) {
                for (int f = 0; f < cellboard[i].length; f++) {
                    if (cellboard[i][f].getStatus() == 'H' || cellboard[i][f].getStatus() == 'M') {
                        System.out.print(cellboard[i][f].getStatus() + " ");
                    }
                    else {
                        System.out.print("-" + " ");
                    }
                }
                System.out.print("\n");
            }
        }
        public void print() {
            for (int i = 0; i < cellboard.length; i++) {
                for (int f = 0; f < cellboard[i].length; f++) {
                    System.out.print(cellboard[i][f].getStatus() + " ");
                }
                System.out.print("\n");
            }
        }
        public void missile(int x, int y) {
        for (int i = x - 1; i < x + 2; i++) {
            for (int f = y - 1; f < y + 2; f++) {
                if ((i) < row && (f) < column) {
                    fire(i, f);
                }
            }
        }
        }
        public int drone(int direction, int index) {
        int count = 0;
        if (direction == 0) { //rows
            for (int i = 0; i < column; i++) {
                if (cellboard[index][i].getStatus() == 'B') {
                    count++;
                }
            }
        }
        else if (direction == 1) {
            for (int i = 0; i < row; i++) {
                if (cellboard[i][index].getStatus() == 'B') {
                    count++;
                }
            }
        }
        return count;
        }
        public int scanner(int x, int y) {
        int size = 0;
        boolean direction = false;
        if (cellboard[x][y].getStatus() == 'B') {
            for (int i = 0; i < boats.length; i++) {
                for (int f = 0; f < boats[i].getLocation().length; f++) {
                    if (cellboard[x][y] == boats[i].getLocation()[f]) {
                        size = boats[i].getLength();
                        direction = boats[i].getOrientation();
                    }
                }
            }
            if (direction == true) {
                System.out.println("Vertical");
            }
            else {
                System.out.println("Horizontal");
            }
            return size;
        }
        else {
            System.out.println("Unknown");
            return 0;
        }
        }
    }