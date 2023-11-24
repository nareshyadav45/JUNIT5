package com.nt.mockito;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MockVsSpy {

	@Test
	public void testMockVsSpy() {
		List<String> listMock = Mockito.mock(ArrayList.class);
		List<String> listSpy = Mockito.spy(new ArrayList());
		//listMock.add("naresh");
		Mockito.when(listMock.size()).thenReturn(5);
		//listSpy.add("yadav");
		Mockito.when(listSpy.size()).thenReturn(10);
		
		
		System.out.println(listMock.size()+" "+listSpy.size());
	
	}
	
	
	
}
