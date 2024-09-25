/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p2.guarderia.modelo;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author estudiante
 */
public class PersonaDAOFile implements IDAO {

    @Override
    public void agregar(IObjetoDTO dto) {
        String rutaArchivo = "personas.csv";
        PersonaDTO dto1 = (PersonaDTO) dto;
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaArchivo))) {

            escritor.write(dto1.toString());
            escritor.newLine();

        } catch (IOException e) {
            System.out.println("Ocurrió un error al escribir el archivo: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(String id) {
        PersonaDTO dto = (PersonaDTO) buscar(id);
        String archivoCSV = "personas.csv";
        String archivoTemp = "personas_temp.csv";
        String valorAEliminar = dto.toString();

        try {

            List<String> lineas = Files.readAllLines(Paths.get(archivoCSV));
            List<String> lineasFiltradas = lineas.stream()
                    .filter(linea -> !linea.contains(valorAEliminar))
                    .collect(Collectors.toList());

            try (BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoTemp))) {
                for (String linea : lineasFiltradas) {
                    escritor.write(linea);
                    escritor.newLine();
                }
            }
            Files.delete(Paths.get(archivoCSV));
            Files.move(Paths.get(archivoTemp), Paths.get(archivoCSV));

        } catch (IOException e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
        }
    }

    @Override
    public void actualizar(String id, IObjetoDTO dto) {
        PersonaDTO lineaAntigua = (PersonaDTO) buscar(id);
        String archivoCSV = "personas.csv";
        String archivoTemp = "personas_temp.csv";
        String valorBuscado = lineaAntigua.toString();
        String nuevaLinea = dto.toString();

        try {
            List<String> lineas = Files.readAllLines(Paths.get(archivoCSV));
            List<String> lineasModificadas = lineas.stream()
                    .map(linea -> {
                        if (linea.contains(valorBuscado)) {
                            return nuevaLinea;
                        }
                        return linea;
                    })
                    .collect(Collectors.toList());

            try (BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoTemp))) {
                for (String linea : lineasModificadas) {
                    escritor.write(linea);
                    escritor.newLine();
                }
            }

            Files.delete(Paths.get(archivoCSV));
            Files.move(Paths.get(archivoTemp), Paths.get(archivoCSV));

        } catch (IOException e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
        }
    }

    @Override
    public IObjetoDTO buscar(String id) {
        String archivoCSV = "personas.csv";
        PersonaDTO dto = null;
        try (BufferedReader lector = new BufferedReader(new FileReader(archivoCSV))) {
            String linea;

            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split(";");

                int idCsv = Integer.parseInt(partes[0]);
                if (idCsv == Integer.parseInt(id)) {
                    dto = new PersonaDTO();
                    dto.setId(id);
                    dto.setNombre(partes[1]);

                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer el archivo: " + e.getMessage());
        }
        return (IObjetoDTO) dto;
    }

    @Override
    public ArrayList<IObjetoDTO> listar() {
        String archivoCSV = "personas.csv";
        PersonaDTO dto = null;
        ArrayList<IObjetoDTO> lista = new ArrayList<IObjetoDTO>();
        try (BufferedReader lector = new BufferedReader(new FileReader(archivoCSV))) {
            String linea;

            while ((linea = lector.readLine()) != null) {

                String[] partes = linea.split(";");
                dto = new PersonaDTO();
                dto.setId(partes[0]);
                dto.setNombre(partes[1]);

                lista.add(dto);
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer el archivo: " + e.getMessage());
        }
        return lista;
    }

}
