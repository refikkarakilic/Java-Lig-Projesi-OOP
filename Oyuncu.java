public class Oyuncu<T> {
    private String isim;
    private int yas;

    //GETTER AND SETTERS

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public int getYas() {
        return yas;
    }

    public void setYas(int yas) {
        this.yas = yas;
    }

    //CONSTRUCTOR
    public Oyuncu(String isim, int yas){
        this.isim = isim;
        this.yas = yas;
    }
}
