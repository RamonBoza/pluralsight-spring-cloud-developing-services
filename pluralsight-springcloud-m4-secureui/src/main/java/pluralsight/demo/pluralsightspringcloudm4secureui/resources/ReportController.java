package pluralsight.demo.pluralsightspringcloudm4secureui.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Primary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@EnableOAuth2Sso
public class ReportController extends WebSecurityConfigurerAdapter {

    @Autowired
    private OAuth2ClientContext clientContext;

    @Autowired
    private OAuth2RestTemplate oAuth2RestTemplate;

    @RequestMapping("/")
    public String loadHome() {
        return "home";
    }

    @RequestMapping("/reports")
    public String loadReports(Model model) {
        OAuth2AccessToken token = clientContext.getAccessToken();
        System.out.println("Token: " + token.getValue());

        ResponseEntity<ArrayList<TollUsage>> tolls = oAuth2RestTemplate.exchange("http://localhost:9002/services/tolldata", HttpMethod.GET, null, new ParameterizedTypeReference<ArrayList<TollUsage>>() {});

        model.addAttribute("tolls", tolls.getBody());

        return "reports";
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/login**")
                .permitAll()
                .anyRequest()
                .authenticated();
    }

    public static class TollUsage {

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

