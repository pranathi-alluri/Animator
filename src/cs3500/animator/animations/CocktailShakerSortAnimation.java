package cs3500.animator.animations;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Creates animation to display how cocktail shaker sort works.
 */
public class CocktailShakerSortAnimation {

  private final ArrayList<Integer> nums;

  /**
   * Constructor for CocktailShakerSortAnimation. Input any list of Integer without repeats.
   * @param nums
   */
  public CocktailShakerSortAnimation(ArrayList<Integer> nums) {
    this.nums = new ArrayList<>(nums);
  }

  private ArrayList<Integer[]> sortNums() {
    Integer[] sortNums = nums.toArray(new Integer[0]);
    ArrayList<Integer[]> output = new ArrayList<>();

    boolean swapped = true;
    int start = 0;
    int end = sortNums.length;

    while (swapped == true) {
      swapped = false;
      output.add(sortNums.clone());

      for (int i = start; i < end - 1; ++i) {
        if (sortNums[i] > sortNums[i+1]) {
          int temp = sortNums[i];
          sortNums[i] = sortNums[i + 1];
          sortNums[i + 1] = temp;
          swapped = true;
          output.add(sortNums.clone());
        }
      }

      if (swapped == false)
        break;
      swapped = false;
      end = end - 1;

      for (int i = end - 1; i >= start; i--) {
        if (sortNums[i] > sortNums[i + 1]) {
          int temp = sortNums[i];
          sortNums[i] = sortNums[i + 1];
          sortNums[i + 1] = temp;
          swapped = true;
          output.add(sortNums.clone());
        }
      }

      start = start + 1;
    }
    return output;
  }

  @Override
  public String toString() {
    StringBuilder output = new StringBuilder();

    int barWidth = 50;
    int barHeight = 50; // pixels per magnitude int
    int boarder = 50;
    int moveTics = 50;
    ArrayList<Integer[]> allNums = this.sortNums();
    int length = allNums.get(0).length;
    int max = allNums.get(allNums.size() - 1)[allNums.get(0).length - 1];

    //setup
    output.append("canvas ").append(barWidth * allNums.get(0).length + 2 * boarder).append(" ").append(barHeight * max + boarder * 2).append("\n");
    Integer[] start = allNums.get(0);
    for(int n = 0; n < length; n++) {
      output.append("rectangle name bar").append(start[n]).append(" min-x ").append(boarder + n*barWidth).append(" min-y ").append(boarder + (max-start[n])*barHeight)
              .append(" width ").append(barWidth).append(" height ").append(start[n]*barHeight).append(" color 0.5 0.5 0.5 from 1 to ").append(moveTics*allNums.size()).append("\n");
    }


    //animation
    for(int i = 1; i < allNums.size(); i++) {
      Integer[] previousNums = allNums.get(i-1);
      Integer[] currentNums = allNums.get(i);
      for(int j = 0; j < length; j++) {
        if(!Objects.equals(previousNums[j], currentNums[j])) {
          output.append("move name bar").append(previousNums[j]).append(" moveto ").append(boarder + j*barWidth).append(" ").append(boarder + (max-previousNums[j])*barHeight)
                  .append(" ").append(boarder + (j+1)*barWidth).append(" ").append(boarder + (max-previousNums[j])*barHeight).append(" from ").append(i*moveTics).append(" to ").append((i+1)*moveTics).append("\n");
          output.append("move name bar").append(currentNums[j]).append(" moveto ").append(boarder + (j+1)*barWidth).append(" ").append(boarder + (max-currentNums[j])*barHeight)
                  .append(" ").append(boarder + j*barWidth).append(" ").append(boarder + (max-currentNums[j])*barHeight).append(" from ").append(i*moveTics).append(" to ").append((i+1)*moveTics).append("\n");
          break;
        }
      }
    }

    return output.toString();
  }
}
