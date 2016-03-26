package com.example.rachel.myfirstapp;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Created by William Glanton on 3/24/2016.
 */
public class CharacterSheet {

    public enum SHEETS {
      SHEET1("CharSht1"), SHEET2("CharSht2"), SHEET3("CharSht3");

        private final String title;

        SHEETS(String s){
            this.title = s;
        }

        public String getTitle(){
            return title;
        }
    }
    private static final String PREFS_NAME = "CharacterSheet";
    private static final String DEFAULT_CHAR_NAME = "THISisNoTAcharACtERName";

    String name;
    String race;
    String cclass;
    int level;
    int str, dex, con, inl, wis, cha;
    int ac;
    int init;
    int spd;
    int hp;

    private void CharaterSheet(){
        name = "noface";
        race = "norace";
        cclass = "noclass";
        hp = 0;
        spd = 0;
        init = 0;
        ac = 0;
        str = 0;
        dex = 0;
        con = 0;
        inl = 0;
        wis = 0;
        cha = 0;
        level = 0;
    }

    public static CharacterSheet loadSheet(SharedPreferences set){
        CharacterSheet ret = new CharacterSheet();

        if(set.getString("cName", DEFAULT_CHAR_NAME) != DEFAULT_CHAR_NAME){
            ret.name = set.getString("cName", DEFAULT_CHAR_NAME);
            ret.race = set.getString("cRace", DEFAULT_CHAR_NAME);
            ret.cclass = set.getString("cClass", DEFAULT_CHAR_NAME);
            ret.level = set.getInt("cLevel", Integer.MIN_VALUE);
            ret.hp = set.getInt("cHp", Integer.MIN_VALUE);
            ret.init = set.getInt("cInit", Integer.MIN_VALUE);
            ret.spd = set.getInt("cSpd", Integer.MIN_VALUE);
            ret.ac = set.getInt("cAc", Integer.MIN_VALUE);
            ret.str = set.getInt("cStr", Integer.MIN_VALUE);
            ret.dex = set.getInt("cDex", Integer.MIN_VALUE);
            ret.con = set.getInt("cCon", Integer.MIN_VALUE);
            ret.inl = set.getInt("cInt", Integer.MIN_VALUE);
            ret.wis = set.getInt("cWis", Integer.MIN_VALUE);
            ret.cha = set.getInt("cCha", Integer.MIN_VALUE);
        }

        return ret;
    }

    public void saveSheet(SharedPreferences set){
        SharedPreferences.Editor editor = set.edit();
        editor.putString("cName", name);
        editor.putString("cRace", race);
        editor.putString("cClass", cclass);
        editor.putInt("cLevel", level);
        editor.putInt("cHp", hp);
        editor.putInt("cInit", init);
        editor.putInt("cSpd", spd);
        editor.putInt("cAc", ac);
        editor.putInt("cStr", str);
        editor.putInt("cDex", dex);
        editor.putInt("cCon", con);
        editor.putInt("cInt", inl);
        editor.putInt("cWis", wis);
        editor.putInt("cCha", cha);

        // Commit the edits!
        editor.commit();
    }
}
