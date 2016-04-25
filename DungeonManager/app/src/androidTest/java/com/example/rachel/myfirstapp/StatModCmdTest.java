package com.example.rachel.myfirstapp;

import junit.framework.TestCase;
import junit.framework.Assert.*;

/**
 * Created by William Glanton on 4/24/2016.eg'apsg's'
 *
 * g
 */
public class StatModCmdTest extends TestCase {
    private static final String DEFAULT_CHAR_NAME = "THISisNoTAcharACtERName";
    CharacterSheet cs;
    StatModCmd tst;

    public void testExecuteMod() throws Exception {
        cs = new CharacterSheet();
        tst = new HpModCmd(DEFAULT_CHAR_NAME, 1);
        int p = cs.hp;
        tst.execute(cs);
        assertEquals(1, cs.hp - p);

        tst = new HpModCmd(DEFAULT_CHAR_NAME, -1);
        p = cs.hp;
        tst.execute(cs);
        assertEquals(-1, cs.hp - p);

        tst = new StrModCmd(DEFAULT_CHAR_NAME, 1);
        p = cs.str;
        tst.execute(cs);
        assertEquals(1, cs.str - p);

        tst = new StrModCmd(DEFAULT_CHAR_NAME, -1);
        p = cs.str;
        tst.execute(cs);
        assertEquals(-1, cs.str - p);

        tst = new StrModCmd(DEFAULT_CHAR_NAME, 1);
        p = cs.dex;
        tst.execute(cs);
        assertEquals(1, cs.dex - p);

        tst = new StrModCmd(DEFAULT_CHAR_NAME, -1);
        p = cs.dex;
        tst.execute(cs);
        assertEquals(-1, cs.dex - p);

        tst = new StrModCmd(DEFAULT_CHAR_NAME, 1);
        p = cs.con;
        tst.execute(cs);
        assertEquals(1, cs.con - p);

        tst = new StrModCmd(DEFAULT_CHAR_NAME, -1);
        p = cs.con;
        tst.execute(cs);
        assertEquals(-1, cs.con - p);

        tst = new StrModCmd(DEFAULT_CHAR_NAME, 1);
        p = cs.wis;
        tst.execute(cs);
        assertEquals(1, cs.wis - p);

        tst = new StrModCmd(DEFAULT_CHAR_NAME, -1);
        p = cs.wis;
        tst.execute(cs);
        assertEquals(-1, cs.wis - p);

        tst = new StrModCmd(DEFAULT_CHAR_NAME, 1);
        p = cs.inl;
        tst.execute(cs);
        assertEquals(1, cs.inl - p);

        tst = new StrModCmd(DEFAULT_CHAR_NAME, -1);
        p = cs.inl;
        tst.execute(cs);
        assertEquals(-1, cs.inl - p);

        tst = new StrModCmd(DEFAULT_CHAR_NAME, 1);
        p = cs.cha;
        tst.execute(cs);
        assertEquals(1, cs.cha - p);

        tst = new StrModCmd(DEFAULT_CHAR_NAME, -1);
        p = cs.cha;
        tst.execute(cs);
        assertEquals(-1, cs.cha - p);
    }

    public void testGetDescription() throws Exception {
        String strS = "str";
        String dexS = "dex";
        String conS = "con";
        String wisS = "wis";
        String intS = "int";
        String chaS = "cha";

        tst = new HpModCmd(DEFAULT_CHAR_NAME, 1);
        assertEquals(tst.getDescription(), DEFAULT_CHAR_NAME + " has replenished 1 points of health");

        tst = new HpModCmd(DEFAULT_CHAR_NAME, -1);
        assertEquals(tst.getDescription(), DEFAULT_CHAR_NAME + " has taken 1 points of damage");

        tst = new StrModCmd(DEFAULT_CHAR_NAME, 1);
        assertEquals(tst.getDescription(), DEFAULT_CHAR_NAME + " has received 1 " + strS);

        tst = new DexModCmd(DEFAULT_CHAR_NAME, 1);
        assertEquals(tst.getDescription(), DEFAULT_CHAR_NAME + " has received 1 " + dexS);

        tst = new ConModCmd(DEFAULT_CHAR_NAME, 1);
        assertEquals(tst.getDescription(), DEFAULT_CHAR_NAME + " has received 1 " + conS);

        tst = new IntModCmd(DEFAULT_CHAR_NAME, 1);
        assertEquals(tst.getDescription(), DEFAULT_CHAR_NAME + " has received 1 " + intS);

        tst = new WisModCmd(DEFAULT_CHAR_NAME, 1);
        assertEquals(tst.getDescription(), DEFAULT_CHAR_NAME + " has received 1 " + wisS);

        tst = new ChaModCmd(DEFAULT_CHAR_NAME, 1);
        assertEquals(tst.getDescription(), DEFAULT_CHAR_NAME + " has received 1 " + chaS);

    }
}