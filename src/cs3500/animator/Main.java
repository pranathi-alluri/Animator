package cs3500.animator;

import java.awt.*;
import java.io.FileNotFoundException;

import javax.swing.*;

import cs3500.animator.io.AnimationFileReader;
import cs3500.animator.model.AnimationBuilder;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.SimpleAnimationModel;

public class Main {
  public static void main(String[] args) {
    // interprets command line arguments and throws appropriate errors
    String in = null;
    String out = "System.out";
    String view = null;
    int speed = 1;
    for(int i = 0; i < args.length - 1; i++) {
      switch (args[i]) {
        case "-in":
          in = args[i+1];
          break;
        case "-out":
          out = args[i+1];
          break;
        case "-view":
          view = args[i+1];
          break;
        case "-speed":
          try {
            speed = Integer.parseInt(args[i+1]);
          } catch (NumberFormatException nfe) {
            Frame frame = new Frame();
            JOptionPane.showMessageDialog(frame, "Error: -speed must be an int");
            throw new IllegalArgumentException("-speed must be an int");
          }
          break;
      }
    }
    if(in == null) {
      Frame frame = new Frame();
      JOptionPane.showMessageDialog(frame, "Error: must specify input text file");
      throw new IllegalArgumentException("you must specify -in");
    } else if(view == null) {
      Frame frame = new Frame();
      JOptionPane.showMessageDialog(frame, "Error: must specify input text file");
      throw new IllegalArgumentException("you must specify -view");
    }

    // uses command line arguments to build the model
    AnimationBuilder builder = new AnimationBuilder();
    AnimationFileReader fileReader = new AnimationFileReader();
    AnimationModel model = new SimpleAnimationModel();
    try {
      model = fileReader.readFile(in, builder);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    // uses command line arguments to build the view

  }
}
