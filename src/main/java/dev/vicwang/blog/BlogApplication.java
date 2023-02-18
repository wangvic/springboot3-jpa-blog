package dev.vicwang.blog;

import dev.vicwang.blog.model.Post;
import dev.vicwang.blog.repository.PostRepository;
import dev.vicwang.blog.service.JsonPlaceholderService;
import dev.vicwang.blog.service.PostService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.List;

@SpringBootApplication
public class BlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(PostRepository postRepository, PostService postService) {
//		return args -> {
//			List<Post> posts= postService.loadPosts();
//			postRepository.saveAll(posts);
//		};
		return args -> {
			WebClient client = WebClient.builder().baseUrl("https://jsonplaceholder.typicode.com").build();
			HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client)).build();
			JsonPlaceholderService jsonPlaceholderService = factory.createClient(JsonPlaceholderService.class);

			List<Post> posts = jsonPlaceholderService.loadPosts();
			postRepository.saveAll(posts);
		};
	}

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
