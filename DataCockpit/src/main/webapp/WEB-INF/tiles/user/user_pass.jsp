<%@ page contentType="text/html;charset=UTF-8" language="java" import="cn.bdqn.datacockpit.entity.Companyinfo" isELIgnored="false"%>

 <!-- Main content -->
    <section class="content container-fluid" style="background:url(resource/images/pic5.jpg);width: 100%">

      <!--------------------------
        | Your Page Content Here |
        -------------------------->
        <div class="register-box">
  <div class="register-logo">
    <a href="#" style="margin-left: 50px"><b>修改密码</b></a>
  </div>

  <div class="register-box-body" style="width: 390px">
    

    <form action="./updatePassword1.shtml" method="post">
    	<input type="hidden" value="${comp.id }" name="id">
      <div class="form-group has-feedback" id="ddd1">

        <sapn style="display: block;margin-bottom: -30px">原密码： </sapn>
              <input type="text" class="form-control" style="width: 270px;margin-left: 85px"   name="oldPassword" value="${comp.password }">
        
      </div>
      <div class="form-group has-feedback" style="margin-top: 30px">

        <sapn style="display: block;margin-bottom: -30px">新密码： </sapn>
             <input type="password" class="form-control" style="width: 270px;margin-left: 85px"  name="password">
       
      </div>
	<span style="color:red" id="error"></span>
      <div class="form-group has-feedback" style="margin-top: 30px">

        <sapn style="display: block;margin-bottom: -30px">确认密码： </sapn>
            <input type="password" class="form-control" style="width: 270px;margin-left: 85px"  name="password1">
       
      </div>

     
     
      

      
      <div class="row" style="margin-top: 20px">
        
        <!-- /.col -->
        <div class="col-xs-4" style="margin-top: 10px;width: 380px">
          <button type="submit" class="btn btn-primary btn-block btn-flat">确认保存</button>
        </div>
        <!-- /.col -->
      </div>
    </form>

   

    
  </div>
  <!-- /.form-box -->
</div>

    </section>