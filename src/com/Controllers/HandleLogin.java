package com.Controllers;

import com.Models.Login;
import com.Models.Student;
import com.Models.User;

import static com.Guis.MainFrame.*;

public class HandleLogin {
    public HandleLogin(){

    }
    public Login CheckLogin(){
        if(listLogin.size()>0){
            return listLogin.get(0);
        }
        return null;
    }

    public Login compare(String password, String userName, int Type){
        if(Type==0){
           for(User item: listUser){
               if(item.getName().trim().equals(userName.trim()) && item.getPassword().trim().equals(password.trim())){
                   listLogin.add(new Login(item.getName(),item.getPassword(), "0"));
                   return new Login(item.getName(),item.getPassword(), "0");
               }
           }
        }
        if(Type == 1){
            for (Student sv: listStudent){
                if(sv.getMssv().trim().equals(userName.trim()) && sv.getPassword().trim().equals(password.trim())){
                    keyType=1;
                    listLogin.add(new Login(sv.getMssv(), sv.getPassword(),"1"));
                    return new Login(sv.getMssv(), sv.getPassword(),"1");
                }
            }
        }
        return null;
    }
}
