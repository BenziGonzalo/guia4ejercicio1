
package guia4ejercicio1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Guia4ejercicio1 {

    public static void main(String[] args) throws SQLException {
        
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            
            Connection conexion = DriverManager.getConnection("jdbc:mariadb://localhost:3306/universidadulp","root","");
            
//            String alumno= "insert into alumno(dni,apellido,nombre,fechaNacimiento,estado) "
//                    + "values (353134351, 'Messi','Lionel', '1990-09-13', true)";
//            
//            PreparedStatement al = conexion.prepareStatement(alumno);
//            int registroAlumno = al.executeUpdate();
//            System.out.println("Filas afectadas: "+ registroAlumno);

//        String materia = "insert into materia(nombre,año,estado) "
//                + "values ('Conseguir laburo',4,true)";
//        
//        PreparedStatement ma = conexion.prepareStatement(materia);
//        int registroMateria = ma.executeUpdate();
//        System.out.println("Filas afectadas "+ registroMateria);

//        String inscripcion = "insert into inscripcion(nota, idAlumno, idMateria) "
//                + "values(10, 4, 3),"
//                + "(10, 4, 4)";
//        
//        PreparedStatement ins = conexion.prepareStatement(inscripcion);
//        int registroInscripcion = ins.executeUpdate();
//        System.out.println("Filas afectadas: "+ registroInscripcion);

        String select = "select * from alumno a join inscripcion i on(a.idAlumno = i.idAlumno)"
                + "join materia m on(i.idMateria = m.idMateria) where nota > 6";
        PreparedStatement sel = conexion.prepareStatement(select);
        ResultSet resultadoSelect = sel.executeQuery();
        
        while(resultadoSelect.next()){
            
            System.out.println("ID: "+ resultadoSelect.getInt("idAlumno"));
            System.out.println("DNI: "+ resultadoSelect.getInt("dni"));
            System.out.println("APELLIDO: "+ resultadoSelect.getString("apellido"));
            System.out.println("NOMBRE: "+ resultadoSelect.getString("nombre"));
            System.out.println("FECHA DE NACIMIENTO "+ resultadoSelect.getDate("fechaNacimiento"));
            System.out.println("ESTADO: "+ resultadoSelect.getBoolean("estado"));
            System.out.println("MATERIA: "+ resultadoSelect.getString("m.nombre"));
            System.out.println("NOTA: "+ resultadoSelect.getInt("nota"));
            System.out.println("");
        }
            
        } catch (ClassNotFoundException ex) {
            
            JOptionPane.showMessageDialog(null, "error al cargar Driver "+ ex.getMessage());
        } catch(SQLException ex){
            
            JOptionPane.showMessageDialog(null, "error de conexión "+ ex.getMessage());
        }
    }
    
}
