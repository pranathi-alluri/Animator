import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

import cs3500.animator.model.AnimationBuilder;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.Keyframe;
import cs3500.animator.model.SimpleAnimationModel;
import cs3500.animator.model.SimpleKeyframe;
import cs3500.animator.model.SimpleOval;
import cs3500.animator.model.SimpleRectangle;

import static org.junit.Assert.assertEquals;

public class AnimationBuilderTest {

  private AnimationModel model;
  private AnimationBuilder builder;

  public AnimationBuilderTest() {
    this.model = new SimpleAnimationModel();
    this.builder = new AnimationBuilder(this.model);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    AnimationBuilder builder = new AnimationBuilder(null);
  }

  @Test
  public void testSetBounds() {
    this.builder.setBounds(10, 20);
    assertEquals(this.model.getHeight(), 20);
    assertEquals(this.model.getWidth(), 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullNameOval(){
    this.builder.addOval(null, 0, 0, 1, 1,1, 1,1, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullNameRectangle(){
    this.builder.addRectangle(null, 0, 0, 1, 1,1, 1,1, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullNameAddMove(){
    this.builder.addMove(null, 0, 0, 1, 1,1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullNameAddColor(){
    this.builder.addColorChange(null, 0, 0, 1, 1,1, 1, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullNameAddScale(){
    this.builder.addScaleToChange(null, 0, 0, 1, 1,1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalTimeNameOval(){
    this.builder.addOval("oval", 0, 0, 1, 1,1, 1,1, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalTimeRectangle(){
    this.builder.addRectangle("rectangle", 0, 0, 1, 1,1, 1,1, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalTimeAddMove(){
    this.builder.addMove("shape", 0, 0, 1, 1,1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalTimeAddColor(){
    this.builder.addColorChange("shape", 0, 0, 1, 1,1, 1, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalTimeAddScale(){
    this.builder.addScaleToChange("shape", 0, 0, 1, 1,1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMoveIllegalName() {
    this.builder.addRectangle("rect1", 10, 10, 20, 30, 0, (float) 0.1, 0, 10, 50);
    this.builder.addMove("oval1",10, 10, 20, 30,10, 30);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddColorChangeIllegalName() {
    this.builder.addRectangle("rect1", 10, 10, 20, 30, 0, (float) 0.1, 0, 10, 50);
    this.builder.addColorChange("oval1",10, 10, 20, 30,10, 30, 20, 20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddScaleToChangeIllegalName() {
    this.builder.addRectangle("rect1", 10, 10, 20, 30, 0, (float) 0.1, 0, 10, 50);
    this.builder.addScaleToChange("oval1",10, 10, 20, 30,10, 30);
  }

  @Test
  public void testAddOval() {
    Keyframe oval11 = new SimpleKeyframe(new SimpleOval("oval1", 10, 10, new Color(0, (float) 0.1, 0), 60, 40), 10);
    Keyframe oval12 = new SimpleKeyframe(new SimpleOval("oval1", 10, 10, new Color(0, (float) 0.1, 0), 60, 40), 50);
    ArrayList<Keyframe> oval1 = new ArrayList<>();
    oval1.add(oval11);
    oval1.add(oval12);
    this.builder.addOval("oval1", 10, 10, 20, 30, 0, (float) 0.1, 0, 10, 50);
    assertEquals(this.builder.build().getAllKeyframesOfShape("oval1"), oval1);
  }

  @Test
  public void testAddRect() {
    Keyframe rect11 = new SimpleKeyframe(new SimpleRectangle("rect1", 10, 10, new Color(0, (float) 0.1, 0), 30, 20), 10);
    Keyframe rect12 = new SimpleKeyframe(new SimpleRectangle("rect1", 10, 10, new Color(0, (float) 0.1, 0), 30, 20), 50);
    ArrayList<Keyframe> rect1 = new ArrayList<>();
    rect1.add(rect11);
    rect1.add(rect12);
    this.builder.addRectangle("rect1", 10, 10, 20, 30, 0, (float) 0.1, 0, 10, 50);
    assertEquals(this.builder.build().getAllKeyframesOfShape("rect1"), rect1);
  }

  @Test
  public void testAddMove() {
    Keyframe rect11 = new SimpleKeyframe(new SimpleRectangle("rect1", 10, 10, new Color(0,  0, (float) 0.1), 30, 20), 10);
    Keyframe rect12 = new SimpleKeyframe(new SimpleRectangle("rect1", 10, 10, new Color(0,  0, (float) 0.1), 30, 20), 20);
    Keyframe rect13 = new SimpleKeyframe(new SimpleRectangle("rect1", 20, 20, new Color(0,  0, (float) 0.1), 30, 20), 30);
    Keyframe rect14 = new SimpleKeyframe(new SimpleRectangle("rect1", 20, 20, new Color(0,  0, (float) 0.1), 30, 20), 50);
    ArrayList<Keyframe> rect1 = new ArrayList<>();
    rect1.add(rect11);
    rect1.add(rect12);
    rect1.add(rect13);
    rect1.add(rect14);
    this.builder.addRectangle("rect1", 10, 10, 20, 30, 0, 0, (float) 0.1, 10, 50);
    this.builder.addMove("rect1", 10,10,20,20,20,30);
    assertEquals(this.builder.build().getAllKeyframesOfShape("rect1"), rect1);
  }

  @Test
  public void testAddColorChange() {
    Keyframe rect11 = new SimpleKeyframe(new SimpleRectangle("rect1", 10, 10, new Color(0,  0, (float) 0.1), 30, 20), 10);
    Keyframe rect12 = new SimpleKeyframe(new SimpleRectangle("rect1", 10, 10, new Color(0,  0, (float) 0.1), 30, 20), 32);
    Keyframe rect13 = new SimpleKeyframe(new SimpleRectangle("rect1", 10, 10, new Color((float) 0.1,0, 0), 30, 20), 45);
    Keyframe rect14 = new SimpleKeyframe(new SimpleRectangle("rect1", 10, 10, new Color((float) 0.1,  0, 0), 30, 20), 50);
    ArrayList<Keyframe> rect1 = new ArrayList<>();
    rect1.add(rect11);
    rect1.add(rect12);
    rect1.add(rect13);
    rect1.add(rect14);
    this.builder.addRectangle("rect1", 10, 10, 20, 30, 0, 0, (float) 0.1, 10, 50);
    this.builder.addColorChange("rect1", 0, 0, (float) 0.1, (float) 0.1, 0, 0, 32, 45);
    assertEquals(this.builder.build().getAllKeyframesOfShape("rect1"), rect1);
  }

  @Test
  public void testAddScaleToChange() {
    Keyframe rect11 = new SimpleKeyframe(new SimpleRectangle("rect1", 10, 10, new Color(0,  0, (float) 0.1), 30, 20), 10);
    Keyframe rect12 = new SimpleKeyframe(new SimpleRectangle("rect1", 10, 10, new Color(0,  0, (float) 0.1), 30, 20), 15);
    Keyframe rect13 = new SimpleKeyframe(new SimpleRectangle("rect1", 10, 10, new Color(0,  0, (float) 0.1), 10, 10), 49);
    Keyframe rect14 = new SimpleKeyframe(new SimpleRectangle("rect1", 10, 10, new Color(0,  0, (float) 0.1), 10, 10), 50);
    ArrayList<Keyframe> rect1 = new ArrayList<>();
    rect1.add(rect11);
    rect1.add(rect12);
    rect1.add(rect13);
    rect1.add(rect14);
    this.builder.addRectangle("rect1", 10, 10, 20, 30, 0, 0, (float) 0.1, 10, 50);
    this.builder.addScaleToChange("rect1", 20, 30, 10, 10, 15, 49);
    assertEquals(this.builder.build().getAllKeyframesOfShape("rect1"), rect1);
  }
}
