import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.UsuarioDAO;
import DAO.UsuarioDAOImpl;
import MODELO.Direccion;
import MODELO.Telefono;
import MODELO.Usuario;

public class main {

    public static void main(String[] args) {
        try {
            // Inicializar DAO
            UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

            // Crear Dirección
            Direccion direccion = new Direccion();
            direccion.setCalle("Avenida Principal");
            direccion.setNroPuerta(1234);
            direccion.setApto("5B");

            // Crear Teléfonos
            Telefono telefono1 = new Telefono();
            telefono1.setPais("Uruguay");
            telefono1.setLocalidad("Montevideo");
            telefono1.setNumero(987654321L);
            telefono1.setTipoTelefono("Móvil");

            Telefono telefono2 = new Telefono();
            telefono2.setPais("Uruguay");
            telefono2.setLocalidad("Montevideo");
            telefono2.setNumero(27123456L);
            telefono2.setTipoTelefono("Fijo");

            List<Telefono> telefonos = new ArrayList<>();
            telefonos.add(telefono1);
            telefonos.add(telefono2);

            // Crear Usuario
            Usuario usuario = new Usuario();
            usuario.setNombres("Lauta");
            usuario.setApellidos("Pérez");
            usuario.setTipoDocumento("CI");
            usuario.setNumeroDocumento(93483984); // Asegúrate de que es int
            usuario.setFechaNacimiento(new java.util.Date()); // Reemplaza con la fecha correcta
            usuario.setDomicilio(direccion);
            usuario.setTelefonos(telefonos);
            usuario.setEmail("juan.perez@example.com");
            usuario.setContrasena("passwordSeguro123"); // Considera hashear la contraseña
            usuario.setTipoUsuario("socio");
            usuario.setEstado("activo");

            // Crear Usuario (incluye Dirección y Teléfonos)
            usuarioDAO.create(usuario);
            System.out.println("Usuario creado con ID: " + usuario.getId());

            // Leer Usuario
            Usuario usuarioLeido = usuarioDAO.read(usuario.getId());
            if (usuarioLeido != null) {
                System.out.println("\nUsuario leído:");
                System.out.println("Nombre: " + usuarioLeido.getNombres());
                System.out.println("Apellido: " + usuarioLeido.getApellidos());
                System.out.println("Dirección: " + usuarioLeido.getDomicilio().getCalle() + " "
                        + usuarioLeido.getDomicilio().getNroPuerta() + " "
                        + usuarioLeido.getDomicilio().getApto());
                System.out.println("Teléfonos:");
                for (Telefono telefono : usuarioLeido.getTelefonos()) {
                    System.out.println("- " + telefono.getTipoTelefono() + ": " + telefono.getNumero());
                }
            } else {
                System.out.println("No se encontró el usuario con ID: " + usuario.getId());
            }

            // Actualizar Usuario (ejemplo)
            usuarioLeido.setApellidos("González"); // Cambiar apellido
            usuarioLeido.getDomicilio().setCalle("Avenida Secundaria"); // Cambiar calle
            // Actualizar teléfonos, por ejemplo, cambiar el número de un teléfono
            if (!usuarioLeido.getTelefonos().isEmpty()) {
                usuarioLeido.getTelefonos().get(0).setNumero(123456789L);
            }
            usuarioDAO.update(usuarioLeido);
            System.out.println("\nUsuario actualizado.");

            // Leer nuevamente el Usuario para verificar la actualización
            Usuario usuarioActualizado = usuarioDAO.read(usuarioLeido.getId());
            if (usuarioActualizado != null) {
                System.out.println("\nUsuario actualizado leído:");
                System.out.println("Nombre: " + usuarioActualizado.getNombres());
                System.out.println("Apellido: " + usuarioActualizado.getApellidos());
                System.out.println("Dirección: " + usuarioActualizado.getDomicilio().getCalle() + " "
                        + usuarioActualizado.getDomicilio().getNroPuerta() + " "
                        + usuarioActualizado.getDomicilio().getApto());
                System.out.println("Teléfonos:");
                for (Telefono telefono : usuarioActualizado.getTelefonos()) {
                    System.out.println("- " + telefono.getTipoTelefono() + ": " + telefono.getNumero());
                }
            }

            // Desactivar Usuario
            usuarioDAO.deactivate(usuarioActualizado.getId());
            System.out.println("\nUsuario desactivado.");

            // Listar todos los Usuarios activos
            List<Usuario> usuariosActivos = usuarioDAO.listAll();
            System.out.println("\nLista de usuarios activos:");
            for (Usuario u : usuariosActivos) {
                System.out.println("- ID: " + u.getId() + ", Nombre: " + u.getNombres() + " " + u.getApellidos());
            }

        } catch (SQLException e) {
            System.err.println("Error en la base de datos: " + e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("Número de documento inválido. Debe ser un entero.");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Ocurrió un error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
