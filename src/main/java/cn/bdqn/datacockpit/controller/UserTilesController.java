package cn.bdqn.datacockpit.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonArray;

import cn.bdqn.datacockpit.entity.Analysistasks;
import cn.bdqn.datacockpit.entity.Companyinfo;
import cn.bdqn.datacockpit.entity.Datarelation;
import cn.bdqn.datacockpit.entity.Info;
import cn.bdqn.datacockpit.service.AnalysistasksService;
import cn.bdqn.datacockpit.service.InfoService;
import cn.bdqn.datacockpit.service.TableinfoService;
import cn.bdqn.datacockpit.service.XsTableService;
import cn.bdqn.datacockpit.utils.ChineseToPinYin;
import cn.bdqn.datacockpit.utils.DownloadExcel;
import cn.bdqn.datacockpit.utils.ImportExecl;
import cn.bdqn.datacockpit.utils.JdbcUtil;
import net.sf.json.JSONArray;

/**
 * Created by ehsy_it on 2016/8/10.
 */
@Controller
public class UserTilesController {
    @Autowired
    private XsTableService xs;

    @Autowired
    private InfoService infoService;

    @Autowired
    private TableinfoService tableinfoService;
    
    @Autowired
    private AnalysistasksService analysistasksService;
    
    public static final String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");
    
    @RequestMapping("/user_pass")
    public String pass(Model model) {
        model.addAttribute("checks", "geren2");
        return "user_pass.pages";
    }

    @RequestMapping("/user_update")
    public String update(Model model) {
        model.addAttribute("checks", "geren1");
        return "user_update.pages";
    }

    /**
     * 
     * Description: 转发到用户首页<br/>
     *
     * @author dengJ
     * @param model
     * @return
     */
    @RequestMapping("/user_index")
    @RequiresPermissions("user")
    public String index(Model model) {
        return "user_index.pages";
    }

    /**
     * 
     * Description: 取通知信息和系统信息并重定向到user_index<br/>
     *
     * @author dengJ
     * @param req
     * @return
     */
    @RequestMapping("/user_second")
    @RequiresPermissions("user")
    public String userSecond(HttpServletRequest req) {
    	 List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
        List<Info> infoList = infoService.selectAllInfo();
        Date time = new Date();
        Date ti1 = new Date(time.getTime() - 7 * 24 * 60 * 60 * 1000);
        if (infoList != null) {
            for (Info info : infoList) {
                Date date = info.getPublishDate();
                Map<String, Object> map = new HashMap<String, Object>();
                if (ti1.before(date)) {
                    map.put("date", 1);
                } else {
                    map.put("date", 0);
                }

                map.put("info", info);
                lists.add(map);
                System.out.println(date);
            }
        }
        HttpSession session = req.getSession();
        session.setAttribute("tongzhi", infoList);
        return "redirect:/user_index.shtml";
    }

    @RequestMapping("/user_shuju1")
    @RequiresPermissions("user")
    public String shuju1(Model model,HttpServletRequest req) {
        model.addAttribute("checks", "shuju1");
        List<Datarelation> relation=tableinfoService.getDataRelation();
        System.out.println(relation);
        model.addAttribute("relation", relation);
        
        String reid="1";
        //初始化维度列
        String table1=tableinfoService.getTable1(Integer.parseInt(reid));
    	String table2=tableinfoService.getTable2(Integer.parseInt(reid));
    	
    	 ChineseToPinYin ctp = new ChineseToPinYin();
	     String table1pinyin = ctp.getPingYin(table1);
	     String table2pinyin = ctp.getPingYin(table2);
	     
	     Datarelation dr=tableinfoService.getOneDataRelation(Integer.parseInt(reid));
	     
	     HashMap<String, Object> map1=new HashMap<String, Object>();
	     map1.put("table2", table1pinyin);
	     map1.put("field2", dr.getCol1());
	     String field1=tableinfoService.getField1(map1);
	     
	     HashMap<String, Object> map2=new HashMap<String, Object>();
	     map2.put("table2", table2pinyin);
	     map2.put("field2", dr.getCol2());
	     String field2=tableinfoService.getField1(map2);
	     
	     List<String> showlist=new ArrayList<String>();
	     showlist.add(table1);
	     showlist.add(table2);
	     showlist.add(field1);
	     showlist.add(field2);
	     model.addAttribute("slist", showlist);
	     
	     //处理分析表的数据
	     HttpSession session=req.getSession();
	     Companyinfo cy = (Companyinfo) session.getAttribute("infos");
	     Integer id = cy.getId();
	     List<Analysistasks> analist=analysistasksService.selectAllTasks(id);
        return "user_shuju1.pages";
    }

    @RequestMapping("/user_shuju2")
    @RequiresPermissions("user")
    public String shuju2(Model model) {
        model.addAttribute("checks", "shuju2");
        return "user_shuju2.pages";
    }

    //下载
    @RequestMapping("download")
    @ResponseBody
    @RequiresPermissions("user")
    public void ExcelDown(@RequestParam String id,HttpServletRequest request,HttpServletResponse response) throws Exception{
    	//System.out.println("进入ajax，值："+id);
    	String docsPath = request.getSession().getServletContext()
				.getRealPath("upload");
		String fileName = id + System.currentTimeMillis() + ".xlsx";
		String filePath = docsPath + FILE_SEPARATOR + fileName;
		try {
			OutputStream os = new FileOutputStream(filePath);
			XSSFWorkbook wb = new XSSFWorkbook();
			XSSFSheet sheet = wb.createSheet("test");
			
			//获得数据表数据
			JdbcUtil jdbc1 = new JdbcUtil();
	        ApplicationContext context = jdbc1.getContext();
	        context = new ClassPathXmlApplicationContext("spring-common.xml");
	        JdbcTemplate jt = (JdbcTemplate) context.getBean("jdbcTemplate");
	        List<Map<String, Object>> lists = jdbc1.selectObj(jt, id);

			for (int i = 0; i < lists.size(); i++) {
				XSSFRow row = sheet.createRow(i);
				Object obj=lists.get(i);
				
				String obj1=obj.toString();
				String obj2=obj1.substring(1, obj1.length()-1);
				String sobj[]=obj2.split(",");
				for(int j=0;j<sobj.length-2;j++){
					String ssobj[]=sobj[j].split("=");
					row.createCell(j).setCellValue(ssobj[1]);
				}
			
			}
			wb.write(os);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		DownloadExcel de=new DownloadExcel();
		de.download(filePath, response);
    }
    
    @RequestMapping("/user_shuju3")
    @RequiresPermissions("user")
    public String shuju3(Model model, HttpServletRequest req) {
        model.addAttribute("checks", "shuju3");
        String names = req.getParameter("id");
        ChineseToPinYin ctp = new ChineseToPinYin();
        String name = ctp.getPingYin(names);
        model.addAttribute("name2", names);
        model.addAttribute("name1", name);
        JdbcUtil jdbc1 = new JdbcUtil();
        ApplicationContext context = jdbc1.getContext();
        context = new ClassPathXmlApplicationContext("spring-common.xml");
        JdbcTemplate jt = (JdbcTemplate) context.getBean("jdbcTemplate");
        List<Map<String, Object>> lists = jdbc1.selectObj(jt, name);
        
        //添加部分
        System.out.println("长度："+lists.size());
        List<String> column=infoService.getColumns(name);
        List<String> columns=new ArrayList<String>();
        columns.addAll(column.subList(0, column.size()-2));
        model.addAttribute("columns", columns);
        System.out.println("列长度:"+columns.size());
        //
        if (lists != null) {
            try {
                int shows = (Integer)lists.get(0).get("shows");
                model.addAttribute("shows", shows);
                String time = "'";
                Date date = null;
                for (int i = 0; i < lists.size(); i++) {
                    date = (Date) lists.get(i).get("times");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    time = time + sdf.format(date) + "','";
                    if (i == lists.size() - 1) {
                        date = (Date) lists.get(i).get("times");
                        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                        time = time + sdf2.format(date);
                    }
                }
                time = "[" + time + "']";
                model.addAttribute("lists", time);
                String fNums = "";
                for (int i = 0; i < lists.size(); i++) {
                    if (i == lists.size() - 1) {
                        fNums = fNums + lists.get(i).get("daofangrenshu");
                    } else {
                        fNums = fNums + lists.get(i).get("daofangrenshu") + ",";
                    }
                }
                fNums = "[" + fNums + "]";

                String rNums = "";
                for (int i = 0; i < lists.size(); i++) {

                    if (i == lists.size() - 1) {
                        rNums = rNums + lists.get(i).get("renchourenshu");
                    } else {
                        rNums = rNums + lists.get(i).get("renchourenshu") + ",";
                    }
                }
                rNums = "[" + rNums + "]";
                model.addAttribute("rNums", rNums);
                model.addAttribute("fNums", fNums);
                Set<String> sets = new HashSet<String>();

                for (int i = 0; i < lists.size(); i++) {
                    sets = lists.get(i).keySet();
                }
                List<String> lists3 = new ArrayList<String>();
                for (String string : sets) {
                    lists3.add(string);
                }
                model.addAttribute("lists3", lists3);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "user_shuju3.pages";
    }

    @RequestMapping("/user_guanxitu")
    @RequiresPermissions("user")
    public String userGuanxitu(Model model) {
    	List<String> listss=tableinfoService.getAllTableinfos();
        model.addAttribute("checks", "shuju4");
        model.addAttribute("listss", listss);
        return "user_guanxitu.pages";
    }

    @RequestMapping("/user_uploads_01")
	@RequiresPermissions("user")
    public String upload(Model model, HttpServletRequest req) throws Exception {
        String urls = req.getParameter("urls");
        
        String tb1 = urls.substring(12);
        String[] tb2 = tb1.split("\\.");
        String tbNames = tb2[0];
        ChineseToPinYin ctp = new ChineseToPinYin();
        String tableName = ctp.getPingYin(tbNames);
        
        System.out.println("tableName:"+tableName);
        System.out.println("urls:"+urls);
        
        ImportExecl poi = new ImportExecl();
        List<List<String>> list = poi.read(urls);
        List<String> head = list.get(0);
        List<String> heads = new ArrayList<String>();
        for (int i = 0; i < head.size(); i++) {
            if (head.get(i).equals("日期")) {
                heads.add("times");
            } else {
                heads.add(ctp.getPingYin(head.get(i)));
            }
        }

        List<Map<String, Object>> contents = new ArrayList<Map<String, Object>>();
        for (int j = 1; j < list.size(); j++) {
            List<String> content = list.get(j);
            Map<String, Object> maps = new HashMap<String, Object>();
            for (int k = 0; k < content.size(); k++) {
                if (heads.get(k).equals("times")) {
                    String date1 = content.get(k);
                    StringBuilder sb = new StringBuilder(date1);
                    sb.insert(4, "-");
                    sb.insert(7, "-");
                    String dates = sb.toString();
                    maps.put(heads.get(k), dates);
                } else if (content.get(k).matches("[0-9]+")) {
                    Integer num = Integer.parseInt(content.get(k));
                    maps.put(heads.get(k), num);

                } else {
                    maps.put(heads.get(k), content.get(k));
                }
            }
            contents.add(maps);
        }
        JdbcUtil jdbcs = new JdbcUtil();
        ApplicationContext context = jdbcs.getContext();
        context = new ClassPathXmlApplicationContext("spring-common.xml");
        JdbcTemplate jt = (JdbcTemplate) context.getBean("jdbcTemplate");
        jdbcs.saveObj(jt, tableName, contents);
        return null;
    }

    @ResponseBody
    @RequestMapping("/user_uploadss")
    @RequiresPermissions("user")
    public Map<String, String> uploads(Model model, HttpServletRequest req) throws Exception {
        String urls = req.getParameter("urls");
        String tb1 = urls.substring(12);
        String[] tb2 = tb1.split("\\.");
        String tbNames = tb2[0];
        ChineseToPinYin ctp = new ChineseToPinYin();
        String tableName = ctp.getPingYin(tbNames);
        ImportExecl poi = new ImportExecl();
        List<List<String>> list = poi.read(urls);
        List<String> head = list.get(0);
        List<String> heads = new ArrayList<String>();
        for (int i = 0; i < head.size(); i++) {
            if (head.get(i).equals("日期")) {
                heads.add("times");
            } else {
                heads.add(ctp.getPingYin(head.get(i)));
            }
        }

        List<Map<String, Object>> contents = new ArrayList<Map<String, Object>>();
        for (int j = 1; j < list.size(); j++) {
            List<String> content = list.get(j);
            Map<String, Object> maps = new HashMap<String, Object>();
            for (int k = 0; k < content.size(); k++) {
                if (heads.get(k).equals("times")) {
                    String date1 = content.get(k);
                    StringBuilder sb = new StringBuilder(date1);
                    sb.insert(4, "-");
                    sb.insert(7, "-");
                    String dates = sb.toString();
                    maps.put(heads.get(k), dates);
                } else if (content.get(k).matches("[0-9]+")) {
                    Integer num = Integer.parseInt(content.get(k));
                    maps.put(heads.get(k), num);

                } else {
                    maps.put(heads.get(k), content.get(k));
                }
            }
            contents.add(maps);
        }
        JdbcUtil jdbcs = new JdbcUtil();
        ApplicationContext context = jdbcs.getContext();
        context = new ClassPathXmlApplicationContext("spring-common.xml");
        JdbcTemplate jt = (JdbcTemplate) context.getBean("jdbcTemplate");
        jdbcs.saveObj(jt, tableName, contents);
        Map<String, String> maps = new HashMap<String, String>();
        maps.put("flag", "1");
        return maps;
    }

    @RequestMapping("/user_uploads")
    public String uploads01(MultipartFile upload,HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
    	String myfile= upload.getOriginalFilename();//获取要上传的文件名
    	System.out.println("文件名:"+myfile);
		String upp = request.getSession().getServletContext().getRealPath("upload");
		
		System.out.println(upp);
		File newFile = new File(upp + "//" + myfile);	//创建要上传的文件对象
		//上传文件(将文件写到磁盘中)
		upload.transferTo(newFile);
		
		//别人的存入数据库方法
		 String[] tb2 = myfile.split("\\.");
	     String tbNames = tb2[0];
	     ChineseToPinYin ctp = new ChineseToPinYin();
	     String tableName = ctp.getPingYin(tbNames);
	     System.out.println(tableName);
	     
	     ImportExecl poi = new ImportExecl();
	     String upp1=upp+"\\"+myfile;
	     System.out.println(upp1);
	     List<List<String>> list = poi.read(upp1);
	     List<String> head = list.get(0);
	     List<String> heads = new ArrayList<String>();
	     
	     for (int i = 0; i < head.size(); i++) {
	            if (head.get(i).equals("日期")) {
	                heads.add("times");
	            } else {
	                heads.add(ctp.getPingYin(head.get(i)));
	            }
	        }

	        List<Map<String, Object>> contents = new ArrayList<Map<String, Object>>();
	        for (int j = 1; j < list.size(); j++) {
	            List<String> content = list.get(j);
	            Map<String, Object> maps = new HashMap<String, Object>();
	            for (int k = 0; k < content.size(); k++) {
	                if (heads.get(k).equals("times")) {
	                    String date1 = content.get(k);
	                    StringBuilder sb = new StringBuilder(date1);
	                    sb.insert(4, "-");
	                    sb.insert(7, "-");
	                    String dates = sb.toString();
	                    maps.put(heads.get(k), dates);
	                } else if (content.get(k).matches("[0-9]+")) {
	                    Integer num = Integer.parseInt(content.get(k));
	                    maps.put(heads.get(k), num);

	                } else {
	                    maps.put(heads.get(k), content.get(k));
	                }
	            }
	            contents.add(maps);
	        }
	        JdbcUtil jdbcs = new JdbcUtil();
	        ApplicationContext context = jdbcs.getContext();
	        context = new ClassPathXmlApplicationContext("spring-common.xml");
	        JdbcTemplate jt = (JdbcTemplate) context.getBean("jdbcTemplate");
	        jdbcs.saveObj(jt, tableName, contents);
    	return "redirect:user_shuju2.shtml";
    }
    
    //改进的关系关联功能
    @RequestMapping("/datarelation")
    @ResponseBody
    public List<String> Datarelation(@RequestParam String dname) throws Exception{
    	int did=tableinfoService.getDatatableId(dname);
    	List<String> list=tableinfoService.getDatatableName(did);
    	return list;
    }
    
    //改进分析进度，显示统一的维度列 @RequestParam String reid
    @RequestMapping("/getrelation")
    @ResponseBody
    public List<String> getRelation( @RequestParam String reid) throws Exception{
    	String table1=tableinfoService.getTable1(Integer.parseInt(reid));
    	String table2=tableinfoService.getTable2(Integer.parseInt(reid));
    	
    	 ChineseToPinYin ctp = new ChineseToPinYin();
	     String table1pinyin = ctp.getPingYin(table1);
	     String table2pinyin = ctp.getPingYin(table2);
	     
	     Datarelation dr=tableinfoService.getOneDataRelation(Integer.parseInt(reid));
	     
	     HashMap<String, Object> map1=new HashMap<String, Object>();
	     map1.put("table2", table1pinyin);
	     map1.put("field2", dr.getCol1());
	     String field1=tableinfoService.getField1(map1);
	     
	     HashMap<String, Object> map2=new HashMap<String, Object>();
	     map2.put("table2", table2pinyin);
	     map2.put("field2", dr.getCol2());
	     String field2=tableinfoService.getField1(map2);
	     
	     List<String> showlist=new ArrayList<String>();
	     showlist.add(table1);
	     showlist.add(table2);
	     showlist.add(field1);
	     showlist.add(field2);
    	return showlist;
    }
}
