package com.android.jjnunogarcia.offerchecker.model.jsonparsing;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class TimeToPayoutTest {
  private static final int    AMOUNT   = 15;
  private static final String READABLE = "readable";

  private TimeToPayout timeToPayout;

  @Before
  public void setUp() throws Exception {
    timeToPayout = new TimeToPayout();
    timeToPayout.setAmount(AMOUNT);
    timeToPayout.setReadable(READABLE);
  }

  @Test
  public void testGetAmount() throws Exception {
    Assert.assertEquals(AMOUNT, timeToPayout.getAmount());
  }

  @Test
  public void testGetReadable() throws Exception {
    Assert.assertEquals(READABLE, timeToPayout.getReadable());
  }
}