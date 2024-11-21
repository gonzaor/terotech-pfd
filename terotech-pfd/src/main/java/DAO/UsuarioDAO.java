package DAO;
import MODELO.Usuario;
import CONTROLADOR.Conexion;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public clas UsuarioDAO {
    Conexion conexion;

    public UsuarioDAO(){
        try {
            this.conexion = Conexion.obtenerInstancia();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

private static final String INSERT_SCRIPT = "INSERT INTO usuarios (documento, tipoDocumento, nombre, apellido, correo, id_direccion, id_estado, id_perfil) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE_SCRIPT = "DELETE FROM usuarios WHERE id = ?";
    private static final String GET_USUARIO_SCRIPT = ;
    private static final String GET_ALL_USUARIOS_SCRIPT = ;

    void agregarUsuario(Usuario usuario){
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SCRIPT)) {
            preparedStatement.setObject(1, usuario.getNumeroDocumento());
            preparedStatement.setObject(2, usuario.getTipoDocumento());
            preparedStatement.setObject(3, usuario.getNombres());
            preparedStatement.setObject(4, usuario.getApellidos());
            preparedStatement.setObject(5, usuario.getEmail());
            preparedStatement.setObject(6, usuario.getDomicilio());
            preparedStatement.setObject(7, usuario.getEstado);
            preparedStatement.setObject(8, usuario.getTipoUsuario());
            preparedStatement.executeUpdate();
        }
    };
    void eliminarUsuario(String numeroDocumento);

//  ACTUALIZACION DE USUARIO
//  Parametros:
//    String campoAModificar => nombre del campo a modificar
//    String nuevoValor => valor que tomará el campo a modificar
//    String identificadorUsuario => id, documento o correo utilizado para identificar al usuario
//    int tipoIdentificador => 1 = documento, 2 = correo, null u otro = id. Tipo identificador usado para obtener el usuario a modificar.
    void actualizarUsuario(String campoAModificar, String nuevoValor, String identificadorUsuario, int tipoIdentificador){
        String campoIdentificador;
        switch (tipoIdentificador){
            case 1:
                campoIdentificador = "documento";
                break;
            case 2:
                campoIdentificador = "correo"
                break;
            case null:
                campoIdentificador = "id";
                break;
            default:
                campoIdentificador = "id";
                break;
        }

        String UPDATE_SCRIPT = "UPDATE usuarios SET " + campoAModificar + " = ? WHERE " + campoIdentificador + " = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SCRIPT)) {
            preparedStatement.setObject(1, nuevoValor);
            preparedStatement.setObject(2, identificadorUsuario);
            preparedStatement.executeUpdate();
        }
    }

    Usuario obtenerUsuario(String identificador, int tipoIdentificador);
    List<Usuario> obtenerTodosLosUsuarios();

}
