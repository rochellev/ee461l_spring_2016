package com.example.rachel.myfirstapp;

/**
 * Created by William Glanton on 4/20/2016.
 */
public abstract class StatModCmd implements Command{

    private String name;
    private int mod;

    protected StatModCmd(String name, int mod){
        this.name = name;
        this.mod = mod;
    }

    protected String getName(){return name;}
    protected int getMod(){
        return mod;
    }

    abstract protected void ExecuteMod(CharacterSheet cs);
    abstract protected String getStatName();

    @Override
    public CharacterSheet execute(CharacterSheet cs) {
        if(!cs.name.equals(name)){
            return cs;
        }
        CharacterSheet ret = new CharacterSheet(cs);
        ExecuteMod(cs);
        return ret;
    }

    @Override
    public String getDescription() {
        return name + "has received " + mod + " " + getStatName();
    }

}
