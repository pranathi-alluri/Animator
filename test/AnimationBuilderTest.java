import org.junit.Test;

import cs3500.animator.model.AnimationBuilder;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.SimpleAnimationModel;

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
}
