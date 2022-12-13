package br.com.frota.util;

import br.com.frota.model.Composicao;
import br.com.frota.model.Laboratorio;
import br.com.frota.servico.ServicoLaboratorio;
import br.com.frota.servico.ServicoMarca;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

public class Teste {
    public static void main(String[] args) {
        Laboratorio lab = new Laboratorio( "aaaaaaa", "aaaaaaa", "aaaaaaa", "aaaaa", "a");
        ServicoLaboratorio servico = new ServicoLaboratorio();

        servico.salvar(lab);
    }
}


