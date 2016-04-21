package com.example.rachel.myfirstapp;

/**
 * Created by William Glanton on 4/20/2016.
 */
public class DexModCmd extends StatModCmd {

    DexModCmd(String name, int mod){
        super(name, mod);
    }

    protected void ExecuteMod(CharacterSheet cs){
        cs.dex += getMod();
    }

    protected String getStatName(){
        return "dex";
    }

}
