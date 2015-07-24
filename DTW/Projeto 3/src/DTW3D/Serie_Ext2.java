package DTW3D;

import java.util.ArrayList;

public class Serie_Ext2 {

    private ArrayList<Coords_Ext2> serie;
    private final int id;

    public Serie_Ext2(int id) {
        this.serie = new ArrayList<>();
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Coords_Ext2> getSerie() {
        return serie;
    }

    public void setSerie(ArrayList<Coords_Ext2> coords) {
        this.serie = coords;
    }

}
