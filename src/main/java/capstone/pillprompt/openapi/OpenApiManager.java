package capstone.pillprompt.openapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OpenApiManager {

    private final String BASE_URL = "http://apis.data.go.kr/B552657/ErmctInsttInfoInqireService";
//    private final String apiUri = "/getParmacyBassInfoInqire";
    private final String apiUri = "/getParmacyLcinfoInqire";
    private final String serviceKey = "?ServiceKey=5kbmcjRVJd8R6IClFUx%2BnneQj7dJ0fmwvk6a6Ad0mLGwx2P%2Fbd0usptDScTqxsbepAvvQOjlVpPp04rjoVJ2vQ%3D%3D";
    private final String _type = "&type=xml";
//    private final String pageNo = "&pageNo=110";
//    private final String numOfRows = "&numOfRows=10";
    private final String pageNo = "&WGS84_LON=127";
    private final String numOfRows = "&WGS84_LAT=37";

    public String makeUrl() {
        return BASE_URL +
                apiUri +
                serviceKey +
                _type +
                pageNo +
                numOfRows;
    }
}
