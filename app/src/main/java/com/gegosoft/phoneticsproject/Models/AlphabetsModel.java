package com.gegosoft.phoneticsproject.Models;

public class AlphabetsModel {
    //
//    private static final int TYPE_ONE = 1;
//    private static final int TYPE_TWO = 2;
    String Alphabet;
    int Audio;

    public AlphabetsModel() {
    }

    public AlphabetsModel(String alphabet, int audio) {
        Alphabet = alphabet;
        Audio = audio;
    }

    public int getAudio() {
        return Audio;
    }

    public void setAudio(int audio) {
        Audio = audio;
    }

    public String getAlphabet() {
        return Alphabet;
    }
}