package com.company.project.common.Login;

import com.company.project.common.pojo.TokenPojo;
import com.company.project.common.util.Base64Util;
import com.company.project.common.util.MD5Util;
import com.company.project.construction.pojo.ConstructionConfigInfo;
import com.company.project.core.pojo.ApiResult;
import com.company.project.core.util.NewActivitiUtil;
import com.company.project.foundation.core.Result;
import com.company.project.foundation.core.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static java.lang.Long.parseLong;

/**
 * Created by ZouWeizheng on 2017-12-07.
 */
@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    ConstructionConfigInfo constructionConfigInfo;

    @Autowired
    TokenPojo tokenPojo;

    @GetMapping("/encryptDesToken")
    public Result desToken(@RequestParam String userId, String password, @RequestAttribute String tid, HttpServletRequest request){

        ApiResult apiResult = NewActivitiUtil.verifyUser(constructionConfigInfo.getActivitiUrl(),userId,password,tid);
        if (!(0==apiResult.getErrcode())) {
            return ResultGenerator.genFailResult("账号或密码错误");
        }
        String salt = tokenPojo.getSalt();
        long currentTime = System.currentTimeMillis();
        currentTime/=60000;
        String userAgentMD5 = MD5Util.getMD5(request.getHeader("User-Agent"));
        String message = userAgentMD5 + currentTime + userId ;
        String messageSalt = message + salt;
        String verifyCode = MD5Util.getMD5(messageSalt).substring(3,8);
        String messageBase64 = Base64Util.getBase64(message);
        //token = EncryptAndDecryptUtil.encryptDes(token,"R3$aZ9L@","87456321");
        String token = verifyCode + messageBase64;
        return ResultGenerator.genSuccessResult(token);
    }

    @GetMapping("/decryptDesToken")
    public String decryptDesToken(@RequestParam String token,HttpServletRequest request){
        String salt = "oejengnle";
        long currentTime = System.currentTimeMillis();
        currentTime/=60000;
        String userAgentMD5 = MD5Util.getMD5(request.getHeader("User-Agent"));
        String verifyCodeGet = token.substring(0,5);
        String messageBase64 = token.substring(5,token.length());
        String message = Base64Util.getFromBase64(messageBase64);
        String messageSalt = message + salt;
        String verifyCode = MD5Util.getMD5(messageSalt).substring(3,8);
        if(!verifyCode.equals(verifyCodeGet)) return null;
        String userAgentMD5Before = message.substring(0,16);
        if(!userAgentMD5.equals(userAgentMD5Before)) return null;
        String beforeTime = message.substring(16,24);
        long passTime = currentTime - Long.parseLong(beforeTime);
        if(passTime > 24*60) return null;
        String userId = message.substring(24);
        return userId;
        //return EncryptAndDecryptUtil.decryptDes(token,"R3$aZ9L@","87456321");
    }
}
