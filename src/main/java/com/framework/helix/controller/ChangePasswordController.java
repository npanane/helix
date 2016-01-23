package com.framework.helix.controller;

import com.framework.helix.entity.User;
import com.framework.helix.exception.HelixServiceException;
import com.framework.helix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by nuwan.n.bandara on 6/17/2014.
 */
@Controller
@RequestMapping("/changePassword")
public class ChangePasswordController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView init(ModelMap model) {
        return new ModelAndView("changePassword");
    }

    @RequestMapping(value = "/change", method = RequestMethod.GET)
    public @ResponseBody String changePassword(@RequestParam("userName") String userName,
                 @RequestParam("oldPassword") String oldPassword,@RequestParam("newPassword") String newPassword) {
        try {
              User user=userService.getUser(userName);


           if( user.getPassword().equals(passwordEncrypt(oldPassword))){
               user.setPassword(passwordEncrypt(newPassword));
               System.out.println(user.getUserName());
               System.out.println(user.getPassword());
               System.out.println(user.getIdRole());

               userService.updateUser(user);
               return "Update Password";
           }
           else{
               return "Can not Update Password";
           }
        } catch (HelixServiceException e) {
            e.printStackTrace();
               return "Can not Update Password";
        }
    }

  public String passwordEncrypt(String string){

      MessageDigest md = null;
    try {
      md = MessageDigest.getInstance("MD5");
      md.update(string.getBytes());
      byte byteData[] = md.digest();

      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < byteData.length; i++)
          sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
      return sb.toString().toUpperCase();

      } catch (NoSuchAlgorithmException e) {
          e.printStackTrace();
        return null;
    }
  }
}
