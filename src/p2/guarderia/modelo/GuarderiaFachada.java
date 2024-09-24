/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p2.guarderia.modelo;

import java.util.ArrayList;

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
        idao= new MascotaDAOFile();
        MascotaDTO dto = (MascotaDTO)idao.buscar(id);
        dto.setId(id);
        dto.setNombre(nombre);
        dto.setEdad(edad);
        dto.setRaza(raza);

    }

    public String[] buscarMascota(String id) throws Exception {
        idao = new MascotaDAOFile();
        MascotaDTO dto = (MascotaDTO) idao.buscar(id);
        String[] valores = new String[4];
        if (dto != null) {
            valores[0] = dto.getId();
            valores[1] = dto.getNombre();
            valores[2] = dto.getEdad();
            valores[3] = dto.getRaza();
        }

        return valores;

    }

    public void asignarPropietarioMascota(String idMascota, String idPersona) throws Exception {
        idao=new MascotaDAOFile();
        MascotaDTO mascotadto = (MascotaDTO)idao.buscar(idMascota);
        idao= new PersonaDAOFile();
        PersonaDTO personadto = (PersonaDTO)idao.buscar(idPersona);
        mascotadto.setPropietario(personadto);
        

    }

    public void retirarPropietarioMascota(String idMascota) throws Exception {
        idao=new MascotaDAOFile();
        MascotaDTO mascotadto = (MascotaDTO)idao.buscar(idMascota);
        mascotadto.setPropietario(null);
        

    }

    public String [] listarPersonas() throws Exception {
        idao=new PersonaDAOFile();
        ArrayList <IObjetoDTO> personas = idao.listar();
        String[] per = new String[personas.size()];
        for(int i=0; i<personas.size(); i++){
            per[i] = personas.get(i).toString();
        }
        return per;
    }

    public String[] listarMascotas() throws Exception {
         idao=new PersonaDAOFile();
        ArrayList <IObjetoDTO> mascotas = idao.listar();
        String[] mas = new String[mascotas.size()];
        for(int i=0; i<mascotas.size(); i++){
            mas[i] = mascotas.get(i).toString();
        }
        return mas;
    }

}
