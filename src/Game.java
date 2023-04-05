import java.util.Scanner;

public class Game {
    public static void debug() {
        System.out.println("Choose Board Rows");
        Scanner myScanner = new Scanner(System.in);
        int x = myScanner.nextInt();
        System.out.println("Choose Board Columns");
        Scanner scan2 = new Scanner(System.in);
        int y = scan2.nextInt();
        Board game = new Board(x, y);
        game.placeboats();
        int turns = 0;
        int ships = 0;
        int shots = 0;
        int missiles = 1;
        int scans = 1;
        while (true) {
            if (game.getTotal_length() == game.getShips_hit()) {
                System.out.println("Game Over!");
                System.out.println("Total Turns:" + " " + turns);
                return;
            }
            game.print();
            System.out.println("f to fire, m to shoot missile, s to scan, d for drone");
            Scanner scan3 = new Scanner(System.in);
            String input = scan3.nextLine();
            if (input.equals("f")) {
                System.out.println("Choose x cord to fire to");
                Scanner scan4 = new Scanner(System.in);
                int x1 = scan4.nextInt();
                System.out.println("Choose y cord to fire to");
                Scanner scan5 = new Scanner(System.in);
                int y1 = scan5.nextInt();
                if (game.getCellboard()[x1][y1].getStatus() == 'H' || game.getCellboard()[x1][y1].getStatus() == 'M' ||
                        x1 > game.getRow() || y1 > game.getColumn()) {
                    System.out.println("Penalty, Skipping Turn");
                    turns++;
                    shots++;
                } else {
                    game.fire(x1, y1);
                    turns++;
                    shots++;
                    if (game.getCellboard()[x1][y1].getStatus() == 'H') {
                        System.out.println("Hit!");
                    } else if (game.getCellboard()[x1][y1].getStatus() == 'M') {
                        System.out.println("Miss!");
                    }
                    System.out.println("Turn" + " " + turns);
                }
            } else if (input.equals("m")) {
                if (missiles == 1) {
                    System.out.println("Missiles: 1");
                    System.out.println("Choose x cord to fire to");
                    Scanner scan4 = new Scanner(System.in);
                    int x1 = scan4.nextInt();
                    System.out.println("Choose y cord to fire to");
                    Scanner scan5 = new Scanner(System.in);
                    int y1 = scan5.nextInt();
                    game.missile(x1, y1);
                    for (int i = x1 - 1; i < x1 + 2; i++) {
                        for (int i1 = y1 - 1; i1 < y1 + 2; i1++) {
                            if (game.getCellboard()[x1][y1].getStatus() == 'H') {
                                System.out.println("Hit");
                            }
                        }
                    }
                    turns++;
                    shots++;
                    missiles -= 1;
                } else {
                    System.out.println("Out of missiles");
                }
            } else if (input.equals("s")) {
                if (scans == 1) {
                    System.out.println("Choose x cord");
                    Scanner scan4 = new Scanner(System.in);
                    int x1 = scan4.nextInt();
                    System.out.println("Choose y cord");
                    Scanner scan5 = new Scanner(System.in);
                    int y1 = scan5.nextInt();
                    game.scanner(x1, y1);
                } else {
                    System.out.println("Out of Scans");
                }
            }
            else if (input.equals("d")) {
                System.out.println("Choose 0 for row, 1 for column");
                Scanner scan4 = new Scanner(System.in);
                int x1 = scan4.nextInt();
                System.out.println("Choose which row/column");
                Scanner scan5 = new Scanner(System.in);
                int y1 = scan5.nextInt();
                System.out.println("Has" + " " + game.drone(x1, y1) + " " + "spots that contain a boat");
            }
        }
    } //Debug Mode

    public static void main(String[] args) {
//        Board start = new Board(3, 3);
//        start.placeboats();
//        start.missile(6, 6);
//        start.print();
//        start.display();
//        System.out.println(start.drone(1, 2));
        System.out.println("d to run in debug mode, g to run normally");
        Scanner mode = new Scanner(System.in);
        String x = mode.nextLine();
        if (x.equals("d")) {
            debug();
        } else {
            System.out.println("Choose Board Rows");
            Scanner myScanner = new Scanner(System.in);
            int xcord = myScanner.nextInt();
            System.out.println("Choose Board Columns");
            Scanner scan2 = new Scanner(System.in);
            int ycord = scan2.nextInt();
            Board game = new Board(xcord, ycord);
            game.placeboats();
            int turns = 0;
            int ships = 0;
            int shots = 0;
            int missiles = 1;
            int scans = 1;
            while (true) {
                if (game.getTotal_length() == game.getShips_hit()) {
                    System.out.println("Game Over!");
                    System.out.println("Total Turns:" + " " + turns);
                    return;
                }
                game.display();
                System.out.println("f to fire, m to shoot missile, s to scan, d for drone");
                Scanner scan3 = new Scanner(System.in);
                String input = scan3.nextLine();
                if (input.equals("f")) {
                    System.out.println("Choose xcord cord to fire to");
                    Scanner scan4 = new Scanner(System.in);
                    int x1 = scan4.nextInt();
                    System.out.println("Choose ycord cord to fire to");
                    Scanner scan5 = new Scanner(System.in);
                    int y1 = scan5.nextInt();
                    if (game.getCellboard()[x1][y1].getStatus() == 'H' || game.getCellboard()[x1][y1].getStatus() == 'M' ||
                            x1 > game.getRow() || y1 > game.getColumn()) {
                        System.out.println("Penalty, Skipping Turn");
                        turns++;
                        shots++;
                    } else {
                        game.fire(x1, y1);
                        turns++;
                        shots++;
                        if (game.getCellboard()[x1][y1].getStatus() == 'H') {
                            System.out.println("Hit!");
                        } else if (game.getCellboard()[x1][y1].getStatus() == 'M') {
                            System.out.println("Miss!");
                        }
                        System.out.println("Turn" + " " + turns);
                    }
                } else if (input.equals("m")) {
                    if (missiles == 1) {
                        System.out.println("Missiles: 1");
                        System.out.println("Choose xcord cord to fire to");
                        Scanner scan4 = new Scanner(System.in);
                        int x1 = scan4.nextInt();
                        System.out.println("Choose ycord cord to fire to");
                        Scanner scan5 = new Scanner(System.in);
                        int y1 = scan5.nextInt();
                        game.missile(x1, y1);
                        for (int i = x1 - 1; i < x1 + 2; i++) {
                            for (int f = y1 - 1; f < y1 + 2; f++) {
                                if (game.getCellboard()[x1][y1].getStatus() == 'H') {
                                    System.out.println("Hit");
                                }
                            }
                        }
                        turns++;
                        shots++;
                        missiles -= 1;
                    } else {
                        System.out.println("Out of missiles");
                    }
                } else if (input.equals("s")) {
                    if (scans == 1) {
                        System.out.println("Choose xcord cord");
                        Scanner scan4 = new Scanner(System.in);
                        int x1 = scan4.nextInt();
                        System.out.println("Choose ycord cord");
                        Scanner scan5 = new Scanner(System.in);
                        int y1 = scan5.nextInt();
                        game.scanner(x1, y1);
                    } else {
                        System.out.println("Out of Scans");
                    }
                }
                else if (input.equals("d")) {
                    System.out.println("Choose 0 for row, 1 for column");
                    Scanner scan4 = new Scanner(System.in);
                    int x1 = scan4.nextInt();
                    System.out.println("Choose which row/column");
                    Scanner scan5 = new Scanner(System.in);
                    int y1 = scan5.nextInt();
                    System.out.println("Has" + " " + game.drone(x1, y1) + " " + "spots that contain a boat");
                }
            }
        }
    }
}
