package client;

import moteur.Coup;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import static org.mockito.Mockito.* ;
import org.mockito.Mock;

public class StratTest {

    @Mock
    Strategie stratClient;
    
    JSONArray deck;

    @Before
    public void setUp() throws JSONException {
        deck = new JSONArray("[{value:1},{value:4},{value:2}]");
    }

    @Test
    public void testStratMax() throws Exception {
        stratClient = new StratMax();

        assertEquals(1, stratClient.getCoup(0, deck).getNumeroCarte());
    }

    @Test
    public void testStratRandom() throws Exception {
        stratClient = new StratRandom();

        StratRandom stratRandomMock = org.mockito.Mockito.mock(StratRandom.class);
        when(stratRandomMock.getCoup(0, deck)).thenReturn(new Coup(0, 2));


        Coup coup = stratRandomMock.getCoup(0, deck);
        assertEquals(2, coup.getNumeroCarte());
    }
}