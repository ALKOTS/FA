public class Undo implements Action
{
    UndoableStringBuilder usb=new UndoableStringBuilder();


    public Undo()
    {

    }

    @Override
    public void execute() {
        usb.actionStack.pop();



    }
}