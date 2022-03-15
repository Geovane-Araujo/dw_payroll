package br.com.populateNames;

import br.com.populateNames.service.NomesService;
import br.com.populateNames.util.Util;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public class Main {

    public static final String DIR_JSON = "/home/geovane/Documentos/Workspace/Back-end/JAva/dw_payroll/dados_brutos/candidatos";

    public static void main(String[] args){
        try {
            readJson();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static void readJson() throws IOException {
        List<String> arquivos = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        arquivos = listaArquivos();

        for(String fileName: arquivos){
            Map<String,Object> obj = objectMapper.readValue(new File(DIR_JSON + "/"+fileName),Map.class);
            System.out.printf(obj.toString());

        }


    }

    static List<String> listaArquivos(){
        List<String> listaArquivos = new ArrayList<>();

        File diretorio = new File(DIR_JSON);

        for(File file: diretorio.listFiles()){
            listaArquivos.add(file.getName());
        }

        return listaArquivos;
    }
}
