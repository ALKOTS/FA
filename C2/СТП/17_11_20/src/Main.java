




public class Main {
    static class MyThread extends Thread {

        @Override
        public void run() {

            ss();


        }



    }

    static class MyThread2 extends Thread{


        public void run(){
            try{
                sleep(1000);
            }catch (Exception e){}
            for(int i=0; i<10; i++)
            {
                try {
                    s();
                    System.out.println(this.getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private static synchronized void s() throws InterruptedException
    {
        Thread.sleep(1000);
    }

    private static synchronized void ss() {
        for (int i=0; i<10; i++)
        {
            System.out.println(i);

        }
        System.out.println("------------");
        try {
            Thread.sleep(1000);
        }catch (Exception e){}


    }


    public static void main(String[]args){
        Thread t= new Thread();

        Thread tt=new Thread();
        System.out.println(t.getState());
        t.start();
        System.out.println(t.getState());
        System.out.println("----------");
        MyThread t1=new MyThread();
        MyThread t2=new MyThread();

        t1.start();
        System.out.println(t1.getState());
        System.out.println(t2.getState());
        t2.start();
        System.out.println(t2.getState());

        MyThread2 m1=new MyThread2();
        MyThread2 m2=new MyThread2();
        m1.start();
        m2.start();


    }
}
