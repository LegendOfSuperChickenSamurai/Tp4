package test;


import org.junit.Assert;
import org.junit.Test;

import client.Client;

public class TestClient
{
	
	Client client1;
	Client client2;
	Client client3;
	
	@Test
	public void testGetUsernameInClientClass()
	{
		String foo = "foo";
		this.client1 = new Client(foo, "1.1.1.1", 4444);
		Assert.assertEquals(foo, this.client1.getUsername());
	}
	
	@Test
	public void testGetIpInClientClass()
	{
		String localhost = "localhost";
		this.client2 = new Client("foo", localhost, 4444);
		Assert.assertEquals(localhost, this.client2.getIp());
	}
	
	@Test
	public void testGetPortInClientClass()
	{
		int port = 4444;
		this.client3 = new Client("foo", "1.1.1.1", port);
		Assert.assertEquals(port, this.client3.getPort());
	}

}
