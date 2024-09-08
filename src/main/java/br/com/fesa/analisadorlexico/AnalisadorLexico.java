package br.com.fesa.analisadorlexico;

import java.util.regex.Pattern;

public class AnalisadorLexico {

    public static void main(String[] args) {

        String expressao = "x = 10; y == x;";

        Pattern regexIdentificador = Pattern.compile("[A-Za-z]+");
        Pattern regexNumero = Pattern.compile("[0-9]+");
        Pattern regexPontoVirgula = Pattern.compile(";");
        Pattern regexOperadorRelacional = Pattern.compile(">|<|>=|<=|==|!=");
        Pattern regexAtribuicao = Pattern.compile("=");


        String[] tokens = expressao.split("\\s+|(?<=;)|(?=;)");
        // \\s+ "quebra" a expressão por um ou mais espaços em branco, tabulações e quebras de linha
        // (?<=;) "positive lookbehind assertion"
        // (?=;) "positive lookahead assertion"

        String estado = "Q0";

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i].trim();


            System.out.println("Token " + (i + 1) + ": " + token);

            switch (estado) {
                case "Q0":
                    if (regexIdentificador.matcher(token).matches()) {
                        estado = "Q1";
                    } else if (regexNumero.matcher(token).matches()) {
                        estado = "Q2";
                    } else if (regexPontoVirgula.matcher(token).matches()) {
                        estado = "Q3";
                    } else if (regexOperadorRelacional.matcher(token).matches()) {
                        estado = "Q4";
                    } else if (regexAtribuicao.matcher(token).matches()) {
                        estado = "Q5";
                    } else {
                        System.out.println("A análise léxica reprovou o token: " + token);
                        return;
                    }
                    break;

                case "Q1":
                    estado = "Q0";
                    break;
                case "Q2":
                    estado = "Q0";
                    break;
                case "Q3":
                    estado = "Q0";
                    break;
                case "Q4":
                    estado = "Q0";
                    break;
                case "Q5":
                    estado = "Q0";
                    break;
            }
        }

        if (estado.equals("Q0")) {
            System.out.println("A análise léxica aprovou a expressão: " + expressao);
        } else {
            System.out.println("A análise léxica reprovou a expressão: " + expressao);
        }
    }
}
