import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static String Change_type(String type){
        if(type.equals("C")){
            return "F";
        }else{
            return "C";
        }

    }

    public static void Show(String pref, String type) throws IOException {
        switch (pref){
            case ("1"):
                Yandex(type);
                break;
            case ("2"):
                WeatherCom(type);
                break;
            case ("3"):
                BBC(type);
                break;
        }
    }

    public static String WChoice(){

        Scanner sc=new Scanner(System.in);
        System.out.println("Выберите сервис: \n1. Яндекс погода \n2. Weather.com \n3. BBC weather");
        String ch=sc.nextLine();
        if((ch.equals("1"))|(ch.equals("2"))|(ch.equals("3"))){
            return ch;
        }else{
            System.out.println("Вы не ввели нужный символ, выставляется стандартный сервис(Яндекс)");
            return "1";
        }
    }

    public static void Yandex(String type) throws IOException
    {
        Document doc = null;
        try {
            doc = Jsoup.connect("https://yandex.ru/pogoda/moscow").get();

        } catch (IOException e){
            e.printStackTrace();
        }


        Elements el=doc.select(".temp.fact__temp.fact__temp_size_s .temp__value");
        Elements el2=doc.select(".temp.fact__temp.fact__temp_size_s .temp__unit.i-font.i-font_face_yandex-sans-text-medium");

        Elements hum=doc.select(".term.term_orient_v.fact__humidity");//(".fact__props .term__value");

        if(type.equals("F")){
            System.out.println("Яндекс не предлагает погоды в фаренгейтах, поэтому вот вариант в цельсиях. Для фаренгейта можете возпользоваться другими сервисами.");
        }
        System.out.println("Яндекс погода: "+el.text()+el2.text()+", влажность: "+hum.text());//.split(" ")[3]);//.split(" ")[1]);
    }

    public static void WeatherCom(String type) throws IOException
    {
        Document doc = null;


        try {
            if(type.equals("F")){
                doc = Jsoup.connect("https://weather.com/weather/today/l/34f2aafc84cff75ae0b014754856ea5e7f8ddf618cf9735549dfb5e016c28e10").get();
            }
            else{
                doc = Jsoup.connect("https://weather.com/ru-RU/weather/today/l/34f2aafc84cff75ae0b014754856ea5e7f8ddf618cf9735549dfb5e016c28e10").get();
            }


        } catch (IOException e){
            e.printStackTrace();
        }


        Elements el=doc.select(".CurrentConditions--tempValue--3KcTQ");
        Elements hum=doc.select(".WeatherDetailsListItem--wxData--23DP5");
        System.out.println("Weather.com: "+el.text()+", влажность: "+hum.text().split(" ")[3]);
    }

    public static void BBC(String type) throws IOException
    {

        Document doc = null;

        try {
            doc = Jsoup.connect("https://www.bbc.com/weather/524901").get();

        } catch (IOException e){
            e.printStackTrace();
        }

        Elements el=null;

        if(type.equals("C")){
            el=doc.select(".wr-time-slot-primary__temperature .wr-value--temperature--c");
        }
        else{
            el=doc.select(".wr-time-slot-primary__temperature .wr-value--temperature--f");
        }

        Elements hum=doc.select(".wr-c-station-data__observation.gel-long-primer.gs-u-pl0.gs-u-mv--");


        System.out.println("BBC: "+el.text().split(" ")[0]+", влажность: "+hum.text().split(" ")[1]);
    }


    public static void main(String[] args) throws IOException
    {
        Scanner sc=new Scanner(System.in);
        String type="C";
        String pref="1";
        File safe=new File("safe.txt");
        if(!safe.createNewFile()){
            Scanner rs=new Scanner(safe);
            pref=rs.nextLine();
            type=rs.nextLine();
        }

        while (true){
            System.out.println("Выберите действие: \n1. Показать погоду \n2. Выбрать источник \n3. Сменить еденицы измерения(текущие: "+type+") \n4. Закрыть программу");
            String choice=sc.nextLine();
            switch (choice){
                case ("1"):
                    Show(pref,type);
                    break;
                case ("2"):
                    pref=WChoice();
                    break;
                case ("3"):
                    type=Change_type(type);
                    break;
                case ("4"):
                    FileWriter fw=new FileWriter("safe.txt");
                    fw.write(pref+"\n"+type);
                    fw.close();
                    System.exit(1);
                case ("1337"):
                    Show("1","C");
                    Show("2","C");
                    Show("3","C");
                    Show("1","F");
                    Show("2","F");
                    Show("3","F");


            }
        }
    }
}
