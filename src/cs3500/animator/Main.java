package cs3500.animator;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.*;

import cs3500.animator.io.*;
import cs3500.animator.model.AnimationBuilder;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.SimpleAnimationModel;
import cs3500.animator.view.AnimationViewFactory;
import cs3500.animator.view.SimpleAnimationView;

public class Main {
  public static void main(String[] args) {
    // interprets command line arguments and throws appropriate errors
    String in = null;
    String out = "System.out";
    String viewType = null;
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
          viewType = args[i+1];
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
    } else if(viewType == null) {
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
      Frame frame = new Frame();
      JOptionPane.showMessageDialog(frame, "Error: was unable to find file");
      e.printStackTrace();
    }

    // uses command line arguments to build the view
    Appendable appendable = new StringBuilder();
    SimpleAnimationView view = AnimationViewFactory.getView(viewType, model, appendable, speed);
    try {
      view.makeVisible();
    } catch (IOException e) {
      Frame frame = new Frame();
      JOptionPane.showMessageDialog(frame, "Error: was unable to create view");
      e.printStackTrace();
    }

    // output the view to the appropriate location
    if(out.equals("System.out")) {
      System.out.println(appendable);
    } else {
      try {
        Files.writeString(Paths.get(out), appendable.toString());
      } catch (IOException e) {
        Frame frame = new Frame();
        JOptionPane.showMessageDialog(frame, "Error: was unable to save to -out");
        e.printStackTrace();
      }
    }
  }
}
