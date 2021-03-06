package clubnautico_1130am;

import entidades.Socio;
import guis.SociosForm;
import java.util.List;
import persistencia.ConexionBD;
import persistencia.IConexionBD;
import persistencia.ISociosDAO;
import persistencia.SociosDAO;

public class Principal {

    public static void main(String[] args) {
        IConexionBD conexionBD = new ConexionBD();
        ISociosDAO sociosDAO = new SociosDAO(conexionBD);
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SociosForm(sociosDAO).setVisible(true);
            }
        });
    }
    
}
