package com.zfy.diabetes.utils;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeepSeekUtils {
    private static final String BASE_URL = "https://api.deepseek.com";
    private static final String API_KEY = "sk-3d72810c9b3f4136a7d1c1bad833c52c"; // 替换为你的API密钥


    public static String deepSeek(String patientInfo, String distType) {
        try {
            Long startTime = System.currentTimeMillis();
            // 构建请求体
            JSONObject requestBody = buildRequestBody(patientInfo, distType);

            // 创建HttpRequest实例
            HttpRequest request = HttpRequest.post(BASE_URL + "/chat/completions")
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + API_KEY)
                    .body(requestBody.toString());

            // 发送请求并获取响应
            HttpResponse response = request.execute();

            // 检查响应状态码
            if (response.isOk()) {
                log.info("Response status code: {}", response.getStatus());
                log.debug("Response body: {}", response.body());

                // 解析响应体
                JSONObject responseBody = new JSONObject(response.body());
                JSONArray choices = responseBody.getJSONArray("choices");
                JSONObject message = choices.getJSONObject(0).getJSONObject("message");
                String content = message.getStr("content");

//                // 解析content为JSON对象
//                JSONObject contentJson = new JSONObject(content);
//
//                // 构建返回的JSON对象
//                JSONObject result = new JSONObject();
//                result.set("question", contentJson.getStr("question"));
//                result.set("answer", contentJson.getStr("answer"));

                Long endTime = System.currentTimeMillis();
                log.info("deepSeek api call time: {}ms", endTime - startTime);
                return content;
            } else {
                log.error("Request failed with status code: {}", response.getStatus());
                log.error("Response body: {}", response.body());
                return "Request failed with status code: " + response.getStatus();
            }
        } catch (Exception e) {
            log.error("An error occurred while calling DeepSeek API", e);
            return "An error occurred while calling DeepSeek API: " + e.getMessage();
        }
    }
    private static JSONObject buildRequestBody(String patientInfo, String distType) {
        JSONObject requestBody = new JSONObject();
        requestBody.set("model", "deepseek-chat");
        requestBody.set("messages", new JSONObject[]{
                new JSONObject().set("role", "system").set("content", distType),
                new JSONObject().set("role", "user").set("content", patientInfo)
        });
        requestBody.set("stream", false);
        // 添加 response_format 参数
        JSONObject responseFormat = new JSONObject();
        responseFormat.set("type", "json_object");
        requestBody.set("response_format", responseFormat);
        return requestBody;
    }

    public static void main(String[] args) {
        String patientInfo = "男，23岁，软件开发工程师，疾病史暂无，身高170，体重70，民族汉";
        String system = "根据给出的患者信息进行健康评估，输出json格式数据，result为评估结果就是预测该患者的身体这状况怎么样，以后可能会出现什么风险疾病，一段话就行，plan为干预计划，对以后可能会出现的问题进行一个计划";
        String s = deepSeek(patientInfo, system);
        System.out.println("deepSeek api call time: {}ms");
        System.out.println(s);
    }

}
