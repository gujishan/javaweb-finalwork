<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  	<style type="text/css">
  	#main{
  		position:fixed;
  		width: 400px;
  		height: 600px;
  		top:75%;
  		left:50%;
  		margin-top: -300px;
  		margin-left:-200px;
  		
  	}
  	 #form{
            width:300px;
            height:160px;
            position:relative;
            left:50%;
            top:50%;
            margin-left:-150px;
            margin-top:-80px;

        }
  	td{
  		padding: 5px;
  	}
  	input{
  		width:100%;
  		height:30px;
  	}
  	</style>
  <body style="background: url(res/login10.png);background-size:cover;font-family: 微软宋体;">
	<div id="main">
		<center><h3>用户登录</h3></center>
		<form action="LoginServlet" method="post">
		<td><font color="red" size="2"> ${msg0 }</font></td>
			<table>
				<tr>
					<td width="66" align="right"><font size="3">帐&nbsp;&nbsp;号：</font></td>
					<td colspan="2"><input type="text" name="username" value="${username }"/></td>
					<td><font color="red" size="2"> ${msg2 }</font></td>
				</tr>
				<tr>
					<td align="right"><font size="3">密&nbsp;&nbsp;码：</font></td>
					<td colspan="2"><input type="password" name="password" /></td>
					<td><font color="red" size="2"> ${msg3 }</font></td>
				</tr>
				<tr>
					<td align="right"><font size="3">验证码：</font></td>
				    <td width="110px" valign="middle"><input type="text" name="verifycode" /></td>
				    <td width="90px" valign="middle"><img src="VerifyCodeServlet" id="verify" onclick="document.getElementById('verify').src='VerifyCodeServlet?'+Math.random();"></td>
					<td><font color="red" size="2"> ${msg1 }</font></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td colspan="2" align="center"><input type="submit" value="登录" /></td>
				</tr>
			</table>
		</form>
		<a href="regist.jsp" style="margin-left: 80px;"><font size="2"><i>没有帐号？点击注册</i></font></a>
	</div>
  </body>
</html>
