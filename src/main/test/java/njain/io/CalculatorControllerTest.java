package njain.io;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

public class CalculatorControllerTest {
  @Rule
  public final WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort());
  public CalculatorController controller = Mockito.mock(CalculatorController.class);

  @Before
  public void setup() {
//    controller = new CalculatorController();
  }

  @Test
  public void solvePathTest() {
//    stubFor(post(urlPathMatching("/calculator/3/+/5"))
//        .willReturn(aResponse()
//            .withStatus(HttpURLConnection.HTTP_OK)
//        .withBody("Hurray...")));
//    String s = controller.solvePath(3, "+", 5);
//    System.out.println(s);
  }

}
