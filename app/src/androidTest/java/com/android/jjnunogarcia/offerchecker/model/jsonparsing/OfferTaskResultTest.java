package com.android.jjnunogarcia.offerchecker.model.jsonparsing;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class OfferTaskResultTest {
  private static final String CODE    = "code";
  private static final int    COUNT   = 1;
  private static final String MESSAGE = "message";
  private static final int    PAGES   = 10;

  private OfferTaskResult offerTaskResult;

  @Before
  public void setUp() throws Exception {
    offerTaskResult = new OfferTaskResult();
    offerTaskResult.setCode(CODE);
    offerTaskResult.setCount(COUNT);
    offerTaskResult.setInformation(new Information());
    offerTaskResult.setMessage(MESSAGE);
    offerTaskResult.setOffers(new ArrayList<Offer>());
    offerTaskResult.setPages(PAGES);
  }

  @Test
  public void testGetCode() throws Exception {
    Assert.assertEquals(CODE, offerTaskResult.getCode());
  }

  @Test
  public void testGetMessage() throws Exception {
    Assert.assertEquals(MESSAGE, offerTaskResult.getMessage());
  }

  @Test
  public void testGetCount() throws Exception {
    Assert.assertEquals(COUNT, offerTaskResult.getCount());
  }

  @Test
  public void testGetPages() throws Exception {
    Assert.assertEquals(PAGES, offerTaskResult.getPages());
  }

  @Test
  public void testGetInformation() throws Exception {
    Assert.assertNotNull(offerTaskResult.getInformation());
  }

  @Test
  public void testGetOffers() throws Exception {
    Assert.assertNotNull(offerTaskResult.getOffers());
  }
}