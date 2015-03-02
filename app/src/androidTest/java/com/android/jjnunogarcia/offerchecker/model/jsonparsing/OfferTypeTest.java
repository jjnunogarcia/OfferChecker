package com.android.jjnunogarcia.offerchecker.model.jsonparsing;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class OfferTypeTest {
  private static final int    ID       = 0;
  private static final String READABLE = "readable";

  private OfferType offerType;

  @Before
  public void setUp() throws Exception {
    offerType = new OfferType();
    offerType.setOfferTypeId(ID);
    offerType.setReadable(READABLE);
  }

  @Test
  public void testGetOfferTypeId() throws Exception {
    Assert.assertEquals(ID, offerType.getOfferTypeId());
  }

  @Test
  public void testGetReadable() throws Exception {
    Assert.assertEquals(READABLE, offerType.getReadable());
  }
}