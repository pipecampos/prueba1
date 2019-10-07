/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;


import dao.MensajeDao;
import dao.usuarioDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import vista.BandejaEntrada;
import javax.swing.JOptionPane;
import modelo.Mensaje;
import modelo.Usuario;
import vista.Login;
/**
 *
 * @author chelo
 */
public class LoginController implements ActionListener{
    
    
    private Login vLogin;

    public LoginController(Login vLogin) {
        this.vLogin = vLogin;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getActionCommand().equals("ingresar")){
            
            String nombre= vLogin.getNombreTf().getText(); //nombre obtenido
            
            usuarioDao uDAO = new usuarioDao(); //No modificar
            
            ArrayList<Usuario>usuariosBD = uDAO.getUsuarios();
            boolean nombreExisteEnBD=false; 
            int id=-1;
            for(int i = 0; i < usuariosBD.size(); i++) {
                
               if(usuariosBD.get(i).getNombre().equals(nombre)){
                 nombreExisteEnBD=true;
                 id=usuariosBD.get(i).getId_usuario();
                 break;
               } 
               
            }
            
            if(nombreExisteEnBD){
                BandejaEntrada bandejaVentana= new BandejaEntrada(id);//sería el Id del usuario logueado
                MensajeDao mensajedao = new MensajeDao();
                ArrayList<Mensaje> mensajes = new MensajeDao().getMensajes();
          for (int i = 0; i < mensajes.size(); i++) {
              mensajedao.add(mensajes.get(i).toString());
              
          }
                
                bandejaVentana.setjTable1(mensajedao.getMensajes());
                bandejaVentana.setVisible(true);
  
            }else{
                JOptionPane.showMessageDialog(vLogin, "Error el usuario no existe en la BD", "ERROR", JOptionPane.ERROR);
            }
        }
        
    } 
    
    
}