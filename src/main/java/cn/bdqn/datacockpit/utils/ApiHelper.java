package cn.bdqn.datacockpit.utils;

public class ApiHelper {
	 /**
     *entName:企业名称
     *tableName:当前展示的数据表在数据库中的真实表名
     *recordId:当前点选的数据记录在数据库中的id
     *本方法会访问当前系统的数据库，调取相应的数据结构和数据，与分析模块的结果数据做规则验证后，生成规则验证的结论
     *返回值为字符串，直接以浮层形式显示给用户即可。此方法的返回值不会为空或null
     *
     *
     */
    public String validRules(String entName,String tableName,String recordId){
        String backViewStr = "当到访人数>15，且退订人数低于2时，有90%的置信率，媒体渠道访问人数将不超过10。\n当退订人数大于1，且认筹人数小于5时，有85%的置信率，中介到访人数大于15。";
        return backViewStr;
    }
    
    /**
     *
     *feedbackId:在数据库中存放数据分析任务时，若任务的状态为任务成功，则数据表中的任务反馈字段存放feedbackId
     *本方法被调用后，会根据feedbackId到分析模块的结果集中找到对应的结果，并将结果组合为一个二维的字符串数组。
     *返回值为二维的字符串数组，若无结果，则会返回一个null。返回值固定为3列，第一列是编号，第二列是推理规则，第三列是结论
     *
     */
    public String[][] getAlgorithmResults(String feedbackId){
        String[][] demoStr = {{"1","(A=6,B>7)","C<10"},
                              {"2","(C>6,F<1)","E<=42"}
        };
        return demoStr;
    }
    
    public static void main(String[] args){
        ApiHelper apihelper = new ApiHelper();
        String[][] tStr = apihelper.getAlgorithmResults("1");
        for(int i=0;i<tStr.length;i++){
            System.out.print("\ncols:"+(i+1));
            for(int j=0;j<tStr[i].length;j++){
                System.out.print("\t row:"+(j+1)+tStr[i][j]);
            }
            System.out.println();
        }
        System.out.println(apihelper.validRules("万科","datatable","43243124332199885643"));        
    }
}
