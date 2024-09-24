/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package p2.guarderia.modelo;

import java.util.ArrayList;

/**
 *
 * @author estudiante
 */
public interface IDAO {
   

    public void agregar(IObjetoDTO dto) throws Exception;

    public void eliminar(String id)throws Exception;

    public void actualizar(String id, IObjetoDTO dto)throws Exception;

    public void buscar(String id)throws Exception;

    public ArrayList<IObjetoDTO> listar()throws Exception;

}
