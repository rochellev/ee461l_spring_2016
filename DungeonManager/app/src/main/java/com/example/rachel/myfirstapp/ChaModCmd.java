package com.example.rachel.myfirstapp;

/**
 * Created by William Glanton on 4/20/2016.
 */
public class ChaModCmd extends StatModCmd {

    ChaModCmd(String name, int mod){
        super(name, mod);
    }

    protected void ExecuteMod(CharacterSheet cs){
        cs.cha += getMod();
    }

    protected String getStatName(){
        return "cha";
    }

}
