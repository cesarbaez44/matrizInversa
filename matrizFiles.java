import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class matrizFiles {

    public double[][] fileToMatrix(String fileName){
        double[][] matrix = null;
        try{
            File file = new File("C:\\archivos\\" + fileName);
            FileReader reader = new FileReader(file);
            BufferedReader bufer = new BufferedReader(reader);

            // Se cuentan las filas
            int rows = 0;
            String linea;
            while((linea = bufer.readLine()) != null){
                rows++;
            }
            reader.close();

            // Volvemos a leer para llenar la matriz
            reader = new FileReader(file);
            bufer = new BufferedReader(reader);
            matrix = new double[rows][rows]; // asumimos que es cuadrada
            int i = 0;
            while((linea = bufer.readLine()) != null){
                String[] parts = linea.trim().split("\\s+");
                for(int j=0; j<parts.length; j++){
                    matrix[i][j] = Double.parseDouble(parts[j]);
                }
                i++;
            }
            reader.close();
        } catch(Exception e){   
            System.out.println("Error al leer el archivo: " + e.toString());
        }
        return matrix;
    }

    // escribe la matriz en un nuevoarchivo
    public void writeMatrixToFile(String fileName, double[][] matriz){
        try{
            FileWriter file = new FileWriter("C:\\archivos\\" + fileName);
            PrintWriter writer = new PrintWriter(file);
            for(int i=0; i<matriz.length; i++){
                for(int j=0; j<matriz[i].length; j++){
                    writer.print(matriz[i][j] + " ");
                }
                writer.println();
            }
            file.close();
        } catch(Exception e){
            System.out.println("Error al escribir el archivo: " + e.toString());
        }
    }

    // Calcula la inversa de una matriz 
    public double[][] inverse(double[][] A){
        int n = A.length;
        double[][] aug = new double[n][2*n];

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                aug[i][j] = A[i][j];
            }
            aug[i][i+n] = 1;
        }

        // metodo gauss-Jordan23
        for(int i=0; i<n; i++){
            // Normalizamos fila i
            double diag = aug[i][i];
            if(diag == 0){
                System.out.println("La matriz no es invertible.");
                return null;
            }
            for(int j=0; j<2*n; j++){
                aug[i][j] /= diag;
            }
            // Eliminamos otras filas
            for(int k=0; k<n; k++){
                if(k != i){
                    double factor = aug[k][i];
                    for(int j=0; j<2*n; j++){
                        aug[k][j] -= factor * aug[i][j];
                    }
                }
            }
        }
        // Extraemos la inversa
        double[][] inv = new double[n][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                inv[i][j] = aug[i][j+n];
            }
        }
        return inv;
    }
}