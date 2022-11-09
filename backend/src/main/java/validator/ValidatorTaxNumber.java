package validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorTaxNumber {
    public boolean taxNumberIsValid(String taxNumber){

        boolean isTaxNumberValid = false;
       taxNumber = taxNumber.replace("-", "").replace(".", "").replace("/", "");

        if(taxNumber != null && taxNumber.length() == 11){
            String regex = "[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2}";
            Pattern pattern = Pattern.compile(regex);
            java.util.regex.Matcher matcher = pattern.matcher(taxNumber);

            if(matcher.matches()){
                isTaxNumberValid = true;
            }

        }else if(taxNumber != null && taxNumber.length() == 14){
            String regex = "[0-9]{2}\\.?[0-9]{3}\\.?[0-9]{3}\\//?[0-9]{4}\\-?[0-9]{2}";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(taxNumber);

            if(matcher.matches()){
                isTaxNumberValid = true;
            }
        }      
        return isTaxNumberValid;
    }
}

/*
 * [0-9]{2} Faixa de caracteres: 0 a 9, quantidade: 2 caracteres;
 * [0-9]{3} Faixa de caracteres: 0 a 9, quantidade: 3 caracteres;
 * [0-9]{4} Faixa de caracteres: 0 a 9, quantidade: 4 caracteres;
 * [\.]?Um ponto, opcional. Foi usado \ no ponto, pois ele sozinho é caractere especial;
 * [-]? Um traço, opcional (se acrescentar outros caracteres, comece pelo - sempre);
 * [\/]? Uma barra, opcional. Tambem "escapada" com \ pra agradar o PCRE;
 * (grupo1)|(grupo2) Se um dos grupos validar, a expressão é válida.
 */


