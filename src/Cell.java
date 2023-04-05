public class Cell {
    private int row;
    private int column;
    private char status;

    public Cell(int r, int c, char s) {
        row = r;
        column = c;
        status = s;
    }

    public void setRow(int r) {

        row = r;
    }
    public void setColumn(int c) {

        column = c;
    }
    public void setStatus(char s) {

        status = s;
    }
    public int getRow() {

        return row;
    }
    public int getColumn() {

        return column;
    }
    public char getStatus() {

        return status;

    }
}
