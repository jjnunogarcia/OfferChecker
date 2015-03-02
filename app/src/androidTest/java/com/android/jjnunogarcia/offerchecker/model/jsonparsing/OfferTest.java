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
public class OfferTest {
  private static final int    ID      = 0;
  private static final String LINK    = "https://www.thelink.com/";
  private static final int    PAYOUT  = 23;
  private static final String STOREID = "2";
  private static final String TEASER  = "teaser";
  private static final String TITLE   = "title";
  private static final String ACTIONS = "actions";

  private Offer offer;

  @Before
  public void setUp() throws Exception {
    offer = new Offer();
    offer.setOfferId(ID);
    offer.setLink(LINK);
    offer.setOfferTypes(new ArrayList<OfferType>());
    offer.setPayout(PAYOUT);
    offer.setRequiredActions(ACTIONS);
    offer.setStoreId(STOREID);
    offer.setTeaser(TEASER);
    offer.setThumbnail(new Thumbnail());
    offer.setTitle(TITLE);
    offer.setTimeToPayout(new TimeToPayout());
  }

  @Test
  public void testGetTitle() throws Exception {
    Assert.assertEquals(TITLE, offer.getTitle());
  }

  @Test
  public void testGetOfferId() throws Exception {
    Assert.assertEquals(ID, offer.getOfferId());

  }

  @Test
  public void testGetTeaser() throws Exception {
    Assert.assertEquals(TEASER, offer.getTeaser());
  }

  @Test
  public void testGetRequiredActions() throws Exception {
    Assert.assertEquals(ACTIONS, offer.getRequiredActions());
  }

  @Test
  public void testGetLink() throws Exception {
    Assert.assertEquals(LINK, offer.getLink());
  }

  @Test
  public void testGetOfferTypes() throws Exception {
    Assert.assertNotNull(offer.getOfferTypes());
  }

  @Test
  public void testGetPayout() throws Exception {
    Assert.assertEquals(PAYOUT, offer.getPayout());
  }

  @Test
  public void testGetTimeToPayout() throws Exception {
    Assert.assertNotNull(offer.getTimeToPayout());
  }

  @Test
  public void testGetThumbnail() throws Exception {
    Assert.assertNotNull(offer.getThumbnail());
  }

  @Test
  public void testGetStoreId() throws Exception {
    Assert.assertEquals(STOREID, offer.getStoreId());
  }
}