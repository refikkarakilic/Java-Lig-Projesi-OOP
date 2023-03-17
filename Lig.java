import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Lig<T> {
    private Scanner sc = new Scanner(System.in);

    private String isim;
    private List<Takim<T>> takimlar = new ArrayList<>();

    //GETTER AND SETTERS
    public List<Takim<T>> getTakimlar() {
        return takimlar;
    }
    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    //CONSTRUCTOR
    public Lig(){
        System.out.println("Lig ismi giriniz : ");
        this.setIsim(sc.nextLine());
    }

    public Lig(String isim){
        this.setIsim(isim);
    }

    //FUNCTIONS
    public void addTeam(Takim<T> newTeam){
        takimlar.add(newTeam);
    }

    @Override
    public String toString(){
        String result = "\nLig : " + this.getIsim() + "\n" +
                "======================================\n" +
                "Takım\t-\tRenk\t-\tW\tL\tABS\n";
        for (int i = 0; i < takimlar.size(); i++) {
            Takim<T> takim = this.takimlar.get(i);
            String row = takim.getIsim()+"\t"+takim.getRenk()+"\t\t"+takim.getKazanilanMaclar()+"\t"+
                    takim.getKaybedilenMaclar()+"\t"+takim.getBerabereMaclar()+"\n";
            result += row;
        }
        return result;
    }

    public void sort(){
        List<Takim<T>> duzenliTakimlar = new ArrayList<>();
        for (int j = 0; j < this.takimlar.size(); j++) {
            Takim<T> enBuyuk = null;
            int enBuyukID = -1;
            //tüm listeyi dolaş
            for (int i = 0; i < this.takimlar.size(); i++) {
                //eğer en büyük seçilmediyse ilk veriyi en büyük olarak kabul et
                if (enBuyuk != null){
                    //elemanın kazanılan maçları seçilen en büyük elemandan daha büyükse
                    //elemanı yeni en büyük eleman olarak seç
                    if(this.takimlar.get(i).getKazanilanMaclar() > enBuyuk.getKazanilanMaclar()){
                        enBuyuk = this.takimlar.get(i);
                        enBuyukID = i;
                    }else if (this.takimlar.get(i).getKazanilanMaclar() == enBuyuk.getKazanilanMaclar()){
                        //elemanın kazanılan maç sayısı seçilen en büyük elemanın sayısıyla eşitse
                        //beraberlik oranlarını baz alarak hangisinin en büyük olduğunu bul
                        if(this.takimlar.get(i).getBerabereMaclar() > enBuyuk.getBerabereMaclar()){
                            enBuyuk = this.takimlar.get(i);
                            enBuyukID = i;
                        }else{
                            //elemanın berabere maç sayısı en büyük elemanın sayısıyla eşitse
                            //en az hangisi maç kaybettiyse onu en büyük olarak seç
                            if(this.takimlar.get(i).getKaybedilenMaclar() < enBuyuk.getKaybedilenMaclar()){
                                enBuyuk = this.takimlar.get(i);
                                enBuyukID = i;
                            }
                        }
                    }
                }else{
                    enBuyuk = this.takimlar.get(i);
                    enBuyukID = i;
                }
            }
            duzenliTakimlar.add(enBuyuk);
            this.takimlar.remove(enBuyukID);
        }
        duzenliTakimlar.add(this.takimlar.get(0));
        this.takimlar = duzenliTakimlar;
    }
}
