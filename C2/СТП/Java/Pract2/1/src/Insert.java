public class Insert implements Action
{
    private final int x;

    UndoableStringBuilder usb=new UndoableStringBuilder();
    StringBuilder word=new StringBuilder();
    private final String str;

    public Insert(String str, int x)
    {
        this.str=str;
        this.x=x;


    }

    @Override
    public void execute() {
        word.append(usb.actionStack.peek());
        word.insert(x,str);
        usb.actionStack.push(word);


    }
}
