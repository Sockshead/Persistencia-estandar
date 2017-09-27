package Modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class PersistenciaAsignaCarrito {
    private String Modo;
    private String NombreArchivo = "AsignaCarrito";
    BufferedWriter Bw;
    BufferedReader Br;
   
    
    public ArrayList <AsignaCarrito>  CargarTodo()
  {
     ArrayList <AsignaCarrito> ArrayAsignaCarrito = new  ArrayList <>();
     AsignaCarrito Asig=null;
     if (abrirConexion("Lectura"))
     {
         while ( (Asig=LeerAsignaCarrito() ) != null)
         {
             ArrayAsignaCarrito.add(Asig);
         }
         cerrar();
     }
      return ArrayAsignaCarrito;  
  }
    public void GuardarTodo(ArrayList <AsignaCarrito> ArrayA)
  {
      if ( abrirConexion("Escritura") )
      {
          for (int i=0; i < ArrayA.size(); i++)
          {
              GrabarAsignaCarrito(ArrayA.get(i));
          }
          cerrar();
      }
  }

    public void GrabarAsignaCarrito(AsignaCarrito Asig) {
        try {
            Bw.write(Asig.toString());
            Bw.newLine();
        } catch (IOException Error) {
            System.out.println("Error Guardando la Asignacion");
        }
    }
    
    public AsignaCarrito LeerAsignaCarrito()
    {   String Texto="";
        AsignaCarrito Asig = new AsignaCarrito();
        try {   Texto = Br.readLine();
            if (Texto != null)
            {
                String []Info = Texto.split(",");
                Asig.setCodAsignatura(Info[0]);
                Asig.setCodProfesor(Info[1]);

            }
            else {  Asig = null;  }
                
        } catch(IOException error)
        { 
          Asig = null;  
          System.out.println("Error de lectura");
        }
        return Asig;
    }

    public boolean abrirConexion(String Forma) {
        this.Modo = Forma;
        boolean Ok = false;
        if (Forma.equalsIgnoreCase("Escritura")) {
            try {
                FileWriter Fw
                        = new FileWriter(NombreArchivo, false);
                Bw = new BufferedWriter(Fw);
                Ok = true;

            } catch (IOException ex) {
                Ok = false;
                ex.printStackTrace();
                System.out.println("Error abriendo conexion en modo Escritura");
            }

        }
        if (Forma.equalsIgnoreCase("Lectura")) {
            try {
                FileReader fr = new FileReader(NombreArchivo);
                Br = new BufferedReader(fr);
                Ok = true;
            } catch (IOException ex) {
                Ok = false;
                ex.printStackTrace();
                System.out.println("Error abriendo conexion en modo Lectura");
            }

        }
        return Ok;
    }

    public void cerrar() {
        try {
            if (this.Modo.equalsIgnoreCase("Escritura")) {
                Bw.close();
            }
            if (this.Modo.equalsIgnoreCase("Lectura")) {
                Br.close();
            }
        } catch (IOException variable) {
            System.out.println("Error cerrando archivo");
        }
    }
}
