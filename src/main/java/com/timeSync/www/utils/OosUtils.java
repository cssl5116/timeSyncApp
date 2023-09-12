package com.timeSync.www.utils;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;

/**
 * @author fishx
 * @version 1.0
 * @description: TODO
 * @date 2023/8/29 17:43
 */
@Slf4j
public class OosUtils {
  //设置需要操作的账号的AK、SK、存储空间
  private static final String ACCESS_KEY = "jwvJH5JFIUi_FGlY8Fc8EM8ga1JzOn-Rb5Ffxthg";
  private static final String SECRET_KEY = "8ubWecRiH4CtRTnzWp87n1cXuf9T51B6QEdyk6KA";
  private static final String bucketName = "wx-time-sync";

  //密钥
  private static final Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

  public static void upload(String filePath, String fileName) throws IOException {
    //构造一个带指定 Region 对象的配置类
    Configuration cfg = new Configuration(Region.region2());
    //创建上传对象
    UploadManager uploadManager = new UploadManager(cfg);
    try {
      //调用put方法上传
      Response res = uploadManager.put(filePath, fileName, auth.uploadToken(bucketName));
      //打印返回的信息
      log.debug("七牛云对象存储,响应内容: {}", res.bodyString());
    } catch (QiniuException e) {
      Response r = e.response;
      // 请求失败时打印的异常的信息
      try {
        //响应的文本信息
        log.error("请求失败: {}", r.bodyString());
      } catch (QiniuException e1) {
        //ignore
      }
    }
  }
}
