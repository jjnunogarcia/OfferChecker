package com.android.jjnunogarcia.offerchecker.model.jsonparsing;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class InformationTest {
  private static final int    ID       = 0;
  private static final String NAME     = "name";
  private static final String COUNTRY  = "Germany";
  private static final String LANGUAGE = "de";
  private static final String URL      = "https://www.myurl.com/";
  private static final String CURRENCY = "eur";

  private Information information;

  @Before
  public void setUp() throws Exception {
    information = new Information();
    information.setAppId(ID);
    information.setAppName(NAME);
    information.setCountry(COUNTRY);
    information.setLanguage(LANGUAGE);
    information.setSupportUrl(URL);
    information.setVirtualCurrency(CURRENCY);
  }

  @Test
  public void testGetAppId() throws Exception {
    Assert.assertEquals(ID, information.getAppId());
  }

  @Test
  public void testGetAppName() throws Exception {
    Assert.assertEquals(NAME, information.getAppName());
  }

  @Test
  public void testGetVirtualCurrency() throws Exception {
    Assert.assertEquals(CURRENCY, information.getVirtualCurrency());
  }

  @Test
  public void testGetCountry() throws Exception {
    Assert.assertEquals(COUNTRY, information.getCountry());
  }

  @Test
  public void testGetLanguage() throws Exception {
    Assert.assertEquals(LANGUAGE, information.getLanguage());
  }

  @Test
  public void testGetSupportUrl() throws Exception {
    Assert.assertEquals(URL, information.getSupportUrl());
  }
}