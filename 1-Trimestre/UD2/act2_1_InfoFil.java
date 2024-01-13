public class act2_1_InfoFil extends Thread{
    
    public act2_1_InfoFil(String NomFil){
        this.setName(NomFil);
    }

    public void run(){
        System.out.println("ID : "+this.getId());
        System.out.println("Name : "+this.getName());
        System.out.println("Prioritat : "+this.getPriority());
        System.out.println("Alive : "+ this.isAlive());
        System.out.println("Dimoni : "+ this.isDaemon());
        System.out.println("Alive : "+ this.isInterrupted());
        System.out.println("");  
    }
    public static void main(String[] args) {
        
        act2_1_InfoFil objFill1 = new act2_1_InfoFil("nomFil1");
        act2_1_InfoFil objFill2 = new act2_1_InfoFil("nomFil2");

        objFill1.start();
        objFill2.start();
        

        try {
            objFill1.join();
            objFill2.join();
        } catch (Exception e) {
            // TODO: handle exception
        }
        System.out.println("Acabat");

    }
}
