<%@ page language="java" contentType="text/html; charset=UTF-8"%>
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

  <head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <title>Insert title here</title>
   <style type="text/css">
   body,td,dib {font-size: 12px;font-family:宋体;}
   #progressBar{width:400px;height:12px;background:#FFFFFF;border:1px soild #000000; padding:1px;}
   #progressBarItem{width:%30;height:100%;background:#FF0000;}
   </style>
   </head>  
   <body>
   <h1>开始你的工作</h1><br/>
   <h2>${msg }</h2>
   <iframe name=uploadiframe width=0 height=0></iframe>
   
   <form action="Upload" method ="post" enctype="multipart/form-data" target="uploadiframe" onsubmit="showStatus(); ">
   
   <input type="file" name="file1"  ><br/>
<br/>
   <input type="submit" value="开始上传" id="btnSubmit"></form>
   
    <a href="Download?filename=girl.jpg">下载图片</a>
   
   <div id="status" style="display: none;">
   
  
     上传进度条:
     <div id="progressBar"><div id="progressBarItem"></div></div>
     <div id="statusInfo"></div>
   </div>
   
   
   
   <script type="text/javascript">
   var finished =true;        //上传是否结束
   function $(obj){
   
         return document.getElementById(obj);   //快速返回id为obj的HTML对象
   }
   
   
   function showStatus(){
   
           finished =false;
           $('status').style.display ='block';        //显示进度条
           $('progressBarItem').style.width='1%';     //将进度条置为1%
           $('btnSubmit').disable =true;              //把提交按钮置灰
           setTimeout("requestStatus()",1000);        //1秒钟后执行requestStatus（）方法
   }
   
   
   function requestStatus(){                          //向服务器请求上传进度信息
                                                      //若已经上传完成，则返回
           if(finished)  return;
           
           var req =createRequest();
           req.open("GET","Upload");      //设置请求路径
           req.onreadystatechange=function(){callback(req);}
           
           req.send(null);
           setTimeout("requestStatus()",1000);           //1秒钟后重新请求
   
 
   }
      
   
   function createRequest(){
            
            if(window.XMLHttpRequest){                 //Netscape浏览器
                      
                      return new XMLHttpRequest();
            
            }else{                                   //IE浏览器
                      try{
                          
                          return new ActiveXObject("Msxm12.XMLHTTP");
                      
                      }catch(e){
                      
                          return new ActiveXObject("Microsoft.XMLHTTP");
                      }
            
            }
     
   }
   
   
   function callback(req){          //刷新进度条
   
             if(req.readyState==4){
                   
                   if(req.status !=200){
                   
                         debug("发生错误。  req.status: "+ req.status +"");
                         return;
             
                   }
                   
             
                   debug("status.jsp 返回值: "+req.responseText);
                   var ss=req.responseText.split("||");
                   
                   $('progressBarItem').style.width =''+ss[0] + '%';
                   $('statusInfo').innerHTML ='已完成百分比: '+ss[0] 
                   							+'%<br />已完成数（M）:'+ss[1]
                   							+'<br/>文件总长度(M):'+ss[2]
                   							+'<br/>传输速率(K):'+ss[3]
                   							+'<br/>已用时间(S):'+ss[4]
                   							+'<br/>估计总时间(S):'+ss[5]
                   							+'<br/>估计剩余时间(S):'+ss[6]
                   							+'<br/>正在上传第几个文件:'+ss[7];
                                   
                   
                   
                   if(ss[1]==ss[2]){
                   
                        finished =true;
                        $('statusInfo').innerHTML += "<br/><br/><br/>上传已完成。";
                        $('btnSubmit').disabled=false;
                   
                   }
            
             }
 
   }
  
    function debug(obj){              //显示调试信息
    
          var div =document.createElement("DIV");
          div.innerHTML ="[debug]: "  +obj;
          document.body.appendChild(div);
    
    
    }
    
</script>
</body>
</html>