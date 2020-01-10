package ca.qc.plachanc73.restws.config;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ThreadUserManagerUnitTest extends AbstractUnitTest {

	@Test
	public void testGetClientId() {
		ThreadUserManager.setClientId("clientId1");
		assertTrue("clientId1".equals(ThreadUserManager.getClientId()));

		ThreadUserManager.setClientId("clientId2");
		assertTrue("clientId2".equals(ThreadUserManager.getClientId()));

		ThreadUserManager.setClientId("clientId3");
		assertTrue("clientId3".equals(ThreadUserManager.getClientId()));
	}
}