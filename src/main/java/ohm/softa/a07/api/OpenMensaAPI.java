package ohm.softa.a07.api;

import ohm.softa.a07.model.Meal;
import retrofit2.Call;


import java.util.List;

public interface OpenMensaAPI {
	// TODO add method to get meals of a day
	// Mensa Kesslerplatz: 268
	// Mensa Insel Schütt: 265
	// Mensa Techfak Erlangen: 256
	// example request: GET /canteens/268/days/2023-11-21/meals
	Call<List<Meal>> getMeals(String date);
}
