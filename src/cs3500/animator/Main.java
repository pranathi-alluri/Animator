package cs3500.animator;

import java.awt.Frame;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

import cs3500.animator.Controller.Controller;
import cs3500.animator.Controller.SimpleInteractiveController;
import cs3500.animator.Controller.SimpleTextController;
import cs3500.animator.Controller.SimpleVisualController;
import cs3500.animator.io.AnimationFileReader;
import cs3500.animator.model.AnimationBuilder;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.HashmapAnimationModel;

/**
 * Allows users to create animations from the command line using tags that specify information
 * about the animation.
 * -in: name of .txt file that contains a description of the animation
 * -out: the name destination file, defaults to System.out if not specified
 * -view: choose between the three kinds of views, svg, text, and visual
 * -speed: integer tics per second of the speed of the animation
 */
public class Main {
  /**
   * main method to allow users to create animations.
   *
   * @param args -in: name of .txt file that contains a description of the animation
   *             -out: the name destination file, defaults to System.out if not specified
   *             -view: choose between the three kinds of views, svg, text, and visual
   *             -speed: integer tics per second of the speed of the animation
   */
  public static void main(String[] args) {
    // interprets command line arguments and throws appropriate errors
    String in = null;
    String out = "System.out";
    String viewType = null;
    int speed = 1;
    for (int i = 0; i < args.length - 1; i += 2) {
      switch (args[i]) {
        case "-in":
          in = args[i + 1];
          break;
        case "-out":
          out = args[i + 1];
          break;
        case "-view":
          viewType = args[i + 1];
          break;
        case "-speed":
          try {
            speed = Integer.parseInt(args[i + 1]);
          } catch (NumberFormatException nfe) {
            Frame frame = new Frame();
            JOptionPane.showMessageDialog(frame, "Error: -speed must be an int");
            throw new IllegalArgumentException("-speed must be an int");
          }
          break;
        default:
          Frame frame = new Frame();
          JOptionPane.showMessageDialog(frame, "Error: only use the tags" +
                  " -in, -out, -speed, and -view");
          throw new IllegalArgumentException("Error: only use the" +
                  " tags -in, -out, -speed, and -view");
      }
    }
    if (in == null) {
      Frame frame = new Frame();
      JOptionPane.showMessageDialog(frame, "Error: must specify input text file");
      throw new IllegalArgumentException("you must specify -in");
    } else if (viewType == null) {
      Frame frame = new Frame();
      JOptionPane.showMessageDialog(frame, "Error: must specify input text file");
      throw new IllegalArgumentException("you must specify -view");
    }

    // uses command line arguments to build the model
    AnimationBuilder builder = new AnimationBuilder();
    AnimationFileReader fileReader = new AnimationFileReader();
    AnimationModel model = new HashmapAnimationModel();
    try {
      model = fileReader.readFile(in, builder);
    } catch (FileNotFoundException e) {
      Frame frame = new Frame();
      JOptionPane.showMessageDialog(frame, "Error: was unable to find file");
      e.printStackTrace();
    }

    // uses command line arguments to build the view
    Controller controller = null;
    Appendable appendable = new StringBuilder();
    if (viewType.equals("text") || viewType.equals("svg")){
      controller = new SimpleTextController(model, speed, appendable, viewType);
    } else if (viewType.equals("visual")){
      controller = new SimpleVisualController(model, speed, viewType);
    } else if (viewType.equals("interactive")) {
      controller = new SimpleInteractiveController(model, speed, viewType);
    }
    controller.start();


    // output the view to the appropriate location
    if (out.equals("System.out")) {
      System.out.println(appendable);
    } else {
      String extension = ".txt";
      if (viewType.equals("svg")) {
        extension = ".svg";
      }
      try {
        Files.writeString(Paths.get(out + extension), appendable.toString());
      } catch (IOException e) {
        Frame frame = new Frame();
        JOptionPane.showMessageDialog(frame, "Error: was unable to save to -out");
        e.printStackTrace();
      }
    }
  }
}