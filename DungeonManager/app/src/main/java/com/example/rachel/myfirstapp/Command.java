package com.example.rachel.myfirstapp;

/**
 * Created by William Glanton on 4/15/2016.
 */
public interface Command {

    CharacterSheet execute(CharacterSheet cs);
    String getDescription();

}
