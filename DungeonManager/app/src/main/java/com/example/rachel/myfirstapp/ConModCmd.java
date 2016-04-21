package com.example.rachel.myfirstapp;

/**
 * Created by William Glanton on 4/20/2016.
 */
public class ConModCmd extends StatModCmd {

    ConModCmd(String name, int mod){
        super(name, mod);
    }

    protected void ExecuteMod(CharacterSheet cs){
        cs.con += getMod();
    }

    protected String getStatName(){
        return "con";
    }

}
