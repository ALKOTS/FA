public class Replace implements Action
{
    private final int x;
    private final int y;
    UndoableStringBuilder usb=new UndoableStringBuilder();
    StringBuilder word=new StringBuilder();
    private final String str;

    public Replace(String str, int x, int y)
    {
        this.str=str;
        this.x=x;
        this.y=y;

    }

    @Override
    public void execute() {
        word.append(usb.actionStack.peek());
        word.replace(x,y,str);
        usb.actionStack.push(word);


    }
}
