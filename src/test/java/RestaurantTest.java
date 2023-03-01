import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;


class RestaurantTest {
    Restaurant restaurant ;
    @BeforeEach
    public void setupRestaurant(){
        restaurant = new Restaurant();
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        Restaurant restaurantMockClass = Mockito.mock(Restaurant.class);
        LocalTime inBetweenTime = LocalTime.of(15,00,00,00);
        Mockito.when(restaurantMockClass.getCurrentTime()).thenReturn(inBetweenTime);
        Boolean checkRestaurantOpen = restaurant.isRestaurantOpen(restaurantMockClass);
        assertEquals(true,checkRestaurantOpen);
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        Restaurant restaurantMockClass = Mockito.mock(Restaurant.class);
        LocalTime outsideTime = LocalTime.of(23,30,00,00);
        Mockito.when(restaurantMockClass.getCurrentTime()).thenReturn(outsideTime);
        Boolean checkRestaurantOpen = restaurant.isRestaurantOpen(restaurantMockClass);
        assertEquals(false,checkRestaurantOpen);

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //<<<<<<<<<<<<<<<<<<<<<<<Calculate order value>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Test
    public void calculate_total_orderValue_to_388() throws itemNotFoundException {
        int totalOrderValue = restaurant.calculateOrderValue();
        assertEquals(388,totalOrderValue );
    }

}