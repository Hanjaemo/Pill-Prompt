package capstone.pillprompt.openapi;

import capstone.pillprompt.dto.DrugStoreDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OpenApiController {

    private final OpenApiManager openApiManager;

    @GetMapping("/apitest")
    public String callApiHttp() {

        String json = "";

        try {
            String urlStr = openApiManager.makeUrl();
            log.info(urlStr);

            URL url = new URL(urlStr); // 문자열을 URL 객체로 변환
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection(); // URLConnection 객체를 생성
            // URLConnection은 추상 클래스이므로 구현체인 HttpURLConnection으로 형변환

            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

            json = xmlToJson(br.readLine());

            urlConnection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return json;
    }

    public String xmlToJson(String str) {

        String output = "";

        try {
            String xml = str;
            JSONObject jsonObject = XML.toJSONObject(xml);

            // 원하는 데이터 뽑기
            JSONObject response = (JSONObject) jsonObject.get("response");
            JSONObject body = (JSONObject) response.get("body");
            JSONObject items = (JSONObject) body.get("items");
            JSONArray itemArr = (JSONArray) items.get("item");

            ArrayList<DrugStoreDto> result = new ArrayList<>();
            for (Object o : itemArr) {
                JSONObject item = (JSONObject) o;
                result.add(makeDrugStoreDto(item));
            }

            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            Object json = mapper.readValue(itemArr.toString(), Object.class);
            output = mapper.writeValueAsString(json);
            log.info("output = {}", output);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return output;
    }

    private DrugStoreDto makeDrugStoreDto(JSONObject item) {
        return DrugStoreDto.builder()
                .id((String) item.get("hpid"))
                .name((String) item.get("dutyName"))
                .address((String) item.get("dutyAddr"))
                .telephone((String) item.get("dutyTel1"))
                // 영업시간 추가해야 함
                .lon(((BigDecimal) item.get("wgs84Lon")).doubleValue())
                .lat(((BigDecimal) item.get("wgs84Lat")).doubleValue())
                .build();
    }
}