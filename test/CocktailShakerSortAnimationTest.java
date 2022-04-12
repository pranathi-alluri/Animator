import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import cs3500.animator.animations.CocktailShakerSortAnimation;

import static org.junit.Assert.assertEquals;

public class CocktailShakerSortAnimationTest {
  @Test
  public void testToString() {
    ArrayList<Integer> nums = new ArrayList<>(Arrays.asList(5, 2, 8, 12, 3, 1));
    CocktailShakerSortAnimation c = new CocktailShakerSortAnimation(nums);
    assertEquals("canvas 400 700\n" +
            "rectangle name bar5 min-x 50 min-y 400 width 50 height 250 color 0.5 0.5 0.5 from 1 to 600\n" +
            "rectangle name bar2 min-x 100 min-y 550 width 50 height 100 color 0.5 0.5 0.5 from 1 to 600\n" +
            "rectangle name bar8 min-x 150 min-y 250 width 50 height 400 color 0.5 0.5 0.5 from 1 to 600\n" +
            "rectangle name bar12 min-x 200 min-y 50 width 50 height 600 color 0.5 0.5 0.5 from 1 to 600\n" +
            "rectangle name bar3 min-x 250 min-y 500 width 50 height 150 color 0.5 0.5 0.5 from 1 to 600\n" +
            "rectangle name bar1 min-x 300 min-y 600 width 50 height 50 color 0.5 0.5 0.5 from 1 to 600\n" +
            "move name bar5 moveto 50 400 100 400 from 50 to 100\n" +
            "move name bar2 moveto 100 550 50 550 from 50 to 100\n" +
            "move name bar12 moveto 200 50 250 50 from 100 to 150\n" +
            "move name bar3 moveto 250 500 200 500 from 100 to 150\n" +
            "move name bar12 moveto 250 50 300 50 from 150 to 200\n" +
            "move name bar1 moveto 300 600 250 600 from 150 to 200\n" +
            "move name bar3 moveto 200 500 250 500 from 200 to 250\n" +
            "move name bar1 moveto 250 600 200 600 from 200 to 250\n" +
            "move name bar8 moveto 150 250 200 250 from 250 to 300\n" +
            "move name bar1 moveto 200 600 150 600 from 250 to 300\n" +
            "move name bar5 moveto 100 400 150 400 from 300 to 350\n" +
            "move name bar1 moveto 150 600 100 600 from 300 to 350\n" +
            "move name bar2 moveto 50 550 100 550 from 350 to 400\n" +
            "move name bar1 moveto 100 600 50 600 from 350 to 400\n" +
            "move name bar8 moveto 200 250 250 250 from 450 to 500\n" +
            "move name bar3 moveto 250 500 200 500 from 450 to 500\n" +
            "move name bar5 moveto 150 400 200 400 from 500 to 550\n" +
            "move name bar3 moveto 200 500 150 500 from 500 to 550\n", c.toString());
  }
}
