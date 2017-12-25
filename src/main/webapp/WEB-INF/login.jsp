<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%
	String context = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+context+"/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath %>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>登录</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.7 -->
  <link rel="stylesheet" href="<%=basePath %>/resource/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="<%=basePath %>/resource/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="<%=basePath %>/resource/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="<%=basePath %>/resource/css/AdminLTE.min.css">
  <!-- iCheck -->
  <link rel="stylesheet" href="<%=basePath %>/resource/css/blue.css">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

  <!-- Google Font -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
  <style type="text/css">
      #code_input{
        width: 200px;
        height: 34px;
      }
      #bgvid{
        position: fixed;right: 0;bottom: 0;
        min-width: 100%;min-height: 100%;
        width: auto;
        height: auto;
        z-index: -100;
        background:url(./dahai.jpg) no-repeat;
        background-size: cover;
      }
    </style>
</head>
<body class="hold-transition login-page" onload="exit();">
<!-- <video src="./images/dahai.mp4" controls="controls"></video> -->
	<video autoplay loop poster="dahai.jpg" id="bgvid"> 
    	<source src="<%=basePath %>/resource/images/dahai.mp4" type="video/mp4">
	</video>
     <center>
        <div class="alert alert-success alert-dismissible" style="width: 300px;height: 100px;margin-top:200px">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                <h4>提示</h4>
                   <p>您的权限不够！</p>
                   <p><span id="exittime"></span>秒钟之后将为您跳转到登录页面</p>                   
        </div>
     </center>
       
        <!-- /.col -->
        <div class="col-xs-4" style="font-size: 14px;margin-top: 7px;margin-left:10px">
            <a href="<%=basePath %>/pages/register.jsp" class="text-center" style="color: #272822">申请合作</a>
        </div>

         <div class="col-xs-4" style="font-size: 14px;margin-top: 7px;margin-left:105px">
           <a href="<%=basePath %>/pages/wangjimima.jsp" class="text-center" style="color: #272822">忘记密码</a>
        </div>
        
        <!-- /.col -->
      </div>
    </form>

    
    <!-- /.social-auth-links -->

    
    

  </div>
  <!-- /.login-box-body -->
</div>

    
<!-- /.login-box -->

<!-- jQuery 3 -->
<script src="<%=basePath %>/resource/js/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="<%=basePath %>/resource/js/bootstrap.min.js"></script>
<!-- iCheck -->
<script src="<%=basePath %>/resource/js/icheck.min.js"></script>
<script src="<%=basePath %>/resource/js/gVerify.js"></script>
<script type="text/javascript">  
    	var time = 3; 
    	function exit() {  
       	 	window.setTimeout('exit()', 1000); 
       	 	time = time - 1;  
       	 	document.getElementById("exittime").innerHTML = time;
       	 	if(time==0){
       	 		window.location.href="login.jsp";
       	 	}
   	 	}  
	</script>
</body>
</html>
