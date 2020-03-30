package com.gegosoft.phoneticsproject.Models;

public class SmallAlphabetsModel {
  String SmallAlphabet;
  int SmallAudio;

  public SmallAlphabetsModel() {
  }

  public SmallAlphabetsModel(String smallAlphabet, int smallAudio) {
    SmallAlphabet = smallAlphabet;
    SmallAudio = smallAudio;
  }

  public String getSmallAlphabet() {
    return SmallAlphabet;
  }

  public void setSmallAlphabet(String smallAlphabet) {
    SmallAlphabet = smallAlphabet;
  }

  public int getSmallAudio() {
    return SmallAudio;
  }

  public void setSmallAudio(int smallAudio) {
    SmallAudio = smallAudio;
  }
}