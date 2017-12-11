<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        企业数据管理
        <small></small>
      </h1>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box" style="border-top-color: #DD4B39;">
          <!--<div class="box"> -->
          <div class="nav-tabs-custom">
            <ul class="nav nav-tabs">
              <li class="active" style="border-top-color: white;"><a href="#data1" data-toggle="tab">数据表管理</a></li>
              <li  style="border-top-color: white;"><a href="#data2" data-toggle="tab">数据关系管理</a></li>
              <li  style="border-top-color: white;"><a href="#data3" data-toggle="tab">分析任务管理</a></li>
            </ul>

            <div class="tab-content">
              <!-- Font Awesome Icons -->
              <div class="tab-pane active" id="data1">
              <!-- &nbsp;&nbsp;<span style="color: red;margin-left: 4px;font-weight: bold;;margin-left: 4px;font-weight: bold;">万科集团</span> -->
              <div class="box-header"><button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal2" style="width: 100px;height: 30px;font-size: 14px;line-height: 0px;margin-left: -8px;margin-top: -7px;">新增数据表</button></div>    
                  <table id="example1" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th style="text-align: center;">编号</th>
                  <th style="text-align: center;">数据表名</th>
                  <th style="text-align: center;">最近数据更新时间</th>
                  <th style="text-align: center;">操作</th>
                </tr>
                </thead>
                <tbody style="text-align: center;">
                </tbody>
              </table>

<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h5 class="modal-title">表名：销售数据表</h5>
            </div>
            <div class="modal-body">
                <form>  
                    <div class="form-group">  
                        <table width="100%" height="100%" border="1px" cellspacing="0px" cellpadding="0px" >
                          <tr>
                            <td>编号</td>
                            <td>显示名称</td>
                            <td>字段属性</td>
                          </tr>
                          <tr>
                            <td>1</td>
                            <td><input type="text" name="pname" value="项目名称" style="width: 100%"></td>
                            <td>文本</td>
                          </tr>                          
                          <tr>
                            <td>2</td>
                            <td><input type="text" name="area" value="所在区域" style="width: 100%"></td>
                            <td>文本</td>
                          </tr>
                          <tr>
                            <td>3</td>
                            <td><input type="text" name="date" value="日期" style="width: 100%"></td>
                            <td>时间</td>
                          </tr>
                          <tr>
                            <td>4</td>
                            <td><input type="text" name="number" value="到访人数" style="width: 100%"></td>
                            <td>文本</td>
                          </tr>
                       
                        </table> 
                    </div>  
                </form>  
            </div>
            
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" class="btn btn-primary">
                    提交更改
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<div class="modal fade bs-example-modal-sm" id="myModal2" role="dialog" aria-label="myModalLabel" aria-hidden="true">  
    <div class="modal-dialog modal-sm" style="width: 40%">  
        <div class="modal-content"> 
        <form action="" method="get">   
            <div class="modal-header">  
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">  
                    <span aria-hidden="true">×</span>  
                </button>  
                <p><h5 class="modal-title">新增的数据表名：<input class="tabs"  type="text" name="shujuname"></h5></p>
                <p><h5 class="modal-title">数据列的默认图形展示：<input class="tus"  type="radio" name="shujutu" checked="checked" value="0">折线图
                &nbsp;&nbsp;
                <input class="tus" type="radio" name="shujutu" value="1">柱状图</h5></p> 
            </div>  
            <div class="modal-body">  
                
                    <div class="form-group">  
                        <table width="80%" border="1px" cellspacing="0px" cellpadding="0px" id="tb1">
                          <tr>
                            <td>编号</td>
                            <td>显示名称</td>
                            <td>字段属性</td>
                          </tr>
                          <tr>
                            <td>1</td>
                            <td><input class="inputs" type="text" name="pname" value="项目名称" style="width: 100%"></td>
                            <td><select class="inputs" name="prname">                              	
                                <option value="1" selected="selected">文本</option>
                                <option value="0" selected="selected">时间</option>
                                <option value="2">整数</option>
                                <option value="3">浮点数</option>
                              </select></td>                                                     
                          </tr>                                                  
                       
                        </table> 
                          <span><input type="button" class="mybut" value="添加字段"/></span>  
                    </div>                 
            </div>  
            <div class="modal-footer">  
                <button type="button" class="btn btn-default" data-dismiss="modal">  
                    关闭 
                </button>
                <button id="baocun" type="button" class="btn btn-primary">  
                    保存  
                </button>
            </div> 
             </form>  
             <script type="text/javascript">
             $("#baocun").click(function(){//添加数据表
            	//判断表名
            	 var tablename=$("input[name='shujuname']").val();
            	 var tablename2=tablename.trim();
       				//alert("长度："+tablename.trim().length);
       				 if(tablename2.length==0){
       					alert("请正确输入表名");      				
       				} else{
       				 //动态添加表格数量
                   	 var values=[];
                   	 var tu =document.getElementsByName("shujutu");
                   	 var tuid;
                   	 for(var i = 0; i < 2; i++)
                   	 {
                   	      if(tu[i].checked)
                   	      {
                   	      tuid=i;
                   	      }
                   	  }
                   	 values.push(tuid)
                   	 values.push(tablename2)
                   	 $(".inputs").each(function(i){
                   		 values.push($(this).val());//添加数据字段
                   	 });
                   	 $.ajax({
                      		url:"./admin_create.shtml",
                      		type:"post",
                      		traditional:true,
                      		data:{
         						"values":values             			
                      		},
                      		dataType:"json",
                      		success:function(result){
       	               		if(result.flag==1){
       	               			window.location.href="./admin_shuju1.shtml?id=${No1}"
       	               		}else{
       	               			alert("该表已经存在！");
       	               			window.location.href="./admin_shuju1.shtml?id=${No1}"
       	               		}
                      		} 					
                      }); 
       				}
            	
             });
             var  ii=1;
             $(".mybut").click(function(){          	 
            	// var len= $("#tb1").children();            	
            	ii+=1;
            	// var len01=len.length+1;  //每次编号+1 
            	// alert(ii);
            	 var tr1="<tr><td>"+ii+ 
              "</td><td><input class='inputs' type='text' name='pname' value='项目名称' style='width: 100%'></td>"+
                "<td><select class='inputs' name='prname'> "+                             	
                    "<option value='1' selected='selected'>文本</option>"+
                    "<option value='0'>时间</option>"+
                    "<option value='2'>整数</option>"+
                    "<option value='3'>浮点数</option></select></td> </tr>";
                    //alert(tr1);
                    // var str= $("#tb1").children().html();
                     //alert(tr1);
                    $("#tb1").append(tr1);//#tb是table的id    
                
               });
             	
            
            </script>
        </div>  
    </div>  
  </div>      
</div>
  

              <!-- data2-->
              <div class="tab-pane" id="data2">
                  <!-- &nbsp;&nbsp;<span style="color: red;margin-left: 4px;font-weight: bold;">万科集团</span> -->
              <div class="box-header"><button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal3" style="width: 120px;height: 30px;font-size: 14px;line-height: 0px;margin-left: -8px;margin-top: -7px;">新增数据关系</button></div>   

                  <table id="example3" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th style="text-align: center;">编号</th>
                  <th style="text-align: center;">数据关系名称</th>
                  <th style="text-align: center;">关联表名</th>
                  <th style="text-align: center;">统一维度列</th>
                  <th style="text-align: center;">操作</th>
                </tr>
                </thead>
                <tbody style="text-align: center;">
                <c:forEach items="${listtable2}" var="li" varStatus="status">
                   <tr>
	                  <td>${requestScope.offset+status.index+1}</td>
	                  <td>${li.name }<input type="hidden" value="${li.id}" name="stid"></td>
	                  <td>${li.tid1 }、${li.tid2}</td>
	                  <td>${li.tid1 }.${li.cname1 }、${li.tid2 }.${li.cname2 }</td>
	                  <td><select name="restate">
	                  	<option <c:if test="${li.state == 1 }"> selected="selected"</c:if> value="1" >已启用</option>
	                  	<option <c:if test="${li.state == 2 }"> selected="selected"</c:if> value="2" >已禁用</option>
	                  </select>
	                  </td>
                  </tr>
                </c:forEach>
               
                <!-- <tr>
                 <td>2</td>
                  <td>销售认筹关联</td>
                  <td>销售数据表、到访数据表</td>
                  <td>销售数据表.日期、到访数据表.日期</td>
                  <td><select><option>已启用</option><option>已禁用</option></select></td>
                </tr>
                 <tr>
                  <td>3</td>
                  <td>派单到访关联</td>
                  <td>销售数据表、到访数据表</td>
                  <td>销售数据表.日期、到访数据表.日期</td>
                  <td><select><option>已启用</option><option>已禁用</option></select></td>
                </tr>                -->
                </tbody>
              </table>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <form action="./insert_guanlian.shtml" method="post"> 
                <h5 class="modal-title">关联关系表名：<input type="text"name="name"> <span id="sp1" style="font-size:18px;color:red;"></span></h5>
                
            </div>
            <div class="modal-body">
                 
                    <div class="form-group">  
                        数据表A: <select name="tid1">
                        <c:forEach items="${listf }" var="lf">
                                <option value="${lf.id}">${lf.name}</option>
                                </c:forEach>
                              </select>&emsp;&emsp;&emsp;&emsp;&emsp;
                        统一的维度列: <select name="col1">
                               	  <c:forEach items="${maprtb}" var="rlf">
                               	  <c:if test="${rlf.key !=0}">
                                <option value="${rlf.key}">${rlf.value}</option>
                                </c:if>
                                </c:forEach>
                              </select>                           
                              <br/><br/>
                        数据表B: <select name="tid2">
                               <c:forEach items="${listf }" var="lf">                              
                                <option value="${lf.id}" >${lf.name}</option>                              
                                </c:forEach>
                              </select>&emsp;&emsp;&emsp;&emsp;&emsp;
                        统一的维度列: <select name="col2">
                             	 <c:forEach items="${maprtb }" var="rlf">
                             	  	<c:if test="${rlf.key !=0}">
                                <option value="${rlf.key}">${rlf.value}</option>
                                </c:if>
                                </c:forEach>
                              </select>
                              <br/>
                              <span id="sp2" style="font-size:18px;color:red;"></span>
                              <br/>
                               <span id="sp3" style="font-size:18px;color:red;"></span>
                    </div>  
               <input type="hidden" name="state" value="1">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" class="btn btn-primary" onclick="return $.abc();" >
                    保存
                 
                </button>
                 </form>  
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>  </form>

            </div>
            
            <script type="text/javascript">  
            $.abc=function(){		
            	var name=$("input[name='name']").val();
        		var nname=name.trim();
        		var tab1=$("select[name='tid1']").val();
        		var tab2=$("select[name='tid2']").val();
        		var col1=$("select[name='col1']").val();
        		var col2=$("select[name='col2']").val();
        		var id=$("input[name='stid']").val();
        		var flag=false;//判断是否已经存在
        		
        		alert("tab1:"+tab1+"tab2:"+tab2);
        			if(nname.length==0){      	
        			$("#sp1").text("* 关联关系名称不可以为空");			
        				return false;
        			}else{
        				$("#sp1").text("");	
        				//异步处理是否存在同个账户相同的表名
            			$.ajax({
            				url:"./admin_ajaxname.shtml",
            				data:{"name":nname},
            				type:"post",
            				success:function(data){
            					if(data==2){
            						//名字已经存在了
            						$("#sp1").text("* 名称已存在");
            						return false;
            					}
            				},error:function(){
            					
            				}
            			});
        			}
        			if(tab1==tab2){       				
        			$("#sp2").text("* 关联的两张表不可以相同");	
        				return false;
        			}else{
        				$("#sp2").text("");	
        			}
					//alert("111111");
        		    $.ajax({
        		        	url:"./aaaa222.shtml",
        		        	data:{"tab1":tab1,"tab2":tab2,"col1":col1,"col2":col2,"name":nname},
        		        	type:"POST",
        		        	success:function(message){
        		        		alert(message);
        		        		if(message==1){ 
        		        			$("#sp3").text("此关系已经存在");
        		        		}else{
        		        			window.location.href="./admin_shuju1.shtml?id=${No1}"
        		        		}
        		        	  },
        		        	  error:function(){
        		        		 alert("异步失败");
        		        		 return false;
        		        		 }
        		        	 }); 
        		    return true;
    		}           
            	$("select[name='tid1']").change(function(){
            		//alert($(this).val());
            		var rtname1=$(this).val();
            		$("select[name='col1']").children().remove();
            		var str="";
            		//var rtname= parseInt(rtname1);
            		$.ajax({
            			url:"./admin_ajax.shtml",
            			data:{"rtname1":rtname1},
            			type:"post",
            			dataType:"JSON",
            			success:function(data){            			
            				var da=data[0];
            			
            				for(var i=1;i<da;i++){
            					
            					str+="<option value='"+i+"'>"+data[i]+"</option>"          					
            				}
            				$("select[name='col1']").append(str);
            				
            			},error:function(){
            				alert("异步失败");
            			}
            			
            		});
            	});
            	$("select[name='tid2']").change(function(){
            		//alert($(this).val());
            		var rtname1=$(this).val();
            		$("select[name='col2']").children().remove();
            		var str="";
            		//var rtname= parseInt(rtname1);
            		$.ajax({
            			url:"./admin_ajax2.shtml",
            			data:{"rtname1":rtname1},
            			type:"post",
            			dataType:"JSON",
            			success:function(data){            			
            				var da=data[0];
            			//alert(da);
            			
            				for(var i=1;i<da;i++){
            					
            					str+="<option value='"+i+"'>"+data[i]+"</option>"            					
            				}
            				//alert(str);
            				$("select[name='col2']").append(str);
            				//alert("111");
            			},error:function(){
            				alert("异步失败");
            			}
            			
            		});
            	});
            	$("select[name='restate']").change(function(){
            		var state=$(this).val();
            		var id=$("input[name='stid']").val();
            		var sid=$(this).parent().parent().children().children("input[name='stid']").val();
            		alert(sid);
            	//	alert($("this").parent().parent().parent().children(2).val());
            		$.ajax({
            			url:"./admin_ajax3.shtml",
            			data:{"state":state,"id":sid},
            			type:"post",
            			dataType:"text",
            			success:function(data){ 
            				if(data==2){
            					alert("已存在相同的关系处于启用状态")
            				}
            			},error:function(){
            				alert("异步失败");
            			}          			
            		});
            	});
            </script>
     
 <script type="text/javascript">
	function insert(){
		var did=$("select[name='did']").val();
		var arithmeticid=$("select[name='arithmeticid']").val();
		var rule=$("#rule").val();
		alert("did:"+did+"arithmeticid"+arithmeticid+"rule"+rule);
		//window.location.href="./admininsert2.shtml?did="+did+"?arithmeticid="+arithmeticid+"?rule="+rule;
		window.location.href="./admin_shuju6.shtml?did="+did+"&arithmeticid="+arithmeticid+"&rule="+rule
	}
</script>
    
            <!--data3-->
            <div class="tab-pane" id="data3">
                 <!--  &nbsp;&nbsp;<span style="color: red;margin-left: 4px;font-weight: bold;">万科集团</span> -->
              <div class="box-header"><button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal4" style="mawidth: 140px;height: 30px;font-size: 14px;line-height: 0px;margin-left: -8px;margin-top: -7px;">新增数据分析任务</button></div>    
                  <table id="example4" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th style="text-align: center;">编号</th>
                  <th style="text-align: center;">数据关系名称</th>
                  <th style="text-align: center;">算法设定</th>
                  <th style="text-align: center;">操作</th>
                  <th style="text-align: center;">附加算法规则</th>
                </tr>
                </thead>
                <tbody style="text-align: center;">
                  <c:forEach items="${listas }" var="li" varStatus="status"> 
                   <tr>
	                  <td>${requestScope.offset+status.index+1}</td>
                  		<td>${li.name }</td>
                 		 <td>${li.arithmeticid}</td>
                  		<td><a href="./admin_shuju3.shtml" style="color: white"><button class="btn btn-primary btn-lg" data-toggle="modal" style="width: 110px;height: 20px;font-size: 13px;line-height: 0px">查看任务情况</button></a></td>
                  		<td>${li.rule }</td>
                </tr>
                   </c:forEach>
                </tbody>
              </table>

            </div>
            
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
        </div>

<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal4" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
       <form >  
        <div class="modal-content">      
            <div class="modal-header">            
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h5 class="modal-title">选择数据关系:               
                		<select name="did">
								  <c:forEach items="${listtable2}" var="litab" >
                                  <option value="${litab.id}">${litab.name}</option> 
                                  </c:forEach>                                              
                          </select>
                                 </h5>
            </div>
            <div class="modal-body">
              
                    <div class="form-group">  
                        选择算法编号: <select name="arithmeticid">
                                <option value="1" selected="selected">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                              </select>                       
                              <br/><br/>
                        附加规则:<br/><br/>
                              <textarea cols="auto" rows="8" required="required" placeholder="输入入算法规则" maxlength="200" style="width: 570px;height: 200px;" id="rule" ></textarea>                
                    </div>  
                
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" class="btn btn-primary" onclick="insert();">
                    保存
                </button>
            </div>
             </form> 
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>  
        <!-- /.col -->
      </div>
      <!-- /.row -->
    </section>
    <!-- /.content -->
 <script type="text/javascript"  src="js/jquery-1.8.3.js"></script>
		     
		     