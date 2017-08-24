<%@ page contentType="text/html;charset=UTF-8" language="java" %>
     <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        碧桂园房地产开发有限公司
        <small>销售数据表</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li><a href="#">Tables</a></li>
        <li class="active">Data tables</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
             
          <!-- /.box -->

          <div class="box">
            <div class="box-header">
              <h3 class="box-title"><a href="#" style="color: white"><button class="btn btn-primary btn-lg" data-toggle="modal" style="width: 100px;height: 20px;font-size: 11px;line-height: 0px">导出数据</button></a></h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table id="example1" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th>编号</th>
                  <th>项目名称</th>
                  <th>所在区域</th>
                  <th>日期</th>
                  <th>到访人数</th>
                  <th>认筹人数</th>
                  <th>退订人数</th>
                </tr>
                </thead>
                <tbody>
                <a href="#"><tr>
                  <td>1</td>
                  <td>水岸星城</td>
                  <td>江汉区</td>
                  <td>2017-04-22</td>
                  <td>22</td>
                  <td>12</td>
                  <td>0</td>                 
                </tr></a>
                <!-- class="btn btn-primary btn-lg" data-toggle="modal" href="#myModal" -->
                <a href="#myModal">
                <tr>
                  <td>2</td>
                  <td>万科汉口传奇</td>
                  <td>江汉区</td>
                  <td>2017-04-22</td>
                  <td>23</td>
                  <td>11</td>
                  <td>0</td>
                </tr></a>
                <a href="#"><tr>
                  <td>3</td>
                  <td>万科汉口传奇</td>
                  <td>江汉区</td>
                  <td>2017-04-22</td>
                  <td>23</td>
                  <td>11</td>
                  <td>0</td>
                </tr></a>
                <a href="#"><tr>
                  <td>4</td>
                  <td>万科汉口传奇</td>
                  <td>江汉区</td>
                  <td>2017-04-22</td>
                  <td>23</td>
                  <td>11</td>
                  <td>0</td>
                </tr></a>
                <a href="#"><tr>
                  <td>5</td>
                  <td>万科汉口传奇</td>
                  <td>江汉区</td>
                  <td>2017-04-22</td>
                  <td>23</td>
                  <td>11</td>
                  <td>0</td>
                </tr></a>
                <a href="#"><tr>
                  <td>6</td>
                  <td>万科汉口传奇</td>
                  <td>江汉区</td>
                  <td>2017-04-22</td>
                  <td>23</td>
                  <td>11</td>
                  <td>0</td>
                </tr></a>
                <a href="#"><tr>
                  <td>7</td>
                  <td>万科汉口传奇</td>
                  <td>江汉区</td>
                  <td>2017-04-22</td>
                  <td>23</td>
                  <td>11</td>
                  <td>0</td>
                </tr></a>
                </tbody>
              </table>

<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
            </div>
            <div class="modal-body">
                <form>                      
                        提示:<br/>
                              <textarea cols="90%" rows="8" required="required" placeholder="当用户点选了一行数据后，系统将调用指定接口，传递的参数包括企业id、数据表id、HashMap形式存储的表头和字段值，返回值为字符串，用于直接在页面本区域内显示。" maxlength="100"></textarea>
                </form>  
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" class="btn btn-primary">
                    保存
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>  

             
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->
    </section>
    <!-- /.content -->