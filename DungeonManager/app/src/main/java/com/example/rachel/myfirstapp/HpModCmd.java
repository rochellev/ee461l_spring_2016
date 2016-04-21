package com.example.rachel.myfirstapp;

/**
 * Created by William Glanton on 4/20/2016.
 */
public class HpModCmd extends StatModCmd {

    HpModCmd(String name, int mod){
        super(name, mod);
    }

    protected void ExecuteMod(CharacterSheet cs){
        cs.hp += getMod();
    }

    protected String getStatName(){
        return "hp";
    }

    public String getDescription(){
        String ret = getName() + " has ";
        if(getMod() > 0){
            ret += "replenished " + getMod() + " points of health";
        }else{
            ret += "taken " + (getMod() * -1) + " points of damage";
        }
        return ret;
    }

}
