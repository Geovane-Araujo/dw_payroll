package br.com.populateNames;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static final String DIR_JSON = "/home/geovane/Documentos/Workspace/Back-end/JAva/dw_payroll/dados com nomes de politicos/candidatos";

    public static void main(String[] args){
        readJson();
    }


    static void readJson(){
        listaArquivos();
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
