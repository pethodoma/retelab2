package hu.bme.mit.train.sensor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hu.bme.mit.train.interfaces.*;




import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TrainSensorTest {

    /* 
    @Before
    public void before() {
        // TODO Add initializations
    }

    @Test
    public void tickTest() {
        
        TrainSensorImpl sensor = new TrainSensorImpl(null, null);
        sensor.tick("13:30", 3, 8);
        assertEquals(1,sensor.getTachSize());
    }
    */

    TrainController mockController;
    TrainUser mockUser;
    TrainSensorImpl sensor;
    @Before
    public void init()
    {
        mockController = mock(TrainController.class);
        when(mockController.getReferenceSpeed()).thenReturn(50);
        mockUser = mock(TrainUser.class);
        sensor = new TrainSensorImpl(mockController, mockUser);
        
    }

   @Test
   public void alarm_Unalarmed_setNegative()
   {
        sensor.overrideSpeedLimit(-1);    
        verify(mockUser, times(1)).setAlarmState(true);
   }

   @Test
   public void alarm_Unalarmed_setOver500()
   {
        sensor.overrideSpeedLimit(501);
        verify(mockUser, times(1)).setAlarmState(true);
   }
   
   @Test
   public void alarm_Unalarmed_setGoodValue()
   {
        sensor.overrideSpeedLimit(30);
        verify(mockUser, times(0)).setAlarmState(true);
   }

   @Test
   public void alam_Unalarmed_moreThan50Percent()
   {    
        sensor.overrideSpeedLimit(150);
        verify(mockUser, times(1)).setAlarmState(true);
   }



}
