package LAB2;

public class PunktXYZ extends PunktXY {
    private int z;

    public PunktXYZ(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }


    @Override
    public String toString() {
        return "(" + super.toString() + ", "+z+")";
    }
}
