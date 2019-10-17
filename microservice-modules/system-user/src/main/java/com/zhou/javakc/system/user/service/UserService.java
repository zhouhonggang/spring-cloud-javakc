package com.zhou.javakc.system.user.service;

import com.zhou.javakc.component.data.entity.system.User;
import com.zhou.javakc.component.data.jpa.base.BaseService;
import com.zhou.javakc.component.data.jpa.dynamic.criterion.Criteria;
import com.zhou.javakc.component.data.jpa.dynamic.restrictions.Restrictions;
import com.zhou.javakc.system.user.dao.UserDao;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhou
 * @version v0.0.1
 * @date 2019-08-08 15:48
 */
@Service
public class UserService extends BaseService<User, UserDao> {

    public boolean findByLoginName(String loginName)
    {
        return dao.findByLoginName(loginName) == 0?true:false;
    }

    @Transactional(readOnly=false)
    public User save(User entity)
    {
        if(StringUtils.isEmpty(entity.getUserId()))
        {
            try {
                //获取文件存储路径
                String path = ResourceUtils.getURL("classpath:").getPath();
                File file = new File(path + File.separator + entity.getUserPhotoName());
                //文件转byte[]
                byte[] photo = FileCopyUtils.copyToByteArray(file);
                //准备执行文件存储
                entity.setUserPhoto(photo);
                //删除临时文件
                file.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }

            entity.setRdate(new Timestamp(System.currentTimeMillis()));
        }
        else
        {
            entity.setUdate(new Timestamp(System.currentTimeMillis()));
        }
        return dao.save(entity);
    }

    public Page<User> findAll(User entity)
    {
        Criteria<User> criteria = new Criteria<>();

        //名称模糊搜索
        if(!StringUtils.isEmpty(entity.getUserName()))
        {
            criteria.add(Restrictions.like("userName", entity.getUserName()));
        }
        //手机号模糊搜索
        if(!StringUtils.isEmpty(entity.getUserPhone()))
        {
            criteria.add(Restrictions.like("userPhone", entity.getUserPhone()));
        }
        //性别等值查询
        if(!StringUtils.isEmpty(entity.getUserSex()))
        {
            criteria.add(Restrictions.eq("userSex", entity.getUserSex()));
        }
        //生日区间查询
        if(!StringUtils.isEmpty(entity.getSdate()) && !StringUtils.isEmpty(entity.getEdate()))
        {
            criteria.add(Restrictions.between("userBirthday", entity.getSdate(), entity.getEdate()));
        }
        //角色搜索查询
        if(!StringUtils.isEmpty(entity.getRole()) && !StringUtils.isEmpty(entity.getRole().getRoleId()))
        {
            criteria.add(Restrictions.eq("role.roleId", entity.getRole().getRoleId()));
        }

        Sort sort = new Sort(Sort.Direction.ASC,"userBirthday");

        PageRequest pageable = PageRequest.of(entity.getOffset(), entity.getLimit(), sort);

        return dao.findAll(criteria, pageable);
    }

    public void export(HttpServletResponse response) throws IOException {
        //1.查询数据库(需要导出的数据)
        List<User> list = dao.findAll();
        //2.把查询结果集写入到excel中
        HSSFWorkbook workbook = new HSSFWorkbook();

        //workbook字体
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 20); //字体高度
        font.setBold(true); //加粗
        font.setFontName("黑体"); //字体

        //workbook样式
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setWrapText(true);

        HSSFSheet sheet = workbook.createSheet("用户列表1");

        //2.1创建标题行
        HSSFRow titleRow = sheet.createRow(0);
        titleRow.setRowStyle(cellStyle);
        titleRow.createCell(0).setCellValue("姓名");
        titleRow.createCell(1).setCellValue("性别");
        titleRow.createCell(2).setCellValue("登录名");
        titleRow.createCell(3).setCellValue("手机号");
        titleRow.createCell(4).setCellValue("邮箱");
        titleRow.createCell(5).setCellValue("生日");
        titleRow.createCell(6).setCellValue("地址");
        titleRow.createCell(7).setCellValue("注册日期");
        titleRow.createCell(8).setCellValue("修改日期");
        titleRow.createCell(9).setCellValue("头像");

        //2.2创建数据行
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        for (int i=0;i<list.size();i++) {
            User user = list.get(i);
            HSSFRow dataRow = sheet.createRow(i+1);
            dataRow.createCell(0).setCellValue(user.getUserName());
            dataRow.createCell(1).setCellValue(user.getUserSex()==1?"男":"女");
            dataRow.createCell(2).setCellValue(user.getLoginName());
            dataRow.createCell(3).setCellValue(user.getUserPhone());
            dataRow.createCell(4).setCellValue(user.getUserEmail());
            dataRow.createCell(5).setCellValue(format.format(user.getUserBirthday()));
            dataRow.createCell(6).setCellValue(user.getUserAddress());
            dataRow.createCell(7).setCellValue(format.format(user.getRdate()));
            String edate = StringUtils.isEmpty(user.getUdate())?"":format.format(user.getUdate());
            dataRow.createCell(8).setCellValue(edate);


            ByteArrayInputStream in = new ByteArrayInputStream(user.getUserPhoto());

            ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
            HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
            BufferedImage bufferImg = ImageIO.read(in);
            ImageIO.write(bufferImg, "jpg", byteArrayOut);
            HSSFClientAnchor anchor = new HSSFClientAnchor(480, 30, 700, 250, (short) 9, i+1, (short) 9, i+1);

            patriarch.createPicture(anchor, workbook.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
        }

        //设置workbook列宽自动调整
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        sheet.autoSizeColumn(4);
        sheet.autoSizeColumn(5);
        sheet.autoSizeColumn(6);
        sheet.autoSizeColumn(7);
        sheet.autoSizeColumn(8);

        //3.把excel写入到响应流中
        response.reset();
        response.setContentType("application/vnd.ms-excel; charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename="+System.currentTimeMillis()+".xls");

        OutputStream out = response.getOutputStream();
        workbook.write(out);
        out.flush();
        out.close();
    }

    public Map<String, List<?>> chart()
    {
        Map<String, List<?>> data = new HashMap<>();
        List<String> xdata = new ArrayList<>();
        List<Integer> ydata = new ArrayList<>();

        List<Map<String, Object>> list = dao.chart();
        list.forEach(map->
        {
            xdata.add( map.get("dates").toString() );
            ydata.add( Integer.valueOf(map.get("nums").toString()) );
        });

        data.put("xdata", xdata);
        data.put("ydata", ydata);
        return data;
    }

}
