public class ObservableStringBuilder
{
    private OnChangeListener onChangeListener;
    private StringBuilder stringBuilder;
    public void setOnChangeListener(OnChangeListener changeListener)
    {
        this.onChangeListener=changeListener;
    }



    public  ObservableStringBuilder()
    {
        stringBuilder=new StringBuilder();
    }

    private void notifyOnChangeListener()
    {
        if(onChangeListener!=null)
        {
            onChangeListener.OnChange(this);
        }
    }
    public void append(String s)
    {
        stringBuilder.append(s);
        notifyOnChangeListener();

        //return this;
    }
    public void insert(String s, int x)
    {
        stringBuilder.insert(x,s);
        notifyOnChangeListener();
    }

    public void delete(int x, int y)
    {
        stringBuilder.delete(x,y);
        notifyOnChangeListener();
    }

    public void reverse()
    {
        stringBuilder.reverse();
        notifyOnChangeListener();
    }

    public void replace(String s, int x, int y)
    {
        stringBuilder.replace(x,y,s);
        notifyOnChangeListener();
    }


    public String toString()
    {
        return stringBuilder.toString();
    }
}
