package com.example.rachel.myfirstapp;

/**
 * Created by William Glanton on 4/20/2016.
 */
public class WisModCmd extends StatModCmd {

    WisModCmd(String name, int mod){
        super(name, mod);
    }

    protected void ExecuteMod(CharacterSheet cs){
        cs.wis += getMod();
    }

    protected String getStatName(){
        return "wis";
    }

}
