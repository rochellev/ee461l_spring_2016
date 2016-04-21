package com.example.rachel.myfirstapp;

/**
 * Created by William Glanton on 4/20/2016.
 */
public class IntModCmd extends StatModCmd {

    IntModCmd(String name, int mod){
        super(name, mod);
    }

    protected void ExecuteMod(CharacterSheet cs){
        cs.inl += getMod();
    }

    protected String getStatName(){
        return "int";
    }

}
