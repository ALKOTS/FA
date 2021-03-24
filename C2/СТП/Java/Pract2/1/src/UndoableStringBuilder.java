import java.util.Stack;
public class UndoableStringBuilder{

    public static StringBuilder word=new StringBuilder();
    public static int len;
    public UndoableStringBuilder()
    {
        this.len=0;
    }

    void append(String str)
    {
        Append ap=new Append(str);
        ap.execute();

    }
    void undo()
    {
        Undo u=new Undo();
        u.execute();

    }
    void insert(String str, int x)
    {
        Insert i=new Insert(str, x);
        i.execute();
    }
    void delete(int x, int y)
    {
        Delete d=new Delete(x,y);
        d.execute();
    }
    int length()
    {
        Length l=new Length();
        l.execute();
        return len;
    }
    void replace(String str, int x, int y)
    {
        Replace r=new Replace(str,x,y);
        r.execute();
    }

    String get()
    {
        if(actionStack.empty())
        {
            return ("");
        }
        else
        {
            return(actionStack.peek().toString());
        }

    }



    public static Stack<StringBuilder> actionStack=new Stack<>();


}
