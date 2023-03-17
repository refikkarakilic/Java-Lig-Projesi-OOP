import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Lig<Futbol> superLig = new Lig<>("superlig");
        Takim<Futbol> fb = new Takim<>(superLig, "fenerbahce", "mavi");
        Takim<Futbol> gs = new Takim<>(superLig, "galatasaray", "kirmizi");
        Takim<Futbol> bjk = new Takim<>(superLig);
        try{
            fb.macYap(gs);
            gs.macYap(bjk);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        superLig.sort();
        System.out.println(superLig.toString());
    }
}