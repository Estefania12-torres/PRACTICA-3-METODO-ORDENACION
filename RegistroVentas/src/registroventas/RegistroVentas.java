/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package registroventas;

/**
 *
 * @author Usuario
 */
public class RegistroVentas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Numeros para ordenar
        /*int numeros [] = {10,9,8,7,6,5,4,3,2,1};
        
        //Llamada Metodo de Ordenamiento QuickSort
        quicksort(numeros, 0, numeros.length -1);
        
        //Metodo para imprimir Vector Ordenado
         Imprimir(numeros);*/
        // TODO code application logic here
        int vec[] = {45, 17, 23, 67, 21, 55, 8, 18, 89, 26, 58};
        System.out.println("Vector original");
        Imprimir(vec);
        System.out.println("\n");
        vec = shell(vec);
        Imprimir(vec);
    }

    //metodo ordenamiento quicksort
    public void quickSort(int arr[], int begin, int end) {
    if (begin < end) {
        int partitionIndex = partition(arr, begin, end);

        quickSort(arr, begin, partitionIndex-1);
        quickSort(arr, partitionIndex+1, end);
    }
}
    
    private int partition(int arr[], int begin, int end) {
    int pivot = arr[end];
    int i = (begin-1);

    for (int j = begin; j < end; j++) {
        if (arr[j] <= pivot) {
            i++;

            int swapTemp = arr[i];
            arr[i] = arr[j];
            arr[j] = swapTemp;
        }
    }

    int swapTemp = arr[i+1];
    arr[i+1] = arr[end];
    arr[end] = swapTemp;

    return i+1;
}
    //metodo ordenamiento shell
    public static int[] shell(int arreglo[]) {
        int cont = 0;
        final int N = arreglo.length;
        int salto = N / 2;
        while (salto >= 1) {
            for (int i = 0; i < salto; i++) {
                //para cada subvector recorremos sus elementos
                for (int j = salto + i; j < N; j += salto) {
                    int v = arreglo[j];
                    int n = j - salto;
                    while (n >= 0 && arreglo[n] > v) {
                        arreglo[n + salto] = arreglo[n];
                        n = n - salto;
                    }
                    arreglo[n + salto] = v;
                }
            }
            //obtenemos un nuevo salto
            salto = salto / 2;
        }
        return arreglo;
    }

    public static void Imprimir(int arreglo[]) {
        for (int i = 0; i < arreglo.length; i++) {
            System.out.print(arreglo[i] + " ");
        }
    }

}
