package pluralsight.demo.pluralsightspringcloudm4secureservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
@EnableResourceServer
public class PluralsightSpringcloudM4SecureserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PluralsightSpringcloudM4SecureserviceApplication.class, args);
	}

	@RequestMapping("/tolldata")
	public List<TollUsage> gettollData() {
		TollUsage instance1 = new TollUsage("100", "station150", "B65GT1W", "2016-09-30T06");
		TollUsage instance2 = new TollUsage("101", "station119", "AHY673B", "2016-09-31T06");
		TollUsage instance3 = new TollUsage("102", "station150", "ZN2GP0", "2016-09-32T06");

		List<TollUsage> tolls = new ArrayList<>();
		tolls.add(instance1);
		tolls.add(instance2);
		tolls.add(instance3);

		return tolls;
	}

	public class TollUsage {

		public String Id;
		public String stationId;
		public String licensePlate;
		public String timestamp;

		public TollUsage() {
		}

		public TollUsage(String id, String stationId, String licensePlate, String timestamp) {
			Id = id;
			this.stationId = stationId;
			this.licensePlate = licensePlate;
			this.timestamp = timestamp;
		}
	}

}
