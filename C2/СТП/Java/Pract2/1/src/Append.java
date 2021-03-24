public class Append implements Action
{

    UndoableStringBuilder usb=new UndoableStringBuilder();
    StringBuilder word=new StringBuilder();
    private final String str;

    public Append(String str)
    {
        this.str=str;


    }

    @Override
    public void execute() {
        if(!usb.actionStack.empty())
        {
            word.append(usb.actionStack.peek());
        }
        word.append(str);
        usb.actionStack.push(word);



    }
}
