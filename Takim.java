import java.util.*;
import java.util.concurrent.TimeUnit;

public class Takim<T> {
    private Scanner sc = new Scanner(System.in);
    private Random rd = new Random();
    private String isim;
    private String renk;
    private int kazanilanMaclar;
    private int kaybedilenMaclar;
    private int berabereMaclar;
    private List<Oyuncu<T>> oyuncular = new ArrayList<>();

    // GETTER AND SETTERS

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim){ this.isim = isim; }

    public String getRenk() {
        return renk;
    }

    public void setRenk(String renk){ this.renk = renk; }

    public int getKazanilanMaclar() {
        return kazanilanMaclar;
    }

    public int getKaybedilenMaclar() {
        return kaybedilenMaclar;
    }

    public int getBerabereMaclar() {
        return berabereMaclar;
    }

    //CONSTRUCTOR
    public Takim(Lig<T> lig){
        kazanilanMaclar = 0;
        kaybedilenMaclar = 0;
        berabereMaclar = 0;
        System.out.println("Takım ismi giriniz : ");
        this.setIsim(sc.nextLine());
        System.out.println("Takım rengi giriniz : ");
        this.setRenk(sc.nextLine());
        lig.addTeam(this);
    }
    public Takim(Lig<T> lig, String isim, String renk){
        kazanilanMaclar = 0;
        kaybedilenMaclar = 0;
        berabereMaclar = 0;
        this.setIsim(isim);
        this.setRenk(renk);
        lig.addTeam(this);
    }

    //FUNCTIONS

    //mac yapma
    public void macYap(Takim<T> rakipTakim) throws InterruptedException {
        int atilanGol = 0;
        int yenilenGol = 0;
        for (int i = 1; i <= 10; i++) {
            int atakSavunmaPuani = rd.nextInt(20)+1;
            int ihtimal = rd.nextInt(3);
            TimeUnit.SECONDS.sleep(1);
            if(ihtimal == 0){
                //pozisyon kaçır
                System.out.println(this.getIsim() + " takımı pozisyon kaçırdı!");
                continue;
            }else if (ihtimal == 1){
                //şut çek
                int evSahibiZar = rd.nextInt(15)+1;
                int rakipZar = rd.nextInt(10)+1;
                if ((evSahibiZar + atakSavunmaPuani) > (rakipZar + atakSavunmaPuani)){
                    //gol atıldı
                    System.out.println("Gooollll! (" + this.getIsim() + " takımı gol attı)");
                    atilanGol++;
                }else{
                    //gol kaçtı
                    System.out.println(this.getIsim() + " takımı gol kaçırdı!");
                    continue;
                }
            }else{
                //rakip takımın şutunu savun
                int rakipZar = rd.nextInt(10)+1;
                int evSahibiZar = rd.nextInt(10)+1;
                if ((evSahibiZar + atakSavunmaPuani) > (rakipZar + atakSavunmaPuani)){
                    //gol savunuldu
                    System.out.println(rakipTakim.getIsim() + " şut çekti fakat "+ this.getIsim() + " takımı golü savundu!");
                    continue;
                }else{
                    //kaleye gol atıldı
                    System.out.println("Gooollll! (" + rakipTakim.getIsim() + " takımı gol attı)");
                    yenilenGol++;
                }
            }
            System.out.println("Skor : " + atilanGol + " - " + yenilenGol);
        }

        if(atilanGol > yenilenGol){
            System.out.println("Maçı " + getIsim() + " takımı kazandı!\n");
            kazanilanMaclar++;
            rakipTakim.kaybedilenMaclar++;
        }else if(atilanGol == yenilenGol){
            System.out.println("Maç berabere!\n");
            berabereMaclar++;
            rakipTakim.berabereMaclar++;
        }else{
            System.out.println("Maçı " + rakipTakim.getIsim() + " takımı kazandı!\n");
            kaybedilenMaclar++;
            rakipTakim.kazanilanMaclar++;
        }
    }

    //oyuncu ekleme
    public void addPlayer(){
        System.out.println("Oyuncu ismini giriniz : ");
        String isim = sc.nextLine();
        int yas = -1;
        while (true){
            try{
                System.out.println("Oyuncunun yaşını giriniz : ");
                yas = Integer.parseInt(sc.nextLine());
                break;
            }catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        }
        Oyuncu<T> yeniOyuncu = new Oyuncu<>(isim, yas);
        oyuncular.add(yeniOyuncu);
    }

    //takımdaki oyuncu sayısı
    public int size(){
        return oyuncular.size();
    }

}
