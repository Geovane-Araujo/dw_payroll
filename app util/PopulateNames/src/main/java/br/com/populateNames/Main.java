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
    static Random random = new Random();


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
            String sql = "INSERT INTO person_salary(nome,current_value) VALUES";

            for(Map objSob: jsonCarregados){
                String[] sobrenomeSplit = objSob.get("nomeCompleto").toString().split(" ");
                primeiroNome = nomeSplit[0] + formaSobrenome(sobrenomeSplit);
                double rn = Math.random() * (10000.00 - 1212.00) + 1212.00;
                sql += " ('"+primeiroNome+"',"+rn+"),";
            }
            sql = sql.substring(0,sql.length() - 1) + ";";
            nomesService.executeQuery(sql);
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


        for (int i = 1;i < sobrenomes.length;i++){
            int rn = random.nextInt((sobrenomes.length - 1)) + 1;
            sobrenome += " " + sobrenomes[rn].replace("'"," ");
        }
        return sobrenome;
    }
}
