public class Boat {
    private int length;
    private boolean orientation;
    private Cell[] location;

    public Boat(int l, boolean o) {
        length = l;
        location = new Cell[l];
    }
    public void setLength(int l) {

        length = l;
    }
    public void setOrientation(boolean o) {
        orientation = o;
    }
    public void setLocation(Cell[] c) {
        location = c;
    }
    public int getLength() {
        return length;
    }
    public boolean getOrientation() {
        return orientation;
    }
    public Cell[] getLocation() {
        return location;
    }
    public void addCell(Cell c) {
        for (int i = 0; i < location.length; i++) {
            if (location[i] == null) {
                location[i] = c;
                }
            }
    }

}
