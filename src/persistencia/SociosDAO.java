package persistencia;

import entidades.Socio;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SociosDAO implements ISociosDAO {

    private IConexionBD conexion;

    public SociosDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }        
    
    @Override
    public boolean agregar(Socio socio) {
        try{
            // ESTABLECE UNA CONEXIÓN A MYSQL... SI NO PUEDE LANZA UNA SQLEXCEPTION
            Connection conexion = this.conexion.crearConexion();
            
            // CREAMOS UN OBJETO STATEMENT QUE NOS PERMITE ENVIAR CÓDIGO SQL A LA BD
            Statement comandoSQL = conexion.createStatement();
            
            String codigoSQL = String.format(
                "INSERT INTO socios (nombre, curp) VALUES ('%s', '%s');",
                socio.getNombre(),
                socio.getCurp());

            // SE UTILIZA PARA HACER OPERACIONES DE MODIFICACION DE DATOS (INSERT, DELETE, UPDATE)
            int registrosAfectados = comandoSQL.executeUpdate(codigoSQL);
            
            // CERRAMOS LA CONEXIÓN PARA EVITAR DESPERDICIO DE RECURSOS
            conexion.close();
            
            //return registrosAfectados == 1 ? true : false;
            //return registrosAfectados == 1;
            
            if(registrosAfectados == 1){
                return true;
            }else{
                return false;
            }                                    
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            return false;
        } 
    }

    @Override
    public boolean actualizar(Socio socio) {
        try{
            // ESTABLECE UNA CONEXIÓN A MYSQL... SI NO PUEDE LANZA UNA SQLEXCEPTION
            Connection conexion = this.conexion.crearConexion();
            
            // CREAMOS UN OBJETO STATEMENT QUE NOS PERMITE ENVIAR CÓDIGO SQL A LA BD
            Statement comandoSQL = conexion.createStatement();
            
            String codigoSQL = String.format(
                "UPDATE socios SET nombre='%s',curp='%s' WHERE id_socio = %d;",
                socio.getNombre(),
                socio.getCurp(),
                socio.getId());

            // SE UTILIZA PARA HACER OPERACIONES DE MODIFICACION DE DATOS (INSERT, DELETE, UPDATE)
            int registrosAfectados = comandoSQL.executeUpdate(codigoSQL);
            
            // CERRAMOS LA CONEXIÓN PARA EVITAR DESPERDICIO DE RECURSOS
            conexion.close();
            
            //return registrosAfectados == 1 ? true : false;
            //return registrosAfectados == 1;
            
            if(registrosAfectados == 1){
                return true;
            }else{
                return false;
            }                                    
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            return false;
        } 
    }

    @Override
    public boolean eliminar(Long idSocio) {
        try{
            // ESTABLECE UNA CONEXIÓN A MYSQL... SI NO PUEDE LANZA UNA SQLEXCEPTION
            Connection conexion = this.conexion.crearConexion();
            
            // CREAMOS UN OBJETO STATEMENT QUE NOS PERMITE ENVIAR CÓDIGO SQL A LA BD
            Statement comandoSQL = conexion.createStatement();
            
            String codigoSQL = String.format(
                "DELETE FROM socios WHERE id_socio = %d;",
                idSocio);

            // SE UTILIZA PARA HACER OPERACIONES DE MODIFICACION DE DATOS (INSERT, DELETE, UPDATE)
            int registrosAfectados = comandoSQL.executeUpdate(codigoSQL);
            
            // CERRAMOS LA CONEXIÓN PARA EVITAR DESPERDICIO DE RECURSOS
            conexion.close();
            
            //return registrosAfectados == 1 ? true : false;
            //return registrosAfectados == 1;
            
            if(registrosAfectados == 1){
                return true;
            }else{
                return false;
            }                                    
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public Socio consultar(Long idSocio) {
        Socio socio = null;
        try{
            // ESTABLECE UNA CONEXIÓN A MYSQL... SI NO PUEDE LANZA UNA SQLEXCEPTION
            Connection conexion = this.conexion.crearConexion();
            
            // CREAMOS UN OBJETO STATEMENT QUE NOS PERMITE ENVIAR CÓDIGO SQL A LA BD
            Statement comandoSQL = conexion.createStatement();
            
            String codigoSQL = String.format(
                "SELECT id_socio, nombre, curp FROM socios"
                        + " WHERE id_socio = %d;",
                    idSocio);

            // SE UTILIZA PARA HACER CONSULTAS (SELECT)
            ResultSet resultados = comandoSQL.executeQuery(codigoSQL);
            
            // SI HAY UN REGISTRO
            if(resultados.next()){
                Long id = resultados.getLong("id_socio");
                String nombre = resultados.getString("nombre");
                String curp = resultados.getString("curp");
                socio = new Socio(id, nombre, curp);
            }
            
            // CERRAMOS LA CONEXIÓN PARA EVITAR DESPERDICIO DE RECURSOS
            conexion.close();
            
            return socio;                                 
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            return socio;
        } 
    }

    @Override
    public List<Socio> consultarTodos() {
        List<Socio> listaSocios = new ArrayList<>();
        try{
            // ESTABLECE UNA CONEXIÓN A MYSQL... SI NO PUEDE LANZA UNA SQLEXCEPTION
            Connection conexion = this.conexion.crearConexion();
            
            // CREAMOS UN OBJETO STATEMENT QUE NOS PERMITE ENVIAR CÓDIGO SQL A LA BD
            Statement comandoSQL = conexion.createStatement();
            
            String codigoSQL = String.format(
                "SELECT id_socio, nombre, curp FROM socios;");

            // SE UTILIZA PARA HACER CONSULTAS (SELECT)
            ResultSet resultados = comandoSQL.executeQuery(codigoSQL);
            
            // MIENTRAS HAYA RESULTADOS QUE LEER... LOS LEEMOS
            while(resultados.next()){
                Long idSocio = resultados.getLong("id_socio");
                String nombre = resultados.getString("nombre");
                String curp = resultados.getString("curp");
                Socio socio = new Socio(idSocio, nombre, curp);
                listaSocios.add(socio);
            }
            
            // CERRAMOS LA CONEXIÓN PARA EVITAR DESPERDICIO DE RECURSOS
            conexion.close();
            
            return listaSocios;                                 
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            return listaSocios;
        } 
    }
    
}
