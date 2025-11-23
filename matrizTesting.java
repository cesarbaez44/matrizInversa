import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class matrizTesting {

    public static void printMatriz(double[][] matriz){
        if(matriz == null){
            System.out.println("(matriz nula o no invertible)");
            return;
        }
        for(int i=0; i<matriz.length; i++){
            for(int j=0; j<matriz[i].length; j++){
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        matrizFiles files = new matrizFiles();
        BufferedReader bufer = new BufferedReader(new InputStreamReader(System.in));
        String fileName;

        System.out.println("Programa que calcula la inversa de una matriz cuadrada");
        System.out.print("Escribe el nombre del archivo de la matriz: ");
        fileName = bufer.readLine();

        double[][] matriz = files.fileToMatrix(fileName);
        System.out.println("Matriz original:");
        printMatriz(matriz);

        double[][] inversa = files.inverse(matriz);
        System.out.println("\nMatriz inversa:");
        printMatriz(inversa);

        System.out.print("\nEscribe el nombre del archivo de salida: ");
        fileName = bufer.readLine();
        files.writeMatrixToFile(fileName, inversa);

        System.out.println("Proceso terminado. El archivo se guardó en C:\\archivos\\" + fileName);
    }
}