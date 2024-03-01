import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.Scanner;

public class MetodoDelTriangulo extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Solicitar al usuario las coordenadas y la unidad de medida
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese las coordenadas del vector en formato 'x y', donde x es la coordenada horizontal (oeste/negativo, este/positivo) y y es la coordenada vertical (sur/negativo, norte/positivo):");
        double startX = scanner.nextDouble();
        double startY = scanner.nextDouble();
        System.out.println("Ingrese la dirección cardinal de la orientación del vector (norte, sur, este, oeste):");
        String orientacion = scanner.next().toLowerCase();
        System.out.println("Ingrese la longitud del vector:");
        double longitud = scanner.nextDouble();
        System.out.println("Ingrese la unidad de medida (metros, kilómetros, yardas, millas, pies):");
        String unidad = scanner.next().toLowerCase();

        // Convertir la unidad de medida a metros (sistema internacional)
        switch (unidad) {
            case "metros":
                break;
            case "kilómetros":
                longitud *= 1000; // 1 kilómetro = 1000 metros
                break;
            case "millas":
                longitud *= 1609.34; // 1 milla = 1609.34 metros
                break;
            case "yardas":
                longitud *= 0.9144; // 1 yarda = 0.9144 metros
                break;
            case "pies":
                longitud *= 0.3048; // 1 pie = 0.3048 metros
                break;
            default:
                System.out.println("Unidad de medida no válida. Se asumirá que la longitud está en metros.");
                break;
        }

        // Ajustar las coordenadas según la orientación del vector
        switch (orientacion) {
            case "norte":
                startY -= longitud;
                break;
            case "sur":
                startY += longitud;
                break;
            case "este":
                startX += longitud;
                break;
            case "oeste":
                startX -= longitud;
                break;
            default:
                System.out.println("Orientación no válida. No se realizarán ajustes a las coordenadas.");
                break;
        }

        // Definir el tamaño del lienzo
        int width = 400;
        int height = 400;

        // Crear el lienzo
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Dibujar los ejes cartesianos
        drawAxes(gc, width, height);

        // Dibujar el vector ingresado por el usuario
        drawVector(gc, startX, startY, startX, startY + longitud);

        // Crear la escena
        StackPane root = new StackPane();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root, width, height);

        primaryStage.setTitle("Método del Triángulo para Suma de Vectores");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Método para dibujar los ejes cartesianos
    private void drawAxes(GraphicsContext gc, int width, int height) {
        gc.strokeLine(0, height / 2, width, height / 2); // Eje X
        gc.strokeLine(width / 2, 0, width / 2, height); // Eje Y
    }

    // Método para dibujar un vector en el lienzo
    private void drawVector(GraphicsContext gc, double startX, double startY, double endX, double endY) {
        gc.strokeLine(startX, startY, endX, endY); // Dibujar el vector
        gc.strokeOval(endX - 2, endY - 2, 4, 4); // Dibujar el extremo del vector
    }

    public static void main(String[] args) {
        launch(args);
    }
}
