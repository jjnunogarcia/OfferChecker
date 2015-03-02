package com.android.jjnunogarcia.offerchecker.model.jsonparsing;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class ThumbnailTest {
  private static final String LOWRES  = "lowres";
  private static final String HIGHRES = "highres";

  private Thumbnail thumbnail;

  @Before
  public void setUp() throws Exception {
    thumbnail = new Thumbnail();
    thumbnail.setLowResolution(LOWRES);
    thumbnail.setHighResolution(HIGHRES);
  }

  @Test
  public void testGetLowResolution() throws Exception {
    Assert.assertEquals(LOWRES, thumbnail.getLowResolution());
  }

  @Test
  public void testGetHighResolution() throws Exception {
    Assert.assertEquals(HIGHRES, thumbnail.getHighResolution());
  }
}