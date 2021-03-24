public class Delete implements Action
{
    private final int x;
    private final int y;
    UndoableStringBuilder usb=new UndoableStringBuilder();
    StringBuilder word=new StringBuilder();


    public Delete(int x, int y)
    {
        this.x=x;
        this.y=y;


    }

    @Override
    public void execute()
    {
        word.append(usb.actionStack.peek());
        word.delete(x,y);
        usb.actionStack.push(word);


    }
}
