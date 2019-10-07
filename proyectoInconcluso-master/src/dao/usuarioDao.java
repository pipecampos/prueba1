/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Usuario;


public class usuarioDao {
     Conexion con;

    public usuarioDao() {
        this.con = new Conexion();
    }
       
    public Usuario getEmisor(int id){
        Usuario emisor; // se prepara donde se guardará la persona
        Connection accesoBD = con.getConexion();
        
      try{
            String sql="SELECT * FROM usuario WHERE id = "+id; 
            
   
            Statement st = accesoBD.createStatement(); 
            ResultSet resultados = st.executeQuery(sql); 
            
           if (resultados.first()){ //método para encontrar el primer valor con el que se está comparando (el id)
            
                String nombre = resultados.getString("nombre");
                int Id = resultados.getInt("id_usuario");
              
                
                emisor = new Usuario (Id,nombre);
                return emisor;
       
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
    public ArrayList<Usuario> getUsuarios(){
        
        ArrayList<Usuario> usuarios = new ArrayList<>();
        Connection accesoBD = con.getConexion();

        try{
            String sql="SELECT * FROM usuario ";
            
            Statement st = accesoBD.createStatement();
            ResultSet resultados = st.executeQuery(sql);
           
            
            while ( resultados.next() ) {
                String nombre = resultados.getString("nombre");
                int id = resultados.getInt("id_usuario");
               
                usuarios.add(new Usuario(id,nombre));
            }
            accesoBD.close();
            return usuarios;
        }catch (Exception e){
            System.out.println();
            System.out.println("Error al obtener");
            e.printStackTrace();
            return null;
        }
      }
  
    
    
}
