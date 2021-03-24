public class Length implements Action
{
    UndoableStringBuilder usb=new UndoableStringBuilder();
    StringBuilder word=new StringBuilder();


    public Length()
    {



    }

    @Override
    public void execute() {

        usb.len=usb.actionStack.peek().length();


    }
}
