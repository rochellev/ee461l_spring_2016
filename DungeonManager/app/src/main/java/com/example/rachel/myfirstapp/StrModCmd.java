package com.example.rachel.myfirstapp;

/**
 * Created by William Glanton on 4/17/2016.
 */
public class StrModCmd extends StatModCmd {

    StrModCmd(String name, int mod){
        super(name, mod);
    }

    protected void ExecuteMod(CharacterSheet cs){
        cs.str += getMod();
    }

    protected String getStatName(){
        return "str";
    }

}
