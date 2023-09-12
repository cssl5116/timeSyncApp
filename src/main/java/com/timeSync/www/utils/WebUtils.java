package com.timeSync.www.utils;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.aliyun.tea.TeaException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author fishx
 * @version 1.0
 * @description: web工具类
 * @date 2023/8/29 16:33
 */
@Slf4j
public class WebUtils {
  /**
   * @description: 文件上传
   * @param: MultipartFile, realPath--真实路径
   * @return: 文件名+后缀;如user.png
   * @author fishx
   * @date: 2023/8/30 17:21
   */
  public static String uploadFile(MultipartFile file, String realPath) throws IOException {
    String originalFilename = file.getOriginalFilename();
    log.info("realPath = {}, originalFilename={}", realPath, originalFilename);
    if (StrUtil.isNotBlank(originalFilename)) {
      String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
      String name = RandomUtil.randomString(8);

      File realFile = new File(realPath);
      if (!realFile.exists()) realFile.mkdirs();

      file.transferTo(new File(realPath + name + suffix));
      return name + suffix;
    }
    return "";
  }

  /**
   * @description: java计算工作日（包含法定节假日和调休）
   * @author fishx
   * @date: 2023/8/30 17:23
   */
  public static Map<String, Set<String>> computeWorkingDay() {
    Set<String> holiday_list = new LinkedHashSet<>();
    Set<String> special_workday_list = new LinkedHashSet<>();

    HashMap<String, Object> map = new HashMap<>();
    map.put("key", "73df66dc461d578f4706f645c4e8a9f4");
    map.put("type", 1);

    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    map.put("date", currentYear);
    String url = "https://apis.tianapi.com/jiejiari/index";
    String response = HttpUtil.get(url, map);
    JSONObject resObj = JSONUtil.parseObj(response);
    System.out.println("resObj = " + resObj);
    if (200 == resObj.get("code", int.class)) {
      JSONObject result = resObj.get("result", JSONObject.class);
      JSONArray list = result.get("list", JSONArray.class);
      System.out.println("list = " + list);
      for (Object listObj : list) {
        JSONObject obj = (JSONObject) listObj;
        String vacation = obj.getStr("vacation");
        holiday_list.addAll(Arrays.stream(vacation.split("\\|")).collect(Collectors.toSet()));
        String remark = obj.getStr("remark");
        if (StrUtil.isNotBlank(remark)) {
          special_workday_list.addAll(Arrays.stream(remark.split("\\|")).collect(Collectors.toSet()));
        }
      }
    }
    Map<String, Set<String>> hashMap = new HashMap<>();
    hashMap.put("holiday_list", holiday_list);
    hashMap.put("special_workday_list", special_workday_list);
    return hashMap;
  }

  public static com.aliyun.dysmsapi20170525.Client createClient() throws Exception {
    com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
        // 必填，您的 AccessKey ID
        .setAccessKeyId("LTAI5t6stEf6xLj1of7PAuUB")
        // 必填，您的 AccessKey Secret
        .setAccessKeySecret("4VrjlXPFj0AlpJWOoipbY3mNQvaktQ");
    // Endpoint 请参考 https://api.aliyun.com/product/Dysmsapi
    config.endpoint = "dysmsapi.aliyuncs.com";
    return new com.aliyun.dysmsapi20170525.Client(config);
  }

  public static String sms(String phone) throws Exception {
    String randomNum = RandomUtil.randomNumbers(6);
    com.aliyun.dysmsapi20170525.Client client = WebUtils.createClient();
    com.aliyun.dysmsapi20170525.models.SendSmsRequest sendSmsRequest = new com.aliyun.dysmsapi20170525.models.SendSmsRequest()
        .setPhoneNumbers(phone)
        .setSignName("时刻在线协同办公")
        .setTemplateCode("SMS_463225963")
        .setTemplateParam("{\"code\": \"" + randomNum + "\"}");
    com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
    try {
      // 复制代码运行请自行打印 API 的返回值
      client.sendSmsWithOptions(sendSmsRequest, runtime);
    } catch (TeaException error) {
      // 如有需要，请打印 error
      com.aliyun.teautil.Common.assertAsString(error.message);
    } catch (Exception _error) {
      TeaException error = new TeaException(_error.getMessage(), _error);
      // 如有需要，请打印 error
      com.aliyun.teautil.Common.assertAsString(error.message);
    }
    return randomNum;
  }
}
