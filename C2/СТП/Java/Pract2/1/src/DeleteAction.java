public class DeleteAction //implements Action
{
    StringBuilder sb=new StringBuilder();

    private int size;

    public DeleteAction(int size)
    {
        this.size = size;

    }
    //@Override
    public void undo()
    {
        sb.delete(sb.length() - size, sb.length());
    }
}