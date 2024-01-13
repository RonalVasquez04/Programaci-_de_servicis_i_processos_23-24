import java.util.*;

public class arraylist {

    public static Integer suma(ArrayList<Integer> list){

        Integer sumaTotal = 0;

        for (int index = 0; index < list.size(); index++) {
            sumaTotal = sumaTotal+list.get(index);
        }

        return sumaTotal;
    }

    public static String resultado(ArrayList<Integer> list, Integer sumaTotal){
         
        String numbers = "";
        Integer numMayorMedia = sumaTotal/list.size();
        Integer cantidadMayorMedia = 0;


        for (int i = 0; i < list.size(); i++) {
            numbers = numbers +  String.valueOf(list.get(i)) + ", ";

            if (list.get(i) > numMayorMedia) {
                cantidadMayorMedia++;
            }
        }



        return "Los números ingresados son : " +  numbers + "\n" + "Cantidad de números mayores que la media : " + cantidadMayorMedia; 
    }

    public static void main(String[] args) {

        ArrayList<Integer> Lista = new ArrayList<>();   
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe un número : ");
        Integer num;

        while((num = sc.nextInt()) != -99){

            Lista.add(num);

            System.out.println("Escribe un número : ");

        }

        
        System.out.println(resultado(Lista, suma(Lista)));
        

    }
}