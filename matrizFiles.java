import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

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

}