import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) {
        FileWriter abrirFich = null;
        PrintWriter escribo = null;
        GitHub github = null;
        try {
            //Se pide token al usuario
            String token = JOptionPane.showInputDialog("Introduce el token de GitHub: ");
            String fichero= JOptionPane.showInputDialog("Introduce el directorio del fichero para guardar el token: ");
            fichero=fichero+".txt";
            File fich = new File (fichero);
            try {
                abrirFich = new FileWriter(fichero);
                escribo.println(token);
            }catch(IOException e){
                System.out.println("Error 1"+e);
            }finally {
                escribo.close();
                try{
                    abrirFich.close();
                }catch (IOException e){
                    System.out.println("Error2");
                }

            }

            github = new GitHubBuilder()
                    .withOAuthToken(token)
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }

        GHRepository repo = null;
        try {
            //Se pide al usuario el nombre del repositorio
            String nomRepo = JOptionPane.showInputDialog("Introduce el nombre del repositorio: ");

            repo = github.createRepository(
                    nomRepo, "Este es el nuevo repositorio",
                    "https://www.kohsuke.org/", true/*public*/);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


