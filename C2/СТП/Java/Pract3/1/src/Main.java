import java.lang.reflect.Array;
import java.util.*;

public class Main
{
    public static Collection<String> DupDestroyer(Collection<String> al)
    {
        Collection<String> temp=new ArrayList<>();
        Object[] a=al.toArray();
        for (int i=0;i<a.length;i++)
        {
            for(int j=i+1;j<a.length;j++)
            {
                if(a[i]==a[j])
                {
                    temp.add(a[i].toString());
                    break;
                }
            }
        }
        al.removeAll(temp);
        return al;
    }

    public static ArrayList<String> ColFiller(ArrayList<String> al)
    {
        Random r=new Random();
        for(int i=0; i<1000000; i++)
        {
            al.add(String.valueOf(r.nextInt()));
        }
        return al;
    }

    public static LinkedList<String> LiFiller(LinkedList<String> ll)
    {
        Random r=new Random();
        for(int i=0; i<1000000; i++)
        {
            ll.add(String.valueOf(r.nextInt()));
        }
        return ll;
    }

    public static HashMap<String, Integer> DictFiller(HashMap<String, Integer> RusDic, String text)
    {
        ArrayList<String> al=new ArrayList<String>(Arrays.asList(text.split(" ")));
        for(int i=0; i<al.size(); i++)
        {
            String ending=String.valueOf(al.get(i).charAt((al.get(i).length()-1)));
            switch (ending)
            {
                case ",": al.set(i,al.get(i).replace(",",""));
                break;
                case ".": al.set(i,al.get(i).replace(".",""));
                break;
                case "!": al.set(i,al.get(i).replace("!",""));
                break;
                case "?": al.set(i,al.get(i).replace("?",""));
                break;
                default: break;
            }

        }
        System.out.println(al);

        for(String word:al)
        {
            if(RusDic.containsKey(word))
            {
                RusDic.put(word,RusDic.get(word)+1);
            }
            else
            {
                RusDic.put(word,1);
            }
        }
        return RusDic;
    }

    public static void main(String[] args)
    {
        Collection<String> collection=new ArrayList<String>(Arrays.asList("a","b","a","c","d","b","a"));
        ArrayList<String> collection2=new ArrayList<String>();
        LinkedList<String> linkedList=new LinkedList<String>();
        HashMap<String, Integer> RusDict=new HashMap<String, Integer>();


        System.out.println("Коллекция:");
        System.out.println(collection);

        collection=DupDestroyer(collection);
        System.out.println("Коллекция без дубликатов: ");
        System.out.println(collection);
        System.out.println("Создание коллекции в миллион элементов...");
        long start=System.nanoTime();
        collection2=ColFiller(collection2);
        long finish=System.nanoTime();
        System.out.println("Коллекция создана, времени затрачено: "+(finish-start));
        System.out.println("Создание связного списка на миллион элементов...");
        start=System.nanoTime();
        linkedList=LiFiller(linkedList);
        finish=System.nanoTime();
        System.out.println("Список создан, времени затрачено: "+(finish-start));

        Scanner in=new Scanner(System.in);

        System.out.println("Введите текст");
        String text=in.nextLine();
        RusDict=DictFiller(RusDict,text);
        System.out.println(RusDict);



    }
}
