package ohm.softa.a07.tests;

import ohm.softa.a07.api.OpenMensaAPI;
import ohm.softa.a07.model.Meal;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.util.datetime.DatePrinter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OpenMensaAPITests {

	private static final Logger logger = LogManager.getLogger(OpenMensaAPITests.class);
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

	private OpenMensaAPI openMensaAPI;

	@BeforeEach
	void setup() {

		// use this to intercept all requests and output them to the logging facilities
		HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
		loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

		OkHttpClient client = new OkHttpClient.Builder()
			.addInterceptor(loggingInterceptor)
			.build();

		Retrofit retrofit = new Retrofit.Builder()
			.addConverterFactory(GsonConverterFactory.create())
			.baseUrl("https://openmensa.org/api/v2/")
			.client(client)
			.build();
		openMensaAPI = retrofit.create(OpenMensaAPI.class);
	}

	@Test
	void testGetMeals() throws IOException {
		// TODO prepare call
		Call<List<Meal>> mealsCall = openMensaAPI.getMeals(sdf.format(new Date()));
		// TODO execute the call synchronously
		var mealsResponse = mealsCall.execute();
		// TODO unwrap the body
		List<Meal> meals = mealsResponse.body();

		assertNotNull(meals);
		assertNotEquals(0, meals.size());

		for(Meal m : meals){
			logger.info(m.toString());
		}
	}

}
