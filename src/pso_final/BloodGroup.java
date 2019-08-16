/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pso_final;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author aravi
 */
public enum BloodGroup {
    
       APOSITIVE,
    ANEGETIVE,
    BPOSITIVE,
    BNEGATIVE,
    OPOSITIVE,
    ONEGATIVE,
    ABPOSITIVE,
    ABNEGATIVE;
    
  private static final List<BloodGroup> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
  private static final int SIZE = VALUES.size();
  private static final Random RANDOM = new Random();
  
  public static BloodGroup randomBloodgroup()  {
    return VALUES.get(RANDOM.nextInt(SIZE));
  }
}
