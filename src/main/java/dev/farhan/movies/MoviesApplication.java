package dev.farhan.movies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class MoviesApplication {
	public static void main(String[] args) {
		SpringApplication.run(MoviesApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				//  指定了应用程序的所有路径（/** 表示所有路径）。
				registry.addMapping("/**")
						// allowedOrigins("*") 表示允许任何来源的请求。也就是说，无论请求是从哪个域（Domain）来的，都可以访问。
						.allowedOrigins("*")
						// 表示允许所有的 HTTP 方法，比如 GET、POST、PUT、DELETE 等。
						.allowedMethods("*")
						//  表示允许所有的请求头（如 Content-Type、Authorization 等）
						.allowedHeaders("*")
						.allowCredentials(false).maxAge(3600);
			}
		};
	}
}