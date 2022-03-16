package br.com.populateNames;

import br.com.populateNames.model.Nomes;
import br.com.populateNames.service.NomesService;
import br.com.populateNames.util.Util;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class Main {

    public static final String DIR_JSON = "/home/geovane/Documentos/Workspace/Back-end/JAva/dw_payroll/dados_brutos/candidatos";
    static NomesService nomesService = new NomesService();



    public static void main(String[] args){
        try {
            readJson();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    static void readJson() throws Exception {
        List<String> arquivos = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        arquivos = listaArquivos();

        List<Map<String, Object>> jsonCarregados = new ArrayList<>();

        for (String fileName: arquivos){
            Map<String,Object> obj = objectMapper.readValue(new File(DIR_JSON + "/"+fileName),Map.class);
            jsonCarregados.add(obj);
        }


        for(Map obj: jsonCarregados){
            String[] nomeSplit = obj.get("nomeCompleto").toString().split(" ");
            String primeiroNome = nomeSplit[0];

            for(Map objSob: jsonCarregados){
                Nomes nomes = new Nomes();
                String[] sobrenomeSplit = objSob.get("nomeCompleto").toString().split(" ");
                primeiroNome = nomeSplit[0] + formaSobrenome(sobrenomeSplit);

                nomes.setNome(primeiroNome);
                nomesService.save(nomes);
            }
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

    static String formaSobrenome(String[] sobrenomes){

        String sobrenome = "";
        Random random = new Random();

        for (int i = 1;i < sobrenomes.length;i++){
            int rn = random.nextInt((sobrenomes.length - 1)) + 1;
            sobrenome += " " + sobrenomes[rn];
        }
        return sobrenome;
    }
}
