package client;

import moteur.Carte;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import java.util.ArrayList;
import java.util.Random;

import static org.mockito.Mockito.* ;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class StratTest {

    public String adresse="127.0.0.1";
    public int port=10101;
    public int id=1;

    @Mock Client j1; Strategie stratClient;

    @Before
    public void setUp(){
        j1=new Client(id,adresse,port);
    }

    @Test
    public void testStratMax() throws Exception {
        stratClient=new StratMax();
        Field f2=Client.class.getDeclaredField("stratClient");
        f2.setAccessible(true);
        f2.set(j1,stratClient);

        ArrayList<Carte> deck = new ArrayList<Carte>(5);
        int carteN = -1, carteValue = -1;

        for(byte i = 0; i<5; i++)
            deck.add(new Carte(i));

        for(int i = 0; i<5; i++)
        {
            int value = deck.get(i).getValue();
            if(value > carteValue){
                carteValue = value;
                carteN = i;
              }
        }
        assertEquals(4, carteValue);
    }

    @Test
    public void testStratRandom() throws Exception {
        stratClient=new StratRandom();
        Field f2=Client.class.getDeclaredField("stratClient");
        f2.setAccessible(true);
        f2.set(j1,stratClient);

        ArrayList<Carte> deck = new ArrayList<Carte>(5);
        Random randomNumberMock = org.mockito.Mockito.mock(Random.class);

        for(byte i = 0; i<5; i++)
        {
            deck.add(new Carte(i));
        }

        when(randomNumberMock.nextInt(deck.size())).thenReturn(3);

        int idCarte=randomNumberMock.nextInt(deck.size());

        assertEquals(3, idCarte);
    }
}