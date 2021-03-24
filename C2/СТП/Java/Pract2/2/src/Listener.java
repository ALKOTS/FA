public class Listener implements OnChangeListener{
    @Override
    public void OnChange(ObservableStringBuilder osb) {
        System.out.println("Changed: "+osb.toString());
    }
}
