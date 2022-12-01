package br.com.delivery.pidao.util.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorPassword {

    public boolean passwordIsValid(String password){

        boolean ispasswordValid = false;

        String regex = "(^(? =.*[0-9])(?=.*[az])(?=.*[AZ])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        if(matcher.matches()){
            ispasswordValid = true;
        }
            
        return ispasswordValid;
    }
}

/*
 * ^ representa o caractere inicial da string.
 * (? =. * [0-9]) representa um dígito deve ocorrer pelo menos uma vez.
 * (? =. * [az]) representa um alfabeto minúsculo deve ocorrer pelo menos uma vez.
 * (? =. * [AZ]) representa um alfabeto maiúsculo que deve ocorrer pelo menos uma vez.
 * (? =. * [@ # $% ^ & - + =()] representa um caractere especial que deve ocorrer pelo menos uma vez.
 * (? = \\ S + $) espaços em branco não são permitidos em toda a string.
 * . {8, 20} representa pelo menos 8 caracteres e no máximo 20 caracteres.
 * $ representa o final da string.
 */
