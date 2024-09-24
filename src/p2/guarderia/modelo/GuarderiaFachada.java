/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p2.guarderia.modelo;

/**
 *
 * @author Boris Perez
 */
public class GuarderiaFachada {

    private IDAO idao;

    public void agregarMascota(String id, String nombre, String edad, String raza) throws Exception {
        idao = new MascotaDAOFile();
        MascotaDTO m = new MascotaDTO();
        m.setId(id);
        m.setNombre(nombre);
        m.setEdad(edad);
        m.setRaza(raza);
        idao.agregar(m);

    }

    public void eliminarMascota(String idMascota) throws Exception {
        idao = new MascotaDAOFile();
        idao.eliminar(idMascota);
    }

    public void actualizarMascota(String id, String nombre, String edad, String raza) throws Exception {

    }

    public String[] buscarMascota(String id) throws Exception {
        idao = new MascotaDAOFile();
        MascotaDTO dto = (MascotaDTO)idao.buscar(id);
        if(dto!=null)
            String valores = new String[4];
            
        

        return null;

    }

    public void asignarPropietarioMascota(String idMascota, String idPersona) throws Exception {

    }

    public void retirarPropietarioMascota(String idMascota) throws Exception {

    }

    public String listarPersonas() throws Exception {
        return null;
    }

    public String listarMascotas() {
        return null;
    }

}
