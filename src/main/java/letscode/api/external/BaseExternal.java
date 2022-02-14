package letscode.api.external;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

public class BaseExternal {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected static Object GET(String path, Class toConvert) {
		try {

			WebClient client = WebClient.builder().baseUrl(path)
					.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();

//			
//			ResponseEntity<?> response = client.get()
//				    .retrieve()
//				    .onStatus(status -> status.value() == 401, clientResponse -> Mono.empty())
//				    .toEntity(toConvert)
//				    .block();
//
//				// Manually check and handle the relevant status codes:
//				if (response.getStatusCodeValue() == 401) {
//				    // ...
//				} else {
//				    // ...
//				}
//			

			return client.get().retrieve().onStatus(status -> status.value() != 200, clientResponse -> Mono.empty()).bodyToMono(toConvert).block();
		} catch (Exception e) {
			return null;
		}
	}
}