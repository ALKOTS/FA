public class Main {
    public static void main(String[] args)
    {
        ObservableStringBuilder osb=new ObservableStringBuilder();
        osb.setOnChangeListener(new Listener());
        System.out.println("append");
        osb.append("aa");
        System.out.println("insert");
        osb.insert("bb",2);
        System.out.println("replace");
        osb.replace("c",0,1);
        System.out.println("reverse");
        osb.reverse();
        System.out.println("delete");
        osb.delete(1,2);

    }
}
