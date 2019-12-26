/**
 * 
 */
package com.nous.moviecatalogservice.resources;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.nous.moviecatalogservice.model.CatalogItem;
import com.nous.moviecatalogservice.model.Movie;
import com.nous.moviecatalogservice.model.Rating;

/**
 * @author sachins
 *
 */

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {


	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
		
		RestTemplate restTemplate = new RestTemplate();
		
		
		// get all rated movie Id's
		// for each movieId call movieInfo service and get detail
		// combine the result
		
		List<Rating> ratings = Arrays.asList(
				new Rating("1231", 4),
				new Rating("1232", 3)
				);
		
		return ratings.stream().map( rating -> {
			Movie movie = restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieId(), Movie.class);
			return new CatalogItem(movie.getName(), "Test", rating.getRating());
		})
		.collect(Collectors.toList());
		
		
				
			
		//return Collections.singletonList(new CatalogItem("Transformers", "Transformers test", 4));
		
	}
	
}
