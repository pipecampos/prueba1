/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Mensaje;
import modelo.Usuario;

/**
 *
 * @author pipe
 */
public class MensajeDao {
    
     Conexion con;

    public MensajeDao() {
        this.con = new Conexion();
    }
       
    public Usuario getUsuario(int id){
        Usuario p; // se prepara donde se guardará la persona
        Connection accesoBD = con.getConexion();
        
      try{
            String sql="SELECT * FROM usuario WHERE id = "+id; 
            
   
            Statement st = accesoBD.createStatement(); //objeto que administra las consultas
            ResultSet resultados = st.executeQuery(sql); //es una lista de resultados
            
           if (resultados.first()){ //método para encontrar el primer valor con el que se está comparando (el id)
            
                String nombre = resultados.getString("nombre");
                String apellido = resultados.getString("apellido");
                String rut = resultados.getString("rut");
              
                
                p = new Usuario (id,nombre);
                return p;
       
        }else{
           return null;
           }
        }catch (Exception e){ 
            System.out.println();
            System.out.println("Error al obtener");
            e.printStackTrace();
            return null;
        }
}
    public ArrayList<Mensaje> getMensajes(){
        
        ArrayList<Mensaje> mensajes = new ArrayList<>();
        ArrayList<Usuario> usuarios = new ArrayList<>();
        Connection accesoBD = con.getConexion();

        try{
            String sql="SELECT * FROM mensaje ";
            
            Statement st = accesoBD.createStatement();
            ResultSet resultados = st.executeQuery(sql);
           
            
            while ( resultados.next() ) {
                int id = resultados.getInt("id_mensaje");
                usuarioDao usuarioDAO = new usuarioDao();           
                Usuario usuario = new Usuario();
                Usuario emisor = usuarioDAO.getEmisor(usuario.getId_usuario());
                Usuario remitente = new Usuario();
                String contenido = resultados.getString("contenido");
               
                mensajes.add(new Mensaje(id,emisor,remitente,contenido));
            }
            accesoBD.close();
            return mensajes;
        }catch (Exception e){
            System.out.println();
            System.out.println("Error al obtener");
            e.printStackTrace();
            return null;
        }
      }
    
}
